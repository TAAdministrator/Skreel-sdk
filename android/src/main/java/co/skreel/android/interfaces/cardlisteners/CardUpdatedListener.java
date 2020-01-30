package co.skreel.android.interfaces.cardlisteners;

import co.skreel.android.models.cards.Card;

public interface CardUpdatedListener extends CardListener{
    void onUpdate(Card card);
}
