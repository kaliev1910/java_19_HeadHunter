<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <div class="text-center">

        <h2><@spring.message 'resetPassword.title'></@spring.message></h2>

        <p class="text-warning">${message}</p>

    </div>


</@main.layout>