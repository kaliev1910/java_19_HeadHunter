<#import "../layout.ftlh" as main>
<#import "spring.ftl" as spring>
<@main.layout>
    <h1 class="text-center mb-3">Вы упешно зарегистрированы на сайте!</h1>
    <a href="/hh/profile/${user.email}" class="btn text-center btn-success">Личный кабинет</a>
</@main.layout>