package co.skreel.android.interfaces;

import java.util.List;

import co.skreel.android.models.BankAccount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @POST("bank-accounts")
    Call<BankAccount> createAccount(@Body BankAccount bankAccount);

    @GET("bank-accounts") ///bank-accounts?phone_number=08091990511
    Call<List<BankAccount>> getBankAccounts(@Query("phone_number") String phoneNumber);

    @GET("bank-accounts/{bank_account_id}")  //bank-accounts/sfhkfsuiweuiweuw
    Call<BankAccount> getBankAccountById(@Path("bank_account_id") String bankAccountId);

    @PATCH("bank-accounts/{bank_account_id}")  //bank-accounts/sfhkfsuiweuiweuw
    Call<BankAccount>  updateBankAccount(@Path("bank_account_id") String bankAccountId);

    @DELETE("bank-accounts/{bank_account_id}")  //bank-accounts/sfhkfsuiweuiweuw
    Call<BankAccount> deleteBankAccount(@Path("bank_account_id") String bankAccountId);


}
