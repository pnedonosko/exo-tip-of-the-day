const path = require("path");
const merge = require("webpack-merge");
const webpackCommonConfig = require("./webpack.common.js");

// the display name of the war
const app = "tipoftheday";

// add the server path to your server location path
// TODO Use your local path to eXo Platform server folder
const exoServerPath = "/eXo/tip-of-the-day/platform-community-5.3.3";

let config = merge(webpackCommonConfig, {
  output: {
    path: path.resolve(`${exoServerPath}/webapps/${app}/`)
  },
  devtool: "inline-source-map"
});

module.exports = config;
