<%-- 
    Document   : editUserLogin
    Created on : Mar 11, 2022, 9:57:59 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="editUserLogin" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 style="padding-bottom: 50px" class="my-4 w-100 d-flex justify-content-center">Изменение данных входа пользователя</h2>
            <label for="theEditUsers" style="text-align: center;" class="form-label mt-1">Зарегистрированные пользователи</label>
            <select class="form-select"  id="theEditUsers" name="theEditUsers">
                <c:forEach var="user" items="${users}">
                    <option style="text-align: center" value="${user.id}">${user.firstName}</option>
                </c:forEach>
            </select>
            <div class="form-group">
                <label for="editUserLog" class="form-label mt-4">Изменить логин</label>
                <input type="text" class="form-control" id="editUserLog" name="editUserLog" aria-describedby="" placeholder="" value="${login}">
                <small id="editUserLog" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editUserPassword1" class="form-label mt-4">Изменить пароль</label>
                <input type="text" class="form-control" id="editUserPassword1" name="editUserPassword1" aria-describedby="" placeholder="" value="">
                <small id="editUserPassword1" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editUserPassword2" class="form-label mt-4">Подтвердите пароль</label>
                <input type="text" class="form-control" id="editUserPassword2" name="editUserPassword2" aria-describedby="" placeholder="" value="">
                <small id="editUserPassword2" hidden class="form-text text-muted">Error</small>
            </div>
            <input class="btn btn-primary mt-5" type="submit" value="Изменить">   
        </div>
    </form>
</div>