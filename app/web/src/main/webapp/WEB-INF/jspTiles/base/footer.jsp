<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<table width="100%" cellspacing="0" cellpadding="10">
    <tr>
        <td height="100" bgcolor="#ADD8E6">
            <form>
                <button formaction="../user/newses.html"><spring:message code="footer.showAllNews"/></button>
            </form>
            <br>

            <form>
                <button formaction="../admin/adminCommand.html"><spring:message code="footer.adminPanel"/></button>
            </form>
        </td>
    </tr>
</table>