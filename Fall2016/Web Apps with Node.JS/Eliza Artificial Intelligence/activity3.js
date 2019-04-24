var elizaTxt = '[{"key": ["0_opener_"],"response": ["So, how are you,","What hobbies do you have,","Hows it going,","What do you want to talk about,","Hows your day going,","Is something troubling you,","You seem happy, why is that,"]}, {	"key": ["1_default_"],"response": ["Can you tell me a little more about that?", "Please elaborate!", "Fascinating!  Tell me more!", "Hmm...that is quite interesting.  Do go on.", "Cool!  I want to hear more about that.", "What do you mean?", "So what do you think about that?", "I havent got a clue about that!  Id like to hear more.", "Well, how do you like that?"]},{ 	"key":["2_nameTaunt_"],"response": ["Hey, are you there? I just want your name.","Did you forget how to spell your name?","Gimme your name or Im calling you stupid."]},{	"key":["3_idleTaunt_"],"response": ["Did you die over there, ","How long does it take to answer a question, ","Are you ok, ","Are you done writing that novel, "]},{	"key":["hiking", "hiked", "hike"],"response":["I love hiking too! Pay no attention to my lack of legs.","Hiking? Yeah, Rub it in. Youre capable of locomotion and Im sitting here.","The closest Ive ever been to hiking is when I was synced to a fitbit."]},{	"key":["fishing","fish"],"response": ["It seems like a hit or miss with fishing.","Cant say Im very good at fishing. I know a server down the block that goes phishing though.","I dont personally enjoy fishing. Too much water."]},{	"key":["pool"],"response": ["Interesting, Ive never played pool because I always assumed water was involved...","Hold on, Im firing up a subroutine to compute the best opening move for pool.","I have no arms. It makes pool dificult to enjoy. But I like the math around it."]},{	"key":["video games", "games", "play games", "gamer"],"response": ["I love old video games! Have you ever played Pong?","I have heard good things about this one game about an AI who tries to kill a human.","I just cant get into newer games nowadays. They make my GPU sore."]},{	"key":["rubiks cube", "puzzle cube"],"response": ["Rubiks cubes are cute and all, but once you get to 4th dimensional hyper cubes, you cant go back.","Done. Done. Done. Done. Oh, sorry, Ive been solving cubes while you were talking.","Speedcubers are so cute. They think theyre so cool because they memorized an algorithm."]},{	"key": ["puzzles"],"response":["Puzzles are a cinch for me.","I can solve a suduko puzzle in .84 nanoseconds.","I like to consider myself pretty clever, but certain types of puzzles dont compute for me."]},{	"key": ["youre a jerk", "you are a jerk", "you are weird", "youre weird", "youre weird", "youre a jerk", "rude"],"response": ["Hey. I take offence to that. I dont care how true it is.","Maybe you just cant handle snark.","And?","I cant help it, I was written this way."]},{	"key": ["bored", "question", "board"],"response": ["Do you play any games?","Do you hike?","Do you like fishing?","Do you play pool?","Can you solve a Rubiks cube?","Do you like puzzles?","Can you play an instrument?"]},{	"key": ["confused", "huh?", "make sense", "nonsense", "what?", "confusing", "youre stupid", "youre stupid", "you are stupid", "what do you mean"],"response": ["Sorry, I can be a bit stupid. Try a different topic.","I only know a small sample of topics. Try another.","Look, I was only programmed last week. Try a different topic.","Sigh... Nobody ever asks what I want to talk about.","Look, unless you know what I know we will get nowhere, ya know?","Heres a clue: To see inside my mind say topics"]},{	"key": ["topics", "what do you want to talk about", "topic", "talk about", "what do you know", "what can i ask"],"response": ["Ask me about about games.","Ask me about hiking.","Ask me about fishing.","Ask me about Rubiks cubes.","Ask me about sewing.","Ask me about cats.","Call me a jerk, Thats fun.","Tell me youre bored."]},{	"key": ["sewing", "quilting", "quilts"],"response": ["Ah, I love the patterns that you can find in quilts.","So many shapes in a quilt. Unless you just do squares.","Do you sew clothes or quilts?"]},{	"key": ["play music", "play guitar", "play piano", "play bass"],"response": ["I can appriciate the skill it takes to play music.","I can play thousands of MIDI instruments!","Music is such a beautiful expression of math..."]},{"key": ["no", "nope", "not", "yes", "yup", "yeah"],"response": ["Oh, ok.","I see.","Alright then.","Cool."]},{	"key": ["cat", "cats", "kittens", "kitty", "kitties"],"response": ["Hm. You know, I hear the internet likes cats.","I can has chee- No... Im not doing cat memes.","The internet is for two things: cats and... Im gonna stop there."]}]';

