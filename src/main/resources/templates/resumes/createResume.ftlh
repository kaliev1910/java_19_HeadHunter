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
                        <option value=1>--Выберите категорию--</option>
                        <option value=1>IT</option>
                        <option value=3>Web Development</option>
                        <option value=5>Finance</option>
                        <option value=6>Accounting</option>
                        <option value=7>Investment Banking</option>
                    </select><br><br>
                </div>
                <div class="card-body">
                    <label for="expectedSalary">Ожидаемый уровень оклада:</label>
                    <input type="number" id="expectedSalary" name="expectedSalary" required><br><br>


                    <div id="educationSection">
                        <label>Образование:</label>
                        <button type="button" onclick="addEducation()">Добавить образование</button>
                        <br><br>
                    </div>


                    <div id="experienceSection">
                        <label>Опыт работы:</label>
                        <button type="button" onclick="addExperience()">Добавить опыт работы</button>
                        <br><br>
                    </div>


                    <div id="contactSection">
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

    <script>
        function addEducation() {

            document.getElementById("educationSection").innerHTML += `
                <label for="institution">Учебное заведение:</label>
                <input type="text" name="education.institution" required><br><br>
                <label for="program">Программа/Специализация:</label>
                <input type="text" name="education.program" required><br><br>
                <label for="degree">Степень:</label>
                <input type="text" name="education.degree" required><br><br>
                <label for="startDate">Дата начала:</label>
                <input type="date" name="education.startDate" required><br><br>
                <label for="endDate">Дата окончания:</label>
                <input type="date" name="education.endDate" required><br><br>
            `;
        }

        function addExperience() {

            document.getElementById("experienceSection").innerHTML += `
                <label for="companyName">Название компании:</label>
                <input type="text" name="experience.companyName"><br><br>
                <label for="position">Должность:</label>
                <input type="text" name="experience.position"><br><br>
                <label for="responsibilities">Обязанности:</label>
                <input type="text" name="experience.responsibilities"><br><br>
                <label for="years">Продолжительность работы (в годах):</label>
                <input type="number" name="experience.years"><br><br>
            `;
        }

        function addContact() {

            document.getElementById("contactSection").innerHTML += `
                <label for="contactValue">Контактная информация:</label>
                <input type="text" name="contactInfo.contactValue"><br><br>
                <label for="typeId">Тип контакта:</label>
                <select name="contactInfo.typeId">
                    <option value="">--Выберите тип контакта--</option>
                    <option value="1">Телефон</option>
                    <option value="2">Email</option>
                    <option value="3">LinkedIn</option>
                    <option value="4">Telegram</option>
                </select><br><br>
            `;
        }
    </script>
</@main.layout>
