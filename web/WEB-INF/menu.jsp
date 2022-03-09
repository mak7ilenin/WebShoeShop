<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>
    #dropdownMenuLink {
        background-color: #212529;
        border: none;
    }
    
    dropdownMenuLink {
        margin-bottom: 1px
    }
</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a style="font-size: 26px; font-weight: 700; margin-bottom: 4px;" class="navbar-brand" href="index">Магазин обуви</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <a style="font-size: 22px; color: white; font-weight: 400; margin-bottom: 3px" class="navbar-brand" href="buyModel">Купить обувь</a>
            
            <div class="dropdown">
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                  Добавить
                </a>

                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                  <li><a class="dropdown-item" href="addModel">Модель</a></li>
                  <li><a class="dropdown-item" href="addUser">Пользователя</a></li>
                </ul>
            </div>
            
            <div class="dropdown">
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    Редактировать
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                  <li><a class="dropdown-item" href="editModel">Модель</a></li>
                  <li><a class="dropdown-item" href="editUser">Пользователя</a></li>
                </ul>
            </div>

            </div>
            <ul class="navbar-nav  mb-2 mb-lg-0">
                <li></li>
                <c:if test="${authUser eq null}">
                    <li class="nav-item">
                      <a class="nav-link" href="showIndex">Войти</a>
                    </li>
                </c:if>
                <c:if test="${authUser ne null}">
                    <li class="nav-item">
                      <a class="nav-link" href="logout">Выйти</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<!--<nav class="navbar navbar-expand-lg navbar-light bg-dark">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Features</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Pricing</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="buyModel" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown link
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="editModel">Action</a>
          <a class="dropdown-item" href="editUser">Another action</a>
        </div>
      </li>
    </ul>
  </div>
</nav>-->