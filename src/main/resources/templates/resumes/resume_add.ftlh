<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center mb-3">Создание резюме:</h1>

    <div class="justify-content-around">
        <div class="card m-1 mb-5">
            <form action="/resume/create" method="post">
                <div class="card-title" >
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

                    <!-- Добавление образования -->
                    <label for="institution">Учебное заведение:</label>
                    <input type="text" id="institution" name="institution" required><br><br>
                    <label for="program">Программа/Специализация:</label>
                    <input type="text" id="program" name="program" required><br><br>
                    <label for="degree">Степень:</label>
                    <input type="text" id="degree" name="degree" required><br><br>
                    <label for="startDate">Дата начала:</label>
                    <input type="date" id="startDate" name="startDate" required><br><br>
                    <label for="endDate">Дата окончания:</label>
                    <input type="date" id="endDate" name="endDate" required><br><br>

                    <!-- Добавление опыта работы -->
                    <label for="companyName">Название компании:</label>
                    <input type="text" id="companyName" name="companyName" ><br><br>
                    <label for="position">Должность:</label>
                    <input type="text" id="position" name="position" ><br><br>
                    <label for="responsibilities">Обязанности:</label>
                    <input type="text" id="responsibilities" name="responsibilities" ><br><br>
                    <label for="years">Продолжительность работы (в годах):</label>
                    <input type="number" id="years" name="years" ><br><br>

                    <!-- Добавление контактной информации -->
                    <label for="contactValue">Контактная информация:</label>
                    <input type="text" id="contactValue" name="contactValue" ><br><br>
                    <label for="typeId">Тип контакта:</label>
                    <select id="typeId" name="typeId" >
                        <option value="">--Выберите тип контакта--</option>
                        <option value="1">Телефон</option>
                        <option value="2">Email</option>
                        <option value="3">LinkedIn</option>
                        <option value="4">Telegram</option>
                    </select><br><br>
                </div>
                <div class="card-footer">
                    <input type="submit" value="Создать резюме">
                </div>
            </form>
        </div>
    </div>

</@main.layout>
