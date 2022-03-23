<%-- 
    Document   : deleteModel
    Created on : Mar 23, 2022, 10:33:32 AM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="deleteModel" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 style="padding-bottom: 50px" class="my-4 w-100 d-flex justify-content-center">Удаление обуви</h2>
            <label for="theModels" style="text-align: center;" class="form-label mt-1">Доступные модели</label>
            <select class="form-select"  id="TheModels" name="TheModels">
                <c:forEach var="model" items="${models}">
                    <option style="text-align: center" value="${model.id}">Model: ${model.modelName} // Size: ${model.modelSize} // Brand: ${model.modelFirm} // Price: ${model.price}</option>
                </c:forEach>
            </select>
            <input class="btn btn-primary mt-5" type="submit" value="Удалить">
        </div>
    </form>
</div>