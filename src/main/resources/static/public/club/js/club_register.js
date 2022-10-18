/**
 * create by Zeen Wu
 * on May 13, 2022
*/
function addNewClub() {
    var html = '';
    let ymd = document.getElementById('ymd').value;
    let title = document.getElementById('title').value;
    let Comments = document.getElementById('Comments').value;
    let img = document.getElementById('img').value;
    let str = img.split("\\");
    let img_name = str[str.length-1];

    // validate 
    if(ymd==''){
        alert(MSG_EMPTY);
        document.getElementById('ymd').focus();
        return;
    }else{
        let today = new Date();
        let event = new Date(ymd);
        let days = calculateDays(today, event) + 1;
        if (days < 0) {
            alert(MSG_INVALIDDATE);
            document.getElementById('ymd').focus();
            return;
        }
    }

    let clubs = JSON.parse(sessionStorage.getItem('clubs'));
    if( !clubs ) {
        clubs = new Array();
    }

    let club = {
        "title": title,
        "Comments": Comments,
        "img_name": img_name,
        "ymd": ymd
    };
    
    clubs.push(club);
    sessionStorage.setItem('clubs', JSON.stringify(clubs));

    for(let index in clubs) {
        html+= generateCard(clubs[index], index);
    }
    document.getElementById('club').innerHTML = html;
    
    
}

function setCurrentClub(i) {
    let clubs = JSON.parse(sessionStorage.getItem('clubs'));
    let current_club = {
        "obj": clubs[i],
        "index": i
    }
    sessionStorage.setItem('current_club', JSON.stringify(current_club));

}