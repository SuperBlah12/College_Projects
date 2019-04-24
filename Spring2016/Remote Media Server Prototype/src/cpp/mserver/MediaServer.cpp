/* File Name: MediaServer.cpp
 * Program Name: MediaServer
 * Student Name: Tyler Cole
 */
#include <stdlib.h>
#include <vector>
#include <string>
#include <iostream>
#include <stdio.h>
#include <fstream>
#include <json/json.h>
#include <jsonrpccpp/server.h>
#include <jsonrpccpp/server/connectors/httpserver.h>
#include "MediaLibrary.h"
#include "mediaserverstub.h"

using namespace jsonrpc;
using namespace std;

class MediaServer : public mediaserverstub {
public:  
  MediaServer(AbstractServerConnector &connector, int port);

  virtual void notifyServer();
  virtual string serviceInfo();
  virtual bool addClip(const std::string& album, const std::string& author, const std::string& filename, const std::string& genre, int iType, const std::string& title);
        virtual bool removeClip(const std::string& param1);
        virtual Json::Value getClip(const std::string& param1);
  virtual Json::Value getTitles();
  virtual Json::Value getMusic();
  virtual Json::Value getVideos();
  virtual bool saveJson();
private:
  int portNum;
  MediaLibrary library;
};

MediaServer::MediaServer(AbstractServerConnector &connector,
                                 int port) :
  mediaserverstub(connector){
  portNum = port;
  cout << "MediaServer up and listening on port " << port << endl;
  library = MediaLibrary("media.json");
}

void MediaServer::notifyServer(){
  cout << "Media server notified" << endl;
}

string MediaServer::serviceInfo(){
   std::string msg =
                "MediaLibrary service providing add, remove, get, library, videos, music on port ";
   stringstream ss;
   ss << portNum;
   return  msg.append(ss.str());
}

bool MediaServer::addClip(const std::string& album, const std::string& author, const std::string& filename, const std::string& genre, int iType, const std::string& title)
{
	cout << "Requested addition of media clip with following attributes:"<< endl;
	MediaDescription aClip = MediaDescription(iType,title,author,album,genre,filename);
	cout << aClip.toString() << endl;
	return library.add(aClip);
}

bool MediaServer::removeClip(const std::string& param1)
{
	cout << "Requested removal of " << param1 << "."<< endl;	
	return library.remove(param1);
}

Json::Value MediaServer::getClip(const std::string& param1)
{
	cout << "Requested copy of " << param1 <<"."<< endl;	
	return library.get(param1).toJson();
}

Json::Value MediaServer::getTitles()
{
	cout << "Requested string array of all titles."<< endl;
	Json::Value titles;
	vector<string> lib = library.getTitles();
	for(int i = 0; i < lib.size(); i++)
	      titles["titles"].append(lib[i]);
        return titles;
}

Json::Value MediaServer::getMusic()
{
        cout << "Requested string array of music titles."<< endl;
	Json::Value music;
        vector<string> mus = library.getMusicTitles();
	for(int i = 0; i < mus.size(); i++)
	      music["music"].append(mus[i]);
        return music;
}

Json::Value MediaServer::getVideos()
{
        cout << "Requested string array of video titles."<< endl;
	Json::Value video;
        vector<string> vid = library.getVideoTitles();
	for(int i = 0; i < vid.size(); i++)
	      video["video"].append(vid[i]);
        return video;
}

bool MediaServer::saveJson()
{
	library.toJsonFile("media.json");
	return true;
}

int main(int argc, char * argv[]) {
   int port = 8080;
   if(argc > 1){
      port = atoi(argv[1]);
   }
   //cout << port << endl;
   HttpServer httpserver(port);
   MediaServer ms(httpserver, port);
   ms.StartListening();
   int c = getchar();
   ms.StopListening();
   return 0;
}
