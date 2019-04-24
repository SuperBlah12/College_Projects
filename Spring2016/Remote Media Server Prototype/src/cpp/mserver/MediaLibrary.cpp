/* File Name: MediaLibrary.cpp
 * Program Name: MediaServer
 * Student Name: Tyler Cole
*/

#include "MediaLibrary.h"
#include <stdlib.h>
#include <vector>
#include <string>
#include <iostream>
#include <stdio.h>
#include <fstream>
#include <json/json.h>

using namespace std;

MediaLibrary::MediaLibrary() {

}

MediaLibrary::MediaLibrary(string jsonFileName)
{
	ifstream jsonFile(jsonFileName.c_str());
	if(!jsonFile.is_open())
		cout << "Json file not opened properly" << endl;
	jsonFile.close();
	Json::Reader reader;
	Json::Value root;
	std::ifstream json(jsonFileName.c_str(), std::ifstream::binary);
	bool parseSuccess = reader.parse(json,root,false);
	if(parseSuccess)
	{
		Json::Value::Members mbr = root.getMemberNames();
		for(vector<string>::const_iterator i = mbr.begin(); i!= mbr.end(); i++)
		{
			Json::Value jsonMedia = root[*i];
			add(MediaDescription(jsonMedia));
		}
	}
}

void MediaLibrary::toJsonFile(string jsonFileName)
{
	Json::Value jsonLib;
	for(int i = 0; i < library.size(); i++)
	{
		MediaDescription media = library[i];
		jsonLib[media.getTitle()] = media.toJson();
	}
	Json::StyledStreamWriter ssw("  ");
	std::ofstream jsonOutFile(jsonFileName.c_str(), std::ofstream::binary);
	ssw.write(jsonOutFile, jsonLib);
}

bool MediaLibrary::add(MediaDescription aClip)
{
	library.push_back(aClip);
	if(aClip.getMediaType())
		numVideo++;
	else
		numMusic++;
	return true;
}

bool MediaLibrary::remove(string aTitle)
{
	bool done = false;
	while(!done)
	{
		for(int i = 0; i < library.size(); i++)
		{
			if(library[i].getTitle() == aTitle)
			{
				if(library[i].getMediaType())
					numVideo--;
				else
					numMusic--;
				library.erase(library.begin()+i);
				done = true;
			}
		}
	}
	return done;
}

//TODO : implement error.
MediaDescription MediaLibrary::get(string aTitle)
{
	for(int i = 0; i < library.size(); i++)
	{
		if(library[i].getTitle() == aTitle)
			return library[i];
	}
	return MediaDescription();
}

vector<string> MediaLibrary::getTitles()
{
	vector<string> titles;
	for(int i = 0; i < library.size(); i++)
	{
		titles.push_back(library[i].getTitle());
	}
	return titles;
}

vector<string> MediaLibrary::getMusicTitles()
{
	vector<string> music;
	for(int i = 0; i < library.size(); i++)
	{
		if (!library[i].getMediaType())
			music.push_back(library[i].getTitle());
	}
	return music;
}

vector<string> MediaLibrary::getVideoTitles()
{
	vector<string> video;
	for(int i = 0; i < library.size(); i++)
	{
		if (library[i].getMediaType())
			video.push_back(library[i].getTitle());
	}
	return video;
}
