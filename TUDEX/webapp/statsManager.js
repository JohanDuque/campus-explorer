/**
 * Created by Duque on 01/05/2017.
 */
function manageStats() {
    sortStatsByUserPoints();

    let statsText = "";
    let i = 1;

    let badgeDiv = document.createElement('div');
    badgeDiv.className = 'stats_box';

    stats.forEach(function (stat) {

    statsText = i+ ") " + stat.username;

    let badgeFooter = createRowDiv('badge_footer');

    //let userDiv = createTxtDiv(i+ ") " + stat.username + " .............");
    let statDiv = createDoubleTxtImgDiv('images/fire_1.png', statsText, stat.points);

    //badgeFooter.appendChild(userDiv);
    //badgeFooter.appendChild(statDiv);

    //badgeDiv.appendChild(userDiv);
    badgeDiv.appendChild(statDiv);
    i++;

    });
    appenDivToContent(badgeDiv);
    //let flamesFooterDiv = createTxtImgDiv('images/fire_1.png', 'Your Flame count: ' + flamesCount);
    //document.body.getElementsByClassName('footer-text')[0].appendChild(flamesFooterDiv);
}

function createDoubleTxtImgDiv(img, txt1, txt2) {
    let containerDiv = document.createElement('div');
    containerDiv.className = 'double_txt_img_container';
    containerDiv.appendChild(createRowDiv('double_txt1',txt1));
    containerDiv.appendChild(createRowDiv('double_txt2','...........'));
    containerDiv.appendChild(createRowDiv('double_txt2',txt2));
    containerDiv.appendChild(createImgDiv(img));

    return containerDiv;
}

function sortStatsByUserPoints() {
    stats.sort(compare);
}

function compare(a, b) {
    if (a.points > b.points)
        return -1;
    if (a.points < b.points)
        return 1;
    return 0;
}