package co.skreel.android.models;

public class BankAccount {
    private String bankCode;
    private String accountNumber;
    private String customerId;

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

    static class Builder{
        private String bankCode;
        private String accountNumber;
        private String customerId;

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


