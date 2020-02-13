package co.skreel.android.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import co.skreel.android.R;
import co.skreel.android.interfaces.cardlisteners.CardCreatedListener;
import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.Card;
import co.skreel.android.models.cards.CustomerCard;
import co.skreel.android.networking.SkreelSDK;
import co.skreel.android.utils.SkreelUtil;

public class CreditCardFragment extends Fragment {

    private static final String TAG = "CreditCardFragment";

    private CreditCardViewModel mViewModel;
//    private CreditCardFragmentBinding binding;
    private String monthAndYear, cvvNo, creditCardNo, pinNo;
    boolean isCreditCardNumberVerified, isMonthAndYearVerified, isCVVVerified, isPinVerified;
    private OnCreditCardAdditionComplete onCreditCardAdditionComplete;
    private Button btNext;
    private View view;
    private EditText etCardNumber, etMonthAndYear, etCvv, etPin;


    public static CreditCardFragment newInstance() {
        return new CreditCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.credit_card_fragment,container,false);
//        binding = DataBindingUtil.inflate(inflater, R.layout.credit_card_fragment, container, false);
//        return binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreditCardViewModel.class);
        // TODO: Use the ViewModel

        setupViews();
        setupTextListeners();


        btNext.setOnClickListener(nextButtonOnClickListener);
    }

    private void setupViews(){
        btNext = view.findViewById(R.id.bt_next);

        etCardNumber = view.findViewById(R.id.et_card_number);
        etCvv = view.findViewById(R.id.et_cvv);
        etMonthAndYear = view.findViewById(R.id.et_month_and_year);
        etPin = view.findViewById(R.id.et_pin);
    }

    private void checkEnableNextButton() {
        if (isCreditCardNumberVerified && isMonthAndYearVerified && isCVVVerified && isPinVerified) {
            btNext.setEnabled(true);
        } else {
            btNext.setEnabled(false);
        }
    }


    private void setupTextListeners() {
        etCardNumber.addTextChangedListener(getCreditCardNumberTxtWatcher());
        etCvv.addTextChangedListener(getCVVTxtWatcher());
        etMonthAndYear.addTextChangedListener(getMonthAndYearTxtWatcher());
        etPin.addTextChangedListener(getPinTxtWatcher());

        etCardNumber.setText("1234567890127890");
        etCvv.setText("213");
        etPin.setText("1234");
        etMonthAndYear.setText("08/20");
    }


    private TextWatcher getCreditCardNumberTxtWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                creditCardNo = s.toString();
                isCreditCardNumberVerified = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (creditCardNo.length() == 16) {
                    isCreditCardNumberVerified = true;
                    etCardNumber.setError(null);
                    checkEnableNextButton();
                } else {
                    etCardNumber.setError("Invalid Card Number Length");
                    isCreditCardNumberVerified = false;
                }
            }
        };
    }

    private TextWatcher getCVVTxtWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cvvNo = s.toString();
                isCVVVerified = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (cvvNo.length() == 3) {
                    isCVVVerified = true;
                    etCvv.setError(null);
                    checkEnableNextButton();
                } else {
                    etCvv.setError("Invalid CVV");
                    isCVVVerified = false;
                }

            }
        };
    }

    private TextWatcher getMonthAndYearTxtWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                monthAndYear = s.toString();
                isMonthAndYearVerified = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(monthAndYear.length() == 2 && Pattern.matches("1[0-2]|0[1-9]|\\d", monthAndYear)) {
                    etMonthAndYear.setText(s.toString() + "/");
                    etMonthAndYear.setSelection(3);
                }
                if (monthAndYear.length() == 5) {
                    isMonthAndYearVerified = true;
                    etMonthAndYear.setError(null);
                    checkEnableNextButton();
                } else {
                    etMonthAndYear.setError("Invalid month or year");
                    isMonthAndYearVerified = false;
                }

            }
        };
    }

    private TextWatcher getPinTxtWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pinNo = s.toString();
                isPinVerified = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pinNo.length() == 4) {
                    isPinVerified = true;
                    etPin.setError(null);
                    checkEnableNextButton();
                } else {
                    etPin.setError("Invalid PIN");
                    isPinVerified = false;
                }

            }
        };
    }

    private View.OnClickListener nextButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SkreelUtil.showProgressDialog(getContext(),false);
            Card card = new Card.Builder().setPan(creditCardNo).setExpiryDate(monthAndYear).setPin(pinNo).setCvv(cvvNo).build();
            CustomerCard customerCard = new CustomerCard.Builder().setCard(card).setCustomerId("9302fc3f0dcb4814a1effd323b753ad6").build();

            SkreelSDK.createCard(customerCard, new CardCreatedListener() {
                @Override
                public void onCreated(Card card) {
                    SkreelUtil.hideProgressDialog(getActivity());
                    Toast.makeText(getContext(), "Card Created", Toast.LENGTH_SHORT).show();
                    onCreditCardAdditionComplete.onCreditCardAdded(card);
                    Log.d(TAG, "onCreated: " + card.toString());
                }

                @Override
                public void onFailure(Meta meta) {
                    Toast.makeText(getContext(), meta.toString(), Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onFailure: " + meta.toString());
                    SkreelUtil.hideProgressDialog(getActivity());
                }
            });
        }
    };

    public interface OnCreditCardAdditionComplete {
        void onCreditCardAdded(Card card);
    }

    public void setOnCreditCardAdditionCompleteListener(OnCreditCardAdditionComplete onCreditCardAdditionComplete){
        this.onCreditCardAdditionComplete = onCreditCardAdditionComplete;
    }

}
