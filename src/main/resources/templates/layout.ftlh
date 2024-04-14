<#macro layout>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>HTTP Server Index Page</title>
        <link rel="stylesheet" href="/static/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
              crossorigin="anonymous">


        <style>
            /* Custom Styles */
            body {
                padding-top: 60px; /* Adjust based on your navbar height */
                display: flex;
                flex-direction: column;
                min-height: 100vh; /* Make sure the body fills the whole viewport */
            }

            .navbar-brand {
                font-size: 1.5rem;
                font-weight: bold;
            }

            .navbar-nav .nav-link {
                font-size: 1.1rem;
                font-weight: 500;
            }

            footer {
                background-color: #343a40;
                color: #fff;
                padding: 20px 0;
                text-align: center;
                margin-top: auto; /* Push the footer to the bottom */
                width: 100%; /* Ensure full width */
            }

            footer p {
                margin-bottom: 0;
            }
        </style>
    </head>
    <body>

    <#--    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">-->
    <#--        <div class="container">-->
    <#--            <a class="navbar-brand ml-1" href="/">Your Logo or Site Name</a>-->
    <#--            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"-->
    <#--                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">-->
    <#--                <span class="navbar-toggler-icon"></span>-->
    <#--            </button>-->

    <#--            <div class="collapse navbar-collapse" id="navbarSupportedContent">-->
    <#--                <ul class="navbar-nav ml-auto">-->
    <#--                    <li class="nav-item">-->
    <#--                        <a class="nav-link" href="/vacancies?page=1">All Vacancies</a>-->
    <#--                    </li>-->
    <#--                    <li class="nav-item">-->
    <#--                        <a class="nav-link" href="/resumes?page=1">All Resumes</a>-->
    <#--                    </li>-->
    <#--                    <li class="nav-item">-->
    <#--                        <a class="nav-link" href="/profile">Profile</a>-->
    <#--                    </li>-->
    <#--                </ul>-->
    <#--            </div>-->
    <#--        </div>-->
    <#--    </nav>-->
    <div class="container">

    </div>
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                <ol class="breadcrumb mb-0 container">
                    <li class="breadcrumb-item "><a href="/vacancies?page=1">All Vacancies</a></li>
                    <li class="breadcrumb-item "><a href="/resumes?page=1">All Resumes</a></li>
                    <li class="breadcrumb-item "><a href="/profile">User Profile</a></li>
                </ol>
            </nav>
        </div>
    </div>

    <div class="container">
        <!-- Content Goes Here -->
        <#nested >
    </div>

    <footer>
        <div class="container">
            <p>&copy; 2024 Your Company Name</p>
        </div>
    </footer>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
    </body>
    </html>

</#macro>
