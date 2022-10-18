/**
 * create by Ling Tao, Zeen Wu
 * on June 1, 2022
*/
function startup() {
    showUser();

}

function showUser(){
    let obj = JSON.parse(sessionStorage.getItem("login"));
    //alert(obj);
    if(obj==null){
        //location.replace(HOST);
        return;
    }

    if(obj && obj.object.role=="1"){
        let html = "Welcome: " + obj.object.email + " &nbsp; / &nbsp; Manager";
        let btn = "&nbsp;&nbsp;&nbsp;<button class='btn btn-outline-success' type='button' id='logoff' onclick='userLogoff();'>Logoff</button>";
        document.getElementById("user").innerHTML=html + btn;
    }else{
        alert("You are not manager.");
        //location.replace(HOST);
    }
}

function userLogoff(){
    sessionStorage.removeItem("login");
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
                    //response.object.role = "Student";
                    //sessionStorage.setItem("login", JSON.stringify(response));
                    //getUserInfo(email);
                    //alert("reload");
                    //location.reload();
                  break;
                case 1:
                    sessionStorage.setItem("login", JSON.stringify(response));
                    showUser();
                    //window.location.replace("/manager/home/home.html");
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

