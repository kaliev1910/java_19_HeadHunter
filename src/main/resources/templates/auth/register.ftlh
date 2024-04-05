<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <h2 class="text-center mb-4">Registration Form</h2>
        <form action="/register" method="post" style="max-width: 400px; margin: auto; display: flex; flex-wrap: wrap;">
            <div class="form-group" style="flex-basis: 100%;">
                <label for="name" style="width: 100%;">Name</label>
                <input type="text" class="form-control" id="name" name="name" required style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="surname" style="width: 100%;">Surname</label>
                <input type="text" class="form-control" id="surname" name="surname" required style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="age" style="width: 100%;">Age</label>
                <input type="number" class="form-control" id="age" name="age" required min="18" max="150" style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="email" style="width: 100%;">Email</label>
                <input type="email" class="form-control" id="email" name="email" required style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="password" style="width: 100%;">Password</label>
                <input type="password" class="form-control" id="password" name="password" required minlength="8" style="width: 100%;">
            </div>
            <div class="form-group" style="flex-basis: 100%;">
                <label for="accountType" style="width: 100%;">Account Type</label>
                <select class="form-control" id="accountType" name="accountType" required style="width: 100%;">
                    <option value="applicant">Applicant</option>
                    <option value="employer">Employer</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-block mt-4" style="flex-basis: 100%;">Submit</button>
        </form>
    </div>
</@main.layout>
