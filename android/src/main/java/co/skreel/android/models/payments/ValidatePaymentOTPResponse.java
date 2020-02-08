package co.skreel.android.models.payments;

import com.google.gson.annotations.SerializedName;

import co.skreel.android.models.Meta;

public class ValidatePaymentOTPResponse {
    @SerializedName("data")
    private ValidatePaymentOTP validatePaymentOTP;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("status")
    private String status;

    public ValidatePaymentOTP getValidatePaymentOTP() {
        return validatePaymentOTP;
    }

    public void setValidatePaymentOTP(ValidatePaymentOTP validatePaymentOTP) {
        this.validatePaymentOTP = validatePaymentOTP;
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

}
