package co.skreel.android.networking;

import android.content.Context;
import android.util.Log;

import java.util.List;

import co.skreel.android.interfaces.cardlisteners.CardDeletedListener;
import co.skreel.android.interfaces.cardlisteners.CardRetrievedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerListener;
import co.skreel.android.interfaces.GetDataService;
import co.skreel.android.interfaces.cardlisteners.CardCreatedListener;
import co.skreel.android.interfaces.cardlisteners.CardUpdatedListener;
import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.Card;
import co.skreel.android.models.banks.AllBanksResponse;
import co.skreel.android.models.cards.CardResponse;
import co.skreel.android.models.customer.Customer;
import co.skreel.android.models.customer.CustomerResponse;
import co.skreel.android.utils.SkreelUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkreelSDK {
    private static final String TAG = "SkreelSDK";
    private static SkreelSDK instance = null;

    private static final String BASE_URL = "http://104.131.174.54:2888/api/v1.0/";

    //for Retrofit API
    private Retrofit retrofit;
    private GetDataService service;


    private SkreelSDK(Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GetDataService.class);
    }

    public static synchronized SkreelSDK getInstance(Context context){
        if(null == instance) {
            instance = new SkreelSDK(context);
            Log.d(TAG, "getInstance: Instance has been gotten");
        }
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized SkreelSDK getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(SkreelSDK.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }


    /*
    *
    * LIST OF BANKS
    *
    */

    public static void getBanks(){
           Call<List<AllBanksResponse>> bankCall = getInstance().service.getBanks();
           bankCall.enqueue(new Callback<List<AllBanksResponse>>() {
               @Override
               public void onResponse(Call<List<AllBanksResponse>> call, Response<List<AllBanksResponse>> response) {
                   Log.d(TAG, "onResponse: " + response.body());
               }

               @Override
               public void onFailure(Call<List<AllBanksResponse>> call, Throwable t) {
                   Log.d(TAG, "onFailure: " + t.getMessage());
                   Log.d(TAG, "onFailure: " + t.toString());
               }
           });
    }

    public static void createCustomer(final CustomerListener customerListener){
        Customer customer = new Customer.Builder().setCustomerPhoneNumber("08031234567").build();

        //TODO change this to a string and not a customer type
        Call<CustomerResponse> customerResponseCall = getInstance().service.createCustomer(customer);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if(response.body() != null)
                    customerListener.onCustomerRetrieved(response.body().getData());
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                customerListener.onCustomerNotRetrieved(t.getMessage());
                Log.d(TAG, "onFailure: " + t.getMessage());
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    /*
    *
    * CARDS
    *
    */



    public static void createCard(Card card, final CardCreatedListener cardCreatedListener){
        Call<CardResponse> cardResponseCall = getInstance().service.createCard(card);

        cardResponseCall.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.code() == 201)
                    cardCreatedListener.onCreated(response.body().getCard());
                else
                    cardCreatedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());

            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                //TODO Figure out what to do with this response
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public static void getCardById(String cardId, final CardRetrievedListener cardRetrievedListener){
        Call<CardResponse> cardResponseCall = getInstance().service.getCardById(cardId);

        cardResponseCall.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.code() == 200)
                    cardRetrievedListener.onRetrieve(response.body().getCard());
                else
                    cardRetrievedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());

            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                //TODO Figure out what to do with this response
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public static void updateCard(Card card, final CardUpdatedListener cardUpdatedListener){
        Call<CardResponse> cardResponseCall = getInstance().service.updateCardDetails(card);

        cardResponseCall.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {

                //TODO Verify the code to verify this code snippet before uncommenting
//                if(response.code() == 200)
//                    cardUpdatedListener.onUpdate(response.body().getCard());
//                else
//                    cardUpdatedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {

            }
        });
    }

    public static void deleteCard(String cardId, final CardDeletedListener cardDeletedListener){
        Call<CardResponse> cardResponseCall = getInstance().service.deleteCard(cardId);

        cardResponseCall.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.code() == 204)
                    cardDeletedListener.onDelete(new Meta(204,"No Content"));
                else
                    cardDeletedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());

            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                //TODO Figure out what to do with this response
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
