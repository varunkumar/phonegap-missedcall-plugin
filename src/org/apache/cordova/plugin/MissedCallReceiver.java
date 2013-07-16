package org.apache.cordova.plugin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

public class MissedCallReceiver extends BroadcastReceiver {
    
    private CallbackContext callback_receive;
    private boolean isReceiving = true;
    
    private static String prevCallState = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String state = bundle.getString("state");
        JSONObject result = new JSONObject();
        if (prevCallState.equals("RINGING") && state.equals("IDLE")) {

            Cursor cursor = context.getContentResolver().query(
                    android.provider.CallLog.Calls.CONTENT_URI, null, null,
                    null, android.provider.CallLog.Calls.DATE + " DESC ");

            int numberColumnId = cursor
                    .getColumnIndex(android.provider.CallLog.Calls.NUMBER);
            int nameColumnId = cursor
                    .getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME);
            int typeColumnId = cursor
                    .getColumnIndex(android.provider.CallLog.Calls.TYPE);

            if (cursor.moveToFirst()) {

                String contactNumber = cursor.getString(numberColumnId);
                int type = cursor.getInt(typeColumnId);
                String contactName = cursor.getString(nameColumnId);
                if (type == android.provider.CallLog.Calls.MISSED_TYPE) {

                    if(this.isReceiving && this.callback_receive != null) {

                        try {
                            result.put("contactNumber", contactNumber);
                            result.put("contactName", contactName);
                        } catch (JSONException e) {
                        }
                        PluginResult res = new PluginResult(PluginResult.Status.OK, result);
                        res.setKeepCallback(true);
                        callback_receive.sendPluginResult(res);
                    }
                }
            }
        }
        prevCallState = state;
    }
    
    public void startReceiving(CallbackContext ctx) {
        this.callback_receive = ctx;
        this.isReceiving = true;
    }

    public void stopReceiving() {
        this.callback_receive = null;
        this.isReceiving = false;
    }
}