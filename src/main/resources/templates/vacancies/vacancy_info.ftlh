<#import "../layout.ftlh" as main>
<@main.layout>

    <h5 class="card-header">${vacancy.name}</h5>
    <h6 class="card-header">${vacancy.categoryId}</h6>
    <div class="card-body">
        <h5 class="card-title">${vacancy.salary} KGS</h5>
        <p class="card-title">${vacancy.description}</p>
        <p class="card-text">${vacancy.updateTime}</p>
    </div>
    <a href="/vacancies?page=0" class="btn btn-success">Назад к вакансиям</a>
</@main.layout>