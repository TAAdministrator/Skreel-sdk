package co.skreel.android.interfaces.customerlisteners;

import co.skreel.android.models.Meta;
import co.skreel.android.models.customer.Customer;

public interface CustomerListener {
    void onCustomerRetrieved(Customer customer);

    void onCustomerNotRetrieved(String message);

    void onFailure(Meta meta);
}
