<#import "../layout.ftlh" as main>
<#import "../pager.ftlh" as p>
<@main.layout>

    <h1 class="text-center mb-3">Резюме</h1>
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
                            <div class="badge"><span>Design</span></div>
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