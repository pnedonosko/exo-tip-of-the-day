(function () {

    Vue.component('app', {
        props: ['tip', 'vflag', 'txt'],
        created: function () {
            fetch('/portal/rest/demo/tip').then(response => response.json().then(data => {
                this.tip = data.text;
            }))
        },
        template:
            '<div class="container">' +
                '<h2>Tip for day</h2>' +
                '<div id="tip">' +
                    '{{ tip }}' +
                '</div>' +
                '<div v-if="vflag">' +
                    '<div>' +
                        '<p>Input your tip</p>' +
                        '<textarea maxlength="350" v-model=\'txt\'></textarea>' +
                    '</div>' +
                '</div>' +
                '<div id="btn-block">' +
                    '<a v-if="!vflag" class="btn btn-info" @click="getRandomTip">Next tip</a>' +
                    '<a class="btn btn-info" @click="postTip">{{vflag?\'Submit\':\'Add new tip\'}}</a>' +
                '</div>' +
            '</div>',
        methods: {
            postTip: function () {
                if (this.vflag) {
                    fetch(
                        '/portal/rest/demo/tip',
                        {
                            method: 'POST',
                            headers: {'Content-Type': 'application/json'},
                            body: JSON.stringify({id: 10, text: this.txt})
                        }
                    ).then(result => result.json().then(data => {
                            this.tip = data.text;
                        }
                    ));
                    this.tip = this.txt;
                    this.txt = '';
                }
                this.vflag = !this.vflag;
            },
            getRandomTip: function () {
                fetch('/portal/rest/demo/tip').then(response => response.json().then(data => {
                    this.tip = data.text;
                }))
            }
        },

    });

    function init() {
        new Vue({
            el: '#vueSample',
            template: '<app :tip="tip" />',
            data: {
                tip: 'Some tip was be here.',
                vflag: true,
                txt: ''
            }
        });
    }
    return {'init': init}
})();