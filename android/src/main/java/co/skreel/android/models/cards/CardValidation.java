package co.skreel.android.models.cards;

public class CardValidation {
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
