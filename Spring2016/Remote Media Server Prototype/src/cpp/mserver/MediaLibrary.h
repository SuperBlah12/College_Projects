/* File Name: MediaLibrary.h
 * Program Name: MediaServer
 * Student Name: Tyler Cole
 */
#include <string>
#include <vector>
#include "MediaDescription.h"

using namespace std;

class MediaLibrary
{
public:
	vector<MediaDescription> library;
	MediaLibrary();
        MediaLibrary(string jsonFileName);
	void toJsonFile(string jsonFileName);
	bool add(MediaDescription aClip);
	bool remove(string aTitle);
	MediaDescription get(string aTitle);
	vector<string> getTitles();
	vector<string> getMusicTitles();
	vector<string> getVideoTitles();
private: 
	int numVideo;
	int numMusic;
};
