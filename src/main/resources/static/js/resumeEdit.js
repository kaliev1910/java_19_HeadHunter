function showEducation(resumeId, degree, program, institution, startDate, endDate) {
    let educationSection = document.getElementById("educationContainer");
    let educationFields = '<div id="education' + educationIndex + '" class="form-group">'
        + '<label for="institution">Учебное заведение:</label>'
        + '<input type="text" class="form-control" name="education[' + educationIndex + '].institution" value="' + institution + '" required><br>'
        + '<label for="program">Программа/Специализация:</label>'
        + '<input type="text" class="form-control" name="education[' + educationIndex + '].program" value="' + program + '" required><br>'
        + '<label for="degree">Степень:</label>'
        + '<input type="text" class="form-control" name="education[' + educationIndex + '].degree" value="' + degree + '" required><br>'
        + '<label for="startDate">Дата начала:</label>'
        + '<input type="date" class="form-control" name="education[' + educationIndex + '].startDate" value="' + startDate + '" required><br>'
        + '<label for="endDate">Дата окончания:</label>'
        + '<input type="date" class="form-control" name="education[' + educationIndex + '].endDate" value="' + endDate + '" required><br>'
        + '<button type="button" class="btn btn-danger btn-sm" onclick="removeEducation(' + educationIndex + ')">Удалить образование</button>'
        + '</div>';
    educationSection.insertAdjacentHTML('beforeend', educationFields);
    educationIndex++;
}
function showExperience(companyName, position, responsibilities, startDate, endDate) {
    let experienceSection = document.getElementById("experienceContainer");
    let experienceFields = '<div id="experience' + experienceIndex + '" class="form-group">'
        + '<label for="companyName">Название компании:</label>'
        + '<input type="text" class="form-control" name="experience[' + experienceIndex + '].companyName" value="' + companyName + '" required><br>'
        + '<label for="position">Должность:</label>'
        + '<input type="text" class="form-control" name="experience[' + experienceIndex + '].position" value="' + position + '" required><br>'
        + '<label for="responsibilities">Обязанности:</label>'
        + '<input type="text" class="form-control" name="experience[' + experienceIndex + '].responsibilities" value="' + responsibilities + '" required><br>'
        + '<label for="startDate">Дата начала:</label>'
        + '<input type="date" class="form-control" name="experience[' + experienceIndex + '].startDate" value="' + startDate + '" required><br>'
        + '<label for="endDate">Дата окончания:</label>'
        + '<input type="date" class="form-control" name="experience[' + experienceIndex + '].endDate" value="' + endDate + '" required><br>'
        + '<button type="button" class="btn btn-danger btn-sm" onclick="removeExperience(' + experienceIndex + ')">Удалить опыт</button>'
        + '</div>';
    experienceSection.insertAdjacentHTML('beforeend', experienceFields);
    experienceIndex++;
}
function showContact(resumeId, contactValue, typeId) {
    let contactSection = document.getElementById("contactContainer");
    let contactFields = '<div id="contact' + contactIndex + '" class="form-group">'
        // + '<label for="contactValue">Контактная информация:</label>'
        + '<input type="text" class="form-control" name="contact[' + contactIndex + '].resumeId" value="' + resumeId + '" hidden="hidden"><br>'
        + '<label for="contactValue">Контактная информация:</label>'
        + '<input type="text" class="form-control" name="contact[' + contactIndex + '].contactValue" value="' + contactValue + '" required><br>'
        + '<label for="typeId">Тип контакта:</label>'
        + '<select class="form-control" name="contact[' + contactIndex + '].typeId" required>'
        + '<option value="">--Выберите тип контакта--</option>'
        + '<option value="1" ' + (typeId === 1 ? 'selected' : '') + '>Телефон</option>'
        + '<option value="2" ' + (typeId === 2 ? 'selected' : '') + '>Email</option>'
        + '<option value="3" ' + (typeId === 3 ? 'selected' : '') + '>LinkedIn</option>'
        + '<option value="4" ' + (typeId === 4 ? 'selected' : '') + '>Telegram</option>'
        + '</select><br>'
        + '<button type="button" class="btn btn-danger btn-sm" onclick="removeContact(' + contactIndex + ')">Удалить контакт</button>'
        + '</div>';
    contactSection.insertAdjacentHTML('beforeend', contactFields);
    contactIndex++;
}


