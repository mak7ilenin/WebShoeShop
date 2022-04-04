<%-- 
    Document   : editUserInfo
    Created on : Feb 10, 2022, 1:25:08 PM
    Author     : makso
--%>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0 mb-3" style="width: 40em;">
        <h2 style="padding-bottom: 30px" class="my-4 w-100 d-flex justify-content-center">Изменение личных данных пользователя</h2>
        <label for="theEditUsers" style="text-align: center;" class="form-label mt-1">Зарегистрированные пользователи</label>
        <form action="chooseUserToEdit" method="POST">  
            <select class="form-select"  id="theEditUsers" name="theEditUsers">
                <c:forEach var="user" items="${users}">
                    <option style="text-align: center" value="${user.id}">${user.firstName} ${user.lastName}</option>
                </c:forEach>
            </select>
            <input class="btn btn-secondary mt-3 w-100" type="submit" value="Выбрать">
        </form>

        <form action="editUserInfo" method="POST">
            <div class="form-group">
                <label for="editUserFirstName" class="form-label mt-4">Изменить имя</label>
                <input type="text" class="form-control" id="editUserFirstName" name="editUserFirstName" aria-describedby="" placeholder="" value="${firstName}">
                <small id="editUserFirstName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editUserLastName" class="form-label mt-4">Изменить фамилию</label>
                <input type="text" class="form-control" id="editUserLastName" name="editUserLastName" aria-describedby="" placeholder="" value="${lastName}">
                <small id="editUserLastName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editUserPhone" class="form-label mt-4">Изменить номер телефона</label>
                <input type="text" class="form-control" id="editUserPhone" name="editUserPhone" aria-describedby="" placeholder="" value="${phone}">
                <small id="editUserPhone" hidden class="form-text text-muted">Error</small>
            </div>
            <input class="btn btn-primary mt-5 w-100" type="submit" value="Изменить">
        </form>
    </div>
</div>