/*This woul be my start function, it gets browser's location and starts the application*/

let events; // Global variable for events

function start() {
    //prepareInsertStatements(events); //TODO helper function
    callTudexServlet();

    console.log("Getting geolocation...");
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getPosition);
    } else {
        alert("Please enable Geolocation.");
    }
}

function callTudexServlet() {
    //To make this CORS request work i had to enable CORS in Tomcat7
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/TUDEX/tudexServlet", false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();

    var response = xhttp.responseText;//JSON.parse(xhttp.responseText);
    console.log(response);
    events = JSON.parse(response);
}

function getPosition(position) {
    console.log("Starting application...");
    manageEvents(events, position);
}

function getEventTimeSet(startTime, endTime) {
    let startDate = new Date(startTime);
    let endDate = new Date(endTime);

    return normalizeToTwoDigits(startDate.getHours()) + ":" + normalizeToTwoDigits(startDate.getMinutes()) + " - " + normalizeToTwoDigits(endDate.getHours()) + ":" + normalizeToTwoDigits(endDate.getMinutes());
}

/* This function normalizes the numbers to two digits adding a zero on the left of the number if < 10, example: 1 => 01 */
function normalizeToTwoDigits(digit) {
    return digit > 9 ? digit : '0' + digit;
}

/*The haversine formula determines the great-circle distance between two points on a sphere
 given their longitudes and latitudes.*/
function getDistanceFromLatLonInMeters(lat1, lon1, lat2, lon2) {
    const R = 6371 * 1000; // Radius of the earth in meters
    let dLat = deg2rad(lat2 - lat1); // deg2rad below
    let dLon = deg2rad(lon2 - lon1);
    let haversine =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    let c = 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));
    let distance = R * c; // Distance in meters
    return distance;
}

function deg2rad(deg) {
    return deg * (Math.PI / 180)
}

function prepareInsertStatements(events) {
    let insertStm = "";
    events.forEach(function(event) {
        insertStm += "\nINSERT INTO public.events(type, title, description, flames, coordinates, starttime, endtime) VALUES ( " + "'" + event.type + "'" + ", " + "'" + event.title + "'" + ", " + "'" + event.description + "'" + ", " + event.points + "," + "'" + "(" + event.coords.latitude + ", " + event.coords.longitude + ")" + "', " + event.startTime + ", " + event.endTime + ");";
    });
    console.log(insertStm);
}
