<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table width="100%" cellspacing="0" cellpadding="10">

    <tr bgcolor="#87CEEB">
        <c:forEach items="${categoryList}" var="categories">
            <td>
                <form>
                    <input type="hidden" name="category" value="${categories.category}">
                    <button formaction="../admin/bycategotyAdmin.html"
                            style="width:110px">${categories.category}</button>
                </form>
            </td>
        </c:forEach>
    </tr>

</table>