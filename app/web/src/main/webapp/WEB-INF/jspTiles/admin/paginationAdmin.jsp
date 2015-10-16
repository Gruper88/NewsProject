<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table width="100%" cellspacing="0" cellpadding="10">
    <tr bgcolor="#87CEEB">
        <c:forEach items="${paginationList}" var="paginationList">
            <th>
                <a href="../admin/paginationAdmin.html?news_start=${paginationList[0]}&news_count=5">${paginationList[0]+1}-${paginationList[1]}</a>
            </th>
        </c:forEach>
    </tr>
</table>