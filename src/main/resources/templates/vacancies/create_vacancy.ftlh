<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center mb-3">Create a Job Vacancy:</h1>

    <div class="row justify-content-around">
        <div class="card col-8 m-1 mb-5">
            <form action="/vacancy/create" method="post">
                <div class="form-group">
                    <label for="name">Job Title:</label>
                    <input type="text" id="name" name="name" class="form-control" required>
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
                    <input type="number" id="salary" name="salary" class="form-control" min="0" required>
                </div>

                <div class="form-group">
                    <label for="description">Job Description:</label>
                    <textarea id="description" name="description" rows="5" class="form-control" required></textarea>
                </div>

                <div class="form-group">
                    <label for="expFrom">Required Experience (from):</label>
                    <input type="number" id="expFrom" name="expFrom" class="form-control" min="0" required>
                </div>

                <div class="form-group">
                    <label for="expTo">Required Experience (to):</label>
                    <input type="number" id="expTo" name="expTo" class="form-control" min="0" required>
                </div>
                <div class="form-group" id="contact">
                    <label>Контактная информация:</label>
                    <button type="button" class="btn btn-secondary btn-sm" onclick="addContact()">Добавить
                        контакт
                    </button>
                </div>
                <button class="btn btn-primary" type="submit">Publish</button>
            </form>
        </div>
    </div>
    <script>
        let contactIndex = 0;

        function addContact() {
            var contactSection = document.getElementById("contact");
            var contactFields = '<div id="contact' + contactIndex + '" class="form-group">'
                .concat('<label for="contactValue">Контактная информация:</label>')
                .concat('<input type="text" class="form-control" name="contactInfo[' + contactIndex + '].contactValue" required><br>')
                .concat('<label for="typeId">Тип контакта:</label>')
                .concat('<select class="form-control" name="contactInfo[' + contactIndex + '].typeId">')
                .concat('<option value="">--Выберите тип контакта--</option>')
                .concat('<option value="1">Телефон</option>')
                .concat('<option value="2">Email</option>')
                .concat('<option value="3">LinkedIn</option>')
                .concat('<option value="4">Telegram</option>')
                .concat('</select><br>')
                .concat('<button type="button" class="btn btn-danger btn-sm" onclick="removeContact(' + contactIndex + ')">Удалить контакт</button>')
                .concat('</div>');
            contactSection.insertAdjacentHTML('beforeend', contactFields);
            contactIndex++;
        }
    </script>
</@main.layout>
