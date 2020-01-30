package co.skreel.android.models.cards;

import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.Meta;

public class CardResponse {

    @SerializedName("data")
    private Card card;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public CardResponse() {
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "card=" + card +
                ", meta=" + meta +
                ", status='" + status + '\'' +
                '}';
    }
}
