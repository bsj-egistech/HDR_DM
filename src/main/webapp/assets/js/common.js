window.onload = function() {
    console.log("window Onload");

    pageInit();




}

function gotoPage(idx) {
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = '/main';

    var input1 = document.createElement('input');
    input1.type = 'text';
    input1.name = 'pageIdx';
    input1.value = idx;

    form.appendChild(input1);

    document.body.appendChild(form);

    form.submit();
}

