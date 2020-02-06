package co.skreel.android.models.customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @Expose()
    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("bvn")
    private String bvn;

    @SerializedName("id")
    private String userId;

    public Customer(String phoneNumber, String bvn, String userId) {
        this.phoneNumber = phoneNumber;
        this.bvn = bvn;
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class Builder{
        private String phoneNumber;
        private String bvn;
        private String userId;

        public Builder setCustomerPhoneNumber(final String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setBvn(final String bvn) {
            this.bvn = bvn;
            return this;
        }

        public Builder setUserId(final String userId) {
            this.userId = userId;
            return this;
        }

        public Customer build(){
            return new Customer(phoneNumber,bvn,userId);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", bvn='" + bvn + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
