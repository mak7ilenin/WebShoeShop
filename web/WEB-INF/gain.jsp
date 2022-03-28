<%-- 
    Document   : gain
    Created on : Mar 27, 2022, 4:25:51 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="allGain" method="POST" >
        <div class="card border-4 mb-0" style="width: 70em;">
            <h2 style="padding-bottom: 15px;" class="my-4 w-100 d-flex justify-content-center">Заработок магазина</h2>
            <p class="d-flex justify-content-center">
                <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample" style="width: 20em;">
                  Показать заработок за всё время
                </a>
            </p>
            <div class="collapse d-flex" id="collapseExample">
                <div class="card card-body" style="width: 10em; text-align: center;">
                    <p>${allGain}&nbsp;</p>
                </div>
            </div>
    </form>
   <form action="gainForAMonth" method="POST">
            <h3 style="padding-bottom: 15px" class="my-4 w-100 d-flex justify-content-center">Заработок магазина за определенный месяц</h2>
            <div class="d-flex justify-content-center">
                <div class="list-group d-flex align-content-start flex-wrap" style="width: 35em; text-align: center">
                    <select>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-warning" value="september">Сентябрь</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-warning" value="october">Октябрь</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-warning" value="november">Ноябрь</a>

                        <a href="#" class="list-group-item list-group-item-action list-group-item-info" value="march">Март</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-info" value="april">Апрель</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-info" value="may">Май</a>
                </div>
                <div class="list-group d-flex align-content-start flex-wrap" style="width: 35em; text-align: center">
                        <a href="#" class="list-group-item list-group-item-action list-group-item-primary" value="december">Декабрь</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-primary" value="january">Январь</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-primary" value="february">Февраль</a>

                        <a href="#" class="list-group-item list-group-item-action list-group-item-danger" value="june">Июнь</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-danger" value="july">Июль</a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-danger" value="august">Август</a>
                    </select>
                </div>   
            </div>
            <input class="btn btn-primary mt-5" type="submit" value="Посмотреть">   
        </div>
    </form>
</div>