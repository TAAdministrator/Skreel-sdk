package co.skreel.android.interfaces.customerlisteners;

import co.skreel.android.models.customer.Customer;

public interface CustomerUpdatedListener extends CustomerListener {
    void onCustomerUpdate(Customer customer);
}
