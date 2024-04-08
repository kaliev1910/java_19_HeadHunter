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

    <div class="dropdown mb-3">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                <a href="/applicant/vacancy/${vacancy.id}"><h5 class="card-header card-name">${vacancy.jobTitle}</h5>
                </a>
                <h6 class="card-header">${vacancy.category}</h6>
                <div class="card-body">
                    <h5 class="card-title">${vacancy.salary} KGS</h5>
                    <p class="card-title">${vacancy.descriptionVacancy}</p>
                </div>
                <div class="card-footer">
                    <p class="card-text"><a href="/hh/guest/${vacancy.userEmail}">${vacancy.userEmail}</a></p>
                    <p class="card-text">${vacancy.date}</p>
                    <a href="/applicant/vacancy/${vacancy.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </div>
        </#list>
    </div>


</@main.layout>