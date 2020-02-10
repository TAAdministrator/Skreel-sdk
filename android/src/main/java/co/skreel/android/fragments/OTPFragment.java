//package co.skreel.android.fragments;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.LabeledIntent;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.content.res.Resources;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//
//import com.mukesh.OtpListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OTPFragment extends Fragment implements View.OnClickListener {
//
//    /*
//     *
//     * THE VERIFICATION CODE FRAGMENT
//     *
//     */
//
//    private static final String TAG = "VerifyPhoneFragment";
//    FragmentVerifyPhoneBinding binding;
//    View view;
//    private Context context;
//
//    public static final String PHONE_NO = "phone";
//    private String phone;
//    private boolean isThankUCardShown = false;
//    private AuthViewModel viewModel;
//    private CountDownTimer cTimer = null;
//
//    public static VerifyPhoneFragment newInstance(String phone) {
//        Bundle args = new Bundle();
//        args.putString(PHONE_NO, phone);
//        VerifyPhoneFragment fragment = new VerifyPhoneFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    public OTPFragment() {
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        phone = null;
//        if (bundle != null) {
//            phone = bundle.getString(PHONE_NO);
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_phone, container, false);
//        view = binding.getRoot();
//
//        AuthViewModelFactory authViewModelFactory = InjectorUtils.provideAuthViewModelFactory();
//        viewModel = ViewModelProviders.of(this, authViewModelFactory).get(AuthViewModel.class);
//
//        setPhoneTxt(phone);
//        ((SignupActivity)getActivity()).changeToolbarText("Verification");
//        binding.etPasswordVerify.setListener(new OtpListener() {
//            @Override public void onOtpEntered(String otp) {
//
//                // TODO: show thank u screen onHomeContainerInitialize
//                UIUtils.showTransparentProgress(context, "Verifying...");
//                verifyOtp(otp);
//            }
//        });
//
//        binding.btnRegContinue.setOnClickListener(this);
//        binding.btnCheckAvailability.setOnClickListener(this);
//        binding.btnNext.setOnClickListener(this);
//        binding.btnInviteFriend.setOnClickListener(this);
//        binding.tvResendCode.setOnClickListener(this);
//        return view;
//    }
//
//    private void verifyOtp(String otp) {
//        viewModel.verifyOtp(phone, otp).observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String verifiedStatus) {
//                if (!TextUtils.isEmpty(verifiedStatus) && verifiedStatus.equalsIgnoreCase("success")) {
//                    showCreateHashTag();
//                    UIUtils.hideTransparentDialog();
//                }
//            }
//        });
//
//        observeError();
//
//    }
//
//    private void observeError() {
//        viewModel.getAuthError().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                processError(s);
//                UIUtils.hideTransparentDialog();
//            }
//        });
//    }
//
//    private void processError(String s) {
//        if (!TextUtils.isEmpty(s)) {
//            if (s.contains("is invalid")) {
//                showErrorDialog("Incorrect Pin, Try again");
////                binding.etPasswordVerify.setOTP("");
//            } else {
//                showErrorDialog(s);
//                binding.etEnterHashtag.setError("s");
//                binding.btnCheckAvailability.setEnabled(true);
//            }
//        }
//    }
//
//    private void showErrorDialog(String s) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage(s);
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//    }
//
//    private void showThankUCard() {
//        isThankUCardShown = true;
//        ((SignupActivity)getActivity()).changeToolbarText("Registration Complete");
//        binding.conThankU.setVisibility(View.VISIBLE);
//        binding.cdVerify.setVisibility(View.GONE);
//        binding.conCreateHashtag.setVisibility(View.GONE);
//    }
//
//    private void showCreateHashTag(){
//        ((SignupActivity)getActivity()).changeToolbarText("Create Your Hashtag");
//        binding.conCreateHashtag.setVisibility(View.VISIBLE);
//        binding.conThankU.setVisibility(View.GONE);
//        binding.cdVerify.setVisibility(View.GONE);
//        watchAndValidateHashtag();
//    }
//
//    private void watchAndValidateHashtag() {
//        binding.etEnterHashtag.addTextChangedListener(hashTagTextWatcher());
//    }
//
//    private TextWatcher hashTagTextWatcher() {
//        return new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.length() >= 6 && (UIUtils.isValidHashTag(s.toString()))) {
//                    binding.btnCheckAvailability.setEnabled(true);
//                } else {
//                    binding.btnCheckAvailability.setEnabled(false);
//                }
//            }
//        };
//    }
//
//    private void validateNameAndProceed(final String name) {
//        UIUtils.showTransparentProgress(context, "Checking Availability...");
//        observeError();
//
//        CreateHashTagRequest request = new CreateHashTagRequest();
//        request.setUsername(name);
//
//       viewModel.createHashTag(request).observe(this, new Observer<AuthUser>() {
//           @Override
//           public void onChanged(@Nullable AuthUser authUser) {
//               if (authUser != null) {
//
//                   binding.ivAvailable.setVisibility(View.VISIBLE);
//                   binding.tvNameCheckReason.setText("Hashtag creation successful");
//                   binding.tvNameCheckReason.setTextColor(ContextCompat.getColor(context, R.color.green_color));
//                   binding.tvNameCheckReason.setVisibility(View.VISIBLE);
//                   binding.btnCheckAvailability.setEnabled(false);
//                   PrefUtils.setUserHashTag(requireContext(), name);
//
//                   PrefUtils.setLoginName(requireContext(), name);
////                   Log.i("LOGIN NAME", PrefUtils.getLoginName(requireContext()));
////                   Log.i("LOGIN HASHTAG", PrefUtils.getUserHashTag(requireContext()));
//                   binding.btnNext.setVisibility(View.VISIBLE);
//                   UIUtils.hideTransparentDialog();
//               }
//           }
//       });
//
//    }
//
//    // method to set the user's entered phone number
//    public void setPhoneTxt (String phoneTxt) {
//        binding.tvPhoneTxt.setText(phoneTxt);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        boolean isSetupComplete = PrefUtils.getKeyboardSetupIsComplete(getActivity());
//        if (isSetupComplete && isThankUCardShown) {
//            ((SignupActivity)getActivity()).changeToolbarText("Invite Friends");
//            binding.btnInviteFriend.setVisibility(View.VISIBLE);
//            binding.btnRegContinue.setVisibility(View.GONE);
//            binding.tvExitSetup.setVisibility(View.VISIBLE);
//            binding.tvExitSetup.setOnClickListener(this);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_reg_continue:
////                startActivity(new Intent(getActivity(), AccountSetupActivity.class));
//                startActivity(new Intent(getActivity(), CreditCardSetupActivity.class));
//
//                //Reconfigured to redirect to SetupWizard as opposed to account setup where it
//                //went initially.
////                Intent setupIntent = new Intent();
////                setupIntent.setClass(requireContext(), SetupWizardActivity.class);
////                setupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
////                startActivity(setupIntent);
//                break;
//            case R.id.btn_check_availability:
//                validateNameAndProceed(binding.etEnterHashtag.getText().toString());
//                break;
//            case R.id.btn_next:
//                showThankUCard();
//                break;
//            case R.id.btn_invite_friend:
//                filteredShare();
//                break;
//            case R.id.tv_resend_code:
//                checkTimerAndSendCode();
//                break;
//            case R.id.tv_exit_setup:
//                getActivity().finish();
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//
//                break;
//        }
//    }
//
//    private void checkTimerAndSendCode() {
//
//        cTimer = new CountDownTimer(30000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                binding.tvResendCode.setText("please wait.. Code will be resent in: " + millisUntilFinished / 1000 + " seconds.");
//                binding.tvResendCode.setEnabled(false);
//            }
//
//            public void onFinish() {
//                binding.tvResendCode.setText("Didn't get the code ? Resend Code");
//                binding.tvResendCode.setEnabled(true);
//                resendOTP();
//            }
//        }.start();
//    }
//
//    private void resendOTP() {
//        OTPRequest otpRequest = new OTPRequest();
//        otpRequest.setPhone(phone);
//        viewModel.requestOTP(otpRequest);
//        observeOTP();
//    }
//
//
//    private void observeOTP() {
//        viewModel.getOTPResponse().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                if (!TextUtils.isEmpty(s) && !s.equalsIgnoreCase("null")) {
//
//                    Toast.makeText(context, "OTP successfully sent to your Phone", Toast.LENGTH_SHORT).show();
////                    UIUtils.hideTransparentDialog();
//                }
//            }
//        });
//    }
//
//    /**
//     * share intent for all apps
//     */
//    private void showShareIntent() {
//        String hashTag = PrefUtils.getUserHashTag(getActivity());
//        String shareBody = "Hi there, Join me on #Payme, It is the new way I send & receive cash. My Unique Hashtag is (#"+hashTag+")\n\n";
//        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//        sharingIntent.setType("message/rfc822");
//        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Join Me on #PayMe");
//        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.invite_using)));
//    }
//
//    /**
//     * share intent for filtered apps
//     */
//    public void filteredShare() {
//        Resources resources = getResources();
//
//        String hashTag = PrefUtils.getUserHashTag(getActivity());
//        String shareBody = "Hi there, Join me on #Payme, It is the new way I send & receive cash. My Unique Hashtag is (#"+hashTag+")\n";
//
//        Intent emailIntent = new Intent();
//        emailIntent.setAction(Intent.ACTION_SEND);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Join Me on #PayMe");
//        emailIntent.setType("message/rfc822");
//
//        PackageManager pm = getActivity().getPackageManager();
//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//        sendIntent.setType("text/plain");
//
//        Intent openInChooser = Intent.createChooser(emailIntent, resources.getString(R.string.invite_using));
//
//        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
//        List<LabeledIntent> intentList = new ArrayList<>();
//        for (int i = 0; i < resInfo.size(); i++) {
//            // Extract the label, append it, and repackage it in onHomeContainerInitialize LabeledIntent
//            ResolveInfo ri = resInfo.get(i);
//            String packageName = ri.activityInfo.packageName;
//            if(packageName.contains("android.email")) {
//                emailIntent.setPackage(packageName);
//            } else if(packageName.contains("twitter") || packageName.contains("facebook")
//                    || packageName.contains("mms") || packageName.contains("android.gm")
//                    || packageName.contains("com.whatsapp") || packageName.contains("org.telegram")
//                    || packageName.contains("com.Slack") || packageName.contains("com.imo") || packageName.contains("instagram")) {
//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
//                intent.setAction(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
//
//                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
//            }
//        }
//
//        // convert intentList to array
//        LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);
//
//        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
//        startActivity(openInChooser);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if(cTimer!=null)
//            cTimer.cancel();
//    }
//
//
//}
