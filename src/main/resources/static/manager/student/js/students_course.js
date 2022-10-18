/**
 * create by Ling Tao, Zeen Wu
 * on May 13, 2022
*/


function startup() {
    getStudentCourses();
}


function getStudentCourses(){
    $.ajax({
        type : "get",
        url : `${HOST}/csci/`,
        dataType : "json",
        success : function(data) {
            generateCourseList(data);
        }
    });
}

function getStudentCourseBy(key, value){
    $.ajax({
        type : "get",
        url : `${HOST}/csci/${key}/${value}`,
        dataType : "json",
        success : function(data) {
            generateCourseList(data);
        }
    });
}

function generateCourseList(list) {
    //let titles = ['Course ID','Student ID','Status','Club ID','Instructor ID','Course Name','Course Outline','First name','Last name',"Age","Email","Club","Start Date","Instrutor Name"];
    let titles = ["Club",'Course Name','Course Outline',"Club Start Date","Instrutor Name","Student Email",'Student First name','Student Last name',"Student Age"];
    let html = '';
    html += '<table id="tbl_list" class="table table-dark table-striped">';
    html += '<thead><tr><th scope="col">#</th>';
    for (let i in titles) {
        html += '<th scope="col">' + titles[i] + '</th>';
    }
    html += '</tr></thead><tbody>';

    for(let i = 0; i < list.length; i++) { 
        html += '<tr><td>#</td>';
        html += '<td><a href="#" onclick="getStudentCourseBy(\'club.title\',\''+ list[i]["title"] +'\')">'+ list[i]["title"]+'</a></td>';
        html += '<td><a href="#" onclick="getStudentCourseBy(\'club_course.course\',\''+ list[i]["course"] +'\');" >'+ list[i]["course"]+'</a></td>';
        html += '<td>'+ list[i]["comments"]+'</td>';
        html += '<td>'+ list[i]["sdate"]+'</td>';
        html += '<td>'+ list[i]["name"]+'</td>';
        html += '<td><a href="#" onclick="getStudentCourseBy(\'student.email\',\''+ list[i]["email"] +'\');" >'+ list[i]["email"]+'</a></td>';
        html += '<td>'+ list[i]["fname"]+'</td>';
        html += '<td>'+ list[i]["lname"]+'</td>';
        html += '<td>'+ list[i]["age"]+'</td>';
        html += '</tr>';
    }
    html += '</tbody></table>';
    document.getElementById("Courses_Register_Detail_list").innerHTML = html;
}

