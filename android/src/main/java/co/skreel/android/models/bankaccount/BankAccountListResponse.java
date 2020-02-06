package co.skreel.android.models.bankaccount;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.skreel.android.models.Meta;
import co.skreel.android.models.customer.Customer;

public class BankAccountListResponse {

    @SerializedName("data")
    private List<BankAccount> data;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public BankAccountListResponse() {
    }

    public List<BankAccount> getData() {
        return data;
    }

    public void setData(List<BankAccount> data) {
        this.data = data;
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
        return "BankAccountListResponse{" +
                "data=" + data +
                ", meta=" + meta +
                ", status='" + status + '\'' +
                '}';
    }
}
