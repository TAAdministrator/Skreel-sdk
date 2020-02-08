package co.skreel.android.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import co.skreel.android.R;
import co.skreel.android.databinding.CreditCardFragmentBinding;
import co.skreel.android.models.cards.Card;

public class CreditCardFragment extends Fragment {

    private CreditCardViewModel mViewModel;
    private CreditCardFragmentBinding binding;
    private String monthAndYear, cvvNo, creditCardNo, pinNo;
    boolean isCreditCardNumberVerified, isMonthAndYearVerified, isCVVVerified, isPinVerified;


    public static CreditCardFragment newInstance() {
        return new CreditCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.credit_card_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreditCardViewModel.class);
        // TODO: Use the ViewModel

        setupTextListeners();


    }

    private void checkEnableNextButton() {
        if (isCreditCardNumberVerified && isMonthAndYearVerified && isCVVVerified && isPinVerified) {
            binding.btNext.setEnabled(true);
        } else {
            binding.btNext.setEnabled(false);
        }
    }


    private void setupTextListeners() {
        binding.etCardNumber.addTextChangedListener(getCreditCardNumberTxtWatcher());
        binding.etCvv.addTextChangedListener(getCVVTxtWatcher());
        binding.etMonthAndYear.addTextChangedListener(getMonthAndYearTxtWatcher());
        binding.etPin.addTextChangedListener(getPinTxtWatcher());

//        binding.etCardNumber.setText("123456789012");
//        binding.etCvv.setText("213");
//        binding.etPin.setText("1234");
//        binding.etMonthAndYear.setText("08/20");
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
                if (creditCardNo.length() == 12) {
                    isCreditCardNumberVerified = true;
                    binding.etCardNumber.setError(null);
                    checkEnableNextButton();
                } else {
                    binding.etCardNumber.setError("Invalid Card Number Length");
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
                    binding.etCvv.setError(null);
                    checkEnableNextButton();
                } else {
                    binding.etCvv.setError("Invalid CVV");
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
                    binding.etMonthAndYear.setText(s.toString() + "/");
                    binding.etMonthAndYear.setSelection(3);
                }
                if (monthAndYear.length() == 5) {
                    isMonthAndYearVerified = true;
                    binding.etMonthAndYear.setError(null);
                    checkEnableNextButton();
                } else {
                    binding.etMonthAndYear.setError("Invalid month or year");
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
                    binding.etPin.setError(null);
                    checkEnableNextButton();
                } else {
                    binding.etPin.setError("Invalid PIN");
                    isPinVerified = false;
                }

            }
        };
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Card card = new Card.Builder().setPan(creditCardNo).setExpiryDate(monthAndYear).setPin(pinNo).setCvv(cvvNo).build();
        }
    };


}
