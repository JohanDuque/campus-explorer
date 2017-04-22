function manageEvents(events, position) {

    let flamesCount = 42;


    events.forEach(function(event) {
        //Debugging stuff
        //console.log(event);
        // console.log(position);
        const METERS_100 = 100;
        let distance = getDistanceFromLatLonInMeters(event.coords.latitude, event.coords.longitude, position.coords.latitude, position.coords.longitude);
        console.log(event.title + " -> " + getEventTimeSet(event.startTime, event.endTime) + " # " + distance);

        let eventInAnHourSlot = isEventInAnHourSlot(event);

        if (distance <= METERS_100 && eventInAnHourSlot) {
            let mtsDistance = parseInt(distance) + ' meters'; //gets the integer value and concats meters
            let badgeDiv = createBagdeDiv(event, mtsDistance);

            appenDivToContent(badgeDiv);
            flamesCount++;
        }
    });

    let flamesFooterDiv = createTxtImgDiv('images/fire_1.png', 'Your flame count: ' + flamesCount);
    document.body.getElementsByClassName('footer-text')[0].appendChild(flamesFooterDiv);

}

function isEventInAnHourSlot(event) {
    const HOUR_IN_MILIS = 1000 * 60 * 60; //I'm using a constant here since an Hour will be always an hour :)

    let nowTimestamp = 1492858623573; //TODO go baxk to Date.now();
    let fromNowToStart = event.startTime - nowTimestamp;

    return (nowTimestamp >= event.startTime && nowTimestamp <= event.endTime) || //Event has already started but hasn't finished yet OR
        (nowTimestamp < event.startTime && fromNowToStart >= 0 && fromNowToStart <= HOUR_IN_MILIS); //Event will start in less than an hour
}


function createBagdeDiv(event, distance) {
    let badgeDiv = document.createElement('div');
    badgeDiv.className = 'badge_box';

    //Badge Header
    let badgeHead = document.createElement('div');
    badgeHead.className = 'badge_head';
    badgeHead.innerHTML = event.title;
    badgeDiv.appendChild(badgeHead);

    //Badge Body
    let badgeBody = document.createElement('div');
    badgeBody.className = 'badge_body';
    badgeBody.innerHTML = event.description;
    badgeDiv.appendChild(badgeBody);

    //Badge Footer
    let badgeFooter = document.createElement('div');
    badgeFooter.className = 'badge_footer';
    badgeDiv.appendChild(badgeFooter);

    let eventTimeSet = getEventTimeSet(event.startTime, event.endTime);
    let footerLeft = createImgTxtDiv('images/clock.png', eventTimeSet);
    badgeFooter.appendChild(footerLeft);

    let footerRight = createImgTxtDiv('images/distance.png', distance);
    badgeFooter.appendChild(footerRight);

    return badgeDiv;
}

function createTxtImgDiv(img, txt) {
    let containerDiv = document.createElement('div');
    containerDiv.className = 'img_txt_container';
    containerDiv.appendChild(createTxtDiv(txt));
    containerDiv.appendChild(createImgDiv(img));

    return containerDiv;
}

function createImgTxtDiv(img, txt) {
    let containerDiv = document.createElement('div');
    containerDiv.className = 'img_txt_container';
    containerDiv.appendChild(createImgDiv(img));
    containerDiv.appendChild(createTxtDiv(txt));

    return containerDiv;
}

function createImgDiv(img) {
    let imgDiv = document.createElement('img');
    imgDiv.className = 'img_txt_container_image';
    imgDiv.src = img;

    return imgDiv;
}

function createTxtDiv(txt) {
    let txtDiv = document.createElement('div');
    txtDiv.className = 'img_txt_container_text';
    txtDiv.innerHTML = txt;

    return txtDiv;
}


function appenDivToContent(newDiv) {
    document.body.getElementsByClassName('content')[0].appendChild(newDiv);
}
