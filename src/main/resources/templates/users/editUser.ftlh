<#import "../layout.ftlh" as main>

<@main.layout>
    <h1 class="center mb-3">Редактировать профиль</h1>
    <div class="row">
        <div class="col-sm-6">
            <div class="card profile border-0">
                <div class="card-body">
                    <form action="/user/update/${user.email}" method="post">
                        <fieldset>
                            <div class="form-group">
                                <label for="name">Имя</label>
                                <input type="text" id="name" name="name" class="form-control" value="${user.name}" required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="surname">Фамилия</label>
                                <input type="text" id="surname" name="surname" class="form-control" value="${user.surname}" required>
                            </div>
                            <div class="form-group">
                                <label for="age">Возраст</label>
                                <input type="number" id="age" name="age" class="form-control" value="${user.age}" required>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" class="form-control" value="${user.email}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="password">Новый пароль</label>
                                <input type="password" id="password" name="password" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
