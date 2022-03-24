<%-- 
    Document   : editUserLogin
    Created on : Mar 12, 2022, 2:37:17 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <div class="w-100 d-flex justify-content-center">
        <form action="editMyLogin" method="POST" >
            <div class="card border-0 mb-3" style="width: 40em;">
                <h2 style="padding-bottom: 50px" class="my-4 w-100 d-flex justify-content-center">Изменение данных входа</h2>
                <div class="form-group">
                    <label for="editLogin" class="form-label mt-4">Изменить логин</label>
                    <input type="text" class="form-control" id="editLogin" name="editLogin" aria-describedby="" placeholder="" value="${login}">
                    <small id="editLogin" hidden class="form-text text-muted">Error</small>
                </div>
                <div class="form-group">
                    <label for="editPassword1" class="form-label mt-4">Изменить пароль</label>
                    <input type="text" class="form-control" id="editPassword1" name="editPassword1" aria-describedby="" placeholder="" value="">
                    <small id="editPassword1" hidden class="form-text text-muted">Error</small>
                </div>
                <div class="form-group">
                    <label for="editPassword2" class="form-label mt-4">Подтвердите пароль</label>
                    <input type="text" class="form-control" id="editPassword2" name="editPassword2" aria-describedby="" placeholder="" value="">
                    <small id="editPassword2" hidden class="form-text text-muted">Error</small>
                </div>
                <input class="btn btn-primary mt-5" type="submit" value="Изменить">   
            </div>
        </form>
    </div>
</html>
