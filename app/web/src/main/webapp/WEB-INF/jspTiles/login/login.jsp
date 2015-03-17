<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<spring:url var="authUrl" value="/j_spring_security_check"/>
<!DOCTYPE html PUBLIC
"-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.authorization"/></title>
    <style type="text/css">
        body {
            max-width: 900px;
            margin: auto;
            background:#E8E8E8;
        }
    </style>
</head>
<body>
<table align="center" bgcolor="#E8E8E8" width=300px>
    <tr>
        <td>
            <p>

            <h3>${massage}</h3>
            </p>
        </td>
    </tr>

    <tr>
        <td>
            <form method="post" class="signin" action="${authUrl}">
                <FIELDSET>
                    <LEGEND><spring:message code="admin.authorization"/></LEGEND>
                    <p></p><input type="text" id="username_or_email" name="j_username"></p><br>

                    <p><input type="password" id="password" name="j_password"></p>

                    <input id="remember_me"
                           name="_spring_security_remember_me"
                           type="checkbox"/>
                    <label for="remember_me" class="inline"><spring:message code="admin.remember"/></label>
                    <br>
                    <br>
                    <input name="commit" type="submit" value="<spring:message code="admin.send"/>">
                </FIELDSET>
            </form>
            <script type="text/javascript">
                document.getElementById('username_or_email').focus();
            </script>
            <p><a href="../user/newses.html"><spring:message code="footer.showAllNews"/></a></p>

        </td>
    </tr>

</table>

</body>
</html>