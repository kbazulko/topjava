<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
    <script>
        function clearFilter()
        {
            var childNodes = document.getElementById("filter").getElementsByTagName('input');
            for(i = 0; i < childNodes.length; i++) {
                childNodes[i].value = '';
            }
        }
    </script>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meal list</h2>
    <form id="filter" method="post" action="meals">
        <table cellpadding="4">
            <tr>
                <td>From date</td>
                <td><input type="date" name="fromDate" value=${fromDate}></td>
                <td>From time</td>
                <td><input type="time" name="fromTime" value=${fromTime}></td>
            </tr>
            <tr>
                <td>To date</td>
                <td><input type="date" name="toDate" value=${toDate}></td>
                <td>To time</td>
                <td><input type="time" name="toTime" value=${toTime}></td>
            </tr>
        </table>
        <button type="submit">Filter</button>
        <button onclick="clearFilter()">Clear filter</button>
    </form>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>