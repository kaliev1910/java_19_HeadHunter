<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <h1 class="text-center mb-3"><@spring.message 'vacancy.create.title'></@spring.message></h1>

    <div class="row justify-content-around">
        <div class="card col-8 m-1 mb-5">
            <form action="/vacancy/create" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="form-group">
                    <label for="name"><@spring.message 'vacancy.create.jobTitle'></@spring.message>:</label>
                    <input type="text" id="name" name="name" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="categoryId"><@spring.message 'vacancy.create.category'></@spring.message>:</label>
                    <select id="categoryId" name="categoryId" class="form-control" required>
                        <option value="">--<@spring.message 'vacancy.create.selectCategory'></@spring.message>--</option>
                        <option value="1"><@spring.message 'vacancy.create.category.IT'></@spring.message></option>
                        <option value="6"><@spring.message 'vacancy.create.category.Accounting'></@spring.message></option>
                        <option value="5"><@spring.message 'vacancy.create.category.Finance'></@spring.message></option>
                        <option value="7"><@spring.message 'vacancy.create.category.InvestmentBanking'></@spring.message></option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="salary"><@spring.message 'vacancy.create.salary'></@spring.message>:</label>
                    <input type="number" id="salary" name="salary" class="form-control" min="0" required>
                </div>

                <div class="form-group">
                    <label for="description"><@spring.message 'vacancy.create.description'></@spring.message>:</label>
                    <textarea id="description" name="description" rows="5" class="form-control" required></textarea>
                </div>

                <div class="form-group">
                    <label for="expFrom"><@spring.message 'vacancy.create.requiredExperienceFrom'></@spring.message>:</label>
                    <input type="number" id="expFrom" name="expFrom" class="form-control" min="0" required>
                </div>

                <div class="form-group">
                    <label for="expTo"><@spring.message 'vacancy.create.requiredExperienceTo'></@spring.message>:</label>
                    <input type="number" id="expTo" name="expTo" class="form-control" min="0" required>
                </div>
                <div class="form-group" id="contact">
                    <label><@spring.message 'vacancy.create.contactInfo'></@spring.message>:</label>
                    <button type="button" class="btn btn-secondary btn-sm" onclick="addContact()"><@spring.message 'vacancy.create.addContact'></@spring.message></button>
                </div>
                <button class="btn btn-primary" type="submit"><@spring.message 'vacancy.create.publish'></@spring.message></button>
            </form>
        </div>
    </div>
    <script>
        let contactIndex = 0;

        function addContact() {
            var contactSection = document.getElementById("contact");
            var contactFields = '<div id="contact' + contactIndex + '" class="form-group">'
                .concat('<label for="contactValue"><@spring.message 'resume.contactValue'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="contactInfo[' + contactIndex + '].contactValue" required><br>')
                .concat('<label for="typeId"><@spring.message 'resume.typeId'></@spring.message>:</label>')
                .concat('<select class="form-control" name="contactInfo[' + contactIndex + '].typeId">')
                .concat('<option value="">--<@spring.message 'contact.type.selectType'></@spring.message>--</option>')
                .concat('<option value="1"><@spring.message 'contact.type.phone'></@spring.message></option>')
                .concat('<option value="2"><@spring.message 'contact.type.email'></@spring.message></option>')
                .concat('<option value="3"><@spring.message 'contact.type.linkedin'></@spring.message></option>')
                .concat('<option value="4"><@spring.message 'contact.type.telegram'></@spring.message></option>')
                .concat('</select><br>')
                .concat('<button type="button" class="btn btn-danger btn-sm" onclick="removeContact(' + contactIndex + ')"><@spring.message 'resume.removeContact'></@spring.message></button>')
                .concat('</div>');
            contactSection.insertAdjacentHTML('beforeend', contactFields);
            contactIndex++;
        }
    </script>
</@main.layout>
