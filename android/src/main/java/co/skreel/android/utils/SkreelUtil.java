package co.skreel.android.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import co.skreel.android.R;
import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.CardResponse;
import retrofit2.Response;

public class SkreelUtil {
    private static final String TAG = "SkreelUtil";

    public static ProgressDialog progressDialog;

    public static CardResponse deserializeRetrofitErrorBody(Response response){
        CardResponse cardResponse = new CardResponse();
        Log.d(TAG, "onResponse - Status : " + response.code());
        Gson gson = new Gson();
        TypeAdapter<CardResponse> adapter = gson.getAdapter(CardResponse.class);
        try {
            if (response.errorBody() != null)
                cardResponse =
                        adapter.fromJson(
                                response.errorBody().string());
//                        Log.d(TAG, "onResponse: meat " + cardResponse.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cardResponse;
    }

    public static Meta deleteSuccess(){
        return new Meta(204,"No Content");
    }

    public static void showProgressDialog(Context context, boolean cancelable){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }

        progressDialog = new ProgressDialog(context);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.custom_dialog));
        progressDialog.setCancelable(cancelable);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_custom_dialog);
    }

    public static void hideProgressDialog(Activity activity) {
        if (progressDialog != null && !activity.isFinishing())
            progressDialog.dismiss();
        progressDialog = null;
    }

}
