<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% User registerUser = (User) session.getAttribute("registerUser"); %>
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
		<div class="logout">
			<h1 class="logout-title">Logout</h1>
			<div class="container-logout">
				<p class="logout-msg">ID:<span><%= registerUser.getId() %></span></p>
				<p>上記IDをログアウトしました</p>
				<div class="return">
					<p><a href="LoginUserServlet">login画面に戻る</a></p>   
				</div>
			</div><!-- container-logout -->
		</div><!-- logout -->
	</body>
</html>