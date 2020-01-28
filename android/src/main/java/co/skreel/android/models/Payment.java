package co.skreel.android.models;

public class Payment {
    private String amount;
    private String cardId;
    private String beneficiaryId;
    private String beneficiaryBankCode;
    private String beneficiaryAccountNumber;
    private String narration;

    private Payment(String amount, String cardId, String beneficiaryId, String beneficiaryBankCode, String beneficiaryAccountNumber, String narration) {
        this.amount = amount;
        this.cardId = cardId;
        this.beneficiaryId = beneficiaryId;
        this.beneficiaryBankCode = beneficiaryBankCode;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.narration = narration;
    }

    public String getAmount() {
        return amount;
    }

    public String getCardId() {
        return cardId;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public String getBeneficiaryBankCode() {
        return beneficiaryBankCode;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public String getNarration() {
        return narration;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public void setBeneficiaryBankCode(String beneficiaryBankCode) {
        this.beneficiaryBankCode = beneficiaryBankCode;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    static class Builder{
        private String amount;
        private String cardId;
        private String beneficiaryId;
        private String beneficiaryBankCode;
        private String beneficiaryAccountNumber;
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

        public Builder setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
            this.beneficiaryAccountNumber = beneficiaryAccountNumber;
            return this;
        }

        public Builder setNarration(String narration) {
            this.narration = narration;
            return this;
        }

        public Payment build(){
            return new Payment(amount, cardId, beneficiaryId, beneficiaryBankCode, beneficiaryAccountNumber,narration);
        }
    }

}
