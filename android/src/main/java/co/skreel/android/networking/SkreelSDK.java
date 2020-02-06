package co.skreel.android.networking;

import android.content.Context;
import android.util.Log;

import java.util.List;

import co.skreel.android.interfaces.bankaccountlisteners.BankAccountCreatedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountDeletedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountListRetrievedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountRetrievedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountUpdatedListener;
import co.skreel.android.interfaces.cardlisteners.CardDeletedListener;
import co.skreel.android.interfaces.cardlisteners.CardRetrievedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerCreatedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerDeletedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerListRetrievedListener;
import co.skreel.android.interfaces.GetDataService;
import co.skreel.android.interfaces.cardlisteners.CardCreatedListener;
import co.skreel.android.interfaces.cardlisteners.CardUpdatedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerRetrievedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerUpdatedListener;
import co.skreel.android.models.bankaccount.BankAccount;
import co.skreel.android.models.bankaccount.BankAccountListResponse;
import co.skreel.android.models.bankaccount.BankAccountResponse;
import co.skreel.android.models.cards.Card;
import co.skreel.android.models.banks.AllBanksResponse;
import co.skreel.android.models.cards.CardResponse;
import co.skreel.android.models.customer.Customer;
import co.skreel.android.models.customer.CustomerListResponse;
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

    public static void createCustomer(String phoneNumber , final CustomerCreatedListener customerCreatedListener){
        Customer customer = new Customer.Builder().setCustomerPhoneNumber(phoneNumber).build();

        Call<CustomerResponse> customerResponseCall = getInstance().service.createCustomer(customer);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if(response.code() == 201)
                    customerCreatedListener.onCustomerCreated(response.body().getData());
                else
                    customerCreatedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
//                customerListener.onCustomerNotRetrieved(t.getMessage());
                Log.d(TAG, "onFailure: " + t.getMessage());
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public static void getCustomerbyId(String customerId, final CustomerRetrievedListener customerRetrievedListener){
        Call<CustomerResponse> customerResponseCall = getInstance().service.getCustomerById(customerId);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.code() == 200)
                    customerRetrievedListener.onCustomerRetrieved(response.body().getData());
                else
                    customerRetrievedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());

            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                //TODO: raise an exception. No failure should be silent
            }
        });

    }

    public static void getCustomersByPhoneNumber(List<String> phoneNumbers, final CustomerListRetrievedListener customerListRetrievedListener){
        final Call<CustomerListResponse> customerListResponseCall = getInstance().service.getCustomersByPhoneNumber(phoneNumbers);

        customerListResponseCall.enqueue(new Callback<CustomerListResponse>() {
            @Override
            public void onResponse(Call<CustomerListResponse> call, Response<CustomerListResponse> response) {
                if(response.code() == 200)
                    customerListRetrievedListener.onCustomerListRetrieved(response.body().getData());
                else
                    customerListRetrievedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CustomerListResponse> call, Throwable t) {

            }
        });
    }


    public static void updateCustomer(Customer customer, final CustomerUpdatedListener customerUpdatedListener){
        Call<CustomerResponse> customerResponseCall = getInstance().service.updateCustomerDetails(customer.getUserId(), customer);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.code() == 200)
                    customerUpdatedListener.onCustomerUpdate(response.body().getData());
                else
                    customerUpdatedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                //TODO: raise an exception. No failure should be silent
            }
        });

    }

    public static void deleteCustomer(String customerId, final CustomerDeletedListener customerDeletedListener){
        Call<CustomerResponse> customerResponseCall = getInstance().service.deleteCustomer(customerId);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.code() == 200)
                    customerDeletedListener.onCustomerDeleted(SkreelUtil.deleteSuccess());
                else
                    customerDeletedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                //TODO: raise an exception. No failure should be silent
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
                    cardDeletedListener.onDelete(SkreelUtil.deleteSuccess());
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

    public static void createBankAccount(BankAccount bankAccount, final BankAccountCreatedListener bankAccountCreatedListener){
        Call<BankAccountResponse> bankAccountResponseCall = getInstance().service.createBankAccount(bankAccount);

        bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
            @Override
            public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                if(response.code() == 200)
                    bankAccountCreatedListener.onCreate(response.body().getBankAccount());
                else
                    bankAccountCreatedListener.onFailure(response.body().getMeta());
            }

            @Override
            public void onFailure(Call<BankAccountResponse> call, Throwable t) {

            }
        });

    }


    public static void getBankAccountById(String bankAccountId, final BankAccountRetrievedListener bankAccountRetrievedListener){
        Call<BankAccountResponse> cardResponseCall = getInstance().service.getBankAccountById(bankAccountId);

        cardResponseCall.enqueue(new Callback<BankAccountResponse>() {
            @Override
            public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                if(response.code() == 200)
                    bankAccountRetrievedListener.onRetrieve(response.body().getBankAccount());
                else
                    bankAccountRetrievedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                //TODO Figure out what to do with this response
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public static void getBankAccountListByPhoneNumber(String phoneNumber, final BankAccountListRetrievedListener bankAccountListRetrievedListener){
        Call<BankAccountListResponse> bankAccountListResponseCall = getInstance().service.getBankAccountsByPhoneNumber(phoneNumber);

        bankAccountListResponseCall.enqueue(new Callback<BankAccountListResponse>() {
            @Override
            public void onResponse(Call<BankAccountListResponse> call, Response<BankAccountListResponse> response) {
                if(response.code() == 200)
                    bankAccountListRetrievedListener.onBankAccountListRetrieve(response.body().getData());
                else
                    bankAccountListRetrievedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());

            }

            @Override
            public void onFailure(Call<BankAccountListResponse> call, Throwable t) {
                //TODO Figure out what to do with this response
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public static void updateBankAccount(BankAccount bankAccount, final BankAccountUpdatedListener bankAccountUpdatedListener){
        Call<BankAccountResponse> bankAccountResponseCall = getInstance().service.updateBankAccount(bankAccount.ge,bankAccount);

        bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
            @Override
            public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {

                //TODO Verify the code to verify this code snippet before uncommenting
                if(response.code() == 200)
                    bankAccountUpdatedListener.onUpdate(response.body().getBankAccount());
                else
                    bankAccountUpdatedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<BankAccountResponse> call, Throwable t) {

            }
        });
    }

    public static void deleteBankAccount(String bankAccountId, final BankAccountDeletedListener bankAccountDeletedListener){
        Call<BankAccountResponse> cardResponseCall = getInstance().service.deleteBankAccount(bankAccountId);

        cardResponseCall.enqueue(new Callback<BankAccountResponse>() {
            @Override
            public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                if(response.code() == 204)
                    bankAccountDeletedListener.onDelete(SkreelUtil.deleteSuccess());
                else
                    bankAccountDeletedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                //TODO Figure out what to do with this response
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
