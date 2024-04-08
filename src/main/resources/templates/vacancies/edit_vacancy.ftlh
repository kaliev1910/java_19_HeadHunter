<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="text-center mb-3">Изменение вакансии:</h1>

    <form action="" method="post">
        <fieldset>
            <input type="hidden" name="id" value="${vacancy.id}">
            <h5 class="form-element">
                <label for="vacancy-job-title">Название вакансии</label>
                <input type="text" name="jobTitle" id="vacancy-job-title" value="${vacancy.jobTitle}" required
                       autofocus><br><br>
            </h5>
            <div class="form-element">
            <label for="category">Категория вакансии:</label>
            <select id="category" name="category" required>
                <option value="">--Выберите категорию--</option>
                <option value="IT">IT</option>
                <option value="юриспруденция">Юриспруденция</option>
                <option value="мэнэджмент">Менеджмент</option>
                <option value="продажи">Продажи</option>
                <option value="авто">Авто</option>
                <option value="маркетинг">Маркетинг</option>
            </select><br><br>
            </div>
            <div class="form-element">
                <label for="vacancy-salary">Заработная плата</label>
                <input type="text" name="salary" id="vacancy-salary"
                       value="${vacancy.salary}" required
                       autofocus><br><br>
            </div>
            <div class="form-element">
                <label for="jobDescription">Описание вакансии: </label>
                <input type="text" name="descriptionVacancy" id="jobDescription"
                       value="${vacancy.descriptionVacancy}" rows="5" cols="30"  required><br><br>
            </div>
            <div class="form-element">
                <label for="experienceFrom">Требуемый опыт работы (от):</label>
                <input type="number" name="requiredWorkExperience" id="experienceFrom"
                       value="${vacancy.salary}" required
                       autofocus><br><br>
            </div>
            <div class="form-element">
                <button class="register-button" type="submit">Редактировать</button>
            </div>
        </fieldset>
    </form>

</@main.layout>