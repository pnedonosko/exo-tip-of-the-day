<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div class="container">
    <h2>Tip for day</h2>
    <div id="tip">
        Some tip
    </div>
    <script>
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
    <a class="btn btn-info" id="nextTip">Next tip</a>
    <script>
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
    <a href="#demo" class="btn btn-info" data-toggle="collapse">Add new tip</a>
    <div id="demo" class="collapse">
        <div>
            <p>Input your tip</p>
            <textarea id="tipFromArea" rows="10" cols="45" name="text" style="resize: none"></textarea>
        </div>
        <a href="#demo" id="loadTip" class="btn btn-info" data-toggle="collapse">Submit</a>
        <script>
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
                        $("#tipFromArea").val('');
                    },
                    error: function (jqXHR) {
                        console.log(jqXHR);
                    }
                });
            });
        </script>
    </div>
</div>

</body>
</html>