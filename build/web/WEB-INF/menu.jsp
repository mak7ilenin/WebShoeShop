
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a style="font-size: 22px" class="navbar-brand" href="index.jsp">Магазин обуви</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="addModelBox">Добавить обувь</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="listModels">Список обуви</a>
                </li>   
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="buyModel">Купить обувь</a>
                </li>
            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Поиск" aria-label="Поиск">
                <button class="btn btn-outline-success" style='color: white' type="submit">Найти</button>
            </form>
        </div>
    </div>
</nav>
