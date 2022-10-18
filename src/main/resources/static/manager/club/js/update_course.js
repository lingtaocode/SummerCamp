/**
 * create by Ling Tao, Zeen Wu
 * on June 1, 2022
*/

var ccid = (location.search).substring(6);

function startup() {
    initialization();
    showClub();
    showCourse();
    //alert(location.search);
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

function showCourse(){

    $.ajax({
        type : "get",
        url : `${HOST}/course/ccid/${ccid}`,
        dataType : "json",
        success : function(data) {
            document.getElementById("course").value = data.course;
            document.getElementById("comments").value = data.comments;
        }
    });
}


function updateCourse(){
    cid = sessionStorage.getItem("current_club");
    iid = document.getElementById("instructor").selectedOptions[0].value;
    course = document.getElementById("course").value;
    comments = document.getElementById("comments").value;
    $.ajax({
        method: "put",
        url: `${HOST}/course/update`,
        data: JSON.stringify ({
            "ccid": ccid,
            "cid": cid,
            "iid": iid,
            "course": course,
            "comments": comments
    }),headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
    }}).done((response) => {
        document.getElementById("msg_updateCourse").style.display="inline";
        document.getElementById("msg_updateCourse").innerHTML = "The course has been update.";
        showCourse();
    }).fail((obj, textStatus)=>{
        //An error is 400 ror 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })   
}
