package br.com.unipar.sendmessages.util;

import android.content.Context;
import android.telephony.SmsManager;

public class Sms {

    public static void send(Context ctx, String phoneNumber, String msg) {

        if (!Permission.check(ctx, android.Manifest.permission.SEND_SMS)) {
            return;
        }

        SmsManager sms = SmsManager.getDefault();

        if (msg.length() < 160) {
            sms.sendTextMessage(phoneNumber, null, msg, null, null);
        }
    }

}
