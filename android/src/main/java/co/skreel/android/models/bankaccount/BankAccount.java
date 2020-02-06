package co.skreel.android.models.bankaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankAccount {

    @Expose()
    @SerializedName("bank_code")
    private String bankCode;

    @Expose()
    @SerializedName("account_number")
    private String accountNumber;

    @Expose()
    @SerializedName("customer_id")
    private String customerId;

    @Expose(serialize = false)
    @SerializedName("bank_account_id")
    private String bankAccountId;

    private BankAccount (final Builder builder){
        bankCode = builder.bankCode;
        accountNumber = builder.accountNumber;
        customerId = builder.customerId;
    }


    public String getBankCode() {
        return bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    static class Builder{
        private String bankCode;
        private String accountNumber;
        private String customerId;
//        private String bankAccountId;

        public Builder setBankCode(String bankCode){
            this.bankCode = bankCode;
            return this;
        }

        public Builder setAccountNumber(String accountNumber){
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setCustomerId(String customerId){
            this.customerId = customerId;
            return this;
        }


        public BankAccount build(){
            return new BankAccount(this);
        }
    }

}


