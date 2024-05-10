<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center mt-5 mb-4">Создание резюме</h1>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <form action="/resume/create" method="post" id="resumeForm" name="resumeForm">
                        <div class="form-group">
                            <label for="name">Название резюме:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>

                        <div class="form-group">
                            <label for="categoryId">Категория резюме:</label>
                            <select class="form-control" id="categoryId" name="categoryId" required>
                                <option value="">--Выберите категорию--</option>
                                <option value="1">IT</option>
                                <option value="2">Web Development</option>
                                <option value="3">Finance</option>
                                <option value="4">Accounting</option>
                                <option value="5">Investment Banking</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="expectedSalary">Ожидаемый уровень оклада:</label>
                            <input type="number" class="form-control" id="expectedSalary" name="expectedSalary"
                                   required>
                        </div>

                        <div class="form-group" id="education">
                            <label>Образование:</label>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addEducation()">Добавить
                                образование
                            </button>
                        </div>

                        <div class="form-group" id="experience">
                            <label>Опыт работы:</label>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addExperience()">Добавить
                                опыт работы
                            </button>
                        </div>

                        <div class="form-group" id="contact">
                            <label>Контактная информация:</label>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addContact()">Добавить
                                контакт
                            </button>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">Создать резюме</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        let educationIndex = 0;
        let experienceIndex = 0;
        let contactIndex = 0;

        function addEducation() {
            var educationSection = document.getElementById("education");
            var educationFields = '<div id="education' + educationIndex + '" class="form-group" name="education[' + educationIndex + ']">'
                .concat('<label for="institution">Учебное заведение:</label>')
                .concat('<input type="text" class="form-control" name="education[' + educationIndex + '].institution" required><br>')
                .concat('<input type="text" class="form-control"  value="" name="education[' + educationIndex + '].resumeId"  hidden="hidden" required><br>')
                .concat('<label for="program">Программа/Специализация:</label>')
                .concat('<input type="text" class="form-control" name="education[' + educationIndex + '].program" required><br>')
                .concat('<label for="degree">Степень:</label>')
                .concat('<input type="text" class="form-control" name="education[' + educationIndex + '].degree" required><br>')
                .concat('<label for="startDate">Дата начала:</label>')
                .concat('<input type="date" class="form-control" name="education[' + educationIndex + '].startDate" required><br>')
                .concat('<label for="endDate">Дата окончания:</label>')
                .concat('<input type="date" class="form-control" name="education[' + educationIndex + '].endDate" required><br>')
                .concat('<button type="button" class="btn btn-danger btn-sm" onclick="removeEducation(' + educationIndex + ')">Удалить образование</button>')
                .concat('</div>');
            educationSection.insertAdjacentHTML('beforeend', educationFields);
            educationIndex++;
        }

        function addExperience() {
            var experienceSection = document.getElementById("experience");
            var experienceFields = '<div id="experience' + experienceIndex + '" class="form-group">'
                .concat('<label for="companyName">Название компании:</label>')
                .concat('<input type="text" class="form-control" name="experience[' + experienceIndex + '].companyName" required><br>')
                .concat('<label for="position">Должность:</label>')
                .concat('<input type="text" class="form-control" name="experience[' + experienceIndex + '].position" required><br>')
                .concat('<label for="responsibilities">Обязанности:</label>')
                .concat('<input type="text" class="form-control" name="experience[' + experienceIndex + '].responsibilities" required><br>')
                .concat('<label for="years">Продолжительность работы (в годах):</label>')
                .concat('<input type="number" class="form-control" name="experience[' + experienceIndex + '].years" required><br>')
                .concat('<button type="button" class="btn btn-danger btn-sm" onclick="removeExperience(' + experienceIndex + ')">Удалить опыт</button>')
                .concat('</div>');
            experienceSection.insertAdjacentHTML('beforeend', experienceFields);
            experienceIndex++;
        }

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


        function removeEducation(index) {
            var educationToRemove = document.getElementById("education" + index);
            educationToRemove.remove();
        }

        function removeExperience(index) {
            var experienceToRemove = document.getElementById("experience" + index);
            experienceToRemove.remove();
        }

        function removeContact(index) {
            var contactToRemove = document.getElementById("contact" + index);
            contactToRemove.remove();
        }
    </script>

</@main.layout>
