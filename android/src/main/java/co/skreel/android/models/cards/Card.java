package co.skreel.android.models.cards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {

    @Expose(deserialize = false)
    @SerializedName("customer_id")
    private String customerId;

    @Expose(serialize = false)
    @SerializedName("id")
    private String cardId;

    @Expose(deserialize = false)
    @SerializedName("pin")
    private String pin;

    @Expose()
    @SerializedName("pan")
    private String pan;

    @Expose(deserialize = false)
    @SerializedName("cvv")
    private String cvv;

    @Expose()
    @SerializedName("expiry_date")
    private String expiryDate;

//    public Card(String customerId, String cardId, String pin, String pan, String cvv, String expiryDate) {
//        this.customerId = customerId;
//        this.cardId = cardId;
//        this.pin = pin;
//        this.pan = pan;
//        this.cvv = cvv;
//        this.expiryDate = expiryDate;
//    }


    public Card() {
    }

    public Card(String customerId, String pin, String pan, String cvv, String expiryDate) {
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

    public static class Builder{
        private String customerId;
        private String cardId;
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

        public Builder setCardId(final String cardId){
            this.cardId = cardId;
            return this;
        }

        public Card build(){
            return new Card(customerId,pin,pan,cvv,expiryDate);
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "customerId='" + customerId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", pin='" + pin + '\'' +
                ", pan='" + pan + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
