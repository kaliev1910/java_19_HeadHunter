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
                                </div>
                                <!-- Button to create resume -->
                                <a href="/resumes/create">
                                    <button type="button" class="btn btn-primary">Создать резюме</button>
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


                        <div class="row">
                            <div class="col-md-12 mb-4">
                                <h5 class="mb-3">Отклики</h5>

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

                        <!-- Applicant Resumes -->
                        <div class="row">
                            <div class="col-md-12 mb-4">
                                <h5 class="mb-3">Отклики</h5>
                                <!-- Place for listing responses -->
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
</@main.layout>
