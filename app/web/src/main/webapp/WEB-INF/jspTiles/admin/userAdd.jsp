<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <style type="text/css">
        body {
            max-width: 900px;
            margin: auto;
            background: #E8E8E8;
        }
    </style>
</head>
<body>
<table align="center" bgcolor="#E8E8E8" width=600px>
    <tr>
        <td>
            <s:form id="userEditForm" name="user" action="../admin/userAddWrite.html" modelAttribute="user"
                    method="post">
                <fieldset>
                    <p>

                    <p><label for="email">E-mail :</label></p>
                    <s:input id="email" type="text" value="" path="email" size="30"/><br/>

                    <p><label for="password"><spring:message code="form.password"/> :</label></p>
                    <s:input id="password" type="password" path="password" value="" size="30"/>

                    <s:form id="userDetailsForm" name="userDetails" modelAttribute="userDetails" method="post">

                        <p><label for="userName"><spring:message code="form.userName"/> :</label></p>
                        <s:input id="userName" type="text" value="" path="name" size="30"/><br/>

                        <p><label for="userSurName"><spring:message code="form.userSurName"/> :</label></p>
                        <s:input id="userSurName" type="text" path="surname" value="" size="30"/>

                    </s:form>
                    <p><input id="newsEditFormButton" type="submit" value="<spring:message code="form.saveUser"/>"/></p>
                    </p>
                </fieldset>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>