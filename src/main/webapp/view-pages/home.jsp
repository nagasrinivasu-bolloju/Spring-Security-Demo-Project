<html>
<head>
	<style>
		.container {
			 
			margin-top:40px;
			
			/* border:5px solid red; */
			/* background-color:rgba(0,0,0,0.5); */
			/*box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);*/
			border-radius:5px;
		    //background-color:rgba(0,150,100,0.5);
		}
		.heading1{margin-left:45%;}
		.heading2{margin-left:15%;}
		.heading3{margin-left:35%;}
		.formDiv{margin-left:45%;}
		.submit{margin-left:10%;margin-top:5px;}
		.nextPage{
			margin-left:605px;
			font-size:16px;
			font-family:helvetica;}
	</style>
</head>
<body>
<div class="container">
<h2 class="heading1">Hello World!</h2><br>
<h1 class="heading2">Hello User!!!! welcome to the  SPring MVC DemoApplication</h1><br>
<h3 class="heading3">Enter values of a,b,c in Expression (2a^4+6b^2+c)/d</h3><br>
<div class="formDiv">
<form action="calculate">
	<label>a:</label><input type="text" name="a"><br>
	<label>b:</label><input type="text" name="b"><br>
	<label>c:</label><input type="text" name="c"><br>
	<label>d:</label><input type="text" name="d"><br>
	<input class="submit" type="submit">
</form><br>
</div>
<a class="nextPage" href="add">NextPage</a>
</div>
</body>
</html>
