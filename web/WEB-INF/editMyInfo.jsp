<%-- 
    Document   : editUserInfo
    Created on : Feb 10, 2022, 1:25:08 PM
    Author     : makso
--%>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="editMyInfo" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 style="padding-bottom: 50px" class="my-4 w-100 d-flex justify-content-center">Изменение личных данных</h2>

            <div class="form-group">
                <label for="editFirstName" class="form-label mt-4">Изменить имя</label>
                <input type="text" class="form-control" id="editFirstName" name="editFirstName" aria-describedby="" placeholder="" value="${firstName}">
                <small id="editFirstName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editLastName" class="form-label mt-4">Изменить фамилию</label>
                <input type="text" class="form-control" id="editLastName" name="editLastName" aria-describedby="" placeholder="" value="${lastName}">
                <small id="editLastName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editPhone" class="form-label mt-4">Изменить номер телефона</label>
                <input type="text" class="form-control" id="editPhone" name="editPhone" aria-describedby="" placeholder="" value="${phone}">
                <small id="editPhone" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editMoney" class="form-label mt-4">Изменить количество денег</label>
                <input type="text" class="form-control" id="editMoney" name="editMoney" aria-describedby="" placeholder="" value="${money}">
                <small id="editMoney" hidden class="form-text text-muted">Error</small>
            </div>
            <input class="btn btn-primary mt-5" type="submit" value="Изменить">
        </div>
    </form>
</div>