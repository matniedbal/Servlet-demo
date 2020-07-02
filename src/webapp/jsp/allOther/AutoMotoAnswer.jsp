<html>
<head>
    <title>Beer Answer</title>
</head>
<body>
<h1 align=center>Wyslane rzeczy</h1>
<%
    out.print(request.getAttribute("username"));

%>
${username}
<br>
<%
    out.print(request.getAttribute("beertype"));
%>
${beertype}

${person2}
<br>
${person2.name}
${person2.beertype}
${person2.beer}
</body>
</html>