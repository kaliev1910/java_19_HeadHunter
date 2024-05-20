<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <h1 class="text-center mb-3"><@spring.message 'registration.success'></@spring.message></h1>
    <a href="/hh/profile/${user.email}" class="btn text-center btn-success"><@spring.message 'profile.title'></@spring.message></a>
</@main.layout>
