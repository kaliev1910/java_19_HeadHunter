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

        /* Скрыть название по умолчанию */
        #imageForm label[for="imageUpload"] {
            display: none;
        }

        /* Показать название при наведении на поле выбора файла */
        #imageUpload:hover + label[for="imageUpload"] {
            display: inline;
        }

        .custom-form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .custom-form label {
            font-weight: bold;
        }

        .custom-form input[type="file"] {
            margin-top: 5px;
        }

        .custom-form input[type="submit"] {
            margin-top: 10px;
        }

    </style>

    <#if user.accountType == "APPLICANT" >
        <section style="background-color: #eee;">
            <div class="container py-5">


                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">
                                <img src="/api/images/${user.id}" alt="avatar" class="rounded-circle img-fluid"
                                     style="width: 150px;">
                                <h5 class="my-3">${user.name} ${user.surname}</h5>
                                <p class="text-muted mb-1">Должность: Full Stack Developer</p>
                                <p class="text-muted mb-4">Bay Area, San Francisco, CA</p>
                                <div class="d-flex justify-content-center mb-2">
                                    <a href="/${user.email}/edit">
                                        <button type="button" data-mdb-button-init data-mdb-ripple-init
                                                class="btn btn-primary">
                                            Edit
                                        </button>
                                    </a>
                                    <button class="btn btn-success" data-toggle="modal" data-target="#modal-success">
                                        Change photo
                                    </button>

                                    <div id="modal-success" class="modal modal-message modal-success fade"
                                         style="display: none;"
                                         aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <i class="glyphicon glyphicon-check"></i>
                                                </div>
                                                <div class="modal-title">Change photo</div>
                                                <div class="modal-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <form id="imageForm" action="/upload" method="post"
                                                                      enctype="multipart/form-data" class="custom-form">
                                                                    <label for="file">Выберите изображение для загрузки
                                                                        (PNG, JPG, JPEG):</label><br>
                                                                    <input type="file" id="file" name="file"
                                                                           accept=".jpeg" class="form-control-file"><br>
                                                                    <input type="hidden" id="userId" name="userId"
                                                                           value="${user.id}">
                                                                    <input type="submit" value="Загрузить"
                                                                           class="btn btn-primary">
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-success" data-dismiss="modal">
                                                        OK
                                                    </button>
                                                </div>
                                            </div> <!-- / .modal-content -->
                                        </div> <!-- / .modal-dialog -->
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Full Name</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">${user.name} ${user.surname}</p>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <#list resumes as resume>
                                <div class="col-md-6">
                                    <div class="card mb-4 mb-md-0">
                                        <div class="card-body">
                                            <p class="mb-4">
                                                <i class="text-primary font-italic me-1">name:</i> ${resume.name}
                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    <#--        <h1 class="center mb-3">Личный кабинет соискателя</h1>-->
    <#--        <div class="row">-->
    <#--            <th scope="col">-->

    <#--                <div class="card" style="height: 16rem; width: 13rem;">-->
    <#--                    <div id="imageContainer">-->
    <#--                        <img id="userImage" src="/api/images/${user.id}" class="card-img-top" alt="">-->
    <#--                    </div>-->

    <#--                    <form id="imageForm" action="/upload" method="post" enctype="multipart/form-data">-->
    <#--                        <label for="file">Выберите изображение для загрузки (PNG, JPG, JPEG):</label><br>-->
    <#--                        <input type="file" id="file" name="file" accept=".jpeg"><br><br>-->

    <#--                        <label for="userId" hidden>Email</label>-->
    <#--                        <input type="number" id="userId" name="userId" class="form-control" value="${user.id}"-->
    <#--                               hidden>-->
    <#--                        <input type="submit" value="Загрузить">-->
    <#--                    </form>-->


    <#--                </div>-->
    <#--            </th>-->
    <#--            <th scope="col">-->
    <#--                <div class="row">-->
    <#--                    <div class="col-sm-6">-->
    <#--                        <div class="card profile border-0">-->
    <#--                            <div class="card-body">-->
    <#--                                <h5 class="card-title">${user.name} ${user.surname} </h5>-->
    <#--                                <#if user.age?has_content>-->
    <#--                                    <p class="card-text">Возраст: ${user.age}</p>-->
    <#--                                <#else>-->
    <#--                                    <p class="card-text">Возраст: не указан</p>-->
    <#--                                </#if>-->
    <#--                                <a href="/${user.email}/edit" class="btn btn-primary"-->
    <#--                                   methods="PUT">Редактировать</a>-->
    <#--                            </div>-->
    <#--                        </div>-->
    <#--                    </div>-->
    <#--                </div>-->
    <#--            </th>-->
    <#--            <th scope="col" class="mx-3">-->
    <#--                <div class="mx-3">-->
    <#--                    <a href="/resumes/create" class="btn btn-primary p-5">+<br>Создать<br>резюме</a>-->
    <#--                </div>-->
    <#--            </th>-->
    <#--            <th scope="col">-->
    <#--                <div class="mx-3">-->
    <#--                    <a href="#" class="btn btn-primary p-5"><br>Отклики<br><br></a>-->
    <#--                </div>-->
    <#--            </th>-->
    <#--        </div>-->
    <#--        <h3 class="mt-5">Резюме:</h3>-->
    <#--        <div class="row justify-content-around">-->
    <#--            <#list resume as resume>-->
    <#--                <div class="card col-3 m-1">-->
    <#--                    <div class="card-body">-->
    <#--                        <h5 class="card-title">Должность: ${resume.name}</h5>-->
    <#--                        <h6 class="card-title">Категория работы: ${resume.categoryId}</h6>-->
    <#--                    </div>-->
    <#--                    <a class="card-footer type">-->
    <#--                        <a href="#" class="card-link">Обновить</a>-->
    <#--                        <a href="/resume/${resume.id}" class="card-link">Редактировать</a>-->
    <#--                    </a>-->
    <#--                </div>-->

    <#--            </#list>-->
    <#--        </div>-->
    <#else>
        <h1 class="center mb-3">Личный кабинет работодателя</h1>
        <div class="row">
            <th scope="col mx-3">

                <div class="card" style="height: 16rem; width: 13rem;">
                    <div id="imageContainer">
                        <img id="userImage" src="/api/images/${user.id}" class="card-img-top" alt="">
                    </div>
                    <button id="imageUploadButton" class="btn btn-primary" form="imageForm">
                        <form id="imageForm" action="/upload" method="post" enctype="multipart/form-data">
                            <label for="file">Выберите изображение для загрузки (PNG, JPG, JPEG):</label><br>
                            <input type="file" id="file" name="file" accept=".jpeg"><br><br>

                            <label for="userId" hidden>Email</label>
                            <input type="number" id="userId" name="userId" class="form-control" value="${user.id}"
                                   hidden>
                            <input type="submit" value="Загрузить">
                        </form>
                    </button>

                </div>
            <th/>
            <th scope="col">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="card profile border-0">
                            <div class="card-body">
                                <h3 class="card-title">${user.name}</h3>
                                <a href="/${user.email}/edit" class="btn btn-primary">Редактировать</a>
                            </div>
                        </div>
                    </div>
                </div>
            </th>
            <th scope="col">
                <div class="mx-3">
                    <a href="/vacancy/create" class="btn btn-primary p-5">+<br>Создать<br>вакансию</a>
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