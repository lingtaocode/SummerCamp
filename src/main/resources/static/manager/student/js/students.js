/**
 * create by Ling Tao, Zeen Wu
 * on May 13, 2022
*/


function startup() {
    getStudents();
}


function getStudents(){
    $.ajax({
        type : "get",
        url : `${HOST}/student/`,
        dataType : "json",
        success : function(data) {
            showStudents(data);
        }
    });
}

function showStudents(list) {
    let titles = ['First name', 'Last name', "age", "address", "phone", "email"];
    let html = '';
    html += '<table id="tbl_list" class="table table-dark table-striped">';
    html += '<thead><tr><th scope="col"> #</th>';
    for (let i in titles) {
        html += '<th scope="col">' + titles[i] + '</th>';
    }
    html += '</tr></thead><tbody>';

    for(let i = 0; i < list.length; i++) { 
        html += '<tr>'
        html += '<td>'+ list[i]['sid']+'</td>';
        html += '<td>'+ list[i]['fname']+'</td>';
        html += '<td>'+ list[i]['lname']+'</td>';
        html += '<td>'+ list[i]['age']+'</td>';
        html += '<td>'+ list[i]['address']+'</td>';
        html += '<td>'+ list[i]['phone']+'</td>';
        html += '<td>'+ list[i]['email']+'</td>';
        html += '</tr>'
    }
    html += '</tbody></table>'

    let element = document.getElementById("student_list");
    element.innerHTML = html;
  
}