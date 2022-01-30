<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.FoodLists, model.Food, model.OverFood, model.FoodAndDeadline, java.util.*" %>
<%
//　セッションスコープに保存されたフードリスト(食べ物の種類ごとに分けたリスト)を取得
FoodLists foodLists = (FoodLists) session.getAttribute("foodLists");
if (foodLists == null) {
	foodLists = new FoodLists();
}
LinkedHashMap<String, LinkedHashMap<String, String>> kinds = null;
try {
	kinds = foodLists.getFoodLists();
} catch (NullPointerException e) {
	System.out.println("nullエラーです");
}
//　セッションスコープに保存されたフード＆デッドライン（賞味期限内の食べ物の【名前】と【期限】）を取得

FoodAndDeadline foodAndDeadline = (FoodAndDeadline) session.getAttribute("foodAndDeadline");
if (foodAndDeadline == null) {
	foodAndDeadline = new FoodAndDeadline();
}
LinkedHashMap<String, Integer> fad = null;
try {
	fad = foodAndDeadline.getFoodAndDeadline(); 
} catch (NullPointerException e) {
	System.out.println("nullエラーです");
}
//　セッションスコープに保存されたオーバーフード(賞味期限切れの食べ物の【名前】)を取得
OverFood overFood = (OverFood) session.getAttribute("overFood");
if (overFood == null) {
	overFood = new OverFood();
}
ArrayList<String> ovf = null;
try {
	ovf = overFood.getOverFood();
} catch (NullPointerException e) {
	System.out.println("nullエラーです");
}