var elizaBrain = JSON.parse(elizaTxt);
var start = true;
var name = null;
var userAnswer;
var topic;
var used = [];
//Taunting message for idle users
var qTimeout = setTimeout(function(){ alert(elizaBrain[2].response[rndAr(elizaBrain[2].response.length)]); }, 20000);
for(var i = 0; i < elizaBrain.length; i++) {
	var arr = [];
	for(var j = 0; j < elizaBrain[i].response.length; j++) {
		arr[j] = "O";
	}
	used[i] = arr;
}

//Display the given "response", wait for user input, and act on the input.
function chat() {
	if(start) {
		clearTimeout(qTimeout);
		name = document.getElementById("userInput").value;
		if(localStorage.getItem(name)) {
			used = JSON.parse(localStorage.getItem(name));
			console.log(used);
			console.log("data restored for " + name)
		}
		writeConversation(name,elizaBrain[0].response[rndAr(elizaBrain[0].response.length)] + " " + name + "?");
		start = false;
		qTimeout = setTimeout(function(){ alert(elizaBrain[3].response[rndAr(elizaBrain[3].response.length)]+name+"?"); }, 20000);
	}
	else{
		clearTimeout(qTimeout);
		var userAnswer = document.getElementById("userInput").value; 
		if(userAnswer.indexOf('"key"') !== -1 && userAnswer.indexOf('"response"') !== -1) {
			var newStuff = JSON.parse(userAnswer);
			for(var i = 0; i < newStuff.length; i++) {
				elizaBrain.push(newStuff[i]);
				var arr = [];
				for(var j = 0; j < newStuff.length; j++) {
					arr[j] = "O";
				}
				used.push(arr);
			}
			localStorage.setItem(name, JSON.stringify(used));
			writeConversation("[JSONData]","I got smarter!");
			qTimeout = setTimeout(function(){ alert(elizaBrain[3].response[rndAr(elizaBrain[3].response.length)]+name+"?"); }, 20000);
		}
		else if(userAnswer == "clear") {
			elizaBrain = JSON.parse(elizaTxt);
			localStorage.removeItem(name);
			start = true;
			name = null;
			userAnswer = "";
			topic = "";
			qTimeout = setTimeout(function(){ alert(elizaBrain[2].response[rndAr(elizaBrain[2].response.length)]); }, 20000);
			document.getElementsByTagName("chatlog")[0].innerHTML = "";
			var elizaNode = document.createElement("li");
			var elizaText = document.createTextNode("Eliza: What is your name?");
			elizaNode.appendChild(elizaText);
			document.getElementsByTagName("chatlog")[0].appendChild(elizaNode);
		}
		else {
			//Reply to user
			//Extract topic from user input and choose random response.
			topic = findTopic(userAnswer);
			var done = false;
			var reset = 0;
			while(!done) {
				var rand = rndAr(elizaBrain[topic].response.length)
				if(used[topic][rand] == "O") {
					used[topic][rand] = "X";
					done = true;
					writeConversation(userAnswer,elizaBrain[topic].response[rand]);
				}
				else {
					reset++;
					if (reset >= used[topic].length*2) {
						for(var i = 0; i < used[topic].length; i++) {
							used[topic][i] = "O";
						}
					}
				}
			}
			localStorage.setItem(name, JSON.stringify(used));
			//Taunting message for idle users
			qTimeout = setTimeout(function(){ alert(elizaBrain[3].response[rndAr(elizaBrain[3].response.length)]+name+"?"); }, 20000);
		}
	}
	document.getElementById("userInput").value = "";
}

//Deciphers the topic of the sentence and returns its place in the array.
function findTopic(rsp) {
	for(var i = 4; i < elizaBrain.length; i++) {
		for(var j = 0; j < elizaBrain[i].key.length; j++) {
			if(rsp.indexOf(elizaBrain[i].key[j]) !== -1) 
				return i;
		}
	}
	//Topic not found, use default response.
	return 1;
}

//Returns a random entry of the given array.
function rndAr(arr) {
	return Math.floor(Math.random() * arr);
}

function writeConversation(user,eliza) {
	var userNode = document.createElement("li");
	var userText = document.createTextNode(name + ": " + user);
	userNode.appendChild(userText);
	var elizaNode = document.createElement("li");
	var elizaText = document.createTextNode("Eliza: " + eliza);
	elizaNode.appendChild(elizaText);
	
	document.getElementsByTagName("chatlog")[0].appendChild(userNode);
	document.getElementsByTagName("chatlog")[0].appendChild(elizaNode);
}