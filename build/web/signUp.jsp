<%-- 
    Document   : signUp
    Created on : Mar 3, 2022, 2:46:13 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<div style="margin: 60px">
    <h1 class="text-primary align-items-center" style="text-align: center">WebShoeShop</h1>
    <h2 class="my-5 w-100 d-flex justify-content-center">Регистрация</h2>
    <div class="w-100 d-flex justify-content-center">
        <form action="signUp" method="POST" >
          <div class="card border-0 mb-3" style="width: 20em;">
            <div class="form-group">
              <label for="firstName" class="form-label mt-4">Имя</label>
              <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="" placeholder="" value="${name}">
              <small id="firstName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
              <label for="lastName" class="form-label mt-4">Фамилия</label>
              <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="" placeholder="" value="${name}">
              <small id="lastName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
              <label for="phone" class="form-label mt-4">Номер телефона</label>
              <input type="text" class="form-control" id="phone" name="phone" aria-describedby="" placeholder="" value="${name}">
              <small id="phone" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="card border-0 mb-3" style="width: 20em;">
            <div class="form-group">
              <label for="login" class="form-label mt-4">Логин</label>
              <input type="text" class="form-control" id="login" name="login" aria-describedby="" placeholder="" value="${name}">
              <small id="login" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
              <label for="password" class="form-label mt-4">Пароль</label>
              <input type="text" class="form-control" id="password" name="password" aria-describedby="" placeholder="" value="${name}">
              <small id="password" hidden class="form-text text-muted">Error</small>
            </div>
            <input class="btn btn-primary mt-5" type="submit" value="Зарегистрироваться">
            </form>
          </div>
    </div>
</div>