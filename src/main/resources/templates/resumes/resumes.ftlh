<#import "../layout.ftlh" as main>
<@main.layout>

    <h1 class="text-center mb-3">Резюме</h1>
    <div class="text-center">
        <a href="/resumes?page=1">1</a>
        <a href="/resumes?page=2">2</a>
        <a href="/resumes?page=3">3</a>
    </div>
<#--    <div class="row justify-content-around">-->
<#--        <#list resumes as resume>-->
<#--            <div class="card col-3 m-1">-->
<#--                <div class="card-footer"><a href="/employer/resume/${resume.id}">Должность: ${resume.name}</a></div>-->
<#--                <div class="card-body">-->
<#--                    <h6 class="card-title">Категория работы: ${resume.categoryId}</h6>-->
<#--                    <h6 class="card-title">Контактные детали: </h6>-->
<#--                    <#list resume.contacts as contact>-->
<#--                        <#if contact?has_content>-->
<#--                            <#if contact.typeId=1>-->
<#--                                <p class="card-text"> Phone ${contact.contactValue}</p>-->
<#--                            </#if>-->
<#--                            <#if contact.typeId=2>-->
<#--                                <p class="card-text"> Email ${contact.contactValue}</p>-->
<#--                            </#if>-->
<#--                            <#if contact.typeId=3>-->
<#--                                <p class="card-text"> LinkedIn ${contact.contactValue}</p>-->
<#--                            </#if>-->
<#--                            <#if contact.typeId=4>-->
<#--                                <p class="card-text">Telegram ${contact.contactValue}</p>-->
<#--                            </#if>-->
<#--                        </#if>-->
<#--                    </#list>-->
<#--                    <p class="card-text">${resume.expectedSalary} KGZ</p>-->
<#--                </div>-->
<#--                <div class="card-footer">-->
<#--                    <a href="/${resume.id}" class="btn btn-primary">Подробнее</a>-->
<#--                </div>-->
<#--            </div>-->
<#--        </#list>-->
<#--    </div>-->
    <div class="container mt-5 mb-3">
        <div class="row">
            <#list resumes as resume>
                <div class="col-md-4">
                    <div class="card p-4 mb-2">
                        <div class="d-flex justify-content-left">
                            <div class="d-flex flex-row align-items-center">
                                <div class="ms-2 c-details">

                                        <h6 class="mb-0">${resume.applicantEmail}</h6>

                                    <span>Last Update ${resume.updatedTime}</span>
                                </div>
                            </div>
                            <div class="badge"><span>(-_)</span></div>
                        </div>
                        <div class="mt-5">
                            <a href="/vacancy/${resume.id}">
                                <h5 class="heading">${resume.name}</h5>
                            </a>
                            <h5>${resume.expectedSalary}</h5>
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

</@main.layout>