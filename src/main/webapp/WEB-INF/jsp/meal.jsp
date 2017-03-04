<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="meal.title"/></title>
    <link rel="stylesheet" href="resources/css/style.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>
    <script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <%--<h2><a href="${pageContext.request.contextPath}/"><fmt:message key="app.home"/></a></h2>--%>
    <%--<h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>--%>
    <%--<hr>--%>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <c:set var="create"><fmt:message key="meal.create"/></c:set>
    <c:set var="edit"><fmt:message key="meal.edit"/></c:set>
    <h2>${meal.id == null ? create : edit}</h2>
    <hr>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt><fmt:message key="meal.dateTime"/></dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="meal.description"/></dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="meal.calories"/></dt>
            <dd><input type="number" value="${meal.calories}" name="calories"></dd>
        </dl>
        <button type="submit"><fmt:message key="meal.buttonSave"/></button>
        <button onclick="window.history.back()"><fmt:message key="meal.buttonCancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
