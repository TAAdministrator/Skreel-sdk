package co.skreel.android.interfaces.bankaccountlisteners;

import java.util.List;

import co.skreel.android.interfaces.customerlisteners.CustomerListener;
import co.skreel.android.models.bankaccount.BankAccount;
import co.skreel.android.models.customer.Customer;

public interface BankAccountListRetrievedListener extends BankAccountListener {
    void onBankAccountListRetrieve(List<BankAccount> bankAccounts);
}
