<#import "../layout.ftlh" as main>
<@main.layout>

    <h1 class="text-center mb-3">Резюме</h1>
    <div class="text-center">
        <a href="/resume/resumes?page=0">1</a>
        <a href="/resume/resumes?page=1">2</a>
        <a href="/employer/resumes?page=2">3</a>
    </div>

    <div class="row justify-content-around">
        <#list resumes as resume>
            <div class="card col-3 m-1">
                <div class="card-footer"> <a href="/employer/resume/${resume.id}">Должность: ${resume.jobTitle}</a></div>
                <div class="card-body">
                    <h6 class="card-title">Категория работы: ${resume.category}</h6>
                    <h6 class="card-title">Контактные детали: </h6>
                    <#if resume.contactDetailsDto.telegram?has_content>
                        <p class="card-text"> ${resume.contactDetailsDto.telegram}</p>
                    </#if>
                    <#if resume.contactDetailsDto.email?has_content>
                        <p class="card-text"> ${resume.contactDetailsDto.email}</p>
                    </#if>
                    <#if resume.contactDetailsDto.phoneNumber?has_content>
                        <p class="card-text"> ${resume.contactDetailsDto.phoneNumber}</p>
                    </#if>
                    <#if resume.contactDetailsDto.facebook?has_content>
                        <p class="card-text"> ${resume.contactDetailsDto.facebook}</p>
                    </#if>
                    <#if resume.contactDetailsDto.linkedin?has_content>
                        <p class="card-text"> ${resume.contactDetailsDto.linkedin}</p>
                    </#if>
                    <p class="card-text">${resume.expectedSalary} KGZ</p>
                </div>
                <div class="card-footer">
                    <p>${resume.date}</p>
                    <a href="/employer/resume/${resume.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </div>
        </#list>
    </div>

</@main.layout>