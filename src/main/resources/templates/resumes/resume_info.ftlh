<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout>

    <div class="border p-3">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h5 class="card-title"><@spring.message 'resume.position'/> : ${resume.name}</h5>
                <h6 class="card-title"><@spring.message 'resume.category'/> : resume.categoryId}</h6>
            </div>
            <div class="card-body">
                <p class="card-text"><@spring.message 'resume.expectedSalary'></@spring.message>: ${resume.expectedSalary} KGZ</p>
                <#if contacts?has_content>
                    <h6 class="card-title"><@spring.message 'view.contactDetails'></@spring.message>: </h6>
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
                    <h6 class="card-title"><@spring.message 'vacancy.details.noContactDetails'></@spring.message> </h6>
                </#if>
                <#if educations?? ?has_content>
                    <p class="font-weight-bold mt-3"><@spring.message 'resume.education'></@spring.message>: </p>
                    <div class="row justify-content-start">
                        <#list educations as education>
                            <div class="col-md-4 mb-3">
                                <div class="card border">
                                    <div class="card-header bg-info text-white">
                                        <@spring.message 'resume.institution'></@spring.message>: ${education.institution}
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><@spring.message 'resume.program'></@spring.message>: ${education.program}</li>
                                        </ul>
                                    </div>
                                    <div class="card-footer">
                                        <@spring.message 'resume.years'></@spring.message>: ${education.startDate} - ${education.endDate}
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                <#else >
                    <p class="font-weight-bold mt-3"><@spring.message 'view.noEducation'></@spring.message> </p>
                </#if>
                <#if experiences?has_content >
                    <p class="font-weight-bold mt-3"><@spring.message 'resume.experience'></@spring.message>:</p>
                    <div class="row justify-content-start">
                        <#list experiences as experience>
                            <div class="col-md-4 mb-3">
                                <div class="card border">
                                    <div class="card-header bg-success text-white">
                                        <@spring.message 'resume.companyName'></@spring.message>: ${experience.companyName}
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><@spring.message 'resume.position'></@spring.message>: ${experience.position}</li>
                                        </ul>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><@spring.message 'resume.responsibilities'></@spring.message>: ${experience.responsibilities}</li>
                                        </ul>
                                        <ul class="list-group list-group-flush">
                                            <li  class="list-group-item"> <@spring.message 'resume.years'></@spring.message>: ${experience.startDate} <@spring.message 'resume.to'></@spring.message> ${experience.endDate}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                <#else >
                    <p class="font-weight-bold mt-3"><@spring.message 'view.noExperience'></@spring.message></p>
                </#if>
            </div>
        </div>
        <a href="#" class="btn btn-primary mt-3" onclick="goBack()"><@spring.message 'view.goBackButton'></@spring.message></a>
    </div>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>

</@main.layout>
