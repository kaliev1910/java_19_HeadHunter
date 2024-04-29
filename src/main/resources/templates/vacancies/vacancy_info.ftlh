<#import "../layout.ftlh" as main>
<@main.layout>


    <div class="card">
        <div class="card-header bg-primary text-white">
            <h5 class="card-title">Должность: ${vacancy.name}</h5>
            <h6 class="card-title">Категория работы: ${vacancy.categoryId}</h6>
        </div>
        <div class="card-body">
            <p class="card-text">Зарплата: ${vacancy.salary} KGZ</p>
            <p class="card-text">Описание: ${vacancy.description}</p>
            <#if contacts?has_content>
                <h6 class="card-title">Контактные детали: </h6>
                <div class="row justify-content-start">
                    <#list contacts as contact>
                        <#if contact.id?has_content>
                            <div class="col-auto">
                                <p class="card-text">${contact.contactValue} <i class="${contact.typeId}"></i></p>
                            </div>
                        </#if>
                    </#list>
                </div>
            <#else >
                <h6 class="card-title">Контактные детали отсутствуют </h6>
            </#if>
        </div>
    </div>

    <a href="/vacancies?page=0" class="btn btn-primary mt-3 ">Перейти к списку вакансий</a>

    <#assign
    known = SPRING_SECURITY_CONTEXT??
    >
    <#if known>
        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal>

        <#assign authorities = SPRING_SECURITY_CONTEXT.authentication.authorities
        name = user.getUsername()
        />
        <#if authorities?seq_contains("APPLICANT")>

            <!-- Button trigger modal -->
            <button id="apply-btn" type="button" class="btn btn-primary mt-3" data-toggle="modal"
                    data-target="#exampleModal">
                Откликнуться на вакансию
            </button>
            <p id="user-email">${name}</p>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Отправить отклик</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="responseForm">

                                <div class="form-group">
                                    <label for="resumeId">Выберите резюме:</label>
                                    <select class="form-control" id="resumeId" name="resumeId">

                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                            <!-- Кнопка для отправки формы -->
                            <button type="button" id="submitBtn" class="btn btn-primary">Отправить отклик</button>
                        </div>
                    </div>
                </div>
            </div>

        </#if>
    </#if>
    <script>
        const element = document.getElementById("apply-btn");
        element.addEventListener("click", getResumesByEmail);

        function getResumesByEmail(email) {
            var xhr = new XMLHttpRequest();
            let name = document.getElementById("user-email").innerText
            xhr.open('GET', '/api/resume/resumes/email/<#if name?has_content>${name}</#if>', true);

            xhr.onload = function () {
                if (xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    console.log(response);
                    updateResumeList(response);
                } else {
                    console.error('Request failed. Status: ' + xhr.status);
                }
            };

            xhr.onerror = function () {
                console.error('Request failed. Network error.');
            };

            xhr.send();
        }

        function updateResumeList(resumes) {
            const select = document.getElementById('resumeId');
            select.innerHTML = '';
            resumes.forEach(resume => {
                const option = document.createElement('option');
                option.id = "resumeId";
                option.value = resume.id;
                option.textContent = resume.name;
                select.appendChild(option);
            });
        }


        document.getElementById("submitBtn").addEventListener("click", function () {
            const form = document.getElementById('responseForm');
            const formData = new FormData(form);
            const resumeId = formData.get('resumeId');
            fetch(`/api/vacancies/apply/${vacancy.id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(resumeId)
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Отклик успешно отправлен');
                        let modal = document.getElementById('exampleModal');

                        function closeModal() {
                            modal.ariaHidden = 'false';
                            modal.style.display = 'none';
                        }

                        setTimeout(closeModal, 3000);
                    } else {
                        console.error('Ошибка при отправке отклика');
                    }
                })
                .catch(error => {
                    console.error('Сетевая ошибка:', error);
                });
        });

    </script>
</@main.layout>
