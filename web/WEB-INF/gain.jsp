<%-- 
    Document   : gain
    Created on : Mar 27, 2022, 4:25:51 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="gain" method="POST" >
        <div class="card border-4 mb-0" style="width: 100em;">
            <h2 style="padding-bottom: 15px;" class="my-4 w-100 d-flex justify-content-center">Заработок магазина</h2>
            <p class="d-flex justify-content-center">
                <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample" style="width: 20em;">
                  Показать заработок за всё время
                </a>
            </p>
            <div class="collapse d-flex" id="collapseExample" style="text-align: center;">
                <div class="card card-body" style="width: 10em; text-align: center;">
                    text...
                </div>
            </div>
            <h3 style="padding-bottom: 15px" class="my-4 w-100 d-flex justify-content-center">Заработок магазина за определенный месяц</h2>
            <div class="d-flex justify-content-center">
                <div class="list-group d-flex align-content-start flex-wrap" style="width: 40em">

                    <a href="#" class="list-group-item list-group-item-action list-group-item-warning">Сентябрь</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-warning">Октябрь</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-warning">Ноябрь</a>

                    <a href="#" class="list-group-item list-group-item-action list-group-item-primary">Декабрь</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-primary">Январь</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-primary">Февраль</a>
                </div>
                <div class="list-group d-flex align-content-start flex-wrap" style="width: 40em">
                    <a href="#" class="list-group-item list-group-item-action list-group-item-info">Март</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-info">Апрель</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-info">Май</a>

                    <a href="#" class="list-group-item list-group-item-action list-group-item-danger">Июнь</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-danger">Июль</a>
                    <a href="#" class="list-group-item list-group-item-action list-group-item-danger">Август</a>
                </div>              
            </div>
        </div>
    </form>
</div>