<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <h2>Registration Form</h2>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="surname">Surname:</label>
                <input type="text" class="form-control" id="surname" name="surname" required>
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="number" class="form-control" id="age" name="age" required min="18" max="150">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required minlength="8">
            </div>
            <div class="form-group">
                <label for="accountType">Account Type:</label>
                <select class="form-control" id="accountType" name="accountType" required>
                    <option value="applicant">Applicant</option>
                    <option value="employer">Employer</option>
                </select>
            </div>
            <button  type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</@main.layout>
