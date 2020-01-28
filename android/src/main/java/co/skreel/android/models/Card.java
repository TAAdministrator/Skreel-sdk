package co.skreel.android.models;

public class Card {
    private String customerId;
    private String pin;
    private String pan;
    private String cvv;
    private String expiryDate;

    private Card(String customerId, String pin, String pan, String cvv, String expiryDate) {
        this.customerId = customerId;
        this.pin = pin;
        this.pan = pan;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    static class Builder{
        private String customerId;
        private String pin;
        private String pan;
        private String cvv;
        private String expiryDate;

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setPin(String pin) {
            this.pin = pin;
            return this;
        }

        public Builder setPan(String pan) {
            this.pan = pan;
            return this;
        }

        public Builder setCvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Card build(){
            return new Card(customerId,pin,pan,cvv,expiryDate);
        }
    }
}
