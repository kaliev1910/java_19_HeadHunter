<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8 col-sm-10">
                <div class="card shadow-lg rounded-lg mt-5">
                    <div class="card-header bg-primary text-white">
                        <h3 class="text-center font-weight-bold my-3"><@spring.message 'login.title'></@spring.message></h3>
                    </div>
                    <div class="card-body">
                        <form action="/login" method="post" id="login-form" name="login-form">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <#if error??>
                                <b class="error">${error}</b>
                            </#if>

                            <div class="form-group">
                                <label for="username" class="font-weight-bold"><@spring.message 'login.username'></@spring.message>:</label>
                                <input type="email" class="form-control" id="username" name="username"
                                       placeholder="<@spring.message 'login.placeholder.username'/>" required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="font-weight-bold"><@spring.message 'login.password'></@spring.message>:</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="<@spring.message 'login.placeholder.password'/>" required>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary btn-block"><@spring.message 'login.button'></@spring.message></button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <p><@spring.message 'login.dont'></@spring.message> <a href="/register"><@spring.message 'login.register'></@spring.message></a></p>
                    </div>
                    <div class="card-footer text-center">
                        <p><@spring.message 'login.forgotPassword'></@spring.message><a href="/forgot_password"><@spring.message 'resetPassword.title'></@spring.message></a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.getElementById('login-form').addEventListener('submit', onLoginHandler)

        function onLoginHandler(event) {
            let form = event.target
            let userFormData = new FormData(form)
            let user = Object.fromEntries(userFormData)
            saveUser(user)
        }
        document.addEventListener('DOMContentLoaded', function () {
            let user = readUser();
            if (user) {
                document.getElementById('username').value = user.username;
                document.getElementById('password').value = user.password;
                document.forms['login-form'].submit();
            }
        });
        function saveUser(user) {
            let userAsJson = JSON.stringify(user)
            localStorage.setItem('user', userAsJson)

        }
        function readUser() {
            let userAsJson = localStorage.getItem('user')
            return JSON.parse(userAsJson)

        }

        const baseUrl = 'http://localhost:9999'
        function makeHeaders() {
            let headers = new Headers();
            let user = readUser();
            headers.set('Content-Type', 'application/json');
            if (user) {
                headers.set('Authorization', 'Basic ' + btoa(user.username + ":" + user.password));
            }
            return headers;


        }

        const requestSettings = {
            method: 'GET',
            headers: makeHeaders()
        }
        async function makeRequest(url, options) {
            let settings = options || requestSettings

            let response = await fetch(url, settings)
            if (response.ok) {
                return await response.json()
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error;
            }

        }
        function updateOptions(options) {
            let update = {...options};
            update.mode = 'cors';
            update.headers = makeHeaders()
            return update;
        }



    </script>

</@main.layout>
