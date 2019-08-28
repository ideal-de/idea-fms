<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/7/26
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String path=request.getContextPath();%>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="<%=path%>/file/upload.do" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><input type="file" name="loadFile"></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
