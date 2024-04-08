<#import "../layout.ftlh" as main>
<@main.layout>

    <h5 class="card-header">${vacancy.jobTitle}</h5>
    <h6 class="card-header">${vacancy.category}</h6>
    <div class="card-body">
        <h5 class="card-title">${vacancy.salary} KGS</h5>
        <p class="card-title">${vacancy.descriptionVacancy}</p>
        <p class="card-text">${vacancy.date}</p>
        <p class="card-text"><a href="/hh/guest/${vacancy.userEmail}">${vacancy.userEmail}</a></p>
    </div>
    <a href="/hh/vacancies?page=0" class="btn btn-success">Назад к вакансиям</a>
</@main.layout>