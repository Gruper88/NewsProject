<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


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

            <s:form id="newsEditForm" name="news" action="../admin/editWriteNews.html"  modelAttribute="news" method="post">
                <fieldset>
                    <p><label for="datepicker"><spring:message code="form.date"/></label></p>
                    <s:input id="datepicker" type="text" value=""  path="date" size="30"/><br/>
                    <p><label for="title"><spring:message code="form.title"/></label></p>
                    <s:input id="title" type="text" path="title" value="" size="30"/><br/>
                    <p><label for="description"><spring:message code="form.description"/></label></p>
                    <s:input id="description" type="text" path="description" value="" size="30"/><br/>
                    <p><label for="newstext"><spring:message code="form.text"/></label><br/></p>
                    <s:textarea id="newstext" type="textarea" rows="10" cols="60" path="newstext" value=""/><br/>
                    <s:input id="newsId" type="hidden" path="newsId" value="" maxlength="10"/><br/>
                    <p><input id="newsEditFormButton" type="submit" value="<spring:message code="form.button"/>"/></p>
                </fieldset>
            </s:form>
        </td>
</table>

</body>
</html>