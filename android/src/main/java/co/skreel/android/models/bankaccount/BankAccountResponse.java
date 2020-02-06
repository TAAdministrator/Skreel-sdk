package co.skreel.android.models.bankaccount;

import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.Meta;

public class BankAccountResponse {
    @SerializedName("data")
    private BankAccount bankAccount;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public BankAccountResponse() {
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BankAccountResponse{" +
                "bankAccount=" + bankAccount +
                ", meta=" + meta +
                ", status='" + status + '\'' +
                '}';
    }
}
