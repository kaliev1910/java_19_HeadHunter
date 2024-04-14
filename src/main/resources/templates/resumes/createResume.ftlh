<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center mb-3">Создание резюме:</h1>

    <div class="justify-content-around">
        <div class="card m-1 mb-5">
            <form action="/resume/create" method="post" id="resumeForm">
                <div class="card-title">
                    <p>Название резюме:</p>
                    <input type="text" id="name" name="name" required><br><br>
                    <label for="categoryId">Категория резюме:</label>
                    <select id="categoryId" name="categoryId" required>
                        <option value="">--Выберите категорию--</option>
                        <option value="1">IT</option>
                        <option value="2">Web Development</option>
                        <option value="3">Finance</option>
                        <option value="4">Accounting</option>
                        <option value="5">Investment Banking</option>
                    </select><br><br>
                </div>
                <div class="card-body">
                    <label for="expectedSalary">Ожидаемый уровень оклада:</label>
                    <input type="number" id="expectedSalary" name="expectedSalary" required><br><br>

                    <div id="education">
                        <label>Образование:</label>
                        <button type="button" onclick="addEducation()">Добавить образование</button>
                        <br><br>
                    </div>

                    <div id="experience">
                        <label>Опыт работы:</label>
                        <button type="button" onclick="addExperience()">Добавить опыт работы</button>
                        <br><br>
                    </div>

                    <div id="contact">
                        <label>Контактная информация:</label>
                        <button type="button" onclick="addContact()">Добавить контакт</button>
                        <br><br>
                    </div>
                </div>
                <div class="card-footer">
                    <input type="submit" value="Создать резюме">
                </div>
            </form>
        </div>
    </div>

    <script type="text/javascript">
        //<![CDATA[
        let educationIndex = 0;
        let experienceIndex = 0;
        let contactIndex = 0;

        function addEducation() {
            var educationSection = document.getElementById("education");
            var educationFields = `
                <label for="institution">Учебное заведение:</label>
                <input type="text" name="education[0].institution" required><br><br>
                <label for="program">Программа/Специализация:</label>
                <input type="text" name="education[0].program" required><br><br>
                <label for="degree">Степень:</label>
                <input type="text" name="education[0].degree" required><br><br>
                <label for="startDate">Дата начала:</label>
                <input type="date" name="education[0].startDate" required><br><br>
                <label for="endDate">Дата окончания:</label>
                <input type="date" name="education[0].endDate" required><br><br>
            `;
            educationSection.insertAdjacentHTML('beforeend', educationFields);
            educationIndex++;
        }

        function addExperience() {
            var experienceSection = document.getElementById("experience");
            var experienceFields = `
                <label for="companyName">Название компании:</label>
                <input type="text" name="experience[0].companyName"><br><br>
                <label for="position">Должность:</label>
                <input type="text" name="experience[0].position"><br><br>
                <label for="responsibilities">Обязанности:</label>
                <input type="text" name="experience[0].responsibilities"><br><br>
                <label for="years">Продолжительность работы (в годах):</label>
                <input type="number" name="experience[0].years"><br><br>
            `;
            experienceSection.insertAdjacentHTML('beforeend', experienceFields);
            experienceIndex++;
        }

        function addContact() {
            var contactSection = document.getElementById("contact");
            var contactFields = `
                <label for="contactValue">Контактная информация:</label>
                <input type="text" name="contactInfo[0].contactValue"><br><br>
                <label for="typeId">Тип контакта:</label>
                <select name="contactInfo[0].typeId">
                    <option value="">--Выберите тип контакта--</option>
                    <option value="1">Телефон</option>
                    <option value="2">Email</option>
                    <option value="3">LinkedIn</option>
                    <option value="4">Telegram</option>
                </select><br><br>
            `;
            contactSection.insertAdjacentHTML('beforeend', contactFields);
            contactIndex++;
        }
        //]]>
    </script>

</@main.layout>
