/**
 * create by Zeen Wu
 * on June 1, 2022
*/

function startup() {
    initialization();
    showUser();
    showClub();
    if(sessionStorage.getItem("login")==null){
        showCourse(0);
        document.getElementById("btn_joinClass").style.display="none";
    }else{
        showCourse(1);
    }
    
}

function showClub(){
    let cid = sessionStorage.getItem("current_club");

    $.ajax({
        type : "get",
        url : `${HOST}/club/cid/${cid}`,
        dataType : "json",
        success : function(data) {
            
            var html = "";
            html = '<div class="card col-4"><img src="images/' 
            + data['pfile'] +'" class="card-img-top" alt=""><div class="card-body"><h5 class="card-title"> ' 
            + data['title'] + '</h5><p class="card-text"> ' 
            + data['comments'] + '</p></div></div>';
            document.getElementById('current_club').innerHTML = html;
        }
    });
}

function showCourse(edit){
    let cid = sessionStorage.getItem("current_club");
    let sid = 0;
    //alert(sessionStorage.getItem("user"));
    if(sessionStorage.getItem("user")!=null){
        //alert(JSON.parse(sessionStorage.getItem("user")).sid);
        sid = JSON.parse(sessionStorage.getItem("user")).sid;
    }
    
    $.ajax({
        type : "get",
        url : `${HOST}/course/cid/${cid}/${sid}`,
        dataType : "json",
        success : function(data) {
            let title = ['Course ID', 'Course', 'Detail'];
            if (edit==1){
                document.getElementById('course_list').innerHTML = generateEditList(data, title);
            }else{
                document.getElementById('course_list').innerHTML = generateList(data, title);
            }
            
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
        html += '<tr><td></td>';
        html += '<td>'+ list[i]['ccid']+'</td>';
        html += '<td>'+ list[i]['course']+'</td>';
        html += '<td>'+ list[i]['comments']+'</td>';
        html += '</tr>'
    }
    html += '</tbody></table>'
    return html;
}

function generateEditList(list, titles) {
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
        html += '<td>'+ list[i]['course']+'</td>';
        html += '<td>'+ list[i]['comments']+'</td>';
        html += '</tr>'
    }
    html += '</tbody></table>'
    return html;
}

function joinClass(){
    let sid = JSON.parse(sessionStorage.getItem("user")).sid;
    let elm = document.getElementsByName("ckb_course");
    for(let i=0; i<elm.length; i++){
        if(elm[i].checked){
            addCourse(elm[i].value, sid);
        }
    }
    let element = document.getElementById("msg_joinClass");
    element.innerHTML="Welcome join us. You can check the details in your account. <a href='../student/student_profile.html'>My course list.</a>";
    element.style.display="inline";
    setTimeout(() => {showCourse(1);}, 100);
}

function addCourse(ccid, sid){
    $.ajax({
        method: "post",
        url: `${HOST}/coursestudent/new`,
        data: JSON.stringify ({
            "ccid": ccid,
            "sid": sid,
            "status": 0
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        
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