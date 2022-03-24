<%-- 
    Document   : deleteModel
    Created on : Mar 23, 2022, 10:33:32 AM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="deleteUser" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 style="padding-bottom: 50px" class="my-4 w-100 d-flex justify-content-center">Удаление пользователя</h2>
            <label for="TheUsers" style="text-align: center;" class="form-label mt-1">Зарегистрированные пользователи</label>
            <select class="form-select"  id="TheUsers" name="TheUsers">
                <c:forEach var="user" items="${users}">
                    <option style="text-align: center" value="${user.id}">User: ${user.firstName} ${user.lastName} // Phone: ${user.phone} // Money: ${user.money}</option>
                </c:forEach>
            </select>
            <input class="btn btn-primary mt-5" type="submit" value="Удалить">
        </div>
    </form>
</div>