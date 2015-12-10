  var exec = require('cordova/exec');
  
  var MissedCallPlugin = function() {};

  MissedCallPlugin.prototype.startReception = function(successCallback,failureCallback) {
    return exec(successCallback, failureCallback, 'MissedCallPlugin', 'StartReception', []);
  }

  MissedCallPlugin.prototype.stopReception = function(successCallback,failureCallback) {
    return exec(successCallback, failureCallback, 'MissedCallPlugin', 'StopReception', []);
  }

  module.exports = new MissedCallPlugin();
