<%-- 
    Document   : gain
    Created on : Mar 27, 2022, 4:25:51 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <div class="mb-0" style="width: 70em;">
        <h2 style="padding-bottom: 15px; font-size: 36px" class="my-4 w-100 d-flex justify-content-center">Прибыль магазина</h2>
        <p class="d-flex justify-content-center">
            <button class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="true" aria-controls="collapseExample" style="width: 20em; margin-bottom: 15px">
              Показать заработок за всё время
            </button>
        </p>
        <div class="collapse justify-content-center" id="collapseExample">
            <div style="width: 70em; padding-left: 0px; text-align: center; padding-bottom: 15px">
                <p>${allGain}&nbsp;</p>
            </div>
        </div>
        <h3 class="my-2 w-100 d-flex justify-content-center">Прибыль магазина за определенный месяц</h2>
        <p class="d-flex justify-content-center">
            ${gainForAMonth}&nbsp;
        </p>
        <div class="d-flex justify-content-center" style="font-size: 18px; font-family: Molot">
            <div class="list-group d-flex align-content-start flex-wrap" style="width: 35em; text-align: center">
                <a href="gainForSeptember" class="list-group-item list-group-item-action list-group-item-warning">Сентябрь</a>
                <a href="gainForOctober" class="list-group-item list-group-item-action list-group-item-warning">Октябрь</a>
                <a href="gainForNovember" class="list-group-item list-group-item-action list-group-item-warning">Ноябрь</a>

                <a href="gainForMarch" class="list-group-item list-group-item-action list-group-item-info">Март</a>
                <a href="gainForApril" class="list-group-item list-group-item-action list-group-item-info">Апрель</a>
                <a href="gainForMay" class="list-group-item list-group-item-action list-group-item-info">Май</a>
            </div>
            <div class="list-group d-flex align-content-start flex-wrap" style="width: 35em; text-align: center">
                <a href="gainForDecember" class="list-group-item list-group-item-action list-group-item-primary">Декабрь</a>
                <a href="gainForJanuary" class="list-group-item list-group-item-action list-group-item-primary">Январь</a>
                <a href="gainForFebruary" class="list-group-item list-group-item-action list-group-item-primary">Февраль</a>

                <a href="gainForJune" class="list-group-item list-group-item-action list-group-item-danger">Июнь</a>
                <a href="gainForJuly" class="list-group-item list-group-item-action list-group-item-danger">Июль</a>
                <a href="gainForAugust" class="list-group-item list-group-item-action list-group-item-danger">Август</a>
            </div>   
        </div>
    </div>
</div>