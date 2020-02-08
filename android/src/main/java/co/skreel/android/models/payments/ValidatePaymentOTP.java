package co.skreel.android.models.payments;

import com.google.gson.annotations.SerializedName;

public class ValidatePaymentOTP {

    @SerializedName("gateway_payment_id")
    private String gatewayPaymentId;

    @SerializedName("otp")
    private String otp;

    @SerializedName("card_id")
    private String cardId;

    @SerializedName("payment_reference")
    private String paymentReference;

    private ValidatePaymentOTP(String gatewayPaymentId, String otp, String cardId, String paymentReference) {
        this.gatewayPaymentId = gatewayPaymentId;
        this.otp = otp;
        this.cardId = cardId;
        this.paymentReference = paymentReference;
    }

    public String getGatewayPaymentId() {
        return gatewayPaymentId;
    }

    public void setGatewayPaymentId(String gatewayPaymentId) {
        this.gatewayPaymentId = gatewayPaymentId;
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

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    static class Builder{
        private String gatewayPaymentId;
        private String otp;
        private String cardId;
        private String paymentReference;

        public Builder setGatewayPaymentId(String gatewayPaymentId) {
            this.gatewayPaymentId = gatewayPaymentId;
            return this;
        }

        public Builder setOtp(String otp) {
            this.otp = otp;
            return this;
        }

        public Builder setCardId(String cardId) {
            this.cardId = cardId;
            return this;
        }

        public Builder setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        public ValidatePaymentOTP build(){
            return new ValidatePaymentOTP(gatewayPaymentId,otp,cardId,paymentReference);
        }
    }
}
