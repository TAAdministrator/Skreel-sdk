package co.skreel.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import co.skreel.android.interfaces.cardlisteners.CardCreatedListener;
import co.skreel.android.interfaces.cardlisteners.CardDeletedListener;
import co.skreel.android.interfaces.cardlisteners.CardRetrievedListener;
import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.Card;
import co.skreel.android.networking.SkreelSDK;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class).getBanks();
//
//        Call<Bank> servic

//        SkreelSDK.getBanks();
//        SkreelSDK.createCustomer(new CustomerListener() {
//            @Override
//            public void onCustomerRetrieved(Customer customer) {
//                Log.d(TAG, "onCustomerRetrieved: " + customer.toString());
//            }
//
//            @Override
//            public void onCustomerNotRetrieved(String message) {
//                Log.d(TAG, "onCustomerNotRetrieved: " + message);
//            }
//        });

        Card card = new Card.Builder().setCustomerId("9302fc3f0dcb4814a1effd323b753ad6").setPan("5369000009234800").setPin("1234").setCvv("123").setExpiryDate("06/20").build();


        SkreelSDK.createCard(card, new CardCreatedListener() {
            @Override
            public void onCreated(Card card) {
                Log.d(TAG, "onCardCreated: " + card.toString());
            }

            @Override
            public void onFailure(Meta meta) {
                Log.d(TAG, "onCardCreationFailed: " + meta.toString());
            }
        });

        SkreelSDK.getCardById("d587532279f54ef0939ad534b993986c", new CardRetrievedListener() {
            @Override
            public void onRetrieve(Card card) {
                Log.d(TAG, "onRetrieve: " + card.toString());
            }

            @Override
            public void onFailure(Meta meta) {
                Log.d(TAG, "onFailure: " + meta.toString());
            }
        });

        SkreelSDK.deleteCard("d587532279f54ef0939ad534b993986c", new CardDeletedListener() {
            @Override
            public void onDelete(Meta meta) {

            }

            @Override
            public void onFailure(Meta meta) {

            }
        });
    }
}
