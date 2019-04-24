//Initialize Modules
var express = require('express');
var session = require('express-session');
var fs = require("fs");
var bodyParser = require('body-parser');
var app = express();
var admin = express();
var TIMER = 120000;

//Initialize App
app.set('view engine', 'pug');
app.set('views', './pages');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(require('cookie-parser')('SB12'));
app.use(session({secret: 'SB12', resave: false, saveUninitialized: true}));
app.use('/tools',admin);

//Initialize Admin
admin.set('view engine', 'pug');
admin.set('views', './pages');
admin.use(bodyParser.urlencoded({ extended: true }));
admin.use(bodyParser.json());
admin.use(require('cookie-parser')('SB12'));
admin.use(session({secret: 'SB12', resave: false, saveUninitialized: true}));

//Initialize Survey
var SURVEY = JSON.parse(fs.readFileSync('./survey.json','UTF-8'));

//Landing Page
app.get('/', function(req, res) {
	//Send New and logged-out users to Login Page
	if(!req.signedCookies.ans) {
		res.redirect('/login');
	}
	//Send Admins to Tools page
	else if(req.signedCookies.user == 'admin'){
		res.redirect('/tools');
	}
	//Send Logged-in Users to Match Page
	else {
		res.redirect('/matches');
	}
});

//Login Page (Initial)
app.get('/login', function(req, res) {
	if(!req.signedCookies.user) {
		res.sendFile('pages/login.html', {root: __dirname});
	}
	//Logged In User
	else {
		res.render('login', {user: req.signedCookies.user});
	}
});

//Login Page (Logged In)
app.post('/login', function(req, res){
	if(req.body.username == req.body.password){
		//Login Admin
		if(req.body.username == 'admin') {
			res.cookie('user','admin',{signed:true});
			res.redirect('/tools')
		}
		else {
			//Existing User
			if(req.body.username && fs.existsSync('./users/'+req.body.username+'.user')){
				var txt = fs.readFileSync('./users/'+req.body.username+'.user','UTF-8');
				var tmp = JSON.parse(txt);
				res.cookie('user',tmp.user,{signed:true});
				res.cookie('ans', tmp.answer,{signed:true});
				res.redirect('/matches');
			}
			//New User
			else {
				res.cookie('user',req.body.username, {signed : true});
				res.cookie('timer',(Date.now()+TIMER),{signed : true});
				res.redirect('/survey/1');
			}
		}
	}
	//Bad Login
	else{
		res.sendFile('pages/login.html', {root: __dirname});
	}
});

//Logout Sequence
app.get('/logout', function(req, res) {
	res.clearCookie('user');
	res.clearCookie('ans');
	req.session.destroy();
	res.redirect('/');
});

//Survey Page (GET)
app.get('/survey/:qid', function(req, res) {
	if(!req.signedCookies.timer) {
		res.cookie('timer',(Date.now()+TIMER),{signed : true});
	}
	if(!req.session.answer) {
		req.session.answer = [];
	}
	//Survey Question below 1
	if(req.params.qid <= 0){
		res.status(404).render('error', {errCode : "404 Not Found"});
	}
	//Timeout
	if(req.signedCookies.timer <= Date.now()) {
		res.clearCookie('timer');
		res.render('error', {errCode : "Survey Timeout"});
	}
	else {
		var num = parseInt(req.params.qid) - 1;
		if(num < SURVEY.length) {
			//GET Query
			if(req.query.ans) {
				res.render('survey', {num : req.params.qid, qst: SURVEY[num].Question, ans: SURVEY[num].Answers, chk: req.query.ans});
			}
			//Exisitng User Answers on File
			else if(req.signedCookies.ans) {
				res.render('survey', {num : req.params.qid, qst: SURVEY[num].Question, ans: SURVEY[num].Answers, chk: req.signedCookies.ans[num]});
			}
			//Existing User Answers in Session
			else if(req.session.answer[num]) {
				res.render('survey', {num : req.params.qid, qst: SURVEY[num].Question, ans: SURVEY[num].Answers, chk: req.session.answer[num]});
			}
			//New User
			else {
				res.render('survey', {num : req.params.qid, qst: SURVEY[num].Question, ans: SURVEY[num].Answers});
			}
		}
		//Survey Question does not exist
		else {
			res.status(404).render('error', {errCode : "404 Not Found"});
		}
	}
});

