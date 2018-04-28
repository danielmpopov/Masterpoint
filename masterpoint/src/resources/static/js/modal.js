
function myFunction() {
    var x, text;

    x = document.getElementById("pricePerUnit").value;

    if (isNaN(x) || x < 0.001 || x > 10000) {
        text = "Невалидна цена на услуга! Моля въведете цена от 0.001 до 1000";
        document.getElementById("error").innerHTML = text;
        return false
    } else {
        text = "Input OK";
    }

};

$('#myModal').on('show.bs.modal', function (event) {
    document.getElementById("myForm").reset();
    document.getElementById("error").innerHTML = "";
    document.getElementById("pricePerUnit").disabled = false;

    var button = $(event.relatedTarget);
    var subCategory = button.data('subcategory');
    var unit = button.data('unit');
    var action = button.data('action');
    var repairWorkId = button.data('rwid');
    var pricePerUnit = button.data('price');
    var buttonText = "";
    var buttonClass = "";
    var modal = $(this);
    if (action === "add") {
        modal.find('.modal-title').text('Добавете ' + subCategory + ' към Вашите услуги');
        buttonClass="btn-info";
        buttonText="Добави";
        document.getElementById("myForm").action = '/repairworks/add/' + subCategory;
    } else if (action === "edit" ) {
        modal.find('.modal-title').text('Редактирайте ' + subCategory );
        buttonClass="btn-warning";
        buttonText ="Редактирай";
        document.getElementById("myForm").action = '/repairworks/edit/' + repairWorkId;
        document.getElementById("pricePerUnit").value = pricePerUnit;
    } else {
        modal.find('.modal-title').text('Изтрийте ' + subCategory );
        buttonClass="btn-danger";
        buttonText = "Изтрий";
        document.getElementById("myForm").action = '/repairworks/delete/' + repairWorkId;
        document.getElementById("pricePerUnit").value = pricePerUnit;
        document.getElementById("pricePerUnit").disabled = true;
    }
    modal.find('.input-group-text').text(unit)
    document.getElementById("myButton").innerHTML=buttonText;
    document.getElementById("myButton").classList.add(buttonClass);

});

