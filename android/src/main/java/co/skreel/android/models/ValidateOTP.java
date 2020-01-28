package co.skreel.android.models;

public class ValidateOTP {
    private String gatewayPaymentId;
    private String otp;
    private String cardId;
    private String paymentReference;

    private ValidateOTP(String gatewayPaymentId, String otp, String cardId, String paymentReference) {
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

        public ValidateOTP build(){
            return new ValidateOTP(gatewayPaymentId,otp,cardId,paymentReference);
        }
    }
}
