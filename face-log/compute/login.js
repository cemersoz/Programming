$(document).ready(function(){
	var errorstatus=-1;
	var errorstatus2=-1;
	var errors2 = ["please fill all fields", "invalid e-mail", "invalid password", "passwords do not match"];
	var errors = ["please fill all fields","invalid e-mail","e-mail or password wrong"];
	$("#login").click(function(){
		var email = $("#email").val();
		var password = $("#password").val();
		// Checking for blank fields.
		if( email =='' || password ==''){
			$('input[type="text"],input[type="password"]').css("border","1px solid red");
			$('input[type="text"],input[type="password"]').css("box-shadow","0 0 2px red");
			document.getElementById("warning").innerHTML = errors[0];
		} else {
			$.post("login.php",{ email1: email, password1:password}, 
			function(data) {
				if(data=='Invalid Email.......') {
					$('input[type="text"]').css({"border":"2px solid red","box-shadow":"0 0 3px red"});
					$('input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
					document.getElementById("warning").innerHTML = errors[1];
				} else if(data=='Email or Password is wrong...!!!!'){
					$('input[type="text"],input[type="password"]').css({"border":"2px solid red","box-shadow":"0 0 3px red"});
					document.getElementById("warning").innerHTML = errors[2];
				} else if(data=='Successfully Logged in...'){
					$("form")[0].reset();
					$('input[type="text"],input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
					alert(data);
				} else{
					alert(data);
				}
			});
		}
	});
	$("#signup").click(function(){
		$('input[type="text2"],input[type="text3"],input[type="password2"]').css("border",".1vw solid #2f6a3e");
		$('input[type="text2"],input[type="text3"],input[type="password2"]').css("box-shadow","0 0 0px red");
		var fname = $("#fname").val();
		var lname = $("#lname").val();
		var email = $("#email2").val();
		var password = $("#password2").val();
		var rpassword = $("#password3").val();
		//Checking for blank fields.
		if( fname =='' || lname=='' || email =='' || password =='' || rpassword==''){
			$('input[type="text2"],input[type="text3"],input[type="password2"]').css("border","1px solid red");
			$('input[type="text2"],input[type="text3"],input[type="password2"]').css("box-shadow","0 0 2px red");
			document.getElementById("warning2").innerHTML = errors2[0];
		} else if(password != rpassword){
			$('input[type="password2"]').css("border","1px solid red");
			$('input[type="password2"]').css("box-shadow","0 0 2px red");
			document.getElementById("warning2").innerHTML = errors2[3];
		} else{		
			document.getElementById("warning2").innerHTML = errors2[2];
			$.post("login.php",{ fname1: fname, lname1: lname, email1: email, password1:password}, 
			function(data) {
				if(data=='Invalid Email.......') {
					$('input[id="email2"]').css({"border":"2px solid red","box-shadow":"0 0 3px red"});
					document.getElementById("warning2").innerHTML = errors[1];
				}  else if(data=='Successfully Signed up...'){
					$("form")[0].reset();
					$('input[type="text"],input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
					alert(data);
				} else{
					alert(data);
				}
			});
		}
	});
/*
$("#logn").click(function(){
        $(".lgn").toggleClass("lgn2");
    });*/
});