<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <div class="container">
        <h2 class="text-center mb-4"><@spring.message 'resume.creation'></@spring.message></h2>
        <form action="/register" id="registrationForm"  method="post" style="max-width: 400px; margin: auto; display: flex; flex-wrap: wrap;">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>

            <div class="form-group" style="flex-basis: 100%;">
                <label for="name" style="width: 100%;"><@spring.message 'registration.name'></@spring.message></label>
                <input type="text" class="form-control" id="name" name="name" required style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="surname" style="width: 100%;"><@spring.message 'registration.surname'></@spring.message></label>
                <input type="text" class="form-control" id="surname" name="surname" required style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="age" style="width: 100%;"><@spring.message 'registration.age'></@spring.message></label>
                <input type="number" class="form-control" id="age" name="age" required min="18" max="120"
                       style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="email" style="width: 100%;"><@spring.message 'registration.email'></@spring.message></label>
                <input type="email" class="form-control" id="email" name="email" required style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="password" style="width: 100%;"><@spring.message 'registration.password'></@spring.message></label>
                <input type="password" class="form-control" id="password" name="password" required minlength="4" maxlength="24"
                       style="width: 100%;">
            </div>

            <div class="form-group" style="flex-basis: 100%;">
                <label for="accountType" style="width: 100%;"><@spring.message 'registration.accountType'></@spring.message></label>
                <select class="form-control" id="accountType" name="accountType" required style="width: 100%;">
                    <option value="APPLICANT"><@spring.message 'registration.accountType.applicant'></@spring.message></option>
                    <option value="EMPLOYER"><@spring.message 'registration.accountType.employer'></@spring.message></option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-block mt-4" style="flex-basis: 100%;"><@spring.message 'registration.submit'></@spring.message></button>
        </form>
    </div>
    <script>
        $(document).ready(function() {
            $('#registrationForm').submit(function(e) {
                e.preventDefault();

                $.ajax({
                    type: 'POST',
                    url: '/register',
                    data: $(this).serialize(),
                    success: function(response) {

                        $('#registrationResultModal .modal-body').text(response);
                        $('#registrationResultModal').modal('show');
                    },
                    error: function(xhr, status, error) {
                        $('#registrationResultModal .modal-body').text(xhr.responseText);
                        $('#registrationResultModal').modal('show');
                    }
                });
            });
        });

    </script>
</@main.layout>
