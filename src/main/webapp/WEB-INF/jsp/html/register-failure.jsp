<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册失败</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url>../css/register.css</c:url>">
    <script src="<c:url>../js/jumping.js</c:url>"></script>
</head>

<body>
    <h1>注册失败：<br />
        该链接已失效，请重新注册。 <br />
        正在跳转至首页...</h1>

    <p>如果没有自动跳转，点击
        <a href="/graphiql" class="hover-shadow hover-color">
            这里
        </a>
    </p>
</body>

</html>