<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/18 0018
  Time: 上午 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>上传文件</h3>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/up">

    <input type="file" name="file" />

    <input type="text" name="name" value=""/>
    <input type="text" name="password"   value=""/>
    <input type="submit">
</form>

</body>
</html>