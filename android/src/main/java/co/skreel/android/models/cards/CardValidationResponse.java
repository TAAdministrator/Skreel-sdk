package co.skreel.android.models.cards;

import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.Meta;
import co.skreel.android.models.payments.Payment;

public class CardValidationResponse {
    @SerializedName("data")
    private CardValidation cardValidation;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;


    public CardValidation getCardValidation() {
        return cardValidation;
    }

    public void setCardValidation(CardValidation cardValidation) {
        this.cardValidation = cardValidation;
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
