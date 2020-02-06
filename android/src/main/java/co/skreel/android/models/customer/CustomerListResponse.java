package co.skreel.android.models.customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.skreel.android.models.Meta;

public class CustomerListResponse {

    @SerializedName("data")
    private List<Customer> data;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public CustomerListResponse() {
    }

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> data) {
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
        return "CustomerListResponse{" +
                "data=" + data +
                ", meta=" + meta +
                ", status='" + status + '\'' +
                '}';
    }
}
