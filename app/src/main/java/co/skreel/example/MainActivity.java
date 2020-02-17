package co.skreel.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import co.skreel.android.activities.SkreelCardActivity;
import co.skreel.android.interfaces.cardlisteners.CardCreatedListener;
import co.skreel.android.interfaces.cardlisteners.CardDeletedListener;
import co.skreel.android.interfaces.cardlisteners.CardRetrievedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerCreatedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerListRetrievedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerRetrievedListener;
import co.skreel.android.interfaces.customerlisteners.CustomerUpdatedListener;
import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.Card;
import co.skreel.android.models.customer.Customer;
import co.skreel.android.networking.SkreelSDK;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SkreelSDK.getBanks();


        //********************** CARD CALLS **************************//
//        Card card = new Card.Builder().setCustomerId("9302fc3f0dcb4814a1effd323b753ad6").setPan("5369000009234800").setPin("1234").setCvv("123").setExpiryDate("06/20").build();
//
//        SkreelSDK.createCard(card, new CardCreatedListener() {
//            @Override
//            public void onCreated(Card card) {
//                Log.d(TAG, "onCardCreated: " + card.toString());
//            }
//
//            @Override
//            public void onFailure(Meta meta) {
//                Log.d(TAG, "onCardCreationFailed: " + meta.toString());
//            }
//        });
//
//        SkreelSDK.getCardById("d587532279f54ef0939ad534b993986c", new CardRetrievedListener() {
//            @Override
//            public void onRetrieve(Card card) {
//                Log.d(TAG, "onRetrieve: " + card.toString());
//            }
//
//            @Override
//            public void onFailure(Meta meta) {
//                Log.d(TAG, "onFailure: " + meta.toString());
//            }
//        });
//
//        SkreelSDK.deleteCard("d587532279f54ef0939ad534b993986c", new CardDeletedListener() {
//            @Override
//            public void onDelete(Meta meta) {
//
//            }
//
//            @Override
//            public void onFailure(Meta meta) {
//
//            }
//        });


        //********************** CUSTOMER CALLS **************************//

//        SkreelSDK.createCustomer("07032068837", new CustomerCreatedListener() {
//            @Override
//            public void onCustomerCreated(Customer customer) {
//                Log.d(TAG, "onCustomerCreated: " + customer);
//            }
//
//            @Override
//            public void onFailure(Meta meta) {
//                Log.d(TAG, "onFailure: " + meta);
//            }
//        });

//        SkreelSDK.getCustomerbyId("0800186ed9f143c48ed628f0db241a7f", new CustomerRetrievedListener() {
//            @Override
//            public void onCustomerRetrieved(Customer customer) {
//                Log.d(TAG, "onCustomerRetrieved: " + customer);
//            }
//
//            @Override
//            public void onFailure(Meta meta) {
//                Log.d(TAG, "onFailure: " + meta);
//
//            }
//        });

//        String[] numbers = {"07032068836", "07032068837","07032068838", "07032068839"};
//
//        List<String> list = Arrays.asList(numbers);
//
//        SkreelSDK.getCustomersByPhoneNumber(list, new CustomerListRetrievedListener() {
//            @Override
//            public void onCustomerListRetrieved(List<Customer> customers) {
//                Log.d(TAG, "onCustomerListRetrieved: " + customers.toString());
//            }
//
//            @Override
//            public void onFailure(Meta meta) {
//                Log.d(TAG, "onFailure: " + meta);
//            }
//        });


//        Customer customer = new Customer.Builder().setBvn("1234567890").setUserId("0800186ed9f143c48ed628f0db241a7f").build();
//
//        SkreelSDK.updateCustomer(customer, new CustomerUpdatedListener() {
//            @Override
//            public void onCustomerUpdate(Customer customer) {
//                Log.d(TAG, "onCustomerUpdate: " + customer);
//            }
//
//            @Override
//            public void onFailure(Meta meta) {
//                Log.d(TAG, "onFailure: " + meta);
//            }
//        });

//        SkreelSDK.deleteCustomer();


//        Intent i = new Intent(this, SkreelCardActivity.class);
        Button button = findViewById(R.id.click_me);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkreelSDK.displayCardView(MainActivity.this,"0800186ed9f143c48ed628f0db241a7f",5);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 5){
            if(resultCode == Activity.RESULT_OK){
                Card ca = (Card)data.getSerializableExtra("card");
//                Toast.makeText(this, "CARD: " + ca.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
