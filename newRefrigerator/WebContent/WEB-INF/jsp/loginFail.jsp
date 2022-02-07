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
		<div class="fail">
			<h1 class="fail-title">Login</h1>
			<div class="container-fail">
				<div class="fail-msg">
					<p>ログインに失敗しました</p>
					<p>ログインIDまたは<br>パスワードが間違っています</p>
					<p>未登録の場合は<a href="RegisterUser">ログインIDを登録する</a></p>
				</div><!-- fail-msg -->
				<div class="return">
					<p><a href="LoginUserServlet">login画面に戻る</a></p>   
				</div><!-- return -->
			</div><!-- container-fail -->
   		</div><!-- fail -->
   	</body>
</html>