/**
 * create by Zeen Wu
 * on June 1, 2022
*/

function startup() {
    showUser();
    showClub();
    showCourse();
}

function showClub(){
    let cid = sessionStorage.getItem("current_club");

    $.ajax({
        type : "get",
        url : `${HOST}/club/cid/${cid}`,
        dataType : "json",
        success : function(data) {
            
            var html = "";
            html = '<div class="card col-4" ><img src="images/' 
            + data['pfile'] +'" class="card-img-top" alt=""><div class="card-body"><h5 class="card-title"> ' 
            + data['title'] + '</h5><p class="card-text"> ' 
            + data['comments'] + '</p></div></div>';
            document.getElementById('current_club').innerHTML = html;
        }
    });
}

function showCourse(){
    let cid = sessionStorage.getItem("current_club");
    $.ajax({
        type : "get",
        url : `${HOST}/course/cid/${cid}`,
        dataType : "json",
        success : function(data) {
            let title = ['Course ID','Club ID','Instuctor ID', 'Course', 'Detail'];
            document.getElementById('course_list').innerHTML = generateList(data, title);
        }
    });


}

/**
 * purpose: generate a data list and save it in session
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
        html += '<tr><td><input type="checkbox" name="ckb_course" id="ckb'+ (i+1) +'" value="'+ list[i]["ccid"] +'"></td>';
        for(let key in list[i]){
            html += '<td>'+ list[i][key]+'</td>';
        }
        html += '</tr>'
    }
    html += '</tbody></table>'
    return html;
}

function joinClass(){
    let elm = document.getElementsByName("ckb_course");
    let value = new Array();
    for(let i=0; i<elm.length; i++){
        if(elm[i].checked)
            value.push(elm[i].value);
    }
    return value;
}