<header>
	<!---<link rel="stylesheet" type="text/css" href="login.css">-->
	<script src="login.js"></script>
</header>
<body>
	<div class="header"><h1>Vispr</h1></div>

	<div id="content">
		<div class="box" id="signup">
			<h2>SIGNUP</h2>
			<h3>Please enter your credentials</h3>
			<input class = "text" id = "first" maxlength = 10 placeholder = "name"><br><br>
			<input class = "text" id = "last"  placeholder = "lastname"><br><br>
			<input class = "utext" id = "user" maxlength = 16 placeholder = "username"><br><br>
			<input class = "text" id = "mil" placeholder = "e-mail"><br><br>
			<input class = "password" id = "pass" placeholder = "password"><br><br>
			<input class = "password" id = "pass2" placeholder = "re-type password">
			<button type="button" onclick="signup()">Submit</button>		
			<p id="erol"></p>
		</div>
		<div class="box" id="login">
			<h2>LOGIN</h2>
			<h3>Please enter your account information</h3>
			<input class = "text" id = "usr" placeholder = "username"><br><br>
			<input class = "password" id = "pass3" placeholder = "password">
			<button type = "button" onclick="login()">Submit</button>
			<p id="erol2"></p>
		</div>
	</div>
</body>
