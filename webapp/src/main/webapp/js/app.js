(function () {

  Vue.component('app', {
    props: ['tip', 'vflag', 'txt'],
    created: function () {
      fetch('/portal/rest/tipoftheday/random').then(response => response.json().then(data => {
        this.tip = data.text;
      })).catch(err => {
        console.log("Failed to request random tip: " + JSON.stringify(err));
      });
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
            '/portal/rest/tipoftheday/tip',
            {
              method: 'POST',
              headers: {
                'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
              },
              body: 'text=' + encodeURIComponent(this.txt)
            }
          ).then(result => result.json().then(data => {
              this.tip = data.text;
            })
          ).catch(err => {
            console.log("Failed to update a tip: " + JSON.stringify(err));
          });
          this.tip = this.txt;
          this.txt = '';
        }
        this.vflag = !this.vflag;
      },
      getRandomTip: function() {
        fetch('/portal/rest/tipoftheday/random').then(response => response.json().then(data => {
          this.tip = data.text;
        })).catch(err => {
          console.log("Failed to get random tip: " + JSON.stringify(err));
        });
      }
    }
  });

  function init() {
    new Vue({
      el: '#tipoftheday-app',
      template: '<app :tip="tip" />',
      data: {
        tip: 'Some tip should be here.',
        vflag: true,
        txt: ''
      }
    });
  }
  return {'init': init}
})();