<%--
  Created by IntelliJ IDEA.
  User: xiwen.yxw
  Date: 2016/6/4
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search main page</title>
</head>
<body>
    <form method="post" action="<c:url value=\'/search.html'/>">
        <table>
            <tr>
                <td>检索域</td>
                <td><input type="text" name="searchField"/></td>
            </tr>
            <tr>
                <td>检索词</td>
                <td><input type="text" name="searchKey"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="提交"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
