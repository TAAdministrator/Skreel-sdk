package co.skreel.android.interfaces.cardlisteners;

import co.skreel.android.models.cards.CardValidationOTP;

public interface CardValidationOTPListener extends CardListener {
    void onSuccess(CardValidationOTP cardValidationOTP);
}
