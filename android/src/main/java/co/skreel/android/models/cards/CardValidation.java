package co.skreel.android.models.cards;

import com.google.gson.annotations.SerializedName;

public class CardValidation {

    @SerializedName("card_id")
    private String cardId;

    public CardValidation(String cardId) {
        this.cardId = cardId;
    }

    public CardValidation() {
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
