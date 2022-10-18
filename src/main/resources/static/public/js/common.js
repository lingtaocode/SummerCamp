/**
 * create by Ling Tao, Zeen Wu
 * on June 1, 2022
*/
const HOST = "http://localhost:8045"

function initialization(){
    let elms = document.getElementsByName("alert_msg");
    for(let i=0; i<elms.length; i++){
        elms[i].style.display = "none";
    }
}

function userLogoff(){
    sessionStorage.removeItem("login");
    sessionStorage.removeItem("user");
    location.replace(HOST);
    //location.reload();
}


function userLogin(){
	let email = document.getElementById("email").value;
	let pword = document.getElementById("pword").value;
    login(email, pword);
}

function login(email, pword){

    $.ajax({
        method: "post",
        url: `${HOST}/user/login`,
        data: JSON.stringify ({
            "email": email,
            "pword": pword
        }),
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        if(response.object==null){
            //alert(response.message);
            switch(response.message) {
                case '0':
                    alert('User not found.');
                  break;
                case '2':
                    alert('Password is incorrect.');
                  break;
                default:
                    alert('a');
                    alert(response.message);
            }
        }else{
            switch(response.object.role) {
                case 0:
                    response.object.role = "Student";
                    sessionStorage.setItem("login", JSON.stringify(response));
                    getUserInfo(email);
                    //alert("reload");
                    //location.reload();
                  break;
                case 1:
                    sessionStorage.setItem("login", JSON.stringify(response));
                    window.location.replace("/manager/home/home.html");
                  break;
                default:
                    alert("Please contact webmaster, message = " + response.message );
            } 
        }
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 ror 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert('b');
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert('c');
            alert(obj.responseText)
        }
    })
    
}

function showUser(){
    let obj = JSON.parse(sessionStorage.getItem("login"));
    //alert(obj);
    if(obj==null) return;
    let user = JSON.parse(sessionStorage.getItem("user"));
    if(obj && obj.message=="1"){
        let html = "Welcome: <a href='../student/student_profile.html'>" 
        + user.fname + " "
        + user.lname + " "
        
        + "</a> / " + obj.object.role + " &nbsp;&nbsp; " + obj.object.email;

        let btn = "&nbsp;&nbsp;&nbsp;<button class='btn btn-outline-success' type='button' id='logoff' onclick='userLogoff();'>Log Out</button>";
        
        document.getElementById("user").innerHTML=html + btn;
    }

}

function getUserInfo(email){
    $.ajax({
        method: "get",
        url: `${HOST}/student/email/${email}`,        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        //alert(response.sid);
        sessionStorage.setItem("user", JSON.stringify(response));
        showUser();
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 ror 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })

}

/**
 * purpose: generate a Card components
 * @param {obj}  club
 * @param {index} index
 * @return string
*/
function generateCard(obj, index) {
    
    let html ='';
    if (obj) {
        let today = new Date();
        let event = new Date(obj['ymd']);
        let days = Math.floor(calculateDays(today, event)) + 1;
        let msg = 'We are open in <b>' + days + '</b> days. ';
        html = '<div class="card col-4" ><img src="images/' 
        + obj['img_name'] +'" class="card-img-top" alt=""><div class="card-body"><h5 class="card-title"> ' 
        + obj['title'] + '</h5><p class="card-text"> ' 
        + msg + obj['Comments'] + '</p><div id="div_'
        + index +'"><a href="stud_club.html" class="btn btn-primary" onclick="setCurrentClub('
        + index +');">Check Schedule</a></div></div></div>';
    }
    
    return html;
}

/**
 * purpose: generate a data list and save it in session
 * @param {title}  table title
 * @param {storage} session or local
 * @param {key} key
 * @return string
*/
function generateList(key, titles, storage) {
    let obj;
    let html = '';
    switch (storage) {
        case 'session':
            obj = JSON.parse(sessionStorage.getItem(key));
        break;
        case 'local':
            obj = JSON.parse(localStorage.getItem(key));
        break;
        default: 

    }
    
    html += '<table id="tbl_list" class="table table-dark table-striped">';
    html += '<thead><tr><th scope="col">#</th>';
    for (let i in titles) {
        html += '<th scope="col">' + titles[i] + '</th>';
    }
    html += '</tr></thead><tbody>';

    for(let i = 0; i < obj.length; i++) {
        html += '<tr><td>'+ (i+1) +'</td>';
        for(let key in obj[i]){
            html += '<td>'+ obj[i][key]+'</td>';
        }
        html += '</tr>'
    }
    html += '</tbody></table>'
    return html;
}

function deleteRow(obj) {
    let index = (obj.parentNode).parentNode.rowIndex;
    let table = document.getElementById('tbl_list');
    table.deleteRow(index);
}

function delete_Row(index) {
    let table = document.getElementById('tbl_list');
    table.deleteRow(index);
}


