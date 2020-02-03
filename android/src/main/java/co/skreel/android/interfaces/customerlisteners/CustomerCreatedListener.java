package co.skreel.android.interfaces.customerlisteners;

import co.skreel.android.models.customer.Customer;

public interface CustomerCreatedListener extends CustomerListener {
    void onCustomerCreated(Customer customer);
}
