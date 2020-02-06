package co.skreel.android.interfaces.bankaccountlisteners;

import co.skreel.android.models.bankaccount.BankAccount;

public interface BankAccountRetrievedListener extends BankAccountListener {
    void onRetrieve(BankAccount bankAccount);
}
