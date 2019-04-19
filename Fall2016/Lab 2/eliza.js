//Initialize the jsonArray from file
var fs = require('fs');
var txt = fs.readFileSync("./Dictionaries/dictionary.json","UTF-8");
var JsonArray = JSON.parse(txt);

//Initialize readline
const readline = require('readline');
const rl = readline.createInterface({
		input: process.stdin,
		output: process.stdout
    });

//Watch for changes to Eliza's "brain"
fs.watch("./Dictionaries", (eventType, filename) =>  {
	txt = fs.readFileSync("./Dictionaries/"+filename,"UTF-8");
	var newArray = JSON.parse(txt);
	for(var r = 0; r < newArray.length; r++)
		JsonArray.push(newArray[r]);
	pushLog("I just got smarter.");
});
	
//Initialize important variables
var name;	
var userAnswer;
var topic;
var chatLog = [];
var coffeeTime = false;

//Code execute
var cInterval = setInterval(function(){ coffeeTime = true }, 180000);
getName(chat);

//Get the name of the user.
function getName(callback) {
	chatLog.push("Eliza: What is your name?");
	//Taunting message for idle users
	var nameTimeout = setTimeout(function(){ pushLog(rndArVal(JsonArray[2].response)); }, 20000);
	//Ask for user's name and wait for input.
	rl.question("What is your name?:\n", 
		function(answer) {
		name = answer;
		clearTimeout(nameTimeout);
		chatLog.push(name+": "+ name);
		pushLog("Nice to meet you, " + name + ".");
		callback(rndArVal(JsonArray[0].response));
		}
	);
}

//Display the given "response", wait for user input, and act on the input.
function chat(response) {
	chatLog.push("Eliza: " + response);
	//Taunting message for idle users
	var qTimeout = setTimeout(function(){ pushLog(rndArVal(JsonArray[3].response)+name+"?"); }, 20000);
	//Reply to user and wait for input
	rl.question(response + ":\n", 
		function(answer) {
			userAnswer = answer;
			clearTimeout(qTimeout);
			chatLog.push(name+": "+answer);
			//User wants to quit
			if (userAnswer == "quit"){
				rl.close();
				console.log("It was nice talking to you "+name+".");
				process.exit();
			}
			//Outputs log file of chat.
			else if (userAnswer == "log") {
				var logs = "[Chat Start]";
				chatLog.push("[Log Created]");
				for (var l = 0; l < chatLog.length; l++) {
					logs = logs + "\r\n" + chatLog[l];
				}
				fs.writeFile(name+"_"+getDateTime()+".log", logs,function(err){}); 
				console.log("[Log Created]");
				if(!coffeeTime)
					chat(response);
				else
					dunkinDonuts();
			}
			//Extract topic from user input and choose random response.
			else {
				topic = findTopic(userAnswer);
				if(!coffeeTime)
					chat(rndArVal(JsonArray[topic].response));
				else
					dunkinDonuts();
			}
		}
	);
}

//Deciphers the topic of the sentence and returns its place in the array.
function findTopic(rsp){
	for(var i = 4; i < JsonArray.length; i++) {
		for(var j = 0; j < JsonArray[i].key.length; j++) {
			if(rsp.indexOf(JsonArray[i].key[j]) !== -1) 
				return i;
		}
	}
	//Topic not found, use default response.
	return 1;
}

//Add text to chat log AND print that text.
function pushLog(text) {
	chatLog.push("Eliza: " + text);
	console.log(text);
}

//Returns a random entry of the given array.
function rndArVal(arr) {
	return arr[Math.floor(Math.random() * arr.length)];
}

//Gets the current date in format appropriate for files.
function getDateTime() {
	var date = new Date();
	var yer = date.getFullYear();
	var mon = date.getMonth()+1;
	var day = date.getDate();
	var hor = date.getHours();
	var min = date.getMinutes();
	if(day.length == 1) { min = 0 + day; }
	if(mon.length == 1) { min = 0 + mon; }
	if(min.length == 1) { min = 0 + min; }
	if(hor.length == 1) { hor = 0 + hor; }
	return yer + "-" + mon + "-" + day + "_" + hor + "." + min;
}

//Asks the user to coffee every three minutes
//Closes on "yes", Continues on all others. Never asks again on "maybe".
function dunkinDonuts() 
{
	chatLog.push("You sure can talk. I need some coffee – join me at Dunkin, "+name+" ?");
	rl.question("You sure can talk. I need some coffee – join me at Dunkin, "+name+" ?:\n",
	function(answer){
		chatLog.push(answer);
		if(answer.includes("Yes") || answer.includes("yes")) {
			console.log("I'll see you there " +name +".");
			rl.close();
			process.exit();
		}
		if(answer.includes("Maybe") || answer.includes("maybe")){
			clearInterval(cInterval);
			coffeeTime = false;
			chat("I guess we can talk more " +name +".");
		}
		else {
			coffeeTime = false;
			chat("I guess we can talk more " +name +".");
		}
	});
}