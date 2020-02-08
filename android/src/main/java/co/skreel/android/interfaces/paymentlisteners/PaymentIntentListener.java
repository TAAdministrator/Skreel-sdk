package co.skreel.android.interfaces.paymentlisteners;

import co.skreel.android.models.payments.Payment;

public interface PaymentIntentListener extends PaymentListener{
    void onSuccess(Payment payment);
}
