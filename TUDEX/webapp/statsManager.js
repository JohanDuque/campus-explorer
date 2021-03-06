/**
 * Created by Duque on 01/05/2017.
 */
function manageStats(currentUser) {
    sortStatsByUserPoints();

    let tudexBox = createStatsBox(stats, "Tudelf");
    appenDivToContent(tudexBox);

    let currentUserFaculty = currentUser.faculty;

    let facultyStats = stats.filter(function(stat) {
        return stat.faculty === currentUserFaculty;
    });

    let facultyBox = createStatsBox(facultyStats, currentUserFaculty);
    appenDivToContent(facultyBox);

    let flamesFooterDiv = createTxtImgDiv('images/fire_1.png', 'Top Campus Explorers');
    document.body.getElementsByClassName('footer-text')[0].appendChild(flamesFooterDiv);
}

let createStatsBox = function (stats, boxTitle) {
    let i = 1;

    let badgeBox = document.createElement('div');
    badgeBox.className = 'stats_box';

    //Badge Header
    badgeBox.appendChild(createRowDiv('stats_head', boxTitle));

    //Stats Container
    let statsContainer = document.createElement('div');
    statsContainer.className = 'stats_container';

    stats.forEach(function (stat) {
        let statsText = i + '.  ' + stat.username + ' ' + stat.surname;
        let statDiv = createDoubleTxtImgDiv('images/fire_1.png', statsText, stat.points);

        //In this way i highlight the current user
        if (stat.username === currentUser.username) {
            statDiv.style.color = "#fed952";
            statDiv.style.fontWeight = "bold";
        }

        statsContainer.appendChild(statDiv);
        i++;
    });

    badgeBox.appendChild(statsContainer);

    return badgeBox;
};

function createDoubleTxtImgDiv(img, txt1, txt2) {
    let containerDiv = document.createElement('div');
    containerDiv.className = 'double_txt_img_container';
    containerDiv.appendChild(createRowDiv('double_txt1',txt1));
    containerDiv.appendChild(createRowDiv('double_txt2',txt2));
    containerDiv.appendChild(createImgDiv(img));

    return containerDiv;
}

function sortStatsByUserPoints() {
    stats.sort(compareUserPoints);
}

function compareUserPoints(a, b) {
    if (a.points > b.points)
        return -1;
    if (a.points < b.points)
        return 1;
    return 0;
}
