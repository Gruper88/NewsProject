<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
            <s:form id="newsAddForm" name="news" action="../admin/newsAddWrite.html" modelAttribute="news"
                    method="post">
                <fieldset>
                    <p>

                    <p><label for="datepicker"><spring:message code="form.date"/> :</label></p>
                    <s:input id="datepicker" type="text" value="" path="date" size="30" autocomplete="off"/><br/>

                    <p><label for="title"><spring:message code="form.title"/> :</label></p>
                    <s:input id="title" type="text" path="title" value="" size="30"/><br/>

                    <p><label for="description"><spring:message code="form.description"/> :</label></p>
                    <s:input id="description" type="text" path="description" value="" size="30"/><br/>

                    <!-- inter dinamic Category JQ-->
                    <p><spring:message code="form.category"/> :
                        <br>
                        <br>
                        <span>
                        <input list="catigory" type="feild" name="category_1" size="30">
                            <datalist id="catigory">
                                <c:forEach items="${categoryList}" var="productCategories">
                                    <option>${productCategories.category}</option>
                                </c:forEach>
                            </datalist>
                        </span>
                    </p>

                    <p class="hide">
                        <span>
                        <input list="catigory" type="feild" name="category_2" size="30">
                        </span>
                        <a class="del_file" href="#"><spring:message code="form.delCategory"/></a>
                    </p>

                    <p class="hide">
                        <span>
                        <input list="catigory" type="feild" name="category_3" size="30">
                        </span>
                        <a class="del_file" href="#"><spring:message code="form.delCategory"/></a>
                    </p>

                    <p class="hide"><span>
                        <input list="catigory" type="feild" name="category_4" size="30">
                        </span>
                        <a class="del_file" href="#"><spring:message code="form.delCategory"/></a>
                    </p>

                    <p>
                        <a href="#" class="add_file"><spring:message code="form.addCategory"/></a>
                    </p><br>

                    <p><label for="newstext"><spring:message code="form.text"/></label><br/></p>
                    <s:textarea id="newstext" type="textarea" rows="10" cols="60" path="newstext" value=""/><br/>
                    <input type="hidden" name="userEmail" value="<sec:authentication property="principal.username"/>">

                    <p><input id="newsEditFormButton" type="submit" value="<spring:message code="form.saveNews"/>"/></p>
                </fieldset>
            </s:form>
        </td>
    </tr>

</table>

</body>
</html>