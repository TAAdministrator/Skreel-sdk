package co.skreel.android.models.cards;

import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.Meta;

public class CardValidationOTPResponse {
    @SerializedName("data")
    private CardValidationOTP cardValidationOTP;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;


    public CardValidationOTP getCardValidationOTP() {
        return cardValidationOTP;
    }

    public void setCardValidationOTP(CardValidationOTP cardValidationOTP) {
        this.cardValidationOTP = cardValidationOTP;
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
