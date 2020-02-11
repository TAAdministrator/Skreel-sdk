package co.skreel.android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import co.skreel.android.R;
import co.skreel.android.fragments.OTPFragment;
import co.skreel.android.fragments.CreditCardFragment;
import co.skreel.android.models.cards.Card;

public class SkreelCardActivity extends AppCompatActivity implements CreditCardFragment.OnCreditCardAdditionComplete, OTPFragment.OTPListener {

    private static final String TAG = "SkreelCardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skreel_card);

        Log.d(TAG, "onCreate: Skreel Card Activity Created.");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Fragment fragment = new CreditCardFragment();
        switchFragment(fragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
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
        if(fragment instanceof CreditCardFragment)
            ((CreditCardFragment) fragment).setOnCreditCardAdditionCompleteListener(this);
        else if(fragment instanceof OTPFragment)
            ((OTPFragment)fragment).setOnOTPRecievedListener(this);
    }

    @Override
    public void onOTPRecieved(Card card) {

        Intent intent=new Intent();
        intent.putExtra("card",card);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
