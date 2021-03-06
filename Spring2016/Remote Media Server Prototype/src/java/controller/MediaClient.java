package ser321.media;

import ser321.media.download.*;
import java.io.*;
import java.util.*;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;
import tjcole2.media.MediaDescription;

public class MediaClient {

	public String serviceURL;
	public JsonRpcRequestViaHttp server;
	public String ipAdd;
	public int dPort;
        public static int id = 0;

	MediaClient(String ipAdd, String jPort, String dPort) {
		this.serviceURL = "http://" + ipAdd + ":" + jPort + "/";
		this.ipAdd = ipAdd;
		this.dPort = Integer.parseInt(dPort);
		try{
			this.server = new JsonRpcRequestViaHttp(new URL(serviceURL));
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception ex) {
			System.out.println("Malformed URL "+ex.getMessage());
		}

	}

	private String packageMediaCall(String oper, String[] params) {
		/*
		bool addClip(const std::string& album, const std::string& author, const std::string& filename, const std::string& genre, int iType, const std::string& title);
		bool removeClip(const std::string& param1);
		Json::Value getClip(const std::string& param1);
		Json::Value getTitles();
		Json::Value getMusic();
		Json::Value getVideos();
		bool saveJson();
		*/
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jsonrpc","2.0");
      		jsonObj.put("method",oper);
      		jsonObj.put("id",++id);
		String almost = jsonObj.toString();
		if(oper == "addClip") {
			JSONObject jParams = new JSONObject();
        	 /* 	"params": { 	"iType": 0,
                  * 		    	"title": "title",
                  * 			"author": "author",
                  * 			"album": "album",
                  * 			"genre": "genre",
                  * 			"filename": "filename"
                  * 	},
		  */
			int iType = 1;
			if(params[0].equals("0"))
				iType = 0;
			jParams.put("iType",iType);
			jParams.put("title",params[1]);
			jParams.put("author",params[2]);
			jParams.put("album",params[3]);
			jParams.put("genre",params[4]);
			jParams.put("filename",params[5]);
			jsonObj.put("params",jParams);
			almost = jsonObj.toString();
			return almost;
		} else if(params != null) { //getTitles, getMusic, getVideos, removeClip		
			String stuffs = "\"" + params[0] + "\"";
			if(params.length > 1) {			
				for(int i = 1; i < params.length; i++) {
					stuffs = stuffs + ", \"" + params[i] + "\"";
				}
			}
			String toInsert = ",\"params\":[" + stuffs + "]";
			String begin = almost.substring(0,almost.length()-1);
			String end = almost.substring(almost.length()-1);
			String ret = begin + toInsert + end;
			return ret;
		}
		else {
			return almost;
		}
	}

	public void addClip(MediaDescription aClip) {
		String[] params = {	"" + aClip.getMediaType(),
					aClip.getTitle(),
					aClip.getAuthor(),
					aClip.getAlbum(),
					aClip.getGenre(),
					aClip.getFilename()};
		String ret = packageMediaCall("addClip",params);
		System.out.println(ret);
		try {		
			System.out.println(server.call(ret));
		} catch (Exception ex) {
         		System.out.println("Exception while adding clip to server:");
         		ex.printStackTrace();
      		}
	}
	
	public void removeClip(String aTitle) {
		String[] params = {aTitle};
		String ret = packageMediaCall("removeClip",params);
		try {		
			server.call(ret);
		} catch (Exception ex) {
         		System.out.println("Exception while removing clip from server:");
         		ex.printStackTrace();
      		}
	}
				
	public MediaDescription getClip(String aTitle) {
		String[] params = {aTitle};
		String ret = packageMediaCall("getClip",params);
		String resp;
		try {		
			resp = server.call(ret);
		} catch (Exception ex) {
         		System.out.println("Exception while getting clip from server:");
         		ex.printStackTrace();
			return null;
      		}
		JSONObject jsonLib = new JSONObject(resp);
		jsonLib = jsonLib.getJSONObject("result");
		return new MediaDescription(	
			jsonLib.getInt("mediaType"),
			jsonLib.getString("title"),
			jsonLib.getString("author"),
			jsonLib.getString("album"),
			jsonLib.getString("genre"),
			jsonLib.getString("filename")
		);
	}
				
	public String[] getTitles() {
		String ret = packageMediaCall("getTitles",null);
		String resp;
		try {		
			resp = (server.call(ret));
		} catch (Exception ex) {
         		System.out.println("Exception while getting titles from server:");
         		ex.printStackTrace();
			return null;
      		}
		JSONObject jsonLib = new JSONObject(resp);
		jsonLib = jsonLib.getJSONObject("result");
		JSONArray jsarLib = jsonLib.getJSONArray("titles");
		String[] lib = new String[jsarLib.length()];
		for (int i = 0; i < lib.length; i++) {
			lib[i] = jsarLib.getString(i);		
		} 
		return lib;
	}                     
           
	public String[] getVideos() {		
		String ret = packageMediaCall("getVideos",null);
		String resp;
		try {		
			resp = (server.call(ret));
		} catch (Exception ex) {
         		System.out.println("Exception while getting videos from server:");
         		ex.printStackTrace();
			return null;
      		}
		JSONObject jsonVids = new JSONObject(resp);
		jsonVids = jsonVids.getJSONObject("result");
		JSONArray jsarVids = jsonVids.getJSONArray("video");
		String[] vids = new String[jsarVids.length()];
		for (int i = 0; i < vids.length; i++) {
			vids[i] = jsarVids.getString(i);		
		} 
		return vids;
	}
				
	public String[] getMusic() {
		String ret = packageMediaCall("getMusic",null);
		String resp;
		try {		
			resp = (server.call(ret));
		} catch (Exception ex) {
         		System.out.println("Exception while getting music from server:");
         		ex.printStackTrace();
			return null;
      		}
		JSONObject jsonMus = new JSONObject(resp);
		jsonMus = jsonMus.getJSONObject("result");
		JSONArray jsarMus = jsonMus.getJSONArray("music");
		String[] mus = new String[jsarMus.length()];
		for (int i = 0; i < mus.length; i++) {
			mus[i] = jsarMus.getString(i);		
		} 
		return mus;
	}
	
	public void saveLibrary() {
		String ret = packageMediaCall("saveJson",null);
		try {		
			server.call(ret);
		} catch (Exception ex) {
         		System.out.println("Exception while saving library to server:");
         		ex.printStackTrace();
      		}
	}

	public boolean downloadMedia(String filename, int type) {
		DownloadClient dc = new DownloadClient(ipAdd, dPort, filename, type);
		return dc.downloadMedia();
	}
}
