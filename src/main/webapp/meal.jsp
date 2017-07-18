<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Добавить прием пищи</title>
</head>
<body>

<form method="POST" action='meals' name="frmAddMeal">
    ID : <input type="text" readonly="readonly" name="mealId"
                     value="<c:out value="${meal.id}" />" /> <br />
    Описание : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Калории : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
    Дата/ время : <input
        type="datetime-local" name="dateTime"
        value="<c:out value="${meal.dateTime}" />"/> <br />
     <input
        type="submit" value="Применить" />
</form>
</body>
</html>