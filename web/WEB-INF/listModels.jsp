<%-- 
    Document   : listModels
    Created on : Mar 25, 2022, 11:28:00 PM
    Author     : makso
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0 mb-3" style="width: 40em;">
        <h2 style="padding-top: 150px" class="my-4 w-100 d-flex justify-content-center">Список моделей</h2>
        <label for="lookModel" class="form-label mt-4">Доступные модели</label>
        <select class="form-select"  id="lookModel" name="lookModel">
            <c:forEach var="model" items="${models}">
                <option style="text-align: center" value="${model.id}">Model: ${model.modelName} // Size: ${model.modelSize} // Brand: ${model.modelFirm} // Price: ${model.price}</option>
            </c:forEach>
        </select>
        <a href="showIndex" class="btn btn-primary mt-5">Войти</a>
    </div>
</div>