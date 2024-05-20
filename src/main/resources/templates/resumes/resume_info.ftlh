<#import "../layout.ftlh" as main><#import "spring.ftl" as spring>
<@main.layout>

    <div class="border p-3">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h5 class="card-title">Должность: ${resume.name}</h5>
                <h6 class="card-title">Категория работы: ${categories.get(resume.categoryId).name}</h6>
            </div>
            <div class="card-body">
                <p class="card-text">Ожидаемая зарплата: ${resume.expectedSalary} KGZ</p>
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
                <#if educations?has_content>
                    <p class="font-weight-bold mt-3">Образование: </p>
                    <div class="row justify-content-start">
                        <#list educations as education>
                            <div class="col-md-4 mb-3">
                                <div class="card border">
                                    <div class="card-header bg-info text-white">
                                        Учебное заведение: ${education.institution}
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">Специальность: ${education.program}</li>
                                        </ul>
                                    </div>
                                    <div class="card-footer">
                                        Годы обучения: ${education.startDate} - ${education.endDate}
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                <#else >
                    <p class="font-weight-bold mt-3">Образование отсутствует </p>
                </#if>
                <#if experiences?has_content >
                    <p class="font-weight-bold mt-3">Опыт работы:</p>
                    <div class="row justify-content-start">
                        <#list experiences as experience>
                            <div class="col-md-4 mb-3">
                                <div class="card border">
                                    <div class="card-header bg-success text-white">
                                        Организация: ${experience.companyName}
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">Должность: ${experience.position}</li>
                                        </ul>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">Обязанности: ${experience.responsibilities}</li>
                                        </ul>
                                        <ul class="list-group list-group-flush">
                                            <li  class="list-group-item"> Годы работы: ${experience.startDate} по ${experience.endDate}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                <#else >
                    <p class="font-weight-bold mt-3">Опыт работы отсутствует</p>
                </#if>
            </div>
        </div>
        <a href="#" class="btn btn-primary mt-3" onclick="goBack()">Вернуться на предыдущую страницу</a>
    </div>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>

</@main.layout>
