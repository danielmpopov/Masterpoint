function validate() {
    var x, text;

    x = document.getElementById("pricePerUnit").value;

    if (isNaN(x) || x < 0.001 || x > 10000) {
        text = "Невалидна цена на услуга! Моля въведете положително число";
        document.getElementById("demo").innerHTML = text;
        return false
    } else {
        text = "Input OK";
    }

};


function myFunction() {
    var x, text;

    x = document.getElementById("pricePerUnit").value;

    if (isNaN(x) || x < 0.001 || x > 10000) {
        text = "Невалидна цена на услуга! Моля въведете положително число";
        document.getElementById("demo").innerHTML = text;
        return false
    } else {
        text = "Input OK";
    }

};

$('#myModal').on('show.bs.modal', function (event) {
    document.getElementById("myForm").reset();
    document.getElementById("demo").innerHTML = "";
    document.getElementById("pricePerUnit").disabled = false;
    var button = $(event.relatedTarget);
    var subCategory = button.data('subcategory');
    var unit = button.data('unit');
    var action = button.data('action');
    var repairWorkId = button.data('rwid');
    var pricePerUnit = button.data('price');
    var modal = $(this);
    if (action === "add") {
        modal.find('.modal-title').text('Добавете ' + subCategory + ' към Вашите услуги');
        document.getElementById("myForm").action = '/repairworks/add/' + subCategory;
    } else if (action === "edit" ) {
        modal.find('.modal-title').text('Редактирайте ' + subCategory );
        document.getElementById("myForm").action = '/repairworks/edit/' + repairWorkId;
        document.getElementById("pricePerUnit").value = pricePerUnit;
    } else {
        modal.find('.modal-title').text('Изтрийте ' + subCategory );
        document.getElementById("myForm").action = '/repairworks/delete/' + repairWorkId;
        document.getElementById("pricePerUnit").value = pricePerUnit;
        document.getElementById("pricePerUnit").disabled = true;
    }
    modal.find('.input-group-text').text(unit)

});



