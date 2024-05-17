<#import "../layout.ftlh" as main>
<@main.layout>

    <h1>Edit Education</h1>
    <form action="/education/update" method="post">
        <input type="hidden" name="id" value="${education.id?c}">
        <input type="hidden" name="resumeId" value="${education.resumeId?c}">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div>
            <label for="institution">Institution:</label>
            <input type="text" id="institution" name="institution" value="${education.institution?html}" maxlength="100" required>
            <#if errors['institution']?exists>
                <span style="color: red;">${errors['institution']}</span>
            </#if>
        </div>

        <div>
            <label for="program">Program:</label>
            <input type="text" id="program" name="program" value="${education.program?html}" maxlength="100" required>
            <#if errors['program']?exists>
                <span style="color: red;">${errors['program']}</span>
            </#if>
        </div>

        <div>
            <label for="degree">Degree:</label>
            <input type="text" id="degree" name="degree" value="${education.degree?html}" maxlength="50" required>
            <#if errors['degree']?exists>
                <span style="color: red;">${errors['degree']}</span>
            </#if>
        </div>

        <div>
            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" value="${education.startDate?date('yyyy-MM-dd')}">
        </div>

        <div>
            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" value="${education.endDate?date('yyyy-MM-dd')}">
        </div>

        <div>
            <button type="submit">Save</button>
        </div>

    </form>

</@main.layout>