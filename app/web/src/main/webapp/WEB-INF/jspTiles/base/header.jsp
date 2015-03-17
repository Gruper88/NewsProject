<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<spring:url var="logout" value="/j_spring_security_logout"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table width="100%"  cellspacing="0" cellpadding="10">
    <tr>
        <td height="100" bgcolor="#ADD8E6">
            <br>
            <div style="float: right;vertical-align:top">
                <a href="../user/newses.html?locale=ru" style="padding: 5px">RU</a>
                <a href="../user/newses.html?locale=en" style="padding: 0px">EN</a>

            </div>
            <h1 style=" margin-bottom: -15px;margin-top: -5px"><spring:message code="header.news"/></h1>
            <br>
            <div class="privetINFO"  style="float: right;vertical-align:bottom">
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                    <a class="logout" href="${logout}" id="logOutButton"><spring:message code="admin.logout"/></a>
                </sec:authorize>
            </div>
            <br>
            <br>
            <div style="float: right;vertical-align:middle"><sec:authorize ifAnyGranted="ROLE_ADMIN">
                <span>Hello: <sec:authentication property="principal.username"/> </span>
            </sec:authorize></div>
        </td>
    </tr>
</table>