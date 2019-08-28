<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/7/27
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入jstl标签库-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String path=request.getContextPath(); %>
<html>
<head>
    <title>文件列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>序号</td>
        <td>文件名称</td>
        <td>上传日期</td>
        <td>文件大小</td>
        <td>文件类型</td>
        <td>浏览次数</td>
        <td>下载次数</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody >

    <c:forEach items="${list}" var="f" varStatus="n">

    <tr>
        <td>${n.count}</td>
        <td   title="${f.name}" >
            <a href="#" id="preview_${f.id}">
            <c:if test="${f.name.length()>10}">
                  <c:out value="${f.name.substring(0,10)}+..."/>
            </c:if>
            <c:if test="${f.name.length()<=10}">
                ${f.name}
            </c:if>
            </a>
        </td>
        <td>
            <fmt:formatDate value="${f.ctime}" pattern="yyyy-MM-dd HH:mm"/>
        </td>
        <td>${f.total}</td>
        <td>
            <img src="<%=path%>/static/image/${f.suffix}.png" style="width:25px; height: 25px"
                onerror="javascript:this.src='<%=path%>/static/image/wma.png';">

        </td>
        <td>${f.preview}</td>
        <td>${f.downnum}</td>
        <td>
            <a>修改</a>
            <a href="<%=path%>/file/delete/${f.id}.do">删除</a>
            <a href="<%=path%>/file/download/${f.id}.do">下载</a>

        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<div id="box" style="display: none">
    <font color="aqua">red</font>
</div>
<script type="application/javascript" src="<%=path%>/static/js/jquery-3.4.1.min.js"></script>
<script type="application/javascript" src="<%=path%>/static/js/layer/layer.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $('a[id^=preview_]').click(function () {
            var id=$(this).attr('id');
            var ids=id.substring(8);
            var title=$(this).parent().attr('title');
            layer.open({
                type: 2,
                title: false,
                skin: 'layui-layer-rim', //加上边框
                area: ['840px', '515px'],
                content: ['<%=path%>/file/browse.do', 'no'] //iframe的url，no代表不显示滚动条

            });
            });

    });
</script>
</body>
</html>
