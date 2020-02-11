package co.skreel.android.models.cards;

import com.google.gson.annotations.SerializedName;

public class CardValidationOTP {

    @SerializedName("otp")
    private String otp;

    @SerializedName("card_id")
    private String cardId;

    public CardValidationOTP(String cardId, String otp) {
        this.cardId = cardId;
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "CardValidationOTP{" +
                "otp='" + otp + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
