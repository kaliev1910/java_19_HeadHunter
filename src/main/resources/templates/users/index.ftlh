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

        .profile {
            width: 500px;
        }

        .user-photo {
            width: 250px;
            height: auto;
        }

    </style>
    <#if user.accountType == "APPLICANT" >
        <h1 class="center mb-3">Личный кабинет соискателя</h1>
        <div class="row">
            <th scope="col">
                <#if user.avatar?has_content>
                    <div class="card" style="height: 16rem ;width: 13rem;">
                        <img src="..." class="card-img-top" alt="...">
                    </div>
                <#else >
                    <div class="card bg-secondary" style="height: 16rem ;width: 13rem;">
                        <img src="..." class="card-img-top" alt="...">
                    </div>
                </#if>
            </th>
            <th scope="col">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="card profile border-0">
                            <div class="card-body">
                                <h5 class="card-title">${user.name} ${user.surname} </h5>
                                <#if user.age?has_content>
                                    <p class="card-text">Возраст: ${user.age}</p>
                                <#else>
                                    <p class="card-text">Возраст: не указан</p>
                                </#if>
                                <a href="/hh/redact/${user.email}" class="btn btn-primary"
                                   methods="PUT">Редактировать</a>
                            </div>
                        </div>
                    </div>
                </div>
            </th>
            <th scope="col" class="mx-3">
                <div class="mx-3">
                    <a href="/create" class="btn btn-primary p-5">+<br>Создать<br>резюме</a>
                </div>
            </th>
            <th scope="col">
                <div class="mx-3">
                    <a href="#" class="btn btn-primary p-5"><br>Отклики<br><br></a>
                </div>
            </th>
        </div>
        <h3 class="mt-5">Резюме:</h3>
        <div class="row justify-content-around">
            <#list resume as resume>
                <div class="card col-3 m-1">
                    <div class="card-body">
                        <h5 class="card-title">Должность: ${resume.name}</h5>
                        <h6 class="card-title">Категория работы: ${resume.categoryId}</h6>
                    </div>
                    <a class="card-footer type">
                        <a href="#" class="card-link">Обновить</a>
                        <a href="/resume/${resume.id}" class="card-link">Редактировать</a>
                    </a>
                </div>

            </#list>
        </div>
    <#else>
        <h1 class="center mb-3">Личный кабинет работодателя</h1>
        <div class="row">
            <th scope="col mx-3">
                <#if user.avatar?has_content>
                    <div class="card" style="height: 16rem ;width: 13rem;">
                        <img src="..." class="card-img-top" alt="...">
                    </div>
                <#else >
                    <div class="card bg-secondary" style="height: 16rem ;width: 13rem;">
                        <img src="..." class="card-img-top" alt="...">
                    </div>
                </#if>
            <th/>
            <th scope="col">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="card profile border-0">
                            <div class="card-body">
                                <h3 class="card-title">${user.name}</h3>
                                <a href="/user/${user.email}" class="btn btn-primary">Редактировать</a>
                            </div>
                        </div>
                    </div>
                </div>
            </th>
            <th scope="col">
                <div class="mx-3">
                    <a href="/vacancies/create" class="btn btn-primary p-5">+<br>Создать<br>вакансию</a>
                </div>
            </th>
            <th scope="col">
                <div class="mx-3">
                    <a href="#" class="btn btn-primary p-5"><br>Отклики<br><br></a>
                </div>
            </th>


        </div>
        <h3 class="mt-5">Вакансии:</h3>


        <div class="row justify-content-around">
            <#list vacancies as vacancy>
                <div class="row">
                    <th scope="col">
                        <div class="card  mx-3 mb-3" style="width: 13rem;">
                            <div class="card-body">
                                <h5 class="card-title">${vacancy.name}</h5>
                                <h6 class="card-title">Категория работы: ${vacancy.categoryId}</h6>
                            </div>
                            <a class="card-footer type">
                                <a href="#" class="card-link">Обновить</a>
                                <a href="/vacancy/${vacancy.id}" class="card-link">Редактировать</a>
                            </a>
                        </div>
                    </th>
                </div>
            </#list>
        </div>


    </#if>



</@main.layout>