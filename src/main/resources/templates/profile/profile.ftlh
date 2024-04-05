<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container">
        <div class="bg-dark-blue text-white d-flex justify-content-between align-items-center">
            <div class="logo">
            </div>
            <div class="user-info">
                <img src="avatar.png" alt="Аватар пользователя">
                <div class="user-details">
                    <span class="name">Иван Иванов</span>
                    <span class="age">55 лет</span>
                </div>
            </div>
        </div>
        <main class="py-5">
            <section class="row">
                <div class="col-md-8">
                    <h2 class="mb-4">Резюме</h2>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <a href="#">
                                <h3>Название резюме</h3>
                                <p class="mb-0">Дата обновления: 01/09/2022</p>
                            </a>
                            <button class="btn btn-sm btn-outline-primary float-end">Редактировать</button>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <h3>Название резюме</h3>
                                <p class="mb-0">Дата обновления: 03/03/2023</p>
                            </a>
                            <button class="btn btn-sm btn-outline-primary float-end">Редактировать</button>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <h3>Название резюме</h3>
                                <p class="mb-0">Дата обновления: 12/12/2023</p>
                            </a>
                            <button class="btn btn-sm btn-outline-primary float-end">Редактировать</button>
                        </li>
                    </ul>
                    <a href="#" class="btn btn-primary mt-3">Создать резюме</a>
                </div>
                <div class="col-md-4">
                    <h2 class="mb-4">Вакансии</h2>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <a href="#">
                                <h3>Название вакансии</h3>
                                <p class="mb-0">Компания: Google</p>
                                <p class="mb-0">Зарплата: от 100 000 руб.</p>
                            </a>
                            <button class="btn btn-sm btn-outline-primary float-end">Подробнее</button>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <h3>Название вакансии</h3>
                                <p class="mb-0">Компания: Яндекс</p>
                                <p class="mb-0">Зарплата: от 80 000 руб.</p>
                            </a>
                            <button class="btn btn-sm btn-outline-primary float-end">Подробнее</button>
                        </li>
                    </ul>
                </div>
            </section>
        </main>
    </div>

</@main.layout>
