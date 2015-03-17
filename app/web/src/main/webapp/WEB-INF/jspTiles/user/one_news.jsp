<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table width="100%" cellspacing="0" cellpadding="10">
    <tr>
        <td width="600" bgcolor="#E8E8E8">
            <h3>${currentNews.title}</h3>

            <p>${currentNews.newstext}</p>
        </td>
    </tr>
    <tr>
        <td>
            <p>Author:</p>

            <p>${autorUserDetails.name} ${autorUserDetails.surname}</p>
        </td>
    </tr>
</table>