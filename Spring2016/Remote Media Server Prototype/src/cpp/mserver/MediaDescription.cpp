/* File Name: MediaDescription.cpp
 * Program Name: MediaServer
 * Student Name: Tyler Cole
 * 
 */
#include "MediaDescription.h"
#include <stdlib.h>
#include <vector>
#include <string>
#include <iostream>
#include <stdio.h>
#include <fstream>
#include <json/json.h>

using namespace std;

//Default Constructor. Makes a null song.
MediaDescription::MediaDescription()
{
	mediaType = 0;
	title = "null";
	author = "null";
	album = "null";
	genre = "null";
	filename = "null";
}

//Full Constructor
MediaDescription::MediaDescription(int mediaType, string title, string author, string album, string genre, string filename) : mediaType(mediaType), title(title), author(author), genre(genre), filename(filename)
{
	if(mediaType == 0)
		this->album = album;
}

MediaDescription::MediaDescription(Json::Value jsonMedia)
{
	mediaType = jsonMedia["mediaType"].asInt();
	title = jsonMedia["title"].asString();
	author = jsonMedia["author"].asString();
	album = jsonMedia["album"].asString();
	genre = jsonMedia["genre"].asString();
	filename = jsonMedia["filename"].asString();
}

Json::Value MediaDescription::toJson()
{
	Json::Value jsonMedia;
	jsonMedia["mediaType"] = mediaType;
	jsonMedia["title"] = title;
	jsonMedia["author"] = author;
	jsonMedia["album"] = album;
	jsonMedia["genre"] = genre;
	jsonMedia["filename"] = filename;
	return jsonMedia;
}

string MediaDescription::toString()
{
	if (mediaType == 0)
		return 	"Song Title: " + title +
				"\nAuthor: " + author +
				"\nAlbum: " + album +
				"\nGenre: " + genre +
				"\nFile Name:" + filename;
	else
		return 	"Movie Title: " + title +
				"\nAuthor: " + author +
				"\nGenre: " + genre +
				"\nFile Name:" + filename;
}

//MediaType
int MediaDescription::getMediaType()
{
	return this->mediaType;
}
void MediaDescription::setMediaType(int mediaType)
{
	this->mediaType = mediaType;
}
//Title
string MediaDescription::getTitle()
{
	return this->title;
}
void MediaDescription::setTitle(string title)
{
	this->title = title;
}
//Author
string MediaDescription::getAuthor()
{
	return this->author;
}
 void MediaDescription::setAuthor(string author)
{
	this->author = author;
}
//Genre
string MediaDescription::getGenre()
{
	return this->genre;
}
void MediaDescription::setGenre(string genre)
{
	this->genre = genre;
}
//Filename
string MediaDescription::getFilename()
{
	return this->filename;
}
void MediaDescription::setFilename(string filename)
{
	this->filename = filename;
}
//Album
string MediaDescription::getAlbum()
{
	if (mediaType==0)
		return this->album;
	return "";
}
void MediaDescription::setAlbum(string album)
{
	if (mediaType==0)
		this->album = album;
}
