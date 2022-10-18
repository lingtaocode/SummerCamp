/**
 * create by Ling Tao, Zeen Wu
 * on June 1, 2022
*/

//window.addEventListener('load', setInterval('startup()', 1000));

function startup() {
    showUser();
    //let event = new Date('June 27, 2022');
    //document.getElementById('clock').innerText = eventClock(event);
}


function postData(){
    
	let email = document.getElementById("email").value;
	let pword = document.getElementById("pword").value;

    $.ajax({
        
        method: "post",
        url: `${HOST}/newuser`,
        data: JSON.stringify ({
            "email": email,
            "pword" : pword
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type' : 'application/json'
        }
    }).done((response) => {
        alert('user created')
        // print(response)
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



