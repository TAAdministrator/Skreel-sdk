package co.skreel.android.interfaces.paymentlisteners;

import co.skreel.android.models.payments.Payment;
import co.skreel.android.models.payments.ValidatePaymentOTP;

public interface PaymentOtpListener extends PaymentListener{
    void onSuccess(ValidatePaymentOTP validatePaymentOTP);
}
