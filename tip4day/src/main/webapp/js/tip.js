// $(document).ready(function () {

    console.log("Hello TIP.js");

    $("#nextTip").click(function () {
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
    });


    $("#loadTip").click(function () {
        $.ajax('/portal/rest/demo/tip', {
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                id: 10,
                text: $("#tipFromArea").val()
            }),
            success: function () {
                $("#tip").text(data.text);
            },
            error: function (jqXHR) {
                console.log(jqXHR);
            }
        });
    });


// });