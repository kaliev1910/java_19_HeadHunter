<#import "../layout.ftlh" as main>
<#import "../pager.ftlh" as p>
<#import "/spring.ftl" as spring>
<@main.layout>

    <h1 class="text-center mb-3"><@spring.message 'page.title'></@spring.message></h1>
    <div class="dropdown mb-3">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            <@spring.message 'filter.label'></@spring.message>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="${url}?filter=1"><@spring.message 'filter.option.IT'></@spring.message></a>
            <a class="dropdown-item" href="${url}?filter=2"><@spring.message 'filter.option.Programming'></@spring.message></a>
            <a class="dropdown-item" href="${url}?filter=3"><@spring.message 'filter.option.WebDevelopment'></@spring.message></a>
            <a class="dropdown-item" href="${url}?filter=4"><@spring.message 'filter.option.MobileDevelopment'></@spring.message></a>
            <a class="dropdown-item" href="${url}?filter=5"><@spring.message 'filter.option.Finance'></@spring.message></a>
            <a class="dropdown-item" href="${url}?filter=6"><@spring.message 'filter.option.Accounting'></@spring.message></a>
            <a class="dropdown-item" href="${url}?filter=7"><@spring.message 'filter.option.InvestmentBanking'></@spring.message></a>
        </div>
    </div>
    <@p.pager url resumes />

    <div class="container mt-5 mb-3">
        <div class="row">
            <#list resumes.content as resume>
                <div class="col-md-4">
                    <a href="/resume/${resume.id}">
                        <div class="card p-4 mb-2">
                            <div class="d-flex justify-content-left">
                                <div class="d-flex flex-row align-items-center">
                                    <div class="ms-2 c-details">

                                        <h6 class="mb-0">${resume.applicantEmail}</h6>

                                        <span><@spring.message 'page.lastUpdate'></@spring.message> ${resume.updatedTime}</span>
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
                                        <div class="progress-bar" role="progressbar" style="width: 50%"
                                             aria-valuenow="50"
                                             aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <div class="mt-3"><span class="text1"><@spring.message 'page.applied'></@spring.message> <span
                                                    class="text2"><@spring.message 'page.capacity'></@spring.message></span></span></div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
    </div>

</@main.layout>
