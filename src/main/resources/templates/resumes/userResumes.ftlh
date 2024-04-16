<#import "../layout.ftlh" as main>
<@main.layout>

    <h1 class="text-center mb-3">Мои Резюме</h1>
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
                            </div>
                        </div>
                    </div>
                </div>
                <div class="buttons">
                    <a href="/resume/${resume.id}">
                        <button type="button" class="btn btn-primary">Подробнее</button>
                    </a>
                    <a href="/resume/${resume.id}/edit">
                        <button type="button" class="btn btn-primary">Редактировать</button>
                    </a>
                </div>
            </#list>
        </div>
    </div>


</@main.layout>