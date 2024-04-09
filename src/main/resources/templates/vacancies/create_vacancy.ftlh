<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center mb-3">Создание вакансии:</h1>

    <div class="row justify-content-around">
        <div class="card col-1 m-1 mb-5">
            <form action="" method="post">
                <label for="jobTitle">Название вакансии:</label>
                <input type="text" id="jobTitle" name="jobTitle" required><br><br>
                <label for="category">Категория вакансии:</label>
                <select id="category" name="category" required>
                    <option value="">--Выберите категорию--</option>
                    <option value=1>IT</option>
                    <option value=>5</option>
                    <option value=6>Accounting</option>
                    <option value=7>Investment Banking</option>

                </select><br><br>
                <label for="salary">Заработная плата:</label>
                <input type="number" id="salary" name="salary" required><br><br>
                <label for="description">Детальное описание вакансии:</label><br>
                <textarea id="description" name="descriptionVacancy" rows="5" cols="30" required></textarea><br><br>
                <label for="experienceFrom">Требуемый опыт работы (от):</label>
                <input type="number" id="experienceFrom" name="requiredWorkExperience" required><br><br>
                <label for="experienceTo">Требуемый опыт работы (до):</label>
                <input type="number" id="experienceTo" name="experienceTo" required><br><br>

                <div class="form-element">
                    <button class="register-button" type="submit">Опубликовать</button>
                </div>
            </form>
        </div>
    </div>

</@main.layout>