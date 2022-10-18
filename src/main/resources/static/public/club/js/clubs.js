/**
 * create by Zeen Wu
 * on June 1, 2022
*/

function startup() {
    showUser();
    showClubs();
}

function showClubs() {
    $.ajax({
        type : "get",
        url : `${HOST}/club/`,
        dataType : "json",
        success : function(data) {
            var $data = $(data);
            var index = 0;
            var html = "";
            $data.each(
                function() {
                    html += generateCard(this, index) 
                    index+=1;        
                }
            );
            document.getElementById('clubs').innerHTML = html;
        }
    });
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
        let event = new Date(obj['sdate']);
        let days = Math.floor(calculateDays(today, event)) + 1;
        let msg = 'We are open in <b>' + days + '</b> days. ';
        html = '<div class="card col-4" ><img src="images/' 
        + obj['pfile'] +'" class="card-img-top" alt=""><div class="card-body"><h5 class="card-title"> ' 
        + obj['title'] + '</h5><p class="card-text"> ' 
        + msg + obj['comments'] + '</p><div id="div_'
        + index +'"><a href="course_club.html" class="btn btn-primary" onclick="setCurrentClub('
        + obj['cid'] +');">Check Details</a></div></div></div>';
    }
    
    return html;
}

function setCurrentClub(cid) {
    sessionStorage.setItem('current_club', cid);

}