%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <title>My Refrigerator</title>
        <meta name="description" content="冷蔵庫の中身を整理するアプリ">
        <!-- ファビコンをつくる -->
        <link rel="icon" type="image/png" href="images/favicon.png">
        <!-- スマホ用式もつくる -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSS -->
        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
        <link href="https://fonts.googleapis.com/css?family=Philosopher" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>

	<body>
		<div id="home" class="big-bg">
			<div class="big-refrigerator wrapper">
			        
				<h1><img class="refrigerator" src="images/refrigerator.png" alt="冷蔵庫"></h1>
				
				<div class="home-content">
					<h2 class="click">Please click</h2>
					<img class="cursor" src="images/click.png" alt="カーソル">  
				</div><!-- /.home-content -->
				
   			</div><!-- /.big-refrigerator -->
   			
			<div class="branch hidden">
				<div class="branch0 hidden">
					<h5>【選択してください】</h5>
					<div id="select-1"><a>①冷蔵庫に入れる</a></div><br>
					<div id="select-2"><a>②中身をみる</a></div>
				</div><!-- /.branch0 -->
				<!-- 追加 要修正　-->
				<form action="DeleteFoodServlet" method="post">
				<div class="branch-contents hidden">
					<div class="back-or-delete">
						<h5 class="back2"><a>≪戻る</a></h5>
						<input class="delete" type="submit" value="削除≫">
					</div>
					<div id="branch-delete"> 
						<select name="branch_delete" onchange="change(this)">
							<optgroup label="②中身をみる"></optgroup>
							<option>〈選択してください〉</option>
							<% if(kinds != null) { %>
								<% for(String kind: kinds.keySet()) { %>
									<% LinkedHashMap<String, String> names = null; %> 
									<% try { %>
										<% names = kinds.get(kind); %>
									<% } catch (NullPointerException e) { %>
										<% System.out.println("nullエラーです"); %>
									<% } %>
									<% if(names != null && !(names.isEmpty())) { %>
										<option>【<%=	 kind %>】</option>
										<% for(String name: names.keySet()) { %>	
											<% String expiration = names.get(name); %>
											<option value="<%= name %>">　<%= name %>(<%= expiration %>)</option>
										<% } %>
									<% } %>
								<% } %>
							<% } %>
						</select>
					</div><!-- /#branch-delete -->
					<div id="message">冷蔵庫から削除するものを選択してください</div>
				</div><!-- /.branch-contents -->
				</form>
				<!-- ここまで追加 要修正　-->
              	<form action="RegisterFoodServlet" method="post">
              	<div class="branch-all hidden">
                  	<div class="back-or-save">
                      	<h5 class="back"><a>≪戻る</a></h5>
                      	<input class="save" type="submit" value="保存≫">
                  	</div>
                  	<div class="exp-set"><h5>〈消費期限の設定〉<br><span>(例)2022/09/03</span></h5></div>
                  	<div id="branch-meat"> 
                      	<select name="branch_meat" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【肉】</option>
	                        <option value="ウインナー">ウインナー</option>
	                        <option value="牛肉">牛肉</option>
	                        <option value="鶏肉">鶏肉</option>
	                        <option value="豚肉">豚肉</option>
	                        <option value="ハム">ハム</option>
	                        <option value="挽き肉">挽き肉</option>
	                        <option value="ベーコン">ベーコン</option>
	                    </select>
                      	<input type="text" placeholder="20**/**/**" name="expiration_meat">
                  	</div><!-- /#branch-meat -->

                  	<div id="branch-vegetable"> 
	                    <select name="branch_vegetable" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【野菜】</option>
	                        <option value="かぶ">かぶ</option>
	                        <option value="かぼちゃ">かぼちゃ</option>
	                        <option value="キャベツ">キャベツ</option>
	                        <option value="きゅうり">きゅうり</option>
	                        <option value="ゴボウ">ゴボウ</option>
	                        <option value="小松菜">小松菜</option>
	                        <option value="生姜">生姜</option>
	                        <option value="セロリ">セロリ</option>
	                        <option value="大根">大根</option>
	                        <option value="とうもろこし">とうもろこし</option>
	                        <option value="トマト">トマト</option>
	                        <option value="なす">なす</option>
	                        <option value="にら">にら</option>
	                        <option value="にんにく">にんにく</option>
	                        <option value="ねぎ">ねぎ</option>
	                        <option value="にんじん">にんじん</option>
	                        <option value="白菜">白菜</option>
	                        <option value="パプリカ">パプリカ</option>
	                        <option value="ピーマン">ピーマン</option>
	                        <option value="ブロッコリー">ブロッコリー</option>
	                        <option value="ほうれん草">ほうれん草</option>
	                        <option value="レタス">レタス</option>
	                        <option value="れんこん">れんこん</option>
	                	</select><input type="text" placeholder="20**/**/**" name="expiration_vegetable">
                  	</div><!-- /#branch-vegetable -->
                  
	                <div id="branch-fruit"> 
	                    <select name="branch_fruit" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【果物】</option>
	                        <option value="いちご">いちご</option>
	                        <option value="イチジク">イチジク</option>
	                        <option value="キウイ">キウイ</option>
	                        <option value="スイカ">スイカ</option>
	                        <option value="梨">梨</option>
	                        <option value="パイン">パイン</option>
	                        <option value="バナナ">バナナ</option>
	                        <option value="ぶどう">ぶどう</option>
	                        <option value="マスカット">マスカット</option>
	                        <option value="マンゴー">マンゴー</option>
	                        <option value="みかん・柑橘類">みかん・柑橘類</option>
	                        <option value="メロン">メロン</option>
	                        <option value="桃">桃</option>
	                        <option value="りんご">りんご</option>
	                        <option value="レモン">レモン</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_fruit">
	                </div><!-- /#branch-fruit -->
	                  
	                <div id="branch-fish"> 
	                    <select name="branch_fish" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【魚介類】</option>
	                        <option value="アジ">アジ</option>
	                        <option value="穴子">穴子</option>
	                        <option value="鮎">鮎</option>
	                        <option value="イカ">イカ</option>
	                        <option value="ウナギ">ウナギ</option>
	                        <option value="ウニ">ウニ</option>
	                        <option value="エビ">エビ</option>
	                        <option value="牡蠣">牡蠣</option>
	                        <option value="カレイ">カレイ</option>
	                        <option value="鮭">鮭</option>
	                        <option value="サザエ">サザエ</option>
	                        <option value="鯖">鯖</option>
	                        <option value="サンマ">サンマ</option>
	                        <option value="しらす">しらす</option>
	                        <option value="鯛">鯛</option>
	                        <option value="タコ">タコ</option>
	                        <option value="鱈">鱈</option>
	                        <option value="ブリ">ブリ</option>
	                        <option value="魚卵">魚卵</option>
	                        <option value="貝類">貝類</option>
	                        <option value="刺身">刺身</option>
	                        <option value="練り物">練り物</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_fish">
	                </div><!-- /#branch-fish -->
	                  
	                <div id="branch-dry"> 
	                    <select name="branch_dry" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【乾物】</option>
	                        <option value="キクラゲ">キクラゲ</option>
	                        <option value="昆布">昆布</option>
	                        <option value="椎茸">椎茸</option>
	                        <option value="海苔">海苔</option>
	                        <option value="春雨">春雨</option>
	                        <option value="ひじき">ひじき</option>
	                        <option value="わかめ">わかめ</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_dry">
	                </div><!-- /#branch-dry -->
	                  
	                <div id="branch-mushroom"> 
	                    <select name="branch_mushroom" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【きのこ】</option>
	                        <option value="えのきだけ">えのきだけ</option>
	                        <option value="エリンギ">エリンギ</option>
	                        <option value="しいたけ">しいたけ</option>
	                        <option value="しめじ">しめじ</option>
	                        <option value="まいたけ">まいたけ</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_mushroom">
	                </div><!-- /#branch-mushroom -->
	                  
	                <div id="branch-egg"> 
	                    <select name="branch_egg" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【卵】</option>
	                        <option value="鶏卵">鶏卵</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_egg">
	                </div><!-- /#branch-egg -->
	                  
	                <div id="branch-potato"> 
	                    <select name="branch_potato" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【いも類】</option>
	                        <option value="こんにゃく">こんにゃく</option>
	                    	<option value="さつまいも">さつまいも</option>
	                        <option value="里いも">里いも</option>
	                        <option value="じゃがいも">じゃがいも</option>
	                        <option value="長いも">長いも</option>
	                	</select><input type="text" placeholder="20**/**/**" name="expiration_potato">
	                </div><!-- /#branch-potato -->
	                  
	                <div id="branch-bread"> 
	                    <select name="branch_bread" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【パン】</option>
	                        <option value="菓子パン">菓子パン</option>
	                        <option value="食パン">食パン</option>
	                        <option value="惣菜パン">惣菜パン</option>
	                        <option value="デニッシュパン">デニッシュパン</option>
	                        <option value="ピザ">ピザ</option>
	                        <option value="フランスパン">フランスパン</option>
	                        <option value="ロールパン">ロールパン</option>
	                        
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_bread">
	                </div><!-- /#branch-bread -->
	                  
	                <div id="branch-rice"> 
	                    <select name="branch_rice" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【米類】</option>
	                        <option value="ごはん">ごはん</option>
	                        <option value="生米">生米</option>
	                        <option value="餅">餅</option>
	                        <option value="もち米">もち米</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_rice">
	                </div><!-- /#branch-rice -->
	                  
	                <div id="branch-milk"> 
	                    <select name="branch_milk" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【乳製品】</option>
	                        <option value="牛乳">牛乳</option>
	                        <option value="チーズ">チーズ</option>
	                        <option value="生クリーム">生クリーム</option>
							<option value="バター">バター</option>
	                        <option value="ヨーグルト">ヨーグルト</option>
	                        <option value="練乳">練乳</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_milk">
	                </div><!-- /#branch-milk -->
	                  
	                <div id="branch-bean"> 
	                    <select name="branch_bean" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【豆類】</option>
	                        <option value="厚揚げ">厚揚げ</option>
	                        <option value="油揚げ">油揚げ</option>
	                        <option value="いんげん豆">いんげん豆</option>
	                        <option value="枝豆">枝豆</option>
	                        <option value="スナップエンドウ">スナップエンドウ</option>
	                        <option value="豆腐">豆腐</option>
	                        <option value="納豆">納豆</option>
	                        <option value="もやし">もやし</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_bean">
	                </div><!-- /#branch-bean -->
	                  
	                <div id="branch-noodles"> 
	                    <select name="branch_noodles" onchange="change(this)">
	                        <optgroup label="①冷蔵庫に入れる"></optgroup>
	                        <option>【麺類】</option>
	                        <option value="うどん">うどん</option>
	                        <option value="そうめん">そうめん</option>
	                        <option value="そば">そば</option>
	                        <option value="中華麺">中華麺</option>
	                        <option value="パスタ">パスタ</option>
	                    </select><input type="text" placeholder="20**/**/**" name="expiration_noodles">
	                </div><!-- /#branch-noodles -->
	                  
	                <div id="message">冷蔵庫に入れるものを選択してください</div>
	            </div><!-- /.branch-all -->
	            </form>
	        </div><!-- /.branch -->


			<div class="expiration">
				<h3>expiration</h3>
				<div class="soon">
					<h4>もうすぐ期限</h4>
					<div class="soon-food soon-food-hidden">
				<% if(fad != null) { %>
					<% for(String food: fad.keySet()) { %>
						<% int deadline = fad.get(food); %>
						<p><%= food %>残り<%= deadline %>日</p>
					<% } %>
				<% } %>			
						<h4 class="soon-all-display">すべて見る</h4>
					
						<h4 class="soon-all-display2 hidden">一部かくす</h4>
					
					</div><!-- /.soon-food -->
				</div><!-- /.soon -->
				
				<div class="over">
					<h4>期限切れ</h4>
					<div class="over-food over-food-hidden">
					
				<% if(ovf != null) { %>
					<% for(String food: ovf) { %>
						<p><%= food %></p>
					<% } %>
				<% } %>
					
						<h4 class="over-all-display">すべて見る</h4>
					
						<h4 class="over-all-display2 hidden">一部かくす</h4>
				
					</div><!-- /.over-food -->
				</div><!-- /.over --> 
			</div><!-- /.expiration -->       
			 
		</div><!-- /#home -->
		<footer>
				<div class="footer">
						<p><small>&copy; 2022 yukiur</small></p>
				</div>
		</footer>
		<script src="scripts/jquery-3.4.1.min.js"></script>
		<script>
			'use strict';
