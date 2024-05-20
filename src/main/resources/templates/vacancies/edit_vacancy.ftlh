<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <h1 class="text-center mb-3"><@spring.message 'vacancy.edit.title'></@spring.message>:</h1>

    <div class="row justify-content-around">
        <div class="card col-8 m-1 mb-5">
            <form action="vacancy/${vacancy.id}/edit" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="form-group">
                    <label for="name"><@spring.message 'vacancy.edit.jobTitle'></@spring.message>:</label>
                    <input type="text" id="name" name="name" class="form-control" required value="${vacancy.name}">
                </div>

                <div class="form-group">
                    <label for="categoryId"><@spring.message 'vacancy.edit.category'></@spring.message>:</label>
                    <select id="categoryId" name="categoryId" class="form-control" required>
                        <option value="">--<@spring.message 'vacancy.edit.selectCategory'></@spring.message>--</option>
                        <option value="1"><@spring.message 'vacancy.edit.category.IT'></@spring.message></option>
                        <option value="6"><@spring.message 'vacancy.edit.category.Accounting'></@spring.message></option>
                        <option value="5"><@spring.message 'vacancy.edit.category.Finance'></@spring.message></option>
                        <option value="7"><@spring.message 'vacancy.edit.category.InvestmentBanking'></@spring.message></option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="salary"><@spring.message 'vacancy.edit.salary'></@spring.message>:</label>
                    <input type="number" id="salary" name="salary" class="form-control" min="0" required
                           value="${vacancy.salary}">
                </div>

                <div class="form-group">
                    <label for="description"><@spring.message 'vacancy.edit.description'></@spring.message>:</label>
                    <textarea id="description" name="description" rows="5" class="form-control"
                              required>${vacancy.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="expFrom"><@spring.message 'vacancy.edit.requiredExperienceFrom'></@spring.message>:</label>
                    <input type="number" id="expFrom" name="expFrom" class="form-control" min="0" required
                           value="${vacancy.expFrom}">
                </div>

                <div class="form-group">
                    <label for="expTo"><@spring.message 'vacancy.edit.requiredExperienceTo'></@spring.message>:</label>
                    <input type="number" id="expTo" name="expTo" class="form-control" min="0" required
                           value="${vacancy.expTo}">
                </div>

                <button class="btn btn-primary" type="submit"><@spring.message 'vacancy.edit.saveChanges'></@spring.message></button>
            </form>
        </div>
    </div>
</@main.layout>
