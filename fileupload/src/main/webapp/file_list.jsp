<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/18 0018
  Time: 上午 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.neuedu.updown.entity.UploadFile" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%

    List<UploadFile> list = (List<UploadFile>) request.getAttribute("list");
%>

<a href="${pageContext.request.contextPath}/upload.jsp">上传文件</a>

<table cellspacing="0" cellpadding="0" border="1" width="80%">
    <tr>
        <td>序号</td>
        <td>原始文件名</td>
        <td>上传时间</td>
        <td>文件大小</td>
        <td>上传ip</td>
        <td>下载</td>
    </tr>

    <%
        for (int i = 0; i < list.size(); i++) {
            UploadFile upload = list.get(i);

    %>
    <tr>
        <td><%=(i + 1)%>
        </td>
        <td><%=upload.getOriginName()%>
        </td>
        <td><%=upload.getUploadTime()%>
        </td>
        <td><%=upload.getSize()%>
        </td>
        <td><%=upload.getIp()%>
        </td>
        <td><a href="${pageContext.request.contextPath}/download?id=<%=upload.getId()%>">下载</a></td>
    </tr>


    <%
        }
    %>


</table>


</body>
</html>
