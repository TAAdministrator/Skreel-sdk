package co.skreel.android.models.cards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerCard extends Card{
    @Expose(deserialize = false)
    @SerializedName("customer_id")
    private String customerId;


    public CustomerCard(String pin, String pan, String cvv, String expiryDate, String customerId) {
        super(pin, pan, cvv, expiryDate);
        this.customerId = customerId;
    }

    public CustomerCard(String customerId) {
        this.customerId = customerId;
    }
}
