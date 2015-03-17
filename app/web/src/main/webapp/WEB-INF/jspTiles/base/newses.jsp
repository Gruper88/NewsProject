<%@ page language="java" contentType="text/html; charset=UTF-8"
                 pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table bgcolor="#E8E8E8" width="100%">
    <tr>
        <th><a href="../user/sorting.html?sorting=date"><spring:message code="newses.date"/></a></th>
        <th><a href="../user/sorting.html?sorting=title"><spring:message code="newses.title"/></a></th>
        <th><a href="../user/sorting.html?sorting=description"><spring:message code="newses.description"/></a></th>
    </tr>
    <c:forEach items="${newsList}" var="news">
        <tr>
            <td width="15%">
                    ${news.date}
            </td>
            <td width="25%">
                <a href="../user/oneNews.html?news_id=${news.newsId}">${news.title}</a>
            </td>
            <td>
                    ${news.description}
            </td>
        </tr>
    </c:forEach>

</table>