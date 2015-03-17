<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ввод логина и пароля</title>
</head>
<body>

    <table bgcolor="#eeeeee" width="100%">

    <tr>
    <td>

    <p><h1>${error}</h1></p>

    </td>
    </tr>

    <tr>
    <td>

	<form action="/" method="post">
		<FIELDSET>
			<LEGEND>Введите e-mail и пароль</LEGEND>
			<p></p><input type="text" id="name_user" name="name_user"></p><br>
            <p><input type="text" id="password_user" name="password_user"></p><br>
			<input type="hidden" name="operation" value="check">
			<input type="submit" id="otpr" name="otpr" value="Отправить">
		</FIELDSET>
	</form>

    <p><a href="/">Вернуться</a></p>

    </td>
    </tr>

    </table>

</body>
</html>