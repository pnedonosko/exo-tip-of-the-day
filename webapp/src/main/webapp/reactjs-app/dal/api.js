
export const tipApi = {
    getRandomTip(){
        return fetch('/portal/rest/tipoftheday/random');
    },
    addTip(text){
        return fetch(
          '/portal/rest/tipoftheday/tip',
          {
            method: 'POST',
            headers: {
              'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: 'text=' + encodeURIComponent(text)
          }
        )
    }
};
