package co.skreel.android.models.payments;

import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("amount")
    private String amount;

    @SerializedName("card_id")
    private String cardId;

    @SerializedName("beneficiary_id")
    private String beneficiaryId;

    @SerializedName("beneficiary_bank_code")
    private String beneficiaryBankCode;

    @SerializedName("beneficiary_account_number")
    private String beneficiaryBankAccount;

    @SerializedName("narration")
    private String narration;

    public Payment(String amount, String cardId, String beneficiaryId, String beneficiaryBankCode, String beneficiaryBankAccount, String narration) {
        this.amount = amount;
        this.cardId = cardId;
        this.beneficiaryId = beneficiaryId;
        this.beneficiaryBankCode = beneficiaryBankCode;
        this.beneficiaryBankAccount = beneficiaryBankAccount;
        this.narration = narration;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getBeneficiaryBankCode() {
        return beneficiaryBankCode;
    }

    public void setBeneficiaryBankCode(String beneficiaryBankCode) {
        this.beneficiaryBankCode = beneficiaryBankCode;
    }

    public String getBeneficiaryBankAccount() {
        return beneficiaryBankAccount;
    }

    public void setBeneficiaryBankAccount(String beneficiaryBankAccount) {
        this.beneficiaryBankAccount = beneficiaryBankAccount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public static class Builder{
        private String amount;
        private String cardId;
        private String beneficiaryId;
        private String beneficiaryBankCode;
        private String beneficiaryBankAccount;
        private String narration;

        public Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder setCardId(String cardId) {
            this.cardId = cardId;
            return this;
        }

        public Builder setBeneficiaryId(String beneficiaryId) {
            this.beneficiaryId = beneficiaryId;
            return this;
        }

        public Builder setBeneficiaryBankCode(String beneficiaryBankCode) {
            this.beneficiaryBankCode = beneficiaryBankCode;
            return this;
        }

        public Builder setBeneficiaryBankAccount(String beneficiaryBankAccount) {
            this.beneficiaryBankAccount = beneficiaryBankAccount;
            return this;
        }

        public Builder setNarration(String narration) {
            this.narration = narration;
            return this;
        }

        public Payment build(){
            return new Payment(amount,cardId,beneficiaryId,beneficiaryBankCode,beneficiaryBankAccount,narration);
        }
    }
}
