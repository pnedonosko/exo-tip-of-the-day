
require.config({
    baseUrl: "js",
    paths: {
        jquery: "jquery.min.js"
    }
});

function myClick(){
    require(["jquery"], function(){
        alert("Done");
        console.log("It work!!!");
    });
}