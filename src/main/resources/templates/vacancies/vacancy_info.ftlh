<#import "../layout.ftlh" as main>
<@main.layout>


    <div class="card">
        <div class="card-header bg-primary text-white">
            <h5 class="card-title">Должность: ${vacancy.name}</h5>
            <h6 class="card-title">Категория работы: ${vacancy.categoryId}</h6>
        </div>
        <div class="card-body">
            <p class="card-text">Зарплата: ${vacancy.salary} KGZ</p>
            <p class="card-text">Описание: ${vacancy.description}</p>
            <#if contacts?has_content>
                <h6 class="card-title">Контактные детали: </h6>
                <div class="row justify-content-start">
                    <#list contacts as contact>
                        <#if contact.id?has_content>
                            <div class="col-auto">
                                <p class="card-text">${contact.contactValue} <i class="${contact.typeId}"></i></p>
                            </div>
                        </#if>
                    </#list>
                </div>
            <#else >
                <h6 class="card-title">Контактные детали отсутствуют </h6>
            </#if>
        </div>
    </div>

    <a href="/vacancies?page=0" class="btn btn-primary mt-3 ">Перейти к списку вакансий</a>

    <#assign
    known = SPRING_SECURITY_CONTEXT??
    >
    <#if known>
        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal>

        <#assign authorities = SPRING_SECURITY_CONTEXT.authentication.authorities />
        <#if authorities?seq_contains("APPLICANT")>
            <body>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary mt-3" data-toggle="modal" data-target="#exampleModal">
                Launch demo modal
            </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            ...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    </#if>

</@main.layout>