package co.skreel.android.models.cards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.customer.Customer;

public class CustomerCard{
    @Expose(deserialize = false)
    @SerializedName("customer_id")
    private String customerId;

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


    public CustomerCard(Card card, String customerId) {
        this.pan = card.getPan();
        this.pin = card.getPin();
        this.cvv = card.getCvv();
        this.expiryDate = card.getExpiryDate();
        this.customerId = customerId;
    }

    public static class Builder{
        private String customerId;
        private Card card;

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setCard(Card card) {
            this.card = card;
            return this;
        }

        public CustomerCard build(){
            return new CustomerCard(card,customerId);
        }
    }
}
