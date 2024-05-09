<#import "../layout.ftlh" as main>
<@main.layout>

    <h1 class="text-center mt-5 mb-4">Редактирование резюме</h1>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <form action="/resume/update" method="post" id="resumeForm">

                        <label for="applicantEmail"></label><input type="text" class="form-control" id="applicantEmail"
                                                                   name="applicantEmail"
                                                                   value="${resume.applicantEmail}"
                                                                   required hidden="hidden">

                        <div class="form-group">
                            <label for="name">Название резюме:</label>
                            <input type="text" class="form-control" id="name" name="name" value="${resume.name}"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="categoryId">Категория резюме:</label>
                            <select class="form-control" id="categoryId" name="categoryId" required>
                                <option value="">--Выберите категорию--</option>
                                <#list categories as category>
                                    <option value="${category.id}">${category.name}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="expectedSalary">Ожидаемый уровень оклада:</label>
                            <input type="number" class="form-control" id="expectedSalary" name="expectedSalary"
                                   value="${resume.expectedSalary}" required>
                        </div>

                        <div class="form-group" id="educations">
                            <label>Образование:</label>
                            <div id="educationContainer">

                               <#list educations as education>
                                   showEducation(${education.resumeId},${education.degree},${education.program},${education.institution},${education.startDate},${education.endDate});
                               </#list><!-- Education elements will be dynamically added here -->
                            </div>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addEducation()">Добавить образование</button>
                        </div>

                        <div class="form-group" id="experiences">
                            <label>Experience:</label>
                            <div id="experienceContainer">
                                <#list experiences as experience>
                                showEducation(${experience.resumeId},${experience.companyName},${experience.position},${experience.responsibilities},${experience.startDate},${experience.endDate});
                                </#list><!-- Education elements will be dynamically added here -->
                                addExperience()
                            </div>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addExperience()">Добавить опыт работы</button>
                        </div>
                        <div class="form-group" id="contacts">
                            <label>Experience:</label>
                            <div id="contactContainer">
                                <#list contacts as contact>
                                showContact(${contact.resumeId}, ${contact.contactValue}, ${contact.typeId});
                                </#list><!-- Education elements will be dynamically added here -->
                            </div>
                            <button type="button" class="btn btn-secondary btn-sm" onclick="addExperience()">Добавить опыт работы</button>
                        </div>

<#--                        <div class="form-group" id="education">-->
<#--                            <label>Образование:</label>-->
<#--                            <#list educations as education>-->
<#--                                <div id="education" class="form-group">-->
<#--                                    <label for="institution">Учебное заведение:</label>-->
<#--                                    <input type="text" class="form-control"-->
<#--                                           name="education.institution"-->
<#--                                           value="${education.institution}" required><br>-->
<#--                                    <input type="text" class="form-control"-->
<#--                                           name="education.program" required-->
<#--                                           value="${education.program}"><br>-->
<#--                                    <label for="degree">Степень:</label>')-->
<#--                                    <input type="text" class="form-control"-->
<#--                                           name="education.degree"-->
<#--                                           value="${education.degree}" required><br>-->
<#--                                    <label for="startDate">Дата начала:</label>')-->
<#--                                    <input type="date" class="form-control"-->
<#--                                           name="education.startDate"-->
<#--                                           value="${education.startDate}" required><br>-->
<#--                                    <label for="endDate">Дата окончания:</label>')-->
<#--                                    <input type="date" class="form-control"-->
<#--                                           name="education.endDate"-->
<#--                                           value="${education.endDate}" required><br>-->
<#--                                    <button type="button" class="btn btn-danger btn-sm"-->
<#--                                            onclick="removeEducation(this.dataset.educationIndex)">Удалить образование-->
<#--                                    </button>-->
<#--                                </div>-->
<#--                            </#list>-->
<#--                            <button type="button" class="btn btn-secondary btn-sm" onclick="addEducation()">Добавить-->
<#--                                образование-->
<#--                            </button>-->
<#--                        </div>-->

<#--                        <div class="form-group" id="experience">-->
<#--                            <label>Experience:</label>-->
<#--                            <#list experiences as experience>-->

