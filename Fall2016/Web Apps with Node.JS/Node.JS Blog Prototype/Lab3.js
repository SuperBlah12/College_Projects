var fs = require("fs");
var q = require("querystring");
var http = require("http");
var url = require("url");

//Important Variables
var ROOT_DIR = "./"
var ART = [];
var AUTH_HTML = '<form method="post">Username:<br><input type="text" name="user"><br>Password:<br><input type="password" name="pass"><br>Author: <input type="checkbox" name="author"><input type="submit"></form>';
var EDIT_HTML = "<a href = /Editor?type=frag>Fragment Editor</a> <a href = /Editor?type=article>Article Builder</a>";
var DEL_HTML = '<form action=/>I wish to delete this article: <input type="checkbox" name="del"><br><input type="submit" value = "Submit" formaction="/"></form>'
var PUBLIC_LAND = '';
var PRIVATE_LAND = '';
var DEL;
var AUTHOR_LAND = '';
var HEAD = '<html><body><a href="/auth">Login</a><br>Welcome, ' + ROLE;
var FOOT = '</body></html>';
var ROLE = 'Visitor';
var USERNAME = '';

//Process all articles
buildLibrary();

//load Header and footer if exist
fs.readFile(ROOT_DIR+"blogs/header.html","UTF-8",(err,data) => {
	if (err) {
		if(err.code == "ENOENT") {}//Does not exist. No Action.
		else	
			throw err;
	}
	else {
		//Update Header info
		HEAD = data;
		HEAD = HEAD.replace("{{loginURL}}", "/auth");
		HEAD = HEAD.replace("{{Quit}}", "/quit");
		HEAD = HEAD.replace("{{login}}", "Login");
		HEAD = HEAD.replace("{{welcome}}", "Welcome");
		HEAD = HEAD.replace("{{role}}", ROLE);
	}
});
fs.readFile(ROOT_DIR+"blogs/footer.html","UTF-8",(err,data) => {
	if (err) {
		if(err.code == "ENOENT") {}//Does not exist. No Action.
		else {
			throw err;
		}
	}
	else {
		FOOT = data;
	}
});

