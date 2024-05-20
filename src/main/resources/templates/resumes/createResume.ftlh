<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout>
    <h1 class="text-center mt-5 mb-4"><@spring.message 'resume.creation'></@spring.message></h1>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <form action="/resume/create" method="post" id="resumeForm" name="resumeForm">
                        <div class="form-group">
                            <label for="name"><@spring.message 'resume.name'></@spring.message>:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>

                        <div class="form-group">
                            <label for="categoryId"><@spring.message 'resume.category'></@spring.message>:</label>
                            <select class="form-control" id="categoryId" name="categoryId" required>
                                <option value=""><@spring.message 'resume.chooseCategory'></@spring.message></option>
                                <option value="1"><@spring.message 'resume.category.IT'></@spring.message></option>
                                <option value="2"><@spring.message 'resume.category.WebDev'></@spring.message></option>
                                <option value="3"><@spring.message 'resume.category.Finance'></@spring.message></option>
                                <option value="4"><@spring.message 'resume.category.Accounting'></@spring.message></option>
                                <option value="5"><@spring.message 'resume.category.InvestmentBanking'></@spring.message></option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="expectedSalary"><@spring.message 'resume.expectedSalary'></@spring.message>
                                :</label>
                            <input type="number" class="form-control" id="expectedSalary" name="expectedSalary"
                                   required>
                        </div>

                        <div class="form-group" id="education">
                            <label><@spring.message 'resume.education'></@spring.message>:</label>
                            <button type="button" class="btn btn-secondary btn-sm"
                                    onclick="addEducation()"><@spring.message 'resume.addEducation'></@spring.message></button>
                        </div>

                        <div class="form-group" id="experience">
                            <label><@spring.message 'resume.experience'></@spring.message>:</label>
                            <button type="button" class="btn btn-secondary btn-sm"
                                    onclick="addExperience()"><@spring.message 'resume.addExperience'></@spring.message></button>
                        </div>

                        <div class="form-group" id="contact">
                            <label><@spring.message 'resume.contactInfo'></@spring.message>:</label>
                            <button type="button" class="btn btn-secondary btn-sm"
                                    onclick="addContact()"><@spring.message 'resume.addContact'></@spring.message></button>
                        </div>

                        <button type="submit"
                                class="btn btn-primary btn-block"><@spring.message 'resume.creation'></@spring.message></button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        let educationIndex = 0;
        let experienceIndex = 0;
        let contactIndex = 0;

        function addEducation() {
            var educationSection = document.getElementById("education");
            var educationFields = '<div id="education' + educationIndex + '" class="form-group" name="education[' + educationIndex + ']">'
                .concat('<label for="institution"><@spring.message 'resume.education'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="education[' + educationIndex + '].institution" required><br>')
                .concat('<input type="text" class="form-control"  value="" name="education[' + educationIndex + '].resumeId"  hidden="hidden" required><br>')
                .concat('<label for="program"><@spring.message 'resume.program'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="education[' + educationIndex + '].program" required><br>')
                .concat('<label for="degree"><@spring.message 'resume.degree'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="education[' + educationIndex + '].degree" required><br>')
                .concat('<label for="startDate"><@spring.message 'resume.startDate'></@spring.message>:</label>')
                .concat('<input type="date" class="form-control" name="education[' + educationIndex + '].startDate" required><br>')
                .concat('<label for="endDate"><@spring.message 'resume.endDate'></@spring.message>:</label>')
                .concat('<input type="date" class="form-control" name="education[' + educationIndex + '].endDate" required><br>')
                .concat('<button type="button" class="btn btn-danger btn-sm" onclick="removeEducation(' + educationIndex + ')"><@spring.message 'resume.removeEducation'></@spring.message></button>')
                .concat('</div>');
            educationSection.insertAdjacentHTML('beforeend', educationFields);
            educationIndex++;
        }


        function addExperience() {
            var experienceSection = document.getElementById("experience");
            var experienceFields = '<div id="experience' + experienceIndex + '" class="form-group">'
                .concat('<label for="companyName"><@spring.message 'resume.companyName'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="experience[' + experienceIndex + '].companyName" required><br>')
                .concat('<label for="position"><@spring.message 'resume.position'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="experience[' + experienceIndex + '].position" required><br>')
                .concat('<label for="responsibilities"><@spring.message 'resume.responsibilities'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="experience[' + experienceIndex + '].responsibilities" required><br>')
                .concat('<label for="years"><@spring.message 'resume.years'></@spring.message> (в годах):</label>')
                .concat('<input type="number" class="form-control" name="experience[' + experienceIndex + '].years" required><br>')
                .concat('<button type="button" class="btn btn-danger btn-sm" onclick="removeExperience(' + experienceIndex + ')"><@spring.message 'resume.removeExperience'></@spring.message></button>')
                .concat('</div>');
            experienceSection.insertAdjacentHTML('beforeend', experienceFields);
            experienceIndex++;
        }


        function addContact() {
            var contactSection = document.getElementById("contact");
            var contactFields = '<div id="contact' + contactIndex + '" class="form-group">'
                .concat('<label for="contactValue"><@spring.message 'resume.contactValue'></@spring.message>:</label>')
                .concat('<input type="text" class="form-control" name="contactInfo[' + contactIndex + '].contactValue" required><br>')
                .concat('<label for="typeId"><@spring.message 'resume.typeId'></@spring.message>:</label>')
                .concat('<select class="form-control" name="contactInfo[' + contactIndex + '].typeId">')
                .concat('<option value="">--<@spring.message 'contact.type.selectType'></@spring.message>--</option>')
                .concat('<option value="1"><@spring.message 'contact.type.phone'></@spring.message></option>')
                .concat('<option value="2"><@spring.message 'contact.type.email'></@spring.message></option>')
                .concat('<option value="3"><@spring.message 'contact.type.linkedin'></@spring.message></option>')
                .concat('<option value="4"><@spring.message 'contact.type.telegram'></@spring.message></option>')
                .concat('</select><br>')
                .concat('<button type="button" class="btn btn-danger btn-sm" onclick="removeContact(' + contactIndex + ')"><@spring.message 'resume.removeContact'></@spring.message></button>')
                .concat('</div>');
            contactSection.insertAdjacentHTML('beforeend', contactFields);
            contactIndex++;
        }


        function removeEducation(index) {
            var educationToRemove = document.getElementById("education" + index);
            educationToRemove.remove();
        }

        function removeExperience(index) {
            var experienceToRemove = document.getElementById("experience" + index);
            experienceToRemove.remove();
        }

        function removeContact(index) {
            var contactToRemove = document.getElementById("contact" + index);
            contactToRemove.remove();
        }
    </script>

</@main.layout>
