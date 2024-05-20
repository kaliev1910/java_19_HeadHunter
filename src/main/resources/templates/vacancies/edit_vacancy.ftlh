<#import "../layout.ftlh" as main>
<#import "spring.ftl" as spring>
<@main.layout>
    <h1 class="text-center mb-3">Edit Job Vacancy:</h1>

    <div class="row justify-content-around">
        <div class="card col-8 m-1 mb-5">
            <form action="vacancy/${vacancy.id}/edit" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>


                <div class="form-group">
                    <label for="name">Job Title:</label>
                    <input type="text" id="name" name="name" class="form-control" required value="${vacancy.name}">
                </div>

                <div class="form-group">
                    <label for="categoryId">Job Category:</label>
                    <select id="categoryId" name="categoryId" class="form-control" required>
                        <option value="">--Select Category--</option>
                        <option value=1>IT</option>
                        <option value=6>Accounting</option>
                        <option value=5>Finance</option>
                        <option value=7>Investment Banking</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="salary">Salary:</label>
                    <input type="number" id="salary" name="salary" class="form-control" min="0" required
                           value="${vacancy.salary}">
                </div>

                <div class="form-group">
                    <label for="description">Job Description:</label>
                    <textarea id="description" name="description" rows="5" class="form-control"
                              required>${vacancy.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="expFrom">Required Experience (from):</label>
                    <input type="number" id="expFrom" name="expFrom" class="form-control" min="0" required
                           value="${vacancy.expFrom}">
                </div>

                <div class="form-group">
                    <label for="expTo">Required Experience (to):</label>
                    <input type="number" id="expTo" name="expTo" class="form-control" min="0" required
                           value="${vacancy.expTo}">
                </div>

                <button class="btn btn-primary" type="submit">Save Changes</button>
            </form>
        </div>
    </div>
</@main.layout>