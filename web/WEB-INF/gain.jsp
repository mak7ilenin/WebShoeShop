<%-- 
    Document   : gain
    Created on : Mar 27, 2022, 4:25:51 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="gain" method="POST" >
        <div class="card border-4 mb-0" style="width: 30em;">
            <h2 style="padding-bottom: 30px" class="my-4 w-100 d-flex justify-content-center">Запросить обувь у поставщика</h2>
            <div class="form-group">
                <label for="modelName" class="form-label mt-4">Название</label>
                <input type="text" class="form-control" id="modelName" name="modelName" aria-describedby="" placeholder="" value="${modelName}">
                <small id="modelName" hidden class="form-text text-muted">Error</small>
            </div>
            
            <input class="btn btn-primary mt-5" type="submit" value="Добавить">
        </div>
    </form>
</div>