/**
 * create by Ling Tao, Zeen Wu
 * on May 13, 2022
*/
function startup() {
    initialization();

}

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
    
    $.ajax({
        method: "post",
        url: `${HOST}/club/new`,
        data: JSON.stringify ({
            "title": title,
            "sdate": ymd,
            "pfile": img_name,
            "comments": Comments
    }),headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
    }}).done((response) => {
        document.getElementById("msg_addNewClub").style.display="inline";
        document.getElementById("msg_addNewClub").innerHTML = "The club has been added.";
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
