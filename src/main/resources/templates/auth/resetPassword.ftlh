<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout>
    <div class="text-center">
        <h2><@spring.message 'resetPassword.title'></@spring.message></h2>
        <#if error??>
            <b class="text-danger">${error}</b>
        </#if>
    </div>

    <div class="py-5 h-100">
        <div class="row d-flex align-items-center justify-content-center h-100">
            <div class="col-md-7 col-lg-5 col-xl-5">
                <form action="/reset_password" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <input type="hidden" name="token" value="${token!}">
                    <input type="password" name="password" class="form-control"
                           placeholder="<@spring.message 'resetPassword.placeholder.password'></@spring.message>" required autofocus/>
                    <p class="text-center">
                        <button type="submit" class="btn btn-primary mt-3"><@spring.message 'resetPassword.changePassword'></@spring.message></button>
                    </p>
                </form>
            </div>
        </div>
    </div>

</@main.layout>
