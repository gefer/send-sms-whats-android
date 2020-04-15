package br.com.unipar.sendmessages.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class Whats {

    public static void sendWhats(Activity activity, String mensagem) throws IllegalArgumentException {

        PackageManager packageManager = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        List<ResolveInfo> resolvedInfoList = packageManager.queryIntentActivities(intent, 0);

        boolean found = false;

        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.whatsapp")) {
                intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("O WhatsApp não está instalado nesse aparelho!");
        }

        intent.setPackage("com.whatsapp");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);
        activity.startActivity(intent);
    }
}
