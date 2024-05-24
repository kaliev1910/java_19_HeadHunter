<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <h1 class="text-center mb-3"><@spring.message 'vacancy.edit.title'/></h1>

    <div class="row justify-content-around">
        <div class="card col-8 m-1 mb-5">

            <form action="/vacancy/${vacancy.id}/edit" method="post">
                <input type="hidden" id="id" name="id" value="${vacancy.id}">
                <#if _csrf??>
                    <input type="hidden" name="${_csrf.parameterName?default('_csrf')}"
                           value="${_csrf.token?default('_csrf_token')}"/>
                </#if>

                <div class="form-group">
                    <label for="name"><@spring.message 'vacancy.edit.jobTitle'/></label>
                    <input type="text" id="name" name="name" class="form-control" required value="${vacancy.name}">
                </div>
                <p>${vacancy.categoryId}</p>
                <div class="form-group">
                    <label for="categoryId"><@spring.message 'resume.category'></@spring.message>:</label>
                    <select class="form-control" id="categoryId" name="categoryId" required>
                        <option disabled value=""><@spring.message 'resume.chooseCategory'></@spring.message></option>
                        <option value="1"
                                <#if vacancy.categoryId == 1>selected</#if>><@spring.message 'resume.category.IT'></@spring.message></option>
                        <option value="2"
                                <#if vacancy.categoryId == 2>selected</#if>><@spring.message 'resume.category.WebDev'></@spring.message></option>
                        <option value="3"
                                <#if vacancy.categoryId == 3>selected</#if>><@spring.message 'resume.category.Finance'></@spring.message></option>
                        <option value="4"
                                <#if vacancy.categoryId == 4>selected</#if>><@spring.message 'resume.category.Accounting'></@spring.message></option>
                        <option value="5"
                                <#if vacancy.categoryId == 5>selected</#if>><@spring.message 'resume.category.InvestmentBanking'></@spring.message></option>
                    </select>
                </div>


                <div class="form-group">
                    <label for="salary"><@spring.message 'vacancy.edit.salary'/></label>
                    <input type="number" class="form-control" id="salary" name="salary"
                           required value="${vacancy.salary?c}">
                </div>

                <div class="form-group">
                    <label for="description"><@spring.message 'vacancy.edit.description'/></label>
                    <textarea id="description" name="description" rows="5" class="form-control"
                              required>${vacancy.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="expFrom"><@spring.message 'vacancy.edit.requiredExperienceFrom'/></label>
                    <input type="number" id="expFrom" name="expFrom" class="form-control" min="0" required
                           value="${vacancy.expFrom}">
                </div>

                <div class="form-group">
                    <label for="expTo"><@spring.message 'vacancy.edit.requiredExperienceTo'/></label>
                    <input type="number" id="expTo" name="expTo" class="form-control" min="0" required
                           value="${vacancy.expTo}">
                </div>


                <input type="hidden" id="authorEmail" name="authorEmail" value="${vacancy.authorEmail}">
                <input type="hidden" id="active" name="active" value="${vacancy.active?c}">


                <button class="btn btn-primary" type="submit"><@spring.message 'vacancy.edit.saveChanges'/></button>
            </form>


        </div>
    </div>
</@main.layout>
