package co.skreel.android.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import co.skreel.android.R;
import co.skreel.android.interfaces.cardlisteners.CardValidationListener;
import co.skreel.android.interfaces.cardlisteners.CardValidationOTPListener;
import co.skreel.android.models.Meta;
import co.skreel.android.models.cards.Card;
import co.skreel.android.models.cards.CardValidation;
import co.skreel.android.models.cards.CardValidationOTP;
import co.skreel.android.networking.SkreelSDK;
import co.skreel.android.utils.SkreelUtil;

public class OTPFragment extends Fragment {

    private static final String TAG = "OTPFragment";

    private OTPViewModel mViewModel;
    private OTPListener otpListener;

//    OtpFragmentBinding binding;
    private Card card;
    private String cardOTP;
    private View view;
    private OtpView etOtpVerify;
    private Button btnNext;
    private TextView tvResendCode;

    public static OTPFragment newInstance() {
        return new OTPFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//         binding = DataBindingUtil.inflate(inflater,R.layout.otp_fragment, container,false);
//        return binding.getRoot();
        view = inflater.inflate(R.layout.otp_fragment, container,false);
        return view;

    }

    private void setupViews(){
        etOtpVerify = view.findViewById(R.id.et_otp_verify);
        tvResendCode = view.findViewById(R.id.tv_resend_code);
        btnNext = view.findViewById(R.id.btn_next);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OTPViewModel.class);
        // TODO: Use the ViewModel

        setupViews();
        Bundle bundle = getArguments();
        if(bundle != null){
            card = (Card)bundle.getSerializable("card");
//            Log.d(TAG, "onActivityCreated: " + card.toString());
            Toast.makeText(getContext(), card.toString(), Toast.LENGTH_SHORT).show();
        }

        etOtpVerify.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                cardOTP = otp;
//                Log.d("onOtpCompleted=>", otp);
                btnNext.setVisibility(View.VISIBLE);
            }
        });

        btnNext.setOnClickListener(verifyOTPClickListener);
        tvResendCode.setOnClickListener(reSendOTP);

    }

    View.OnClickListener verifyOTPClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SkreelUtil.showProgressDialog(getContext(),false);
            confirmOTPValidation();
        }
    };

    View.OnClickListener reSendOTP = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SkreelUtil.showProgressDialog(getContext(),false);
            cardRevalidation();
        }
    };

    public void confirmOTPValidation(){
        SkreelSDK.cardValidationOTP(card.getCardId(), cardOTP, new CardValidationOTPListener() {
            @Override
            public void onSuccess(CardValidationOTP cardValidationOTP) {
                Log.d(TAG, "onSuccess: " + cardValidationOTP);
                Toast.makeText(getContext(), cardValidationOTP.toString(), Toast.LENGTH_LONG).show();
                SkreelUtil.hideProgressDialog(getActivity());
                otpListener.onOTPRecieved(card);
            }

            @Override
            public void onFailure(Meta meta) {
                Log.d(TAG, "onFailure: " + meta);
                Toast.makeText(getContext(), meta.toString(), Toast.LENGTH_LONG).show();
                SkreelUtil.hideProgressDialog(getActivity());
            }
        });
    }

    public void cardRevalidation(){
        //This is to resend OTP incase it was not recieved in the first instance of card creation.
        SkreelSDK.cardValidation(card.getCardId(), new CardValidationListener() {
            @Override
            public void onSuccess(CardValidation cardValidation) {
                Log.d(TAG, "onSuccess: " + cardValidation);
                Toast.makeText(getContext(), cardValidation.toString(), Toast.LENGTH_LONG).show();
                SkreelUtil.hideProgressDialog(getActivity());
            }

            @Override
            public void onFailure(Meta meta) {
                Log.d(TAG, "onFailure: "  + meta);
                SkreelUtil.hideProgressDialog(getActivity());
            }
        });
    }

    public void setOnOTPRecievedListener(OTPListener otpListener){
        this.otpListener = otpListener;
    }

    public interface OTPListener{
        void onOTPRecieved(Card card);
    }
}
