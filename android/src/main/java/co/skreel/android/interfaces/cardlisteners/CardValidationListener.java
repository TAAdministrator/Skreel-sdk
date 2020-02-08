package co.skreel.android.interfaces.cardlisteners;


import co.skreel.android.models.cards.CardValidation;

public interface CardValidationListener extends CardListener {
    void onSuccess(CardValidation cardValidation);
}
