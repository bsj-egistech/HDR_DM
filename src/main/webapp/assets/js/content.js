console.log("hello");

function pageInit() {
    console.log("pageInit");

    bindingEvt();    
}

function bindingEvt() {
    $("#deal-list tbody tr").on("click", function(e) {
        var url = this.dataset.link;
        window.open(url);
    });

}


