<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
	<meta name="viewport" charset="UTF-8" content="width=device-width, initial-scale=1.0">
	<!--此功能的作用是适配设备屏幕来显示内容，初始缩放规模为1：1-->
	<title>上传歌曲</title>
	<link rel="icon" href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<script type="text/javascript" src="js/jquery.1.11.1.min.js"></script>
	<script type="text/javascript">

		$(function (){
			var songname_check=false;
			var singername_check=false;
			var yourname_check=false;
			var photo=false;
			var song=false;

			//判断歌曲名称是否合格
			$("[name=songname]").blur(function (){
				var name=$(this).val();
				if(name==null||name==""){
					$("#songname_span").text("* 歌曲名称不能为空").css("color","red");
				}else {
					//判断歌曲名称是否重复
					$.post("songupload",{"name":name,"flag":"checkSongName"},function(obj){
						//obj是servlet返回的数据，是boolean
						//true代表填写的电话号码没有被注册，能用
						//false代表填写的电话号码被注册了，不能用
						if(obj){
							$("#songname_span").text("√√√").css("color","green");
							songname_check=true;
						}else{
							$("#songname_span").text("该音乐已被别的用户上传，请换一首试试~").css("color","red");
							phone_res = false;
						}
					},"json");


				}
			});

			//判断歌手名称是否合格
			$("[name=singername]").blur(function (){
				var name=$(this).val();
				if(name==null||name==""){
					$("#singername_span").text("* 歌手名不能为空").css("color","red");
				}else {
					$("#singername_span").text("√√√").css("color","green");
					singername_check=true;
				}
			});

			//判断用户名是否合格
			$("[name=yourname]").blur(function (){
				var yourname=$(this).val();
				if(yourname==null||yourname==""){
					$("#yourname_span").text("* 你的用户名不能为空").css("color","red");
				}else {
					$.post("useraction",{"yourname":yourname,"flag":"checkYourName"},function(obj){
						//obj是servlet返回的数据，是boolean
						//true代表填写的电话号码没有被注册，能用
						//false代表填写的电话号码被注册了，不能用
						if(obj){
							$("#yourname_span").text("已注册用户 √").css("color","green");

							yourname_check=true;
						}else{
							$("#yourname_span").text("未注册用户，请注册~").css("color","red");

						}
					},"json");

				}
			});

			//判断专辑照片文件
			$("[name=photoname]").change(function() {
				//获取input file的files文件数组;
				var file = $(this).get(0).files[0];
				//获取图片大小kb
				var tou_max = file.size;

				//创建用来读取此文件的对象
				var reader = new FileReader();
				//使用该对象读取file文件
				reader.readAsDataURL(file);		//读取文件才能使用onload函数检测
				reader.onload = function (e) {
					if (tou_max > 5194304) {
						$("#photoname_span").text("请上传小于5M的图片！").css("color", "red");
						// $(".input_image").val("");
					} else {
						photo = true;
					}
				}

			});

			//判断音乐文件是否满足要求
			$("[name=songfile]").change(function (){
				var file = $(this).get(0).files[0];
				if(file!=null){
					song=true;
				}else {
					$("#song_span").text("请选择歌曲文件！").css("color","red");
				}
			});

			//提交按钮处理事件
			$("#btn").click(function (){
				if (song && songname_check && singername_check){
					$("#MyForm").submit();		//提交自动刷新页面

				}else {
					alert("请检查填写是否正确或者文件是否已选择？？？");
				}
			});

		});
	</script>
</head>
<body>
<div>
	<marquee direction="left" align="bottom" height="40" width="100%" onmouseout="this.start()" onmouseover="this.stop()" scrollamount="15" scrolldelay="1"><span style="font-size:30px; color: #f00;"><strong></strong></span></marquee>

</div>
<div class="container mt-5">


	<h1>音乐共享站</h1>
	<!-- <?php var_dump($_POST) ?> -->
	<hr>
	<!--		<div class="alert <?php echo empty($GLOBALS['error_message']) ? 'alert-success':'alert-danger' ?>">-->
	<!--			<?php-->
	<!--			if (isset($GLOBALS['error_message'])) {-->
	<!--				echo $GLOBALS['error_message'];-->
	<!--			}else{-->
	<!--				echo $GLOBALS['successful_message'];-->
	<!--			}-->
	<!--			?>-->
	<!--		</div>-->
	<!--	<?php endif?>-->
	<form id="MyForm" action="songupload" method="post" enctype="multipart/form-data" autocomplete="off" >

		<input type="hidden" id="flag" name="flag" value="addSong" runat = server>

		<div class="form-group">
			<label for="songsid">请选择歌曲：<span style="color: orangered">（小妙招：移动端选择歌曲后复制粘贴即可 PC端选择歌曲重命名复制歌曲名）</span></label>
			<input type="file" name="songfile" class="form-control" accept="audio/*" id="songsid"><span id="song_span"></span>
		</div>
		<div class="form-group">
			<label for="songid" >请输入您要上传的音乐名：<span style="color: orangered">（将检测歌曲名是否重复）</span></label>
			<input type="text" name="songname" id="songid" class="form-control" value=""><span id="songname_span"></span>
		</div>
		<div class="form-group">
			<label for="singerid" >请输入歌手名：</label>
			<input type="text" name="singername" id="singerid" class="form-control" value=""><span id="singername_span"></span>
		</div>
		<div class="form-group">
			<label for="yourid" >请输入您的用户名：<span style="color: orangered">（请输入注册用户：fuding）</span></label>
			<input type="text" name="yourname" id="yourid" class="form-control" value="fuding"><span id="yourname_span"></span>
		</div>
		<div class="form-group">
			<label for="photosid">请选择歌手写真：<span style="color: orangered">(不选择写真将使用默认图片)</span></label>
			<!--			accept="image/*"-->
			<input type="file" name="photoname" class="form-control" id="photosid" accept="image/*"><span id="photoname_span"></span>
		</div>

		<div class="mt-5 mb-5"></div>
		<div class="myseting" id="btn" >确认上传</div>


	</form>


</div>
<div style="margin-top:20px; color:#CCC"><span style="font-size:12px;"> <center><span style="color: #096; font-family:'华文新魏'">开发者信息：安康学院-计算机科学与技术20级专升本1班-付鼎实训作品（指导老师：中公-陈微）</span><br />
	<span style="color: #f00; font-family:'华文新魏'">[生命不息 折腾不止]</span><br />
</center>

</span>
</div>
</body>
</html>