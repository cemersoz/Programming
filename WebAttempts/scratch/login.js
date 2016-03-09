function signup() {

    var frst, lst, mail, pss, pss2, err, usr, success;

    // Get the value of the input fields
    frst = document.getElementById("first").value;
    lst = document.getElementById("last").value;
    mail = document.getElementById("mil").value;
    pss = document.getElementById("pass").value;
    pss2 = document.getElementById("pass2").value;
    usr = document.getElementById("user").value;
    success = "Input OK!";

    //login info errors
    var errors=[
        "Name cannot be empty",
        "Name cannot contain spaces",
        "Last name cannot be empty",
        "Last name cannot contain spaces",
        "username too short",
        "username cannot contain spaces",
        "e-mail is not valid",
        "password too short",
        "passwords do not match",
        "Input not valid"
    ];

    // check for proper input, change error to proper error message
    if (frst.length == 0) err = errors[0];
    else if(frst.indexOf(" ")!=-1) err = errors[1];
    else if(lst.length == 0) err = errors[2];
    else if(lst.indexOf(" ")!=-1) err = errors[3];
    else if(usr.length<3) err = errors[4];
    else if(usr.indexOf(" ")!=-1) err = errors[5];
    else if(!validateMail(mail)) err = errors[6];
    else if(pss.length<6) err = errors[7];
    else if(!(pss===pss2)) err = errors[8];
    else err = success;


    document.getElementById("erol").innerHTML = err;
}

function login() {
    var usern, pass, err, success;

    //get values from html
    usern = document.getElementById("usr").value;
    pass = document.getElementById("pass3").value;
    success = "Successfully Logged In!";
    //error messages
    var errors = [
        "username cannot be empty",
        "password cannot be empty",
        "username or password wrong"
    ];

    //check input
    if(usern.length < 1) err = errors[0];
    else if(pass.length < 1) err = errors[1];
    else err = success;

    document.getElementById("erol2").innerHTML = err;
}

//checks for valid email adress
function validateMail(x) {
    var atpos = x.indexOf("@");
    alert(atpos);
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        return false;
    }
    return true;
}