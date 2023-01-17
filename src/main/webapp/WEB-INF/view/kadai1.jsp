<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.kadi" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String errorCode = request.getParameter("error");
	if(errorCode != null && errorCode.equals("1")){
		kadi ac = (kadi)session.getAttribute("input_data");
	
	%>
	<p style="color:red">登録に失敗しました。</p>
		<h3>新規会員登録</h3>
		<form action="kadai1A" method="post">
		氏名:<input type="text" name="name" value="<%=ac.getName() %>"><br>
		年齢:<input type="text" name="age" value="<%=ac.getAge() %>"><br>
		<label>性別</label><br>
		男<input type="radio" name="sei" value="0">
		女<input type="radio" name="sei" value="1"><br>
		電話番号:<input type="text" name="number"><br>
		メールアドレス:<input type="email" name="mail"><br>
		パスワード:<input type="password" name="pass"><br>
		<input type="submit" value="登録">
		</form>
	<%
		} else {
	%>
	<h3>新規会員登録</h3>
		<form action="kadai1A" method="post">
		氏名:<input type="text" name="name"><br>
		年齢:<input type="text" name="age"><br>
		<label>性別</label><br>
		男<input type="radio" name="sei" value="0">
		女<input type="radio" name="sei" value="1"><br>
		電話番号:<input type="text" name="number"><br>
		メールアドレス:<input type="email" name="mail"><br>
		パスワード:<input type="password" name="pass"><br>
		<input type="submit" value="登録">
		</form>
		<%
			}
		%>
</body>
</html>