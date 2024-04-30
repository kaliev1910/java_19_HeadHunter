<#import "../layout.ftlh" as main>
<@main.layout>

    <h1 class="text-center mt-5 mb-4">Редактирование резюме</h1>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <form action="/resume/update" method="post" id="resumeForm">

                            <input type="text" class="form-control" id="applicantEmail" name="applicantEmail" value="${resume.applicantEmail}"
                                   required>

                        <div class="form-group">
                            <label for="name">Название резюме:</label>
                            <input type="text" class="form-control" id="name" name="name" value="${resume.name}"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="categoryId">Категория резюме:</label>
                            <select class="form-control" id="categoryId" name="categoryId" required>
                                <option value="">--Выберите категорию--</option>
                                <option value="1" <#if resume.categoryId == 1>selected</#if>>IT</option>
                                <option value="2" <#if resume.categoryId == 2>selected</#if>>Web Development</option>
                                <option value="3" <#if resume.categoryId == 3>selected</#if>>Finance</option>
                                <option value="4" <#if resume.categoryId == 4>selected</#if>>Accounting</option>
                                <option value="5" <#if resume.categoryId == 5>selected</#if>>Investment Banking</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="expectedSalary">Ожидаемый уровень оклада:</label>
                            <input type="number" class="form-control" id="expectedSalary" name="expectedSalary"
                                   value="${resume.expectedSalary}" required>
                        </div>

                        <div class="form-group" id="education">
                            <label>Образование:</label>
                            <#list educations as education>
                                <div id="education-{{educationIndex}}" class="form-group">
                                    <label for="institution">Учебное заведение:</label>
                                    <input type="text" class="form-control"
                                           name="education[' + educationIndex + '].institution"
                                           value="${education.institution}" required><br>
                                    <input type="text" class="form-control"
                                           name="education[' + educationIndex + '].program" required
                                           value="${education.program}"><br>
                                    <label for="degree">Степень:</label>')
                                    <input type="text" class="form-control"
                                           name="education[' + educationIndex + '].degree"
                                           value="${education.degree}" required><br>
                                    <label for="startDate">Дата начала:</label>')
                                    <input type="date" class="form-control"
                                           name="education[' + educationIndex + '].startDate"
                                           value="${education.startDate}" required><br>
                                    <label for="endDate">Дата окончания:</label>')
                                    <input type="datetime-local" class="form-control"
                                           name="education[' + educationIndex + '].endDate"
                                           value="${education.endDate}" required><br>
                                    <button type="button" class="btn btn-danger btn-sm"
                                            onclick="removeEducation(this.dataset.educationIndex)">Удалить образование
                                    </button>
                                </div>
                            </#list>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addEducation()">Добавить
                                образование
                            </button>
                        </div>

                        <div class="form-group" id="experience">
                            <label>Experience:</label>
                            <#list experiences as experience>
                                <div id="experience[' + experienceIndex + ']" class="form-group">
                                    <label for="company">Компания: </label>
                                    <input type="text" class="form-control"
                                           name="experience[' + experienceIndex + '].institution"
                                           value="${experience.companyName}" required><br>
                                    <input type="text" class="form-control"
                                           name="experience[' + experienceIndex + '].program" required
                                           value="${experience.position}"><br>
                                    <label for="degree">Обязанности:</label>')
                                    <input type="text" class="form-control"
                                           name="experience[' + experienceIndex + ']."
                                           value="${experience.responsibilities}" required><br>
                                    <label for="startDate">Дата начала:</label>')
                                    <input type="date" class="form-control"
                                           name="experience[' + experienceIndex + '].startDate"
                                           value="${experience.startDate}" required><br>
                                    <label for="endDate">Дата окончания:</label>')
                                    <input type="date" class="form-control"
                                           name="experience[' + experienceIndex + '].endDate"
                                           value="${experience.endDate}" required><br>
                                    <button type="button" class="btn btn-danger btn-sm"
                                            onclick="removeEducation(this.dataset.educationIndex)">Удалить образование
                                    </button>
                                </div>
                            </#list>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addEducation()">Добавить
                                образование
                             </button>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Обновить резюме</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        window.addEventListener('load' , ()=>{

            let educationIndex = 0;
            let experienceIndex = 0;
            let contactIndex = 0;
            }

        )

        function addEducation() {
            let educationSection = document.getElementById("education");

            const a = ``;

            let educationFields = '<div id="education' + educationIndex + '" class="form-group">'
                .concat('<label for="institution">Учебное заведение:</label>')
                .concat('<input type="text" class="form-control" name="education[' + educationIndex + '].institution" required><br>')
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
            let experienceSection = document.getElementById("experience");
            let experienceFields = '<div id="experience' + experienceIndex + '" class="form-group">'
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
            let contactSection = document.getElementById("contact");
            let contactFields = '<div id="contact' + contactIndex + '" class="form-group">'
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
            let educationToRemove = document.getElementById("education" + index);
            educationToRemove.remove();
        }

        function removeExperience(index) {
            let experienceToRemove = document.getElementById("experience" + index);
            experienceToRemove.remove();
        }

        function removeContact(index) {
            let contactToRemove = document.getElementById("contact" + index);
            contactToRemove.remove();
        }
    </script>

</@main.layout>
