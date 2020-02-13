package co.skreel.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;


import co.skreel.android.R;

public class CardUtil {

    public static boolean isAllDigits(String pan){
        if(pan == null)
            return false;

        for (char c : pan.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public final static String KEY = "B374A26A71490437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CCF";

    public static boolean hasMonthPassed(int year, int month){
        return true;
    }

    public static String ecrypt_decrypt(Context androidContextObject, String encrypt, Object[] functionParams){

        try {
            Resources resources = androidContextObject.getResources();
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);

            String source = properties.getProperty("jsExecute");
            //String encrypt = "encrypt";
            //Object[] functionParams = new Object[]{"tunji akinde", KEY, 256};
            org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();

            rhino.setOptimizationLevel(-1);
            Scriptable scope = rhino.initStandardObjects();

            rhino.evaluateString(scope, source, "JavaScript", 1, null);
            Object obj = scope.get(encrypt, scope);

            if (obj instanceof Function) {

                Function function = (Function) obj;
                Object result = function.call(rhino, scope, scope, functionParams);
                //Object[] decryptFunctionParams = new Object[]{"EAKYopE+8ViqpCz+mIPCszU=", KEY, 256};
                String response = org.mozilla.javascript.Context.toString(result);

                return response;

            } else {
                Log.i("error", "not a function");
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            org.mozilla.javascript.Context.exit();
        }

        return null;
    }

}
