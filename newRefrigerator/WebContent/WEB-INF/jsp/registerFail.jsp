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
		<div class="register-fail">
			<h1 class="register-fail-title">RegisterAccount</h1>
			<div class="container-register-fail">
				<div class="register-fail-msg">
					<p>ID登録に失敗しました</p>
					<p>ログインIDまたはパスワードは<br>1文字以上入力してください</p>
				</div><!-- register-fail-msg -->
				<div class="register-fail-return">
					<p><a href="RegisterUser">ID登録画面に戻る</a></p>
				</div><!-- register-fail-return -->
			</div><!-- container-register-fail -->
		</div><!-- register-fail -->
	</body>
</html>