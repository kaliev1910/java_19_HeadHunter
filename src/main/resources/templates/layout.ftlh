<#import "/spring.ftl" as spring>
<#macro layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title><@spring.message 'layout.title'></@spring.message></title>
    <link rel="stylesheet" href="../static/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous">

    <script type="text/javascript" src="/static/js/main.js"></script>


    <style>

        body {
            padding-top: 60px;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
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
            margin-top: auto;
            width: 100%;
        }

        footer p {
            margin-bottom: 0;
        }
    </style>
</head>
<body>

<div class="container">

</div>

<div class="row">
    <div class="col">
        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
            <ol class="breadcrumb mb-0 container">
                <li class="breadcrumb-item "><a href="/"><@spring.message 'layout.mainPage'></@spring.message></a></li>
                <li class="breadcrumb-item "><a
                            href="/vacancies?page=1"><@spring.message 'layout.allVacancies'></@spring.message></a></li>
                <li class="breadcrumb-item "><a
                            href="/resumes?page=1"><@spring.message 'layout.allResumes'></@spring.message></a></li>
                <li class="breadcrumb-item "><a href="/chat"><@spring.message 'layout.chat'/></a></li>


                <#assign
                known = SPRING_SECURITY_CONTEXT??
                >
                <#if known>
                    <#assign
                    user = SPRING_SECURITY_CONTEXT.authentication.principal
                    name = user.getUsername()
                    >
                    <li class="breadcrumb-item "><a
                                href="/profile"><@spring.message 'layout.hi'>, ${name}</@spring.message></a></li>
                    <li id="logout" class="breadcrumb-item "><a
                                href="/logout"><@spring.message 'layout.logout'></@spring.message></a></li>
                <#else>
                    <li class="breadcrumb-item "><a href="/login"><@spring.message 'layout.login'></@spring.message></a>
                    </li>
                    <li class="breadcrumb-item "><a
                                href="/register"><@spring.message 'layout.signup'></@spring.message></a></li>
                </#if>


            </ol>
        </nav>
    </div>
    <div class="right-buttons">
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" id="lk" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <i class="flag-united-kingdom flag m-0"></i>
                <@spring.message 'navbar.navi.lang'/>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="?lang=en">English</a></li>
                <li><a class="dropdown-item" href="?lang=ru">Русский</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">

    <#nested >
</div>

<footer>
    <div class="container">
        <p>&copy;HH project</p>
    </div>
</footer>

<script>
    let logout = document.getElementById('logout')
    if (logout) {
        logout.addEventListener('click', () => localStorage.removeItem('user'))
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>

</body>


</#macro>
