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

        body {
            background-color: #eee
        }

        .card {
            border: none;
            border-radius: 10px
        }

        .c-details span {
            font-weight: 300;
            font-size: 13px
        }

        .icon {
            width: 50px;
            height: 50px;
            background-color: #eee;
            border-radius: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 39px
        }

        .badge span {
            background-color: #fffbec;
            width: 60px;
            height: 25px;
            padding-bottom: 3px;
            border-radius: 5px;
            display: flex;
            color: #fed85d;
            justify-content: center;
            align-items: center
        }

        .progress {
            height: 10px;
            border-radius: 10px
        }

        .progress div {
            background-color: red
        }

        .text1 {
            font-size: 14px;
            font-weight: 600
        }

        .text2 {
            color: #a5aec0
        }
    </style>
    <h1 class="text-center mb-3">Вакансии</h1>
    <div class="text-center">
        <a href="/vacancies?page=1">1</a>
        <a href="/vacancies?page=2">2</a>
        <a href="/vacancies?page=3">3</a>
    </div>
    <div class="dropdown mb-3">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Фильтровать по категориям
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="/vacancies/sort/1">IT</a>
            <a class="dropdown-item" href="/vacancies/sort/2">Юриспруденция</a>
            <a class="dropdown-item" href="/vacancies/sort/3">Менеджмент</a>
            <a class="dropdown-item" href="/vacancies/sort/4">Продажи</a>
            <a class="dropdown-item" href="/vacancies/sort/5">Авто</a>
            <a class="dropdown-item" href="/vacancies/sort/6">Маркетинг</a>
        </div>
    </div>
<#--    <section class="py-5">-->

<#--        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">-->
<#--            <#list vacancies as vacancy>-->
<#--                <div class="col mb-5">-->

<#--                    <div class="card h-100">-->
<#--                        <!-- Product image&ndash;&gt;-->

<#--                        <a href="/vacancy/${vacancy.id}">-->
<#--                            <h5 class="card-header card-name">${vacancy.name}</h5>-->
<#--                        </a>-->
<#--                        <h6 class="card-header">${vacancy.categoryId}</h6>-->
<#--                        <div class="card-body">-->
<#--                            <h5 class="card-title">${vacancy.salary} KGS</h5>-->
<#--                            <p class="card-title">${vacancy.description}</p>-->
<#--                        </div>-->
<#--                        <div class="card-footer">-->
<#--                            <p class="card-text"><a href="/hh/guest/${vacancy.authorEmail}">${vacancy.authorEmail}</a>-->
<#--                            </p>-->
<#--                            <p class="card-text">${vacancy.createdDate}</p>-->
<#--                            <a href="/vacancy/${vacancy.id}" class="btn btn-primary">Подробнее</a>-->
<#--                        </div>-->
<#--                        <!-- Product actions&ndash;&gt;-->
<#--                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">-->
<#--                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->

<#--                </div>-->
<#--            </#list>-->

<#--        </div>-->

<#--    </section>-->


    <div class="container mt-5 mb-3">
        <div class="row">
            <#list vacancies as vacancy>
                <div class="col-md-4">
                    <div class="card p-4 mb-2">
                        <div class="d-flex justify-content-left">
                            <div class="d-flex flex-row align-items-center">
                                <div class="ms-2 c-details">
                                    <h6 class="mb-0">${vacancy.authorEmail}</h6>
                                    <span>Last Update ${vacancy.updateTime}</span>
                                </div>
                            </div>
                            <div class="badge"><span>Design</span></div>
                        </div>
                        <div class="mt-5">
                            <a   href="/vacancy/${vacancy.id}">
                                <h5 class="heading">${vacancy.name}</h5>
                            </a>
                            <h5>${vacancy.description}</h5>
                            <div class="mt-5">
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" style="width: 50%" aria-valuenow="50"
                                         aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <div class="mt-3"><span class="text1">32 Applied <span
                                                class="text2">of 50 capacity</span></span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

</@main.layout>
