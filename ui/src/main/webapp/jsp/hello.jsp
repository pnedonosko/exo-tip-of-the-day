
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container">
    <h2>Tip for day</h2>
    <div id="tip" style="margin: 10px 25px; font-style: italic">
        Some tip
    </div>
    <script>
        console.log("load first tip");
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
    </script>
    <div id="demo" class="collapse">
        <div>
            <p>Input your tip</p>
            <textarea id="tipFromArea" maxlength="350" style="width: 100%; height: 70px; resize: none" name="text" r></textarea>
        </div>
    </div>
    <div style="display: flex; justify-content: space-around; margin: 15px 0 0 0">
        <a class="btn btn-info" id="nextTip">Next tip</a>
        <script>
            console.log("next button click");
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
        </script>
        <a href="#demo" id="addNewTipButton" class="btn btn-info">Add new tip</a>
        <script>
            console.log("send tip button click");
            $("#addNewTipButton").click(function () {
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
                        success: function () {
                            $("#tip").text(data.text);
                        },
                        error: function (jqXHR) {
                            console.log(jqXHR);
                        }
                    });
                }
                $("#tipFromArea").val('');
            });
        </script>
    </div>
</div>

</body>