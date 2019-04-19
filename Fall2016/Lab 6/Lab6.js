function getRequestObject() {
	if (window.XMLHttpRequest) {
		return(new XMLHttpRequest());
	} else {
		return(null);
	}
}
var AVG = 3;
var HOT = {"name": "", "temp": 0};
var NICE = {"name": "none of these", "diff": 9999};
populateTables("London");

//Fill the tables with data
function populateTables(query) {
	//Reset variables
	AVG = 3;
	HOT = {"name": "", "temp": 0};
	NICE = {"name": "none of these", "diff": 9999};
	//Populate arrays
	var abbr = ["phx","pfl","3rd"];
	var city = ["Phoenix","Post Falls", query];
	//Load data
	for(var i = 0; i < 3; i++) {
		loadCity(city[i],abbr[i]);
	}	
}

//Make API call and pass data to helper function
function loadCity(query, abbr) {
	var address = "http://api.apixu.com/v1/current.json?key=d66034f087494afda10215001162111&q=" + query;
	var request = getRequestObject();
	request.onreadystatechange = function() { 
		helperLC(query, abbr, request);
	}
	request.open("GET", address, true);
	request.send(null);
}

function helperLC(query, abbr, request) {
	if ((request.readyState == 4) && (request.status == 200)) { //Check for proper headers
		var city = JSON.parse(request.responseText); //Parse Data
		AVG = AVG + city.current.temp_f; //Running average
		
		//Calculate Hottest City
		if(city.current.temp_f > HOT.temp) {
			HOT.temp = city.current.temp_f;
			HOT.name = city.location.name;
		}
		
		/*
		 * Niceness requirements:
		 * First and foremost, no weather conditions that be exaplined using the following words:
		 * Mist, Rain, Snow, Sleet, Drizzle, Freezing, Thunder, Blizzard, Fog, or Ice. 
		 * I reserve the right to deny all cities from being nicest if they all have these conditions.
		 * 
		 * Second, the fair weather city with a temperature closest to 70 degrees is nicest. 
		 * The balance is weighted in favor of warmer cities. Colder cities have 20% added to their difference.
		 */
		var cond = city.current.condition.text;
		if(cond.indexOf("Mist") == -1 && cond.indexOf("rain") == -1 && cond.indexOf("snow") == -1 && cond.indexOf("freezing") == -1 && cond.indexOf("sleet") == -1 && cond.indexOf("drizzle") == -1 && cond.indexOf("Blizzard") == -1 && cond.indexOf("ice") == -1) {
			var diff = city.current.temp_f%70
			if(diff == city.current.temp_f) {
				diff = (70 - city.current.temp_f) * 1.2;
			}
			if(diff < NICE.diff) {
				NICE.diff = diff
				NICE.name = city.location.name;
			}
		}
		
		//Fill Tables
		if (abbr == "3rd") {
			document.getElementById(abbr+"Name").innerHTML = query;
		}
		document.getElementById(abbr+"Time").innerHTML = city.current.last_updated;
		document.getElementById(abbr+"Temp").innerHTML = city.current.temp_f + " F";
		document.getElementById(abbr+"Humi").innerHTML = city.current.humidity + "%";
		document.getElementById(abbr+"Wind").innerHTML = city.current.wind_mph + " MPH";
		document.getElementById(abbr+"Cond").innerHTML = cond;
		document.getElementById("avg").innerHTML = (AVG/3);
		document.getElementById("hot").innerHTML = HOT.name;
		document.getElementById("nice").innerHTML = NICE.name;
	} else {
		console.log("API not ready.");
	}
}

function forecast(query) {
	var address = "http://api.apixu.com/v1/forecast.json?key=d66034f087494afda10215001162111&q=" + query + "&days=2";
	var request = getRequestObject();
	request.onreadystatechange = function() {
		helperFC(query, request);
	}
	request.open("GET", address, true);
	request.send(null);
}

function helperFC(query, request) {
	if ((request.readyState == 4) && (request.status == 200)) { //Check for proper headers
		
		var data = JSON.parse(request.responseText); //Parse Data
		var day = data.forecast.forecastday[1].hour[6]; //Daytime Forecast
		var night = data.forecast.forecastday[1].hour[18]; //Nighttime Forecast
		
		//Create Forecast Table
		document.getElementById("forecast").innerHTML = '<p>Forecast for <fCity id="fCity"></fcity>.</p><table><tr><th>Time</th><th>Last Updated</th><th>Temperature</th><th>Humidity</th><th>Wind Speed</th><th>Conditions</th></tr><tr><td>Day (6:00 AM)</td><td id="dayTime"></td><td id="dayTemp"></td><td id="dayHumi"></td><td id="dayWind"></td><td id="dayCond"></td></tr><tr><td>Night (6:00 PM)</td><td id="nigTime"></td><td id="nigTemp"></td><td id="nigHumi"></td><td id="nigWind"></td><td id="nigCond"></td></tr></table>'
		
		//Forecast for <query> for the date: <date>.
		document.getElementById("fCity").innerHTML = query + " for the date: " + data.forecast.forecastday[1].date;
		//Daytime
		document.getElementById("dayTime").innerHTML = data.current.last_updated;
		document.getElementById("dayTemp").innerHTML = day.temp_f + " F";
		document.getElementById("dayHumi").innerHTML = day.humidity + "%";
		document.getElementById("dayWind").innerHTML = day.wind_mph + " MPH";
		document.getElementById("dayCond").innerHTML = day.condition.text;
		//Nighttime
		document.getElementById("nigTime").innerHTML = data.current.last_updated;
		document.getElementById("nigTemp").innerHTML = night.temp_f + " F";
		document.getElementById("nigHumi").innerHTML = night.humidity + "%";
		document.getElementById("nigWind").innerHTML = night.wind_mph + " MPH";
		document.getElementById("nigCond").innerHTML = night.condition.text;
	} else {
		console.log("API not ready.");
	}
}