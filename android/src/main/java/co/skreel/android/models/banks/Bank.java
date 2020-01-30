package co.skreel.android.models.banks;

import com.google.gson.annotations.SerializedName;

public class Bank {

    @SerializedName("bankAccronym")
    private String bankAcronym;

    @SerializedName("bankCode")
    private String bankCode;

    @SerializedName("bankName")
    private String bankName;

    @SerializedName("type")
    private String type;

    private Bank(String bankAcronym, String bankCode, String bankName, String type) {
        this.bankAcronym = bankAcronym;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.type = type;
    }

    public String getBankAcronym() {
        return bankAcronym;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getType() {
        return type;
    }

    public void setBankAcronym(String bankAcronym) {
        this.bankAcronym = bankAcronym;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Builder{
        private String bankAcronym;
        private String bankCode;
        private String bankName;
        private String type;

        public Builder setBankAcronym(final String bankAcronym){
            this.bankAcronym = bankAcronym;
            return this;
        }

        public Builder setBankCode(final String bankCode){
            this.bankCode = bankCode;
            return this;
        }

        public Builder setBankName(final String bankName){
            this.bankName = bankName;
            return this;
        }

        public Builder setType(final String type){
            this.type = type;
            return this;
        }

        public Bank build(){
            return new Bank(bankAcronym,bankCode,bankName,type);
        }

    }

}
