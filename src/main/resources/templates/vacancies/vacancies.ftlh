<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        form {
            width: 50%;
            max-width: 500px;
            min-width: 200px;
            margin: 0 auto;
        }

        fieldset {
            border: 0;
            padding: 0;
            margin: 0;
            margin-top: 20px;
        }

        fieldset input {
            display: block;
            box-sizing: border-box;
            width: 100%;
            padding: 10px;
        }

        fieldset label {
            display: none;
        }

        col {
            margin-left: 20px;
            margin-right: 20px;
        }

        .card-name {
            height: 70px;
        }
    </style>
    <h1 class="text-center mb-3">Вакансии</h1>
    <div class="text-center">
        <a href="/hh/vacancies?page=0">1</a>
        <a href="/hh/vacancies?page=1">2</a>
        <a href="/hh/vacancies?page=2">3</a>
    </div>
    <div class="dropdown mb-3">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Фильтровать по категориям
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="/hh/vacancies/sort/1">IT</a>
            <a class="dropdown-item" href="/hh/vacancies/sort/2">Юриспруденция</a>
            <a class="dropdown-item" href="/hh/vacancies/sort/3">Менеджмент</a>
            <a class="dropdown-item" href="/hh/vacancies/sort/4">Продажи</a>
            <a class="dropdown-item" href="/hh/vacancies/sort/5">Авто</a>
            <a class="dropdown-item" href="/hh/vacancies/sort/6">Маркетинг</a>
        </div>
    </div>

    <div class="row justify-content-around">
        <#list vacancies as vacancy>
            <div class="card col-3 m-1 mb-5">
                <a href="/hh/vacancy/${vacancy.id}">
                    <h5 class="card-header card-name">${vacancy.name}</h5>
                </a>
                <h6 class="card-header">${vacancy.categoryId}</h6>
                <div class="card-body">
                    <h5 class="card-title">${vacancy.salary} KGS</h5>
                    <p class="card-title">${vacancy.description}</p>
                </div>
                <div class="card-footer">
                    <p class="card-text"><a href="/hh/guest/${vacancy.authorEmail}">${vacancy.authorEmail}</a></p>
                    <p class="card-text">${vacancy.createdDate}</p>
                    <a href="/hh/vacancy/${vacancy.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </div>
        </#list>
    </div>

    <a href="/employer/vacancy/add" class="btn btn-success">Создать новую вакансию</a>
</@main.layout>
