/**
 * Created by Duque on 28/04/2017.
 */
function manageEvents(events, position) {

    let flamesCount = 42;//TODO get real flames count for current user


    events.forEach(function(event) {
        const METERS_100 = 100;

        let distance = getDistanceFromLatLonInMeters(event.latitude, event.longitude, position.coords.latitude, position.coords.longitude);

        //Debugging stuff
        //console.log(event);
        // console.log(position);
        console.log(event.title + " -> " + getEventTimeSet(event.startTime, event.endTime) + " # " + distance);

        if(event.type ==="BONUS"){
            console.error(event.title + " -> " + getEventTimeSet(event.startTime, event.endTime) + " # " + distance);
            if (distance <= METERS_100 && isEventInADaySlot(event)) {
                appenDivToContent(createBonusDiv(event));
                flamesCount = flamesCount + event.flames;
            }
        }

        if(event.type ==="NOTIFICATION") {//TODO Constants
            if (distance <= METERS_100 && isEventInAnHourSlot(event)) {
                let mtsDistance = parseInt(distance) + ' meters'; //gets the integer value and concats meters
                appenDivToContent(createBagdeDiv(event, mtsDistance));
                flamesCount = flamesCount + event.flames;
            }
        }
    });

    let flamesFooterDiv = createTxtImgDiv('images/fire_1.png', 'Your Flame count: ' + flamesCount);
    document.body.getElementsByClassName('footer-text')[0].appendChild(flamesFooterDiv);

}

function isEventInAnHourSlot(event) {
    const HOUR_IN_MILIS = 1000 * 60 * 60; //I'm using a constant here since an Hour will be always an hour :)

    let nowTimestamp = 1492858623573; //TODO go back to Date.now();
    let fromNowToStart = event.startTime - nowTimestamp;

    return (nowTimestamp >= event.startTime && nowTimestamp <= event.endTime) || //Event has already started but hasn't finished yet OR
        (nowTimestamp < event.startTime && fromNowToStart >= 0 && fromNowToStart <= HOUR_IN_MILIS); //Event will start in less than an hour
}

function isEventInADaySlot(event) {
    const DAY_IN_MILIS = 1000 * 60 * 60 *24;

    let nowTimestamp = 1492858623573; //TODO go back to Date.now();
    let fromNowToStart = event.startTime - nowTimestamp;

    return (nowTimestamp >= event.startTime && nowTimestamp <= event.endTime) || //Event has already started but hasn't finished yet OR
        (nowTimestamp < event.startTime && fromNowToStart >= 0 && fromNowToStart <= DAY_IN_MILIS); //Event will start in less than an hour
}

function createBonusDiv(event) {
    let bonusDiv = document.createElement('div');
    bonusDiv.className = 'badge_box';

    let bonusImgDiv = createImgTxtDiv('images/fire_1.png', event.title + " "+event.flames+'+ Flames!');
    bonusImgDiv.appendChild(createImgDiv('images/fire_1.png'));

    bonusImgDiv.className = 'bonus-div';
    bonusDiv.appendChild(bonusImgDiv);

    return bonusImgDiv;
}


function createBagdeDiv(event, distance) {
    let badgeDiv = document.createElement('div');
    badgeDiv.className = 'badge_box';

    //Badge Header
    badgeDiv.appendChild(createRowDiv('badge_head', event.title));

    //Badge Body
    badgeDiv.appendChild(createRowDiv('badge_body', event.description));

    //Badge Footer
    let badgeFooter = createRowDiv('badge_footer');
    let eventTimeSet = getEventTimeSet(event.startTime, event.endTime);

    let footerLeft = createImgTxtDiv('images/clock.png', eventTimeSet);
    badgeFooter.appendChild(footerLeft);

    let footerRight = createImgTxtDiv('images/distance.png', distance);
    badgeFooter.appendChild(footerRight);

    badgeDiv.appendChild(badgeFooter);
    return badgeDiv;
}


function createRowDiv(className, innerHtml) {
    let badgeRowDiv = document.createElement('div');
    badgeRowDiv.className = className ? className : 'badge_body';
    badgeRowDiv.innerHTML = innerHtml ? innerHtml : '';

    return badgeRowDiv;
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

function createTxtDiv(txt) {//FIXME is equal to createRowDiv
    let txtDiv = document.createElement('div');
    txtDiv.className = 'img_txt_container_text';
    txtDiv.innerHTML = txt;

    return txtDiv;
}


function appenDivToContent(newDiv) {
    document.body.getElementsByClassName('content')[0].appendChild(newDiv);
}