/**
 * purpose: generate a data list draggable
 * @param {title} table title
 * @param {obj} student
 * @return string
*/
function clientList(obj, titles) {
    let html = '';  
    if(!obj) return html;
    html += '<table id="tbl_list" class="table table-dark table-striped">';
    html += '<thead><tr><th scope="col">#</th>';
    for (let i in titles) {
        html += '<th scope="col">' + titles[i] + '</th>';
    }
    html += '</tr></thead><tbody>';

    for(let i = 0; i < obj.length; i++) {
        html += '<tr><td> <img src="images/delete.jpg" width="30" onclick="deleteRow(this);"> </td>';
        for(let key in obj[i]){
            if(key=='fname'){
                html += '<td id="drag_item'+i+'" draggable="true" ondragstart="drag(event, this.parentNode.rowIndex)">'+ obj[i][key]+'&nbsp;&nbsp;</td>';
            }else{
                html += '<td>'+ obj[i][key]+'</td>';
            }
            
        }
        html += '</tr>'
    }

    html += '</tbody></table>'

    return html;
}


/**
 * Make an Element Draggable
 */

function drag(ev, index) {
    // The dataTransfer.setData() method sets the data type 
    // and the value of the dragged data
    ev.dataTransfer.setData("text", ev.target.id);
    ev.dataTransfer.setData("index", index);
    
}

function allowDrop(ev) {
    // To allow a drop, we must prevent the default handling of the element.
    // This is done by calling the event.preventDefault() method for the ondragover event
    ev.preventDefault();
}

// When the dragged data is dropped, a drop event occurs.
// the ondrop attribute calls a function, drop(event)
function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var index = ev.dataTransfer.getData("index");
    ev.target.appendChild(document.getElementById(data));
    if(index != "undefined"){
        delete_Row(index);   
    }

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


function isEmail(inputText) {
    
    let rs = false;
    const mailformat = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,5})+$/);
    if (inputText.match(mailformat)) {
        rs =  true;
    } 
    return rs;
}

function ck_Phone(str) {
    let num = '';
    for(let i=0; i<str.length; i++) {
        let c = str.charAt(i);
        if (c=='(' || c==')' || c=='-' || c=='.' || c==' ' || c=='+') {

        } else if(isNaN(c)){
            return false;
        } else {
            num += c;
        }

    }
    return num;
}

function testCanadianPostalCode(postalCode)
{
    const postalCodeRegex = new RegExp(/^[ABCEGHJKLMNPRSTVXY]\d[ABCEGHJKLMNPRSTVXY][ -]?\d[ABCEGHJKLMNPRSTVXY]\d$/i);

    return postalCodeRegex.test(postalCode);
}

function filterSearch() {
    alert('ok');
	$('.searchResult').html('<div id="loading">Loading .....</div>');
	var action = 'fetch_data';
	var minPrice = $('#minPrice').val();
	var maxPrice = $('#maxPrice').val();
	var brand = getFilterData('brand');
	var ram = getFilterData('ram');
	var storage = getFilterData('storage');
	$.ajax({
		url:"action.php",
		method:"POST",
		dataType: "json",		
		data:{action:action, minPrice:	minPrice, maxPrice:maxPrice, brand:brand, ram:ram, storage:storage},
		success:function(data){
			$('.searchResult').html(data.html);
		}
	});
}

function loadDoc(id) {
    //alert(document.getElementById(id).innerText);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
       document.getElementById(id).innerHTML = this.responseText;
      }
    };
    alert('ok');
    xhttp.open("GET", "student_info.txt", true);
    xhttp.send();
}


/**
* function isTextNull(id)
* The purpose of this function is return boolean value of a text type input
*
*/

 function isTextNull(idValue) {
    let rs = false;
    var newtextValue = idValue.trim(); //removes whitespace from both ends of a string and returns a new string
    if (newtextValue.length == 0) {
      alert("must be filled out");
    } else {
      rs = true;
    }//end if condition
    return rs;
  }//end of function isTextNull

  /**
   * function isEmailNull(id)
   * The purpose of this function is return boolean value of a email type input
   *
  */
function isEmailNull(idValue) {
    let rs = false;
    let newemailValue = idValue.trim(); //removes whitespace from both ends of a string and returns a new string
    if (newemailValue.length == 0) {
      alert("Please input a valid email")
    } else {
      rs = true;
    }//end if condition
    return rs;

  }//end of function isEmailNull


  /**
   * function isCheckboxNull(id)
   * The purpose of this function is return boolean value of a checkbox type input
   *
  */

function isCheckboxNull(idValue) {
    let rs = false;
    let newCheckboxValue=document.getElementById(idValue);
    if(newCheckboxValue.checked == true){
        rs = true;
    }else{
        alert("You must agree before submitting");
    }//end if condition
    return rs;
}//end of function isCheckboxNull */


/**
 * Defined Message 
 */
const MSG_SUCCESSEMAIL = "Valid email address!";
const MSG_INVALIDPHONE = "You have entered an invalid phone number!";
const MSG_INVALIDEMAIL = "You have entered an invalid email address!";
const MSG_INVALIDDATE = "Please check the date you entered.";
const MSG_EMPTY = "This field can not be empty!";
const MSG_EMPTY_NICE = "Your feedback is very valuable, say something please.";