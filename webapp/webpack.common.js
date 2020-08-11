const path = require("path");

let config = {
  context: path.resolve(__dirname, "."),
  // set the entry point of the application
  // can use multiple entry
  entry: {
    "tipoftheday-app": "./src/main/webapp/vue-app/app.js"
  },
  output: {
    filename: "js/[name].bundle.js",
    libraryTarget: "amd"
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: ["vue-style-loader", "css-loader"]
      },
      {
        test: /\.less$/,
        use: ["style-loader", "css-loader", "less-loader"]
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: [
          "babel-loader",
          "eslint-loader"
        ]
      },
      {
        test: /\.vue$/,
        use: [
          "vue-loader",
          "eslint-loader"
        ]
      }
    ]
  },
  externals: {
    vue: "Vue",
    vuetify: "Vuetify",
  }
};

module.exports = config;