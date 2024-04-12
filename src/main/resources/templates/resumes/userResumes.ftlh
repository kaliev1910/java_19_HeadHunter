<#import "../layout.ftlh" as main>
<@main.layout>

    <h1 class="text-center mb-3">Резюме</h1>
    <div class="text-center">
        <a href="/resume/resumes?page=0">1</a>
        <a href="/resume/resumes?page=1">2</a>
        <a href="/employer/resumes?page=2">3</a>
    </div>
    <div class="row justify-content-around">
        <#list resume as resume>
            <div class="card col-3 m-1">
                <div class="card-footer"><a href="/employer/resume/${resume.id}">Должность: ${resume.name}</a></div>
                <div class="card-body">
                    <h6 class="card-title">Категория работы: ${resume.categoryId}</h6>
                    <h6 class="card-title">Контактные детали: </h6>
                    <#list contacts as contact>
                        <#if contact?has_content>
                            <#if contact.typeId=1>
                                <p class="card-text"> Phone ${contact.contactValue}</p>
                            </#if>
                            <#if contact.typeId=2>
                                <p class="card-text"> Email ${contact.contactValue}</p>
                            </#if>
                            <#if contact.typeId=3>
                                <p class="card-text"> LinkedIn ${contact.contactValue}</p>
                            </#if>
                            <#if contact.typeId=4>
                                <p class="card-text">Telegram ${contact.contactValue}</p>
                            </#if>


                        </#if>
                    </#list>
                    <p class="card-text">${resume.expectedSalary} KGZ</p>
                </div>
                <div class="card-footer">
                    <a href="/resume/${resume.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </div>
        </#list>
    </div>

</@main.layout>