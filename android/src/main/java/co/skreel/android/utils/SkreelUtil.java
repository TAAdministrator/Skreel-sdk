package co.skreel.android.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.CardResponse;
import retrofit2.Response;

public class SkreelUtil {
    private static final String TAG = "SkreelUtil";

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
}
