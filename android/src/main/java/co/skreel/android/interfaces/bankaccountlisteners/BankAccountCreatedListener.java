package co.skreel.android.interfaces.bankaccountlisteners;

import co.skreel.android.models.bankaccount.BankAccount;

public interface BankAccountCreatedListener extends BankAccountListener {
    void onCreate(BankAccount bankAccount);
}
