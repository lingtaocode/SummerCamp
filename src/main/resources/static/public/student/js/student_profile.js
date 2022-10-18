function startup() {
    initialization();
    showUser();
    showProfile();
    showCourse();
}

function initialization(){
    let elms = document.getElementsByName("alert_msg");
    for(let i=0; i<elms.length; i++){
        elms[i].style.display = "none";
    }
}

function showProfile(){
    let email = JSON.parse(sessionStorage.getItem("login")).object.email;
    document.getElementById("p_welcome").innerHTML = "Welcome " + email;

    $.ajax({
        type : "get",
        url : `${HOST}/student/email/${email}`,
        dataType : "json",
        success : function(data) {
            document.getElementById('fname').value = data.fname;
            document.getElementById('lname').value = data.lname;
            let elm = document.getElementById('age');
            for(let item of elm.options){
                if(item.value == data.age){
                    //alert(data.age);
                    item.selected = true;
                }
            }
            document.getElementById('address').value = data.address;
            document.getElementById('phone').value = data.phone;
            document.getElementById("stu_email").value = email;
        }
    });

}

function updateProfile(){
    let fname = document.getElementById("fname").value;
    let lname = document.getElementById("lname").value;
    let age = document.getElementById("age").selectedOptions[0].value;
    let address = document.getElementById("address").value;
    let phone = document.getElementById("phone").value;
    let email = document.getElementById("stu_email").value;
    $.ajax({
        method: "PUT",
        url: `${HOST}/student/update`,
        data: JSON.stringify ({
            "fname": fname,
            "lname": lname,
            "age": age,
            "address": address,
            "phone": phone,
            "email": email
        }),
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById("div_updateSuccessed").innerHTML="Your profile has been update.";
        document.getElementById("div_updateSuccessed").style.display="inline";
        showProfile();
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


function showCourse(){
    let sid = JSON.parse(sessionStorage.getItem("user")).sid;
    $.ajax({
        type : "get",
        url : `${HOST}/csci/sid/${sid}`,
        dataType : "json",
        success : function(data) {
            let title = ['Course ID', 'Club', 'Course', 'Outline', 'Instructure', 'Starting date'];
            document.getElementById('course_list').innerHTML = generateList(data, title);
        }
    });
}

function generateList(list, titles) {
    let html = '';
    html += '<table id="tbl_list" class="table table-dark table-striped">';
    html += '<thead><tr><th scope="col"> #</th>';
    for (let i in titles) {
        html += '<th scope="col">' + titles[i] + '</th>';
    }
    html += '</tr></thead><tbody>';

    for(let i = 0; i < list.length; i++) { 
        html += '<tr><td><input name="ckb_course" value="'+ list[i]['ccid'] +'" type="checkbox"></td>';
        html += '<td>'+ list[i]['ccid']+'</td>';
        html += '<td>'+ list[i]['title']+'</td>';
        html += '<td>'+ list[i]['course']+'</td>';
        html += '<td>'+ list[i]['comments']+'</td>';
        html += '<td>'+ list[i]['name']+'</td>';
        html += '<td>'+ list[i]['sdate']+'</td>';
        html += '</tr>'
    }
    html += '</tbody></table>'
    return html;
}

function quiteCourse(){
    let sid = JSON.parse(sessionStorage.getItem("user")).sid;
    let elm = document.getElementsByName("ckb_course");
    for(let i=0; i<elm.length; i++){
        if(elm[i].checked){
            flag = deleteCourse(elm[i].value, sid);
            flag = true;
        }
    }
}

function deleteCourse(ccid, sid){
    $.ajax({
        method: "DELETE",
        url: `${HOST}/coursestudent/delete`,
        data: JSON.stringify ({
            "ccid": ccid,
            "sid": sid,
            "status": 0
        }),
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    })
    .done((response) => {
        document.getElementById("div_quiteSuccessed").innerHTML="Your course has been update.";
        document.getElementById("div_quiteSuccessed").style.display="inline";
        setTimeout(() => {showCourse();}, 1000);
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