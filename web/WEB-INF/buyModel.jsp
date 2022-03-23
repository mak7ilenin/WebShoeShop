<%-- 
    Document   : buyModel
    Created on : Mar 6, 2022, 9:41:49 PM
    Author     : makso
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="buyModel" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 style="padding-top: 70px" class="my-4 w-100 d-flex justify-content-center">Покупка обуви</h2>
            <label for="buyModels" class="form-label mt-4">Доступные модели</label>
            <select class="form-select"  id="buyModels" name="buyModels">
                <c:forEach var="model" items="${models}">
                    <option style="text-align: center" value="${model.id}">Model: ${model.modelName} // Size: ${model.modelSize} // Brand: ${model.modelFirm} // Price: ${model.price}</option>
                </c:forEach>
            </select>
            <label for="theUsers" class="form-label mt-4">Зарегистрированные пользователи</label>
            <select class="form-select"  id="buyUsers" name="buyUsers">
                <c:forEach var="user" items="${users}">
                    <option style="text-align: center" value="${user.id}">${user.firstName} // Money: ${user.money}</option>
                </c:forEach>
            </select>
            <input class="btn btn-primary mt-5" type="submit" value="Купить">
        </div>
    </form>
</div>