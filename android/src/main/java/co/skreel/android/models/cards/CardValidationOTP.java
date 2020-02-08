package co.skreel.android.models.cards;

public class CardValidationOTP extends CardValidation {
    private String otp;

    public CardValidationOTP(String cardId, String otp) {
        super(cardId);
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


}
