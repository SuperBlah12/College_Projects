//MediaDescription.h

#include <stdlib.h>
#include <vector>
#include <string>
#include <iostream>
/* File Name: MediaDescription.h
 * Program Name: MediaServer
 * Student Name: Tyler Cole
 */

#include <stdio.h>
#include <fstream>
#include <json/json.h>

using namespace std;

class MediaDescription
{
public:
	MediaDescription();
	MediaDescription(int mediaType, string title, string author, string album, string genre, string filename);
	MediaDescription(Json::Value jsonMedia);
	Json::Value toJson();
	string toString();
	int getMediaType();
	void setMediaType(int mediaType);
	string getTitle();
	void setTitle(string title);
	string getAuthor();
	void setAuthor(string author);
	string getGenre();
	void setGenre(string genre);
	string getFilename();
	void setFilename(string filename);
	string getAlbum();
	void setAlbum(string album);
private: 
	int mediaType; //0 = music, 1 = video 
	string title;
	string author;
	string album;
	string genre;
	string filename;
};
