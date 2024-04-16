<#import "../layout.ftlh" as main>
<@main.layout>


    <div class="border p-3">
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

        <a href="/vacancies?page=0" class="btn btn-primary mt-3">Перейти к списку вакансий</a>

    </div>
</@main.layout>