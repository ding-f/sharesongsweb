
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>音乐列表</title>
	<link rel="icon" href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>

<body>
	 <div>
<%--  <marquee direction="left" align="bottom" height="40" width="100%" onmouseout="this.start()" onmouseover="this.stop()" scrollamount="15" scrolldelay="1"><span style="font-size:30px; color: #f00;"><strong>本站由：安康学院-计算机科学与技术20级专升本1班-付鼎鼎 创建</strong></span></marquee>--%>
  
  </div>
	<div class="contianer py-5">

		<h1 class="display-4">音乐库</h1>
		<hr>
		<dir class="mb-3">
			<a href="addsongs.jsp" class="btn btn-sm btn-outline-success btn-block">点击上传音乐</a>
		</dir>
		<table class="table table-bordered table-striped table-hover " style="text-align: center;">
			<thead class="thead-dark">
				<tr>
					<th>音乐名称</th>
					<th>歌手</th>
					<th>上传者</th>
					<th>歌手写真</th>
					<th>试听（Google可下载）</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody class="text-center">
				<c:forEach var="asong" items="${allsong}">
					<tr style="height: 250px">
						<td class="align-middle">${asong.songname}</td>

						<td class="align-middle">${asong.singername}</td>
						<td class="align-middle">${asong.yourname}</td>
						<td class="align-middle"><img  height="200px" src="${ctx}${asong.photo}"></td>		<%--${ctx}最后无斜杠--%>
						<td class="align-middle"><audio src="${ctx}${asong.song}" controls></audio></td>
						<td class="align-middle">${asong.up_time}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="margin-top:20px; color:#CCC"><span style="font-size:12px;"> <center><span style="color: #096; font-family:'华文新魏'">开发者信息：安康学院-计算机科学与技术20级专升本1班-付鼎实训作品（指导老师：中公-陈微）</span><br />
	<span style="color: #f00; font-family:'华文新魏'">[生命不息 折腾不止]</span><br />
 </center>
	</span>
	</div>
</body>
</html>