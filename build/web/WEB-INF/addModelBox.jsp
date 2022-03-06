<%-- 
    Document   : addAccountBox
    Created on : Feb 10, 2022, 1:25:08 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="createModelBox" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 class="my-3 w-100 d-flex justify-content-center">Добавление обуви на сайт</h2>
            <a href="showUploadFile" class="my-1 w-100 d-flex justify-content-center">Загрузить изображение</a>
            <div class="form-group">
                <label for="modelName" class="form-label mt-4">Название</label>
                <input type="text" class="form-control" id="modelName" name="modelName" aria-describedby="" placeholder="" value="${modelName}">
                <small id="modelName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="modelSize" class="form-label mt-4">Размер</label>
                <input type="text" class="form-control" id="modelSize" name="modelSize" aria-describedby="" placeholder="" value="${modelSize}">
                <small id="modelSize" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="price" class="form-label mt-4">Цена</label>
                <input type="text" class="form-control" id="price" name="price" aria-describedby="" placeholder="" value="${price}">
                <small id="price" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="modelFirm" class="form-label mt-4">Бренд</label>
                <input type="text" class="form-control" id="modelFirm" name="modelFirm" aria-describedby="" placeholder="" value="${modelFirm}">
                <small id="modelFirm" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="model" class="form-label mt-4">Url изображения</label>
                <select class="form-select"  id="model" name="model">
                    <c:forEach var="begin" items="${models}">
                        <option value="${model.id}" <c:if test="${model.id eq model}">selected</c:if>>${model.description}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="url" class="form-label mt-4">URL страницы</label>
                <input type="text" class="form-control" id="url" name="url" aria-describedby="" placeholder="" value="${url}">
                <small id="url" hidden class="form-text text-muted">Error</small>
            </div>
            <input class="btn btn-primary mt-5" type="submit" value="Запомнить">
        </div>
    </form>
</div>
       
