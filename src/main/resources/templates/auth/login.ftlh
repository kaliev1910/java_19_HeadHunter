<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8 col-sm-10">
                <div class="card shadow-lg rounded-lg mt-5">
                    <div class="card-header bg-primary text-white">
                        <h3 class="text-center font-weight-bold my-3">Login</h3>
                    </div>
                    <div class="card-body">
                        <form action="/login" method="post">
                            <div class="form-group">
                                <label for="username" class="font-weight-bold">Username:</label>
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="Enter your username" required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="font-weight-bold">Password:</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Enter your password" required>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary btn-block">Login</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <p>Don't have an account? <a href="/register">Register</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
