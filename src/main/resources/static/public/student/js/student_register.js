/**
 * create by Zeen Wu
 * on May 13, 2022
*/

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'
  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        } else {
            register(event);
        }
        form.classList.add('was-validated')
        
      }, false)
    })
})()


function startup() {
  initialization();
  showUser();

}
function register(event) {
  event.preventDefault();
  event.stopPropagation();


  let email = document.getElementById('stu_email').value;
  let pword = document.getElementById('stu_pword').value;

  let user = {
    "email": email,
    "pword": pword,
    "flag": 0
  }
  newUser(user);
  document.getElementById("div_RegisterSuccessed").style.display="inline";
}

function newUser(user){
  $.ajax({
    method: "post",
    url: `${HOST}/user/new`,
    data: JSON.stringify(user),
    headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
    }
  }).done((response) => {
      if(response==null){
        document.getElementById("div_RegisterSuccessed").innerHTML="The email has already been used.";
      }else{
        newStudent();
      }
  }   
  ).fail((obj, textStatus)=>{
      //An error is 400 ror 500
      if (obj && obj.responseJSON && obj.responseJSON.message){
          //alert(obj.responseJSON.message)
          document.getElementById("div_RegisterSuccessed").innerHTML=obj.responseJSON.message;
          //alert('1');
      }
      if (obj && obj.responseText){
          document.getElementById("div_RegisterSuccessed").innerHTML=obj.responseText;
          //alert('2');
      }
  })
}

function newStudent(){
  let fname = document.getElementById('fname').value;
  let lname = document.getElementById('lname').value;
  let age = document.getElementById('age').value;
  let address = document.getElementById('address').value;
  let phone = document.getElementById('phone').value;
  let email = document.getElementById('stu_email').value;
  
  let student = {
    "fname": fname,
    "lname": lname,
    "age": age,
    "address": address,
    "phone": phone,
    "email": email 
  };

  $.ajax({
    method: "post",
    url: `${HOST}/student/new`,
    data: JSON.stringify(student),
    headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
    }
  }).done((response) => {
      if(response==null){
        alert("Student information is wrong.");
      }else{
        document.getElementById("div_RegisterSuccessed").innerHTML="Register successed! You may login your account.";
        //window.location.replace("../home/home.html");
      }
  }   
  ).fail((obj, textStatus)=>{
      //An error is 400 ror 500
      if (obj && obj.responseJSON && obj.responseJSON.message){
          //alert(obj.responseJSON.message)
          document.getElementById("div_RegisterSuccessed").innerHTML=obj.responseJSON.message;
        
      }
      if (obj && obj.responseText){
          //alert(obj.responseText)
          document.getElementById("div_RegisterSuccessed").innerHTML=obj.responseText;
        
      }
  })
}


function isValidate() {
  let o_phone = document.getElementById('Phone');
  let v_phone = o_phone.value;

  let o_Email = document.getElementById('Email');
  let v_Email = o_Email.value;
 
  if(!isEmail(v_Email)) {
    //ev.preventDefault();
    alert(MSG_INVALIDEMAIL);
    o_Email.focus();
    return false;
  }

  if(ck_Phone(v_phone)) {
    o_phone.value = ck_Phone(v_phone);
  } else {
    //ev.preventDefault();
    alert(MSG_INVALIDPHONE);
    o_phone.focus();
    return false;
  }

  return true;
  
}

  