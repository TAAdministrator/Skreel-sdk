package co.skreel.android.models;

public class Customer {
    private String phoneNumber;

    public Customer(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class Builder{
        private String phoneNumber;

        public Builder setCustomerPhoneNumber(final String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Customer build(){
            return new Customer(phoneNumber);
        }
    }
}
