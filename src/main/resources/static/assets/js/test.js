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
        success: function (res) {
            console.log(res);
            //console.log(res.pomppu);

            $(res.pomppu).each(function(idx, el) {
                //console.log(idx);
                //console.log(el);

                $("#container").append("<div class='row'>" + el.post_title + "</div>");


            });
                


        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });




}







