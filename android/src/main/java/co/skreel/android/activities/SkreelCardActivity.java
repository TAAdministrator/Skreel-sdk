package co.skreel.android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import co.skreel.android.R;
import co.skreel.android.fragments.OTPFragment;
import co.skreel.android.fragments.CreditCardFragment;
import co.skreel.android.models.cards.Card;

public class SkreelCardActivity extends AppCompatActivity implements CreditCardFragment.OnCreditCardAdditionComplete {

    private static final String TAG = "SkreelCardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skreel_card);


        Log.d(TAG, "onCreate: Skreel Card Activity Created.");


        Fragment fragment = new CreditCardFragment();
        switchFragment(fragment);
    }

    @Override
    public void onCreditCardAdded(Card card) {
        Fragment fragment = new OTPFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("card", card);
        fragment.setArguments(bundle);
        switchFragment(fragment);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_acct_setup, fragment);
        transaction.commit();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof CreditCardFragment){
            ((CreditCardFragment) fragment).setOnCreditCardAdditionCompleteListener(this);
        }
    }
}
