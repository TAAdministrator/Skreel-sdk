package co.skreel.android.models.banks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.skreel.android.models.Meta;

public class AllBanksResponse {

    @SerializedName("data")
    private List<Bank> data;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public AllBanksResponse() {
    }


    public List<Bank> getData() {
        return data;
    }

    public void setData(List<Bank> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