//----------------------- プルダウンの編集 ------------------
			function change(obj) {
			
			index = obj.selectedIndex;
			value = obj.options[index].value;
			
			}
//----------------------- 冷蔵庫に保存する ------------------        
			$('.refrigerator').on('click', function(){
			$('.branch, .branch0').toggleClass('hidden');
			});
			
			$('#select-1').on('click', function(){
			$('.branch0, .branch-all').toggleClass('hidden');
			});
			
			$('.back').on('click', function(){
			$('.branch0, .branch-all').toggleClass('hidden');
			});
//----------------------- 冷蔵庫の中身をみる ------------------
			$('#select-2').on('click', function(){
			$('.branch0, .branch-contents').toggleClass('hidden');
			});
			
			$('.back2').on('click', function(){
			$('.branch0, .branch-contents').toggleClass('hidden');
			});
//----------------------- 期限を全て見る or 隠す------------------        
			$('.soon-all-display, .soon-all-display2').on('click', function(){
			$('.soon-food').toggleClass('soon-food-show soon-food-hidden');
			$('.soon-all-display2, .soon-all-display').toggleClass('hidden');
			});

			
			$('.over-all-display, .over-all-display2').on('click', function(){
			$('.over-food').toggleClass('over-food-show over-food-hidden');
			$('.over-all-display2, .over-all-display').toggleClass('hidden');
			});
			
		</script>
	</body>
</html>
