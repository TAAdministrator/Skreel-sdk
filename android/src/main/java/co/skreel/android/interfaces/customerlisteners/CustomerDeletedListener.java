package co.skreel.android.interfaces.customerlisteners;

import co.skreel.android.models.Meta;

public interface CustomerDeletedListener extends CustomerListener{
    void onCustomerDeleted(Meta meta);
}
