package co.skreel.android.interfaces.cardlisteners;

import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.Card;

public interface CardRetrievedListener extends CardListener{
    void onRetrieve(Card card);
}
