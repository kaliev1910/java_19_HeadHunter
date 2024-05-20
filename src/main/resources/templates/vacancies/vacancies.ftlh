<#import "../layout.ftlh" as main>
<#import "spring.ftl" as spring>
<#import "../pager.ftlh" as p>
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

    <div class="dropdown mb-3">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Фильтровать по категориям
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="${url}?filter=1">IT</a>
            <a class="dropdown-item" href="${url}?filter=2">Programming</a>
            <a class="dropdown-item" href="${url}?filter=3">Web Development</a>
            <a class="dropdown-item" href="${url}?filter=4">Mobile Development</a>
            <a class="dropdown-item" href="${url}?filter=5">Finance</a>
            <a class="dropdown-item" href="${url}?filter=6">Accounting</a>
            <a class="dropdown-item" href="${url}?filter=7">Investment Banking</a>
        </div>
    </div>
    <@p.pager url vacancies />

    <div class="container mt-5 mb-3">
        <div class="row">
            <#list vacancies.content as vacancy>
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
    <@p.pager url vacancies />

</@main.layout>
