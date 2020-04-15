package br.com.unipar.sendmessages.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {

    public static boolean check(Context context, String permission) {

        int myVersion = Build.VERSION.SDK_INT;

        //Antes da Lollipop não precisa requisitar permissão...
        if (myVersion < Build.VERSION_CODES.LOLLIPOP_MR1) {
            return true;
        }

        int checkPermission = ContextCompat.checkSelfPermission(context, permission);

        //Requisita permissão se não tiver ainda...
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, 1);
        }

        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }
}
