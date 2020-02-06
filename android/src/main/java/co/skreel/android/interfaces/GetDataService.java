package co.skreel.android.interfaces;

import java.util.List;

import co.skreel.android.models.BankAccount;
import co.skreel.android.models.cards.Card;
import co.skreel.android.models.banks.AllBanksResponse;
import co.skreel.android.models.cards.CardResponse;
import co.skreel.android.models.customer.Customer;
import co.skreel.android.models.customer.CustomerListResponse;
import co.skreel.android.models.customer.CustomerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    //BANK ACCOUNTS
    @POST("bank-accounts")
    Call<BankAccount> createBankAccount(@Body BankAccount bankAccount);

    @GET("bank-accounts")
    Call<BankAccount> getBankAccountsByPhoneNumber(@Query("phone_number") String phoneNumber);

    @GET("bank-accounts/{bank_account_id}")  //bank-accounts/sfhkfsuiweuiweuw
    Call<BankAccount> getBankAccountById(@Path("bank_account_id") String bankAccountId);

    @PATCH("bank-accounts/{bank_account_id}")  //bank-accounts/sfhkfsuiweuiweuw
    Call<BankAccount>  updateBankAccount(@Path("bank_account_id") String bankAccountId);

    @DELETE("bank-accounts/{bank_account_id}")  //bank-accounts/sfhkfsuiweuiweuw
    Call<BankAccount> deleteBankAccount(@Path("bank_account_id") String bankAccountId);


    //BANKS
    @GET("banks")
    Call<List<AllBanksResponse>> getBanks();


    //CARD
    @POST("cards")
    Call<CardResponse> createCard(@Body Card card);

    @GET("cards")
    Call<Card> getCardByPhoneNumber(@Query("phone_number") String phoneNumber);

    @GET("cards/{card_id}")
    Call<CardResponse> getCardById(@Path("card_id") String cardId);

    @PATCH("cards")
    Call<CardResponse> updateCardDetails(@Body Card card);

    @DELETE("cards/{card_id}")
    Call<CardResponse> deleteCard(@Path("card_id") String cardId);



    //CUSTOMERS

    @POST("customers")
    Call<CustomerResponse> createCustomer(@Body Customer customer);

    @GET("customers/{customer_id}")
    Call<CustomerResponse> getCustomerById(@Path("customer_id") String customerId);

    @GET("customers")
    Call<CustomerListResponse> getCustomersByPhoneNumber(@Query("phone_number") List<String> customerPhoneNumbers);

    @PATCH("customers/{customer_id}")
    Call<CustomerResponse> updateCustomerDetails(@Path("customer_id") String customerId, @Body Customer customer);

    @DELETE("customers/{customer_id}")
    Call<CustomerResponse> deleteCustomer(@Path("customer_id") String customerId);

    @GET("customers/{customer_id}/cards")
    Call<CustomerResponse> getListOfCustomerCards(@Path("customer_id") String customerId);

    @GET("customers/{customer_id}/bank-accounts")
    Call<CustomerResponse> getListOfCustomerBankAccounts(@Path("customer_id") String customerId);


    @GET("customers/{customer_id}/cards/{card_id}")
    Call<CustomerResponse> getCustomerCardWithIds(@Path("customer_id") String customerId, @Path("card_id") String cardId);

    @GET("customers/{customer_id}/bank-accounts/{bank_account_id}")
    Call<CustomerResponse> getCustomerBankAccountsWithIds(@Path("customer_id") String customerId, @Path("bank_account_id") String cardId);

}
