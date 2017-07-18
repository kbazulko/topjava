<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://topjava.javawebinar.ru/functions" prefix="f" %>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Приемы пищи</h2>
<c:if test="${!empty meals}">
    <table class="tg">
        <tr>
            <th width="120">id</th>
            <th width="120">Дата/ время</th>
            <th width="180">Описание</th>
            <th width="100">Калории</th>
            <th colspan=2>Действие</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <tr style=${meal.exceed?  'color:red;': 'color:green;'}>
                <td>${meal.id}</td>
                <td>${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy hh:mm')}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Обновить</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<p><a href="meals?action=insert">Добавить прием пищи</a></p>
</body>
</html>