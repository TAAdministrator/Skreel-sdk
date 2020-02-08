package co.skreel.android.models.cards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {

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

    public Card() {
    }

    public Card (String pin, String pan, String cvv, String expiryDate) {
        this.pin = pin;
        this.pan = pan;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
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
        private String pin;
        private String pan;
        private String cvv;
        private String expiryDate;

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

//        public Builder setCardId(final String cardId){
//            this.cardId = cardId;
//            return this;
//        }

        public Card build(){
            return new Card(pin,pan,cvv,expiryDate);
        }
    }

}
