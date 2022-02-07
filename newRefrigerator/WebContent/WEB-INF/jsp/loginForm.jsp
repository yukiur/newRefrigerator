<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <title>My Refrigerator</title>
        <meta name="description" content="冷蔵庫の中身を整理するアプリ">
        <!-- ファビコン -->
		<link rel="icon" type="image/png" sizes="32x32" href="images/favicon-32x32.png">
        <!-- レスポンシブデザイン -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSS -->
        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
        <link href="https://fonts.googleapis.com/css?family=Philosopher" rel="stylesheet">
        <link href="css/login.css" rel="stylesheet">
    </head>
	<body class="wrapper">
	    <div class="login">
	        <h1 class="login-title">Login</h1>
	        <div class="container">
	            <form action="LoginUserServlet" method="post">
	                <div class="id-container">
	                  <span><img src="images/human.png"></span>
	                  <input id="id" name="id" type="text" placeholder="Login ID"><br>
	                </div>
	                <div class="pass-container">
	                  <span><img src="images/key.png"></span>
	                  <input id="pass" name="pass" type="password" placeholder="Password"><br>
	                </div>
	                <input id="login-submit" type="submit" value="Login">
	            </form>
	            <p class="register-button"><a href="RegisterUser">RegisterAccount</a></p>
	        </div><!-- container -->
	    </div><!-- login -->
	</body>
</html>