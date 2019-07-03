
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/some.css"/>

<div id="vueSample">
    <script>
        require(['SHARED/vueSampleBundle'], function(sampleApp) {
            sampleApp.init();
        });
    </script>
</div>
