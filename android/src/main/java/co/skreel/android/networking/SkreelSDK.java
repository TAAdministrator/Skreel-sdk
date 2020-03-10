package co.skreel.android.networking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import co.skreel.android.activities.SkreelCardActivity;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountCreatedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountDeletedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountListRetrievedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountRetrievedListener;
import co.skreel.android.interfaces.bankaccountlisteners.BankAccountUpdatedListener;
import co.skreel.android.interfaces.cardlisteners.CardDeletedListener;
import co.skreel.android.interfaces.cardlisteners.CardRetrievedListener;
import co.skreel.android.interfaces.cardlisteners.CardValidationListener;
import co.skreel.android.interfaces.cardlisteners.CardValidationOTPListener;
import co.skreel.android.interfaces.customerlisteners.CustomerCreatedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerDeletedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerListRetrievedListener;
import co.skreel.android.interfaces.GetDataService;
import co.skreel.android.interfaces.cardlisteners.CardCreatedListener;
import co.skreel.android.interfaces.cardlisteners.CardUpdatedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerRetrievedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerUpdatedListener;
import co.skreel.android.interfaces.paymentlisteners.PaymentIntentListener;
import co.skreel.android.interfaces.paymentlisteners.PaymentOtpListener;
import co.skreel.android.models.bankaccount.BankAccount;
import co.skreel.android.models.bankaccount.BankAccountListResponse;
import co.skreel.android.models.bankaccount.BankAccountResponse;
import co.skreel.android.models.cards.Card;
import co.skreel.android.models.banks.AllBanksResponse;
import co.skreel.android.models.cards.CardResponse;
import co.skreel.android.models.cards.CardValidation;
import co.skreel.android.models.cards.CardValidationOTP;
import co.skreel.android.models.cards.CardValidationOTPResponse;
import co.skreel.android.models.cards.CardValidationResponse;
import co.skreel.android.models.cards.CustomerCard;
import co.skreel.android.models.customer.Customer;
import co.skreel.android.models.customer.CustomerListResponse;
import co.skreel.android.models.customer.CustomerResponse;
import co.skreel.android.models.payments.Payment;
import co.skreel.android.models.payments.PaymentResponse;
import co.skreel.android.models.payments.ValidatePaymentOTP;
import co.skreel.android.models.payments.ValidatePaymentOTPResponse;
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
    private static String customerId;


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
     * SET BEFORE CUSTOMER
     *
     */

    public static void displayCardView(Context context, String customerId, int requestCode){
        Intent intent = new Intent(context, SkreelCardActivity.class);
        intent.putExtra("customer_id", customerId);
        if(context instanceof Activity)
            ((Activity)context).startActivityForResult(intent, requestCode);
        else
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
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

    public static void createCustomer(String phoneNumber ,String identifier , final CustomerCreatedListener customerCreatedListener){
        Customer customer = new Customer.Builder().setCustomerPhoneNumber(phoneNumber).setPrimaryIdentifierType(identifier).build();

//        Log.d(TAG, "createCustomer: " + customer.toString());

        Call<CustomerResponse> customerResponseCall = getInstance().service.createCustomer(customer);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
//                Log.d(TAG, "onResponse: " + response.body());
                if(response.code() == 201)
                    customerCreatedListener.onCustomerCreated(response.body().getData());
                else
                    customerCreatedListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                customerCreatedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                customerRetrievedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                customerListRetrievedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                customerUpdatedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                customerDeletedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
            }
        });
    }


    /*
    *
    * CARDS
    *
    */


    public static void createCard(CustomerCard customerCard, final CardCreatedListener cardCreatedListener){
        Call<CardResponse> cardResponseCall = getInstance().service.createCard(customerCard);

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
                cardCreatedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                cardRetrievedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                cardUpdatedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                cardDeletedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                bankAccountCreatedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                bankAccountRetrievedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                bankAccountListRetrievedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
            }
        });
    }

    public static void updateBankAccount(BankAccount bankAccount, final BankAccountUpdatedListener bankAccountUpdatedListener){
        Call<BankAccountResponse> bankAccountResponseCall = getInstance().service.updateBankAccount(bankAccount.getBankAccountId(),bankAccount);

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
                bankAccountUpdatedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
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
                bankAccountDeletedListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
            }
        });
    }

    public static void makePayment(Payment payment, final PaymentIntentListener paymentIntentListener){
        Call<PaymentResponse> paymentResponseCall = getInstance().service.makePayment(payment);

        paymentResponseCall.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if(response.code() == 201)
                    paymentIntentListener.onSuccess(response.body().getPayment());
                else
                    paymentIntentListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                paymentIntentListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
            }
        });
    }

    public static void validatePaymentOTP(ValidatePaymentOTP validatePaymentOTP, final PaymentOtpListener paymentOtpListener){
        Call<ValidatePaymentOTPResponse> validatePaymentOTPCall = getInstance().service.validatePaymentOTP(validatePaymentOTP);

        validatePaymentOTPCall.enqueue(new Callback<ValidatePaymentOTPResponse>() {
            @Override
            public void onResponse(Call<ValidatePaymentOTPResponse> call, Response<ValidatePaymentOTPResponse> response) {
                if(response.code() == 200)
                    paymentOtpListener.onSuccess(response.body().getValidatePaymentOTP());
                else
                    paymentOtpListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<ValidatePaymentOTPResponse> call, Throwable t) {
                    paymentOtpListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
            }
        });
    }

    public static void cardValidation(String cardId, final CardValidationListener cardValidationListener){
        CardValidation cardValidation = new CardValidation(cardId);
        Call<CardValidationResponse> cardValidationResponseCall = getInstance().service.cardValidation(cardValidation);

        cardValidationResponseCall.enqueue(new Callback<CardValidationResponse>() {
            @Override
            public void onResponse(Call<CardValidationResponse> call, Response<CardValidationResponse> response) {
                if(response.code()== 200)
                    cardValidationListener.onSuccess(response.body().getCardValidation());
                else
                    cardValidationListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CardValidationResponse> call, Throwable t) {
                cardValidationListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
            }
        });
    }

    public static void cardValidationOTP(String cardId, String otp, final CardValidationOTPListener cardValidationOTPListener){
        CardValidationOTP cardValidationOTP = new CardValidationOTP(cardId,otp);
        Call<CardValidationOTPResponse> cardValidationOTPResponseCall = getInstance().service.cardValidationOTP(cardValidationOTP);

        cardValidationOTPResponseCall.enqueue(new Callback<CardValidationOTPResponse>() {
            @Override
            public void onResponse(Call<CardValidationOTPResponse> call, Response<CardValidationOTPResponse> response) {
                if(response.code() == 200)
                    cardValidationOTPListener.onSuccess(response.body().getCardValidationOTP());
                else
                    cardValidationOTPListener.onFailure(SkreelUtil.deserializeRetrofitErrorBody(response).getMeta());
            }

            @Override
            public void onFailure(Call<CardValidationOTPResponse> call, Throwable t) {
                cardValidationOTPListener.onFailure(SkreelUtil.apiCallFailure(t.getMessage()));
            }
        });

    }

}