<#--                                <div id="experience" class="form-group">-->
<#--                                    <label for="company">Компания: </label>-->
<#--                                    <input type="text" class="form-control"-->
<#--                                           name="experience{{{experienceIndex}}}.companyName"-->
<#--                                           value="${experience.companyName}" required><br>-->
<#--                                    <input type="text" class="form-control"-->
<#--                                           name="experience.position" required-->
<#--                                           value="${experience.position}"><br>-->
<#--                                    <label for="degree">Обязанности:</label>')-->
<#--                                    <input type="text" class="form-control"-->
<#--                                           name="experience.responsibilities"-->
<#--                                           value="${experience.responsibilities}" required><br>-->
<#--                                    <label for="startDate">Дата начала:</label>')-->
<#--                                    <input type="date" class="form-control"-->
<#--                                           name="experience.startDate"-->
<#--                                           value="${experience.startDate}" required><br>-->
<#--                                    <label for="endDate">Дата окончания:</label>')-->
<#--                                    <input type="date" class="form-control"-->
<#--                                           name="experience.endDate"-->
<#--                                           value="${experience.endDate}" required><br>-->
<#--                                    <button type="button" class="btn btn-danger btn-sm"-->
<#--                                            onclick="removeEducation(this.dataset.educationIndex)">Удалить образование-->
<#--                                    </button>-->
<#--                                </div>-->
<#--                            </#list>-->
<#--                            <button type="button" class="btn btn-secondary btn-sm" onclick="addEducation()">Добавить-->
<#--                                образование-->
<#--                            </button>-->
<#--                        </div>-->
                        <button type="submit" class="btn btn-primary btn-block">Обновить резюме</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="/static/js/resumeEdit.js"></script>
    <script>
        window.addEventListener('load', () => {
            let educationIndex = 0;
            let experienceIndex = 0;
            let contactIndex = 0;

        });

        function addEducation() {
            let educationSection = document.getElementById("education");

            let educationFields = '<div id="education' + educationIndex + '" class="form-group">'
                + '<label for="institution">Учебное заведение:</label>'
                + '<input type="text" class="form-control" name="education[' + educationIndex + '].institution" required><br>'
                + '<label for="program">Программа/Специализация:</label>'
                + '<input type="text" class="form-control" name="education[' + educationIndex + '].program" required><br>'
                + '<label for="degree">Степень:</label>'
                + '<input type="text" class="form-control" name="education[' + educationIndex + '].degree" required><br>'
                + '<label for="startDate">Дата начала:</label>'
                + '<input type="date" class="form-control" name="education[' + educationIndex + '].startDate" required><br>'
                + '<label for="endDate">Дата окончания:</label>'
                + '<input type="date" class="form-control" name="education[' + educationIndex + '].endDate" required><br>'
                + '<button type="button" class="btn btn-danger btn-sm" onclick="removeEducation(' + educationIndex + ')">Удалить образование</button>'
                + '</div>';
            educationSection.insertAdjacentHTML('beforeend', educationFields);
            educationIndex++;
        }

        function addExperience() {
            let experienceSection = document.getElementById("experience");
            let experienceFields = '<div id="experience' + experienceIndex + '" class="form-group">'
                + '<label for="companyName">Название компании:</label>'
                + '<input type="text" class="form-control" name="experience[' + experienceIndex + '].companyName" required><br>'
                + '<label for="position">Должность:</label>'
                + '<input type="text" class="form-control" name="experience[' + experienceIndex + '].position" required><br>'
                + '<label for="responsibilities">Обязанности:</label>'
                + '<input type="text" class="form-control" name="experience[' + experienceIndex + '].responsibilities" required><br>'
                + '<label for="years">Продолжительность работы (в годах):</label>'
                + '<input type="number" class="form-control" name="experience[' + experienceIndex + '].years" required><br>'
                + '<button type="button" class="btn btn-danger btn-sm" onclick="removeExperience(' + experienceIndex + ')">Удалить опыт</button>'
                + '</div>';
            experienceSection.insertAdjacentHTML('beforeend', experienceFields);
            experienceIndex++;
        }

        function addContact() {
            let contactSection = document.getElementById("contact");
            let contactFields = '<div id="contact' + contactIndex + '" class="form-group">'
                + '<label for="contactValue">Контактная информация:</label>'
                + '<input type="text" class="form-control" name="contactInfo[' + contactIndex + '].contactValue" required><br>'
                + '<label for="typeId">Тип контакта:</label>'
                + '<select class="form-control" name="contactInfo[' + contactIndex + '].typeId">'
                + '<option value="">--Выберите тип контакта--</option>'
                + '<option value="1">Телефон</option>'
                + '<option value="2">Email</option>'
                + '<option value="3">LinkedIn</option>'
                + '<option value="4">Telegram</option>'
                + '</select><br>'
                + '<button type="button" class="btn btn-danger btn-sm" onclick="removeContact(' + contactIndex + ')">Удалить контакт</button>'
                + '</div>';
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
