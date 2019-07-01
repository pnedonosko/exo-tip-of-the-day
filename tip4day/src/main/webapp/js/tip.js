function getRandomTip() {
    $.ajax({
        url: '/portal/rest/demo/tip',
        method: 'GET',
        error: function (xhr) {
            console.log(xhr);
        },
        success: function (data) {
            $("#tip").text(data.text);
        }
    });
}

function postTip() {
    $("#demo").toggleClass('in');
    $("#addNewTipButton").text(($("#addNewTipButton").text() === 'Add new tip') ? 'Submitt' : 'Add new tip');
    if ($("#addNewTipButton").text() === 'Add new tip'){
        $.ajax('/portal/rest/demo/tip', {
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                id: 10,
                text: $("#tipFromArea").val()
            }),
            success: function (data) {
                $("#tip").text(data.text);
                },
            error: function (jqXHR) {
                console.log(jqXHR);
            }
        });
    }
    $("#tipFromArea").val('');
}