//Survey Page (POST) Conversational
app.post('/survey/:qid', function(req, res) {
	var num = parseInt(req.params.qid);
	if(!req.session.answer) {
		req.session.answer = [];
	}
	if(!req.signedCookies.timer) {
		res.cookie('timer',(Date.now()+TIMER),{signed : true});
	}
	//Timeout
	if(req.signedCookies.timer <= Date.now()) {
		res.clearCookie('timer');
		res.render('error', {errCode : "Survey Timeout"});
	}
	else {
		req.session.answer[num-1] = req.body.ans;
		num += 1;
		//Survey Completed
		if(num > SURVEY.length) {
			res.clearCookie('timer');
			res.cookie('ans', req.session.answer, {signed : true});
			var userObj = {user : req.signedCookies.user, answer : req.session.answer};
			userJSON = JSON.stringify(userObj);
			fs.writeFile('./users/'+userObj.user+'.user',userJSON, function(err) {
				if(err) throw err;
			});
			res.redirect('/matches');
		}
		//Next Question
		else if(num <= SURVEY.length){
			res.redirect('/survey/'+num);
		}
		//Something's Gone Wrong
		else {
			res.status(400).render('error', {errCode : "400 Bad Request"});
		}
	}
});

//Matches Page
app.get('/matches', function(req, res) {
	//No answers and/or name on file (or an admin).
	if(!req.signedCookies.ans || !req.signedCookies.user || req.signedCookies.user == 'admin') {
		res.status(400).render('error', {errCode : "400 Bad Request"});
	}
	else{
		var users = [];
		var files = fs.readdirSync('./users')
		var u = null;
		for(var i = 0; i < files.length; i++) {
			var txt = fs.readFileSync('./users/'+files[i],'UTF-8');
			var tmp = JSON.parse(txt);
			users.push({name : tmp.user, answ : tmp.answer});
			if(req.signedCookies.user == tmp.user) {
				u = i;
			}
		}
		//Get best matches
		var list = bestMatch(u, users);
		res.render('matches', {surv: SURVEY, name: req.signedCookies.user, u : users, list : list});
	}
});

//Tools Page
admin.get('/', function(req, res) {
	if(req.signedCookies.user == 'admin'){
		var users = fs.readdirSync('./users');
		for(var i = 0; i < users.length; i++) {
			users[i] = users[i].slice(0, -5)
		}
		res.render('tools', {users: users});
	}
	//Not an admin
	else {
		res.status(403).render('error', {errCode : "403 Forbidden"});
	}
});

//View User Page
admin.get('/user/:uname', function(req, res) {
	if(req.signedCookies.user == 'admin'){
		var txt = fs.readFileSync('./users/'+req.params.uname+'.user','UTF-8');
		var tmp = JSON.parse(txt);
		res.render('user', {surv: SURVEY, a: tmp.answer, name: tmp.user});
	}
	//Not an admin
	else {
		res.status(403).render('error', {errCode : "403 Forbidden"});
	}
});

//Delete Process
admin.get('/delete/:uname', function(req, res) {
	if(req.signedCookies.user == 'admin'){
		if(fs.existsSync('./users/'+req.params.uname+'.user')) {
			fs.unlinkSync('./users/'+req.params.uname+'.user');
		}
		res.redirect('/tools');
	}
	//Not an admin
	else {
		res.status(403).render('error', {errCode : "403 Forbidden"});
	}
});

//404 Error Page
app.use(function(req, res) {
	res.status(404).render('error', {errCode : "404 Not Found"});
});

//Server Listen
app.listen(3000, function() {
	console.log('Lab 4 running port 3000');
});

/*
 *	Generates a sorted list of objects representing a given user's "compatibility"
 *	with another user. It is based on the percentage of matching survey questions.
 */
function bestMatch(user, database) {
	var list = [];
	for(var i = 0; i < database.length; i++) {
		if(i != user) {
			list.push({num : i, match : matchPercent(database[user].answ,database[i].answ)})
		}
	}
	list.sort(compare);
	return list;
}

/*
 *	Prints what percentage of the Array contents match.
 *	matchPercent([1,2,3,4],[1,2,3,4]) returns 100
 *	matchpercent([1,2,3,4],[1,2,3,5]) returns 75
 */
function matchPercent(ar1, ar2) {
	var mat = 0;
	if(ar1.length == ar2.length){
		for(var i = 0; i < ar1.length; i++) {
			if(ar1[i] == ar2[i]) {
				mat++;
			}
		}
	}
	return (mat/ar1.length)*100;
}

//Compares "match" values for bestMatch function
function compare(a,b) {
	if(a.match < b.match)
		return 1;
	if(a.match > b.match)
		return -1;
	return 0;
}