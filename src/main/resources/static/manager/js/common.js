/**
 * create by Zeen Wu
 * on June 1, 2022
*/
const HOST = "http://localhost:8045"

function initialization(){
    let elms = document.getElementsByName("alert_msg");
    for(let i=0; i<elms.length; i++){
        elms[i].style.display = "none";
    }
}

/**
 * purpose: generate a data list
 * @param {title}  table title
 * @param {key} key
 * @return string
*/
function generateList(list, titles) {
    let html = '';
    html += '<table id="tbl_list" class="table table-dark table-striped">';
    html += '<thead><tr><th scope="col">#</th>';
    for (let i in titles) {
        html += '<th scope="col">' + titles[i] + '</th>';
    }
    html += '</tr></thead><tbody>';

    for(let i = 0; i < list.length; i++) { 
        html += '<tr><td>#</td>';
        for(let key in list[i]){
            html += '<td>'+ list[i][key]+'</td>';
        }
        html += '</tr>'
    }
    html += '</tbody></table>'
    return html;
}

/**
 * purpose: generate a data list and save it in session
 * @param {title}  table title
 * @param {key} key
 * @return string
*/
function generateChkList(list, titles) {
    let html = '';
    html += '<table id="tbl_list" class="table table-dark table-striped">';
    html += '<thead><tr><th scope="col">#</th>';
    for (let i in titles) {
        html += '<th scope="col">' + titles[i] + '</th>';
    }
    html += '</tr></thead><tbody>';

    for(let i = 0; i < list.length; i++) { 
        html += '<tr><td><input type="checkbox" name="ckb_course" id="ckb'+ (i+1) +'" value="'+ list[i]["ccid"] +'"></td>';
        for(let key in list[i]){
            html += '<td>'+ list[i][key]+'</td>';
        }
        html += '</tr>'
    }
    html += '</tbody></table>'
    return html;
}


/**
 * create instance of the date object
 */
 function eventClock(event) {
    // create a variable that contains the current date and time
    // var variable_name = new Date("month day, year hours:minutes:seconds");
    let today = new Date();

    // make a call (invoke) the calculateDays function
    let days = calculateDays(today, event);


    // calculate the time left until the new year 
    // call the calculateDays 
    document.getElementById('days').innerText = Math.floor(days);

    // calculate the hours left in the current day
    let hours = ( days - Math.floor(days) ) * 24 ;

    // display hours left to the next lowest integer
    //document.getElementById('txtHrLeft').value = Math.floor(hours);

    // calculate the minutes left in the current hour
    let minutes = ( hours - Math.floor(hours) ) * 60;
    let mm = Math.floor( minutes);
    
    // display minutes rounded to the next lowest integer 
    //document.getElementById('txtMinLeft').value = Math.floor(minutes);

    // calculate the secinds left to the current minute
    let seconds = (minutes - mm) * 60; 
    let ss = Math.floor(seconds);
    let h = Math.floor(hours);
    // formate time h:mm:ss
    if (mm < 10) {
        mm = '0' + mm;
    }

    if (ss < 10) {
        ss = '0' + ss;
    }

    // display seconds left rounded to the next lowest integer 
    //document.getElementById('txtSecLeft').value = Math.floor(seconds);

    let clock = h + ':' + mm + ':' + ss;
    return clock;

} // end function eventClock



/**
 * returns the number of days left between the current
    date and target date (January 1st of the next year)
 * @param {Date} currentDate 
 */
function calculateDays(date1, date2) {
    // calculate the difference between the date1 and date2

    // convert milliseconds to days
    let numberOfDays = (date2 - date1) / (1000 * 60 * 60 * 24);
    return numberOfDays;
}
