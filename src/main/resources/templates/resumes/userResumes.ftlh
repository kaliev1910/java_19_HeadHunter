<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>

    <h1 class="text-center mb-3"><@spring.message 'myresumes.page.title'></@spring.message></h1>
    <div class="container mt-5 mb-3">
        <div class="row">
            <#list resumes as resume>
                <div class="col-md-4">
                    <div class="card p-4 mb-2">
                        <div class="d-flex justify-content-left">
                            <div class="d-flex flex-row align-items-center">
                                <div class="ms-2 c-details">
                                    <h6 class="mb-0">${resume.applicantEmail}</h6>
                                    <span><@spring.message 'myresumes.page.lastUpdate'></@spring.message> ${resume.updatedTime}</span>
                                </div>
                            </div>
                            <div class="badge"><span><@spring.message 'myresumes.page.design'></@spring.message></span></div>
                        </div>
                        <div class="mt-5">
                            <a href="/resume/${resume.id}">
                                <h5 class="heading">${resume.name}</h5>
                            </a>
                            <h5>${resume.expectedSalary}</h5>
                            <div class="mt-5">
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" style="width: 50%" aria-valuenow="50"
                                         aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="buttons">
                    <a href="/resume/${resume.id}">
                        <button type="button" class="btn btn-primary"><@spring.message 'myresumes.page.details'></@spring.message></button>
                    </a>
                    <a href="/resumes/${resume.id}/edit">
                        <button type="button" class="btn btn-primary"><@spring.message 'myresumes.page.edit'></@spring.message></button>
                    </a>
                </div>
            </#list>
        </div>
    </div>

</@main.layout>
