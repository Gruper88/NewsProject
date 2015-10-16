<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title><tiles:insertAttribute name="title"/></title>

    <tiles:insertAttribute name="script"/>

    <style type="text/css">
        body {
            max-width: 900px; /* Максимальная ширина страницы в пикселах */
            margin: auto;
            background: #E8E8E8;
        }
    </style>
</head>
<body>
<div width=900px>

    <tiles:insertAttribute name="error"/>
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="menu"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="one_news"/>
    <tiles:insertAttribute name="newsAdd"/>
    <tiles:insertAttribute name="newsEdit"/>
    <tiles:insertAttribute name="userAdd"/>
    <tiles:insertAttribute name="pagination"/>
    <tiles:insertAttribute name="footer"/>

</div>
</body>
</html>