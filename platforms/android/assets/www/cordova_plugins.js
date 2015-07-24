cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/cordova-plugin-whitelist/whitelist.js",
        "id": "cordova-plugin-whitelist.whitelist",
        "runs": true
    },
    {
        "file": "plugins/com.maleficus.hybrid.MaleficusJSBridgePlugin/www/MaleficusJSBridgePlugin.js",
        "id": "com.maleficus.hybrid.MaleficusJSBridgePlugin.MaleficusJSBridgePlugin",
        "clobbers": [
            "MaleficusJSBridgePlugin"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.0.0",
    "com.maleficus.hybrid.MaleficusJSBridgePlugin": "0.2.11"
}
// BOTTOM OF METADATA
});