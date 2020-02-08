package co.skreel.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import co.skreel.android.R;
import co.skreel.android.fragments.CreditCardFragment;

public class SkreelCardActivity extends AppCompatActivity {

    private static final String TAG = "SkreelCardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skreel_card);


        Log.d(TAG, "onCreate: Skreel Card Activity Created.");


        Fragment fragment = new CreditCardFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_acct_setup, fragment);
        transaction.commit();
    }
}
