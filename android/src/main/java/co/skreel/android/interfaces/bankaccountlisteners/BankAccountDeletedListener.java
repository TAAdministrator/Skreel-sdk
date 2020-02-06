package co.skreel.android.interfaces.bankaccountlisteners;

import co.skreel.android.models.Meta;

public interface BankAccountDeletedListener extends BankAccountListener {
   void onDelete(Meta meta);
}
