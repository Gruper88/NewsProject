<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table bgcolor="E8E8E8" width="100%">
    <tr>
        <th><a href="../admin/sortingAdmin.html?sorting=date"><spring:message code="newses.date"/></a></th>
        <th><a href="../admin/sortingAdmin.html?sorting=title"><spring:message code="newses.title"/></a></th>
    </tr>
    <c:forEach items="${newsList}" var="news">
        <tr>
            <td width="15%">
                    ${news.date}
            </td>
            <td>
                <a href="../admin/newsEdit.html?news_id=${news.newsId}">${news.title}</a>
            </td>
            <td>
                <form>
                    <input type="hidden" name="news_id" value="${news.newsId}">
                    <button formaction="../admin/deleteNews.html"
                            style="width:110px"><spring:message code="admin.delete"/></button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<table width="100%"  cellspacing="0" cellpadding="10">
    <tr>

        <td height="80" bgcolor="#E8E8E8">
            <form>
                <button formaction="../admin/newsAdd.html"><spring:message code="admin.addNews"/></button>
            </form>
            <br>
            <form>
                <button formaction="../admin/userAdd.html"><spring:message code="admin.addUser"/></button>
            </form>
        </td>
    </tr>
</table>

