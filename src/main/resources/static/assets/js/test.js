console.log("test3");

function getData() {

    var postData = {
        name: 'John',
        age: 30
    };


    $.ajax({
        url: '/getHotdealData',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(postData),
        success: function (response) {
            console.log(response);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });




}







