<#import "../layout.ftlh" as main>

<@main.layout>
    <style>
        body {
            background: whitesmoke;
            font-family: 'Open Sans', sans-serif;
        }

        .container-custom {
            max-width: 960px;
            margin: 10px auto;
            padding: 20px;
        }

        h1 {
            font-size: 20px;
            text-align: center;
            margin: 20px 0 20px;

            small {
                display: block;
                font-size: 15px;
                padding-top: 8px;
                color: gray;
            }
        }

        .avatar-upload {
            position: relative;
            max-width: 205px;
            margin: 40px auto;

            .avatar-edit {
                position: absolute;
                right: 12px;
                z-index: 1;
                top: 10px;

                input {
                    display: none;

                    + label {
                        display: inline-block;
                        width: 34px;
                        height: 34px;
                        margin-bottom: 0;
                        border-radius: 100%;
                        background: #FFFFFF;
                        border: 1px solid transparent;
                        box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.12);
                        cursor: pointer;
                        font-weight: normal;
                        transition: all .2s ease-in-out;

                        &:hover {
                            background: #f1f1f1;
                            border-color: #d6d6d6;
                        }

                        &:after {
                            content: "\f040";
                            font-family: 'FontAwesome';
                            color: #757575;
                            position: absolute;
                            top: 10px;
                            left: 0;
                            right: 0;
                            text-align: center;
                            margin: auto;
                        }
                    }
                }
            }

            .avatar-preview {
                width: 192px;
                height: 192px;
                position: relative;
                border-radius: 100%;
                border: 6px solid #F8F8F8;
                box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.1);

                > div {
                    width: 100%;
                    height: 100%;
                    border-radius: 100%;
                    background-size: cover;
                    background-repeat: no-repeat;
                    background-position: center;
                }
            }
        }
    </style>
    <div class="container-custom">
        <div class="avatar-upload">
            <div class="avatar-edit">
            </div>
            <div class="avatar-preview">
                <div id="imagePreview" style="background-image: url(http://localhost:9999/api/images/${user.id});">
                </div>
            </div>
        </div>
    </div>
    <h1 class="center mb-2">Редактировать профиль</h1>
    <div class="container row d-flex justify-content-center">
        <div class="col-sm-6">
            <div class="card profile border-0 ">
                <div class="card-body">
                    <form action="/${user.email}/edit" method="post">
                        <fieldset>
                            <div class="form-group">
                                <label for="name">Имя</label>
                                <input type="text" id="name" name="name" class="form-control" value="${user.name}"
                                       required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="surname">Фамилия</label>
                                <input type="text" id="surname" name="surname" class="form-control"
                                       value="${user.surname}" required>
                            </div>
                            <div class="form-group">
                                <label for="age">Возраст</label>
                                <input type="number" id="age" name="age" class="form-control" value="${user.age}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" class="form-control" value="${user.email}"
                                       disabled>
                            </div>
                            <div class="form-group">
                                <label for="password">Новый пароль</label>
                                <input type="password" id="password" name="password" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#imagePreview').css('background-image', 'url(' + e.target.result + ')');
                    $('#imagePreview').hide();
                    $('#imagePreview').fadeIn(650);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#imageUpload").change(function () {
            readURL(this);
        });
    </script>
</@main.layout>
