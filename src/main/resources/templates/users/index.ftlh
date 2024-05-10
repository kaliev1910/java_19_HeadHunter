<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        /* General styling */
        form {
            width: 50%;
            max-width: 500px;
            min-width: 200px;
            margin: 0 auto;
        }

        /* Form fields */
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

        /* Column styling */
        col {
            margin-left: 20px;
            margin-right: 20px;
        }

        /* Profile styling */
        .profile {
            width: 500px;
        }

        .user-photo {
            width: 250px;
            height: auto;
        }

        /* Custom form styling */
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
        <h1>Личный кабинет соискателя</h1>
        <section style="background-color: #eee;">
            <div class="container py-5">
                <div class="row">
                    <!-- Applicant Profile Card -->
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">
                                <!-- Applicant Photo and Info -->
                                <img src="/api/images/${user.id}" alt="avatar" class="rounded-circle img-fluid"
                                     style="width: 150px;">
                                <h5 class="my-3">${user.name} ${user.surname}</h5>
                                <p class="text-muted mb-1">Должность: Full Stack Developer</p>
                                <p class="text-muted mb-4">Bay Area, San Francisco, CA</p>

                                <!-- Edit and Change Photo Buttons -->
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
                                <!-- Button to create resume -->
                                <a href="/resumes/create">
                                    <button type="button" class="btn btn-primary">Создать резюме</button>
                                </a>
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
                                                                <#if _csrf??>
                                                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                                           value="${(_csrf.token)!'csrf-token'}"/>
                                                                </#if>

                                                                <label for="file">Выберите изображение для загрузки
                                                                    (PNG, JPG, JPEG):</label><br>
                                                                <input type="file" id="file" name="file"
                                                                       accept=".jpeg" class="form-control-file"><br>
                                                                <input type="hidden" id="userId" name="userId"
                                                                       value="${user.id}">
                                                                <button type="submit"
                                                                        class="btn btn-primary">Загрузить
                                                                </button>
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

                    <!-- Applicant Details -->
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
                            <div class="col-md-12 mb-4">
                                <h5 class="mb-3">Отклики</h5>

                                <#if responses?has_content>


                                    <#list responses as response>
                                        <div class="col-md-6">
                                            <div class="card mb-4">
                                                <div class="card-body">
                                                    <p class="mb-4">
                                                        <i class="font-italic me-1">Response name: </i> ${response.id}
                                                    </p>


                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <h5 class="mb-3">Мои резюме</h5>
                            </div>
                            <#list resumes as resume>
                                <div class="col-md-6">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <p class="mb-4">
                                                <i class="font-italic me-1">Resume name: </i> ${resume.name}
                                            </p>
                                            <p class="mb-4">
                                                <i class="font-italic me-1">Updated time: </i> ${resume.updatedTime}
                                            </p>
                                            <div class="buttons">
                                                <a href="/resume/${resume.id}">
                                                    <button type="button" class="btn btn-primary">Подробнее</button>
                                                </a>
                                                <a href="/resumes/${resume.id}/edit">
                                                    <button type="button" class="btn btn-primary">Редактировать</button>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    <#else>
        <h1>Личный кабинет соискателя</h1>
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

                                <!-- Edit and Change Photo Buttons -->
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
                                </div>
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
                                                                <#if _csrf??>
                                                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                                           value="${(_csrf.token)!'csrf-token'}"/>
                                                                </#if>

                                                                <label for="file">Выберите изображение для загрузки
                                                                    (PNG, JPG, JPEG):</label><br>
                                                                <input type="file" id="file" name="file"
                                                                       accept=".jpeg" class="form-control-file"><br>
                                                                <input type="hidden" id="userId" name="userId"
                                                                       value="${user.id}">
                                                                <button type="submit"
                                                                        class="btn btn-primary">Загрузить
                                                                </button>
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
                                <!-- Button to create resume -->
                                <a href="/vacancy/create">
                                    <button type="button" class="btn btn-primary">Создать вакансию</button>
                                </a>
                            </div>
                        </div>
                    </div>

                    <!-- Applicant Details -->
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
                        <#if responses?has_content>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModal">
                                Отклики
                            </button>
                        </#if>
                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="row">
                                        <#if responses?has_content>
                                            <#list responses as response>
                                                <div class="card bg-light mb-3" style="max-width: 18rem;">
                                                    <div class="card-header"></div>
                                                    <div class="card-body">
                                                        <h5 class="card-title">Light card title</h5>
                                                        <p class="card-text">Some quick example text to build on the
                                                            card
                                                            title
                                                            and make up the bulk of the card's content.</p>
                                                    </div>
                                                </div>
                                            </#list>
                                        </#if>

                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-12">
                                <h5 class="mb-3">Мои вакансии</h5>
                            </div>
                            <#list vacancies as vacancy>
                                <div class="col-md-6">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <p class="mb-4">
                                                <i class="font-italic me-1">Resume name: </i> ${vacancy.name}
                                            </p>
                                            <p class="mb-4">
                                                <i class="font-italic me-1">Updated time: </i> ${vacancy.updateTime}
                                            </p>
                                            <div class="buttons">
                                                <a href="/vacancy/${vacancy.id}">
                                                    <button type="button" class="btn btn-primary">Подробнее</button>
                                                </a>
                                                <a href="/vacancy/${vacancy.id}/edit">
                                                    <button type="button" class="btn btn-primary">Редактировать</button>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </#if>
    <script>
        function getResumeById(id) {
            // Замени URL на свой серверный эндпоинт
            const url = `/api/resume/resume/`;
            url.concat(id)
            // Отправляем GET запрос на сервер
            fetch(url)
                .then(response => {
                    // Проверяем статус ответа
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    // Если статус ответа OK, парсим JSON
                    return response.json();
                })
                .then(resume => {
                    // Выводим полученный объект в консоль (можно изменить на свою логику обработки)
                    console.log(resume);
                })
                .catch(error => {
                    // Выводим ошибку в консоль
                    console.error('There was a problem with the fetch operation:', error);
                });
        }

    </script>
</@main.layout>
