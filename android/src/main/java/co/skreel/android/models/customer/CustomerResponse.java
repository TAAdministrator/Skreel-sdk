package co.skreel.android.models.customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.Meta;

public class CustomerResponse {

    @SerializedName("data")
    private Customer data;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public CustomerResponse() {
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
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
        return "CustomerResponse{" +
                "data=" + data +
                ", meta=" + meta +
                ", status='" + status + '\'' +
                '}';
    }
}
