package co.skreel.android.interfaces.bankaccountlisteners;

import co.skreel.android.models.bankaccount.BankAccount;

public interface BankAccountUpdatedListener extends BankAccountListener {
    void onUpdate(BankAccount bankAccount);
}