//SERVER
http.createServer(function (req, res) {
	req.url = decodeURI(req.url);
	var urlObj = url.parse(req.url, true, false);
	var pRole = ROLE;
	//Handle POST Data
	if (req.method == 'POST') {
		var body = '';
		req.on('data', function(chunk) {
			
			//Parse Body
			body += chunk.toString();
			body = q.parse(body);
			
			//Login Data
			if(body.user == body.pass && body.user !== undefined) {
				USERNAME = body.user;
				if(body.author == 'on') {
					ROLE = "Author";
					buildLand();
				}
				else if(body.author == undefined){
					ROLE = "Reviewer";
				}
				HEAD = HEAD.replace(pRole,ROLE);
				if(ROLE == "Author") {
					HEAD = HEAD.replace("<!--{{EDITOR}}-->",EDIT_HTML);
				}
			}
			
			//Fragment Editor
			if(body.ftitle !== undefined) {
				fs.writeFile(ROOT_DIR + "/blogs/" + body.ftitle + ".html", body.fbody, function(){});
			}
			
			//Article Editor
			if(body.atitle !== undefined) {
				var data = '{"Title":"'+body.atitle+'","Author":"' + USERNAME + '","Public":"';
				if(body.apublic = "on") {
					data += 'yes';
				}
				else {
					data += 'no';
				}
				data += '","Fragments":[' + body.abody + ']}';
				fs.writeFile(ROOT_DIR + body.atitle + ".art", data, function(){});
			}
			
			//Rebuild Library and Landing Page
			buildLibrary();
		});
	}
	
	//Load Articles
	if (urlObj.pathname.endsWith(".art")) {
		res.write(HEAD);
		var done = 0;
		for(var i = 0; i < ART.length; i++) {
			if(ART[i].url == urlObj.pathname){
				res.end(ART[i].article + FOOT);
			}
		}
	}
	
	//Load Direct HTML Files
	if (urlObj.pathname.endsWith(".html")) {
		var result;
		if(ROLE == "Visitor") {
			//Check for permission
			for(var i = 0; i < ART.length; i++) {
				for(var j = 0; j < ART[i].Fragments.length; j++) {
					if("/blogs/" + ART[i].Fragments[j] == urlObj.pathname && ART[i].Public == "no") {
						result = "blogs/403.html"
					}
				}
			}
		}
		else {
			result = urlObj.pathname
		}
		fs.readFile(ROOT_DIR + result, function(err, data) {
			res.end(data);
		});
	}
	
	//Login Screen
	if(urlObj.pathname == "/auth"){
		res.end(HEAD+AUTH_HTML+FOOT);	//show login screen
	}
	
	//Quit
	if(urlObj.pathname == "/quit"){
		var pRole = ROLE;
		ROLE = "Visitor";
		HEAD = HEAD.replace(pRole,ROLE);
		HEAD = HEAD.replace(EDIT_HTML,"<!--{{EDITOR}}-->");
		res.end(HEAD+PUBLIC_LAND+FOOT);
	}
	
	//Landing Page
	if(urlObj.pathname == "/") {
		//Check for article Deletion
		HEAD = HEAD.replace(pRole,ROLE);
		if(urlObj.query.del == "on") {
			fs.unlink(ROOT_DIR+ART[DEL].url, function(err){ buildLibrary();
			});
		}
		if(ROLE == "Visitor") {
			res.end(HEAD+PUBLIC_LAND+FOOT);
		}
		else if (ROLE == "Reviewer"){
			res.end(HEAD+PRIVATE_LAND+FOOT);
		}
		else if (ROLE == "Author") {
			res.end(HEAD+AUTHOR_LAND+FOOT);
		}
	}
	
	//Delete Pages
	if(urlObj.pathname.indexOf("/delete") !== -1) {
		if(ROLE == "Author") {
			DEL = urlObj.query.article;
			fs.readFile(ROOT_DIR + "blogs/delete.html", function(err, data){
				res.end(HEAD+data+ART[urlObj.query.article].Title+DEL_HTML+FOOT);
			});
		}
		//Forbidden Access
		else {
			fs.readFile(ROOT_DIR + "blogs/403.html", function(err, data){
				res.end(HEAD+data+FOOT);
			});
		}
	}
	
	//Media Objects
	if(urlObj.pathname.indexOf("/media") !== -1) {
		fs.readFile(ROOT_DIR + urlObj.pathname.slice(1), function(err, data){
			res.writeHead(400, {'Content-type': 'image'});
			res.end(data);
		});
	}
	
	//Editor Pages
	if(urlObj.pathname.indexOf("/Editor") !== -1) {
		if(ROLE == "Author") {
			if(urlObj.query.type == "frag") {
				fs.readFile(ROOT_DIR + "blogs/FragmentEditor.html", function(err, data){
					res.end(HEAD+data+FOOT);
				});
			}
			else if(urlObj.query.type == "article") {
				fs.readFile(ROOT_DIR + "blogs/ArticleBuilder.html", function(err, data){
					res.end(HEAD+data+FOOT);
				});
			}
		}
		//Forbidden Access
		else {
			fs.readFile(ROOT_DIR + "blogs/403.html", function(err, data){
				res.end(HEAD+data+FOOT);
			});
		}
	}
	
}).listen(3000);

function buildLand() {
	PUBLIC_LAND = '';
	PRIVATE_LAND = '';
	for(var i = 0; i < ART.length; i++) {
		if(ART[i].Public == "yes") {
			PUBLIC_LAND += "<br><h1><a href=" + ART[i].url + ">" + ART[i].Title + "</a></h1>";
		}
		else{
			PUBLIC_LAND += "<br><h1>" + ART[i].Title + "</h1>";
		}
		PRIVATE_LAND += "<br><h1><a href=" + ART[i].url + ">" + ART[i].Title + "</a></h1>";
	}
	if(ROLE == "Author") {
		AUTHOR_LAND = '';
		for(var i = 0; i < ART.length; i++) {
			if(ART[i].Author == USERNAME) {
				AUTHOR_LAND += "<br><h1><a href=" + ART[i].url + ">" + ART[i].Title + "</a></h1>     <a href = /blogs/delete?article="+i+">Delete</a>";
			}
			else if (ART[i].Public == "yes") {
				AUTHOR_LAND += "<br><h1><a href=" + ART[i].url + ">" + ART[i].Title + "</a></h1>";
			}
			else {
				AUTHOR_LAND += "<br><h1>" + ART[i].Title + "</h1>";
			}
		}
	}
}

function buildLibrary(){
	ART = [];
	fs.readdir(ROOT_DIR, function(err,files){
		for(var i = 0; i < files.length; i++) {
			if(files[i].endsWith(".art")) {
				var txt = fs.readFileSync(ROOT_DIR + files[i],"UTF-8");
				var tmp = JSON.parse(txt);
				tmp.url = "/"+files[i]; //Adding helpful data
				var str = '';
				for(var j = 0; j < tmp.Fragments.length; j++) {
					str += fs.readFileSync(ROOT_DIR + "blogs/" + tmp.Fragments[j]) //Build article
				}
				tmp.article = str;
				ART.push(tmp);
			}
		}
		buildLand();
	});
}