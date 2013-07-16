phonegap-missedcall-plugin
==========================

This Android Phonegap plugin will notify your app whenever there is a missed call. This plugin was successfully tested with Phonegap 2.9 and Android 4.2.2 (on a Samsung Galaxy Nexus device).

Adding the plugin to project
----------------------------
- Make sure you are using Phonegap >2.0
- Copy 'assets/www/js/plugin/MissedCallPlugin.js' to your project's www folder.
- Include a reference to your JS file in your index.html. (This must be done after including cordova.js)
- Copy the Java files to your platform's src folder. 
- Add reference to the plugin in platform/android/res/xml/config.xml
```<plugin name="MissedCallPlugin" value="org.apache.cordova.plugin.MissedCallPlugin"/>```
- Ensure that the manifest file contains necessary permissions for reading the state of the phone and the call logs. 
```<uses-permission android:name="android.permission.READ_PHONE_STATE" />
	<uses-permission android:name="android.permission.READ_CALL_LOG" />
```

Using the plugin
----------------
To instantiate the plugin object:
```javascript
var missedCallPlugin = cordova.require('cordova/plugin/missedcallplugin');
```

### startReception ###
Start the missed call receiver waiting for missed calls
The success callback function will be called everytime a call is missed.
The callback will receive a JSON object containing the contactName and contactNumber. You can add more fields from MissedCallReceiver.java.
The error callback is called if an error occurs.

Example:
```javascript
  missedCallPlugin.startReception (function(msg) {
    alert(msg);
  }, function() {
    alert("Error while receiving call details");
  });
```

### stopReception ###
Stop the missed call receiver

Example:
```javascript
  missedCallPlugin.stopReception (function() {
    alert("Correctly stopped");
  }, function() {
    alert("Error while stopping the call receiver");
  });
```

License
-------
The source code is available [here](https://github.com/varunkumar/phonegap-missedcall-plugin) under [MIT licence](http://varunkumar.mit-license.org/). Please send any bugs, feedback, complaints, patches to me at varunkumar[dot]n[at]gmail[dot]com.

-- [Varun](http://www.varunkumar.me)

Last Modified: Tue Jul 16 08:56:36 IST 2013
