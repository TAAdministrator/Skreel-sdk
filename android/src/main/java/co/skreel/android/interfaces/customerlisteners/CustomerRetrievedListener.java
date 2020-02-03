package co.skreel.android.interfaces.customerlisteners;

import co.skreel.android.models.customer.Customer;

public interface CustomerRetrievedListener extends CustomerListener{
    void onCustomerRetrieved(Customer customer);
}
