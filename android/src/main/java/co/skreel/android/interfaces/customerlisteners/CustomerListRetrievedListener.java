package co.skreel.android.interfaces.customerlisteners;

import java.util.List;

import co.skreel.android.models.customer.Customer;

public interface CustomerListRetrievedListener extends CustomerListener {
    void onCustomerListRetrieved(List<Customer> customers);
}
