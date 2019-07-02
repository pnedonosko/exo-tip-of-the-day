<%@ page import="java.util.ResourceBundle" %>

<%
    String contextPath = request.getContextPath();
%>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/some.css"/>

<%--<script src="<%=contextPath%>/js/require.js" data-main="js/some"></script>--%>
<%--<script data-main="js/tip" src="js/require.js"></script>--%>

<script src="${pageContext.request.contextPath}/js/require.js" data-main="<%=contextPath%>/js/script"></script>

<%--<script src="<%=contextPath%>/js/jquery.min.js"></script>--%>
<%--<script src="<%=contextPath%>/js/vue.js"></script>--%>
<%--<script src="<%=contextPath%>/js/tip.js"></script>--%>

<div class="container"  id="app">
    <h2 id="alarm">Tip for day</h2>
    <div id="tip">
        {{ tip }}
    </div>
    <div v-if="vflag">
        <div>
            <p>Input your tip</p>
            <textarea maxlength="350" v-model='text'></textarea>
        </div>
    </div>
    <div >
        <a v-if="!vflag" class="btn btn-info" @click="getRandomTip">Next tip</a>
        <a class="btn btn-info" @click="postTip">{{vflag?'Submit':'Add new tip'}}</a>
    </div>
</div>
<%--<script>--%>
<%--    new Vue({--%>
<%--        el: '#app',--%>
<%--        data: {--%>
<%--            tip: 'Do not be afraid to take risks, you can change everything.',--%>
<%--            vflag: false,--%>
<%--            text: ''--%>
<%--        },--%>
<%--        methods: {--%>
<%--            postTip: function(){--%>
<%--                if (this.vflag) {--%>
<%--                    $.ajax('/portal/rest/demo/tip', {--%>
<%--                        type: 'POST',--%>
<%--                        contentType: 'application/json',--%>
<%--                        data: JSON.stringify({--%>
<%--                            id: 10,--%>
<%--                            text: this.text--%>
<%--                        }),--%>
<%--                        success: function (data) {--%>
<%--                            this.tip = data.text;--%>
<%--                        },--%>
<%--                        error: function (jqXHR) {--%>
<%--                            console.log(jqXHR);--%>
<%--                        }--%>
<%--                    });--%>
<%--                    this.text = '';--%>
<%--                }--%>
<%--                this.vflag = !this.vflag;--%>
<%--            },--%>
<%--            getRandomTip: function(){--%>
<%--                $.ajax({--%>
<%--                    url: '/portal/rest/demo/tip',--%>
<%--                    method: 'GET',--%>
<%--                    error: function (xhr) {--%>
<%--                        console.log(xhr);--%>
<%--                    },--%>
<%--                    success: function (data) {--%>
<%--                        this.tip = data.text;--%>
<%--                    }--%>
<%--                });--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>