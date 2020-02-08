package co.skreel.android.models.payments;

import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.Card;

public class PaymentResponse {
    @SerializedName("data")
    private Payment payment;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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

}
