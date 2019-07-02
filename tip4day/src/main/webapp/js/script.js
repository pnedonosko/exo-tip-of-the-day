require(
    // configuration
    {
        paths: {
            // the Vue lib
            'Vue': 'https://unpkg.com/vue@2.5.11/dist/vue.min',
            // Vue RequireJS loader
            'require': 'https://unpkg.com/requirejs-vue@1.1.5/requirejs-vue'
        }
    },
    // load libs right now
    ['Vue', 'require'],
    function(Vue, require){
        // now load our single-file-component app
        // syntax: <vue loader module>!<relative path to .vue file>
        new Vue({
            el: '#app',
            data: {
                tip: 'Do not be afraid to take risks, you can change everything.',
                vflag: false,
                text: ''
            },
            methods: {
                postTip: function(){
                    if (this.vflag) {
                        alert("postTip("+ this.text +")");
                        // $.ajax('/portal/rest/demo/tip', {
                        //     type: 'POST',
                        //     contentType: 'application/json',
                        //     data: JSON.stringify({
                        //         id: 10,
                        //         text: this.text
                        //     }),
                        //     success: function (data) {
                        //         this.tip = data.text;
                        //     },
                        //     error: function (jqXHR) {
                        //         console.log(jqXHR);
                        //     }
                        // });
                        this.text = '';
                    }
                    this.vflag = !this.vflag;
                },
                getRandomTip: function(){
                    alert("get")
                    // $.ajax({
                    //     url: '/portal/rest/demo/tip',
                    //     method: 'GET',
                    //     error: function (xhr) {
                    //         console.log(xhr);
                    //     },
                    //     success: function (data) {
                    //         this.tip = data.text;
                    //     }
                    // });
                }
            }
        });
    }
);