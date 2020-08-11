/**
 * Sample app: Tip of the Day
 */
import tipoftheday from "./components/TipOfTheDay.vue";

// Use Vuetify
Vue.use(Vuetify);
Vue.component("tipoftheday", tipoftheday);
const vuetify = new Vuetify({
  dark: true,
  iconfont: ""
});

// getting language of user
const lang = (eXo && eXo.env && eXo.env.portal && eXo.env.portal.language) || "en";
const localePortlet = "locale.portlet";
const resourceBundleName = "TipOfTheDay";
// Actual i18 file for EN located in services/src/main/resources/locale/portlet/TipOfTheDay_en.xml
const url = `${eXo.env.portal.context}/${eXo.env.portal.rest}/i18n/bundle/${localePortlet}.${resourceBundleName}-${lang}.json`;

export function init() {
  // getting locale ressources
  exoi18n.loadLanguageAsync(lang, url).then(i18n => {
    // init Vue app when locale ressources are ready
    new Vue({
      render: h =>
        h(tipoftheday, { 
          props: { 
            i18n: i18n, 
            language: lang, 
            resourceBundleName: resourceBundleName 
          }
        }),
      i18n,
      vuetify
    }).$mount("#tipoftheday-app");
  });
}
