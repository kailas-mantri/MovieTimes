package com.samples.phoneverification.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samples.phoneverification.R;
import com.samples.phoneverification.activity.HomeActivity;
import com.samples.phoneverification.databinding.FragmentOtpLayoutBinding;
import com.samples.phoneverification.datamodel.StoreFirebaseUser;

import java.util.concurrent.TimeUnit;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    // TODO: Rename and change types of parameters
    EditText et1, et2, et3, et4,  et5, et6;
    FragmentOtpLayoutBinding binding;
    private final static String TAG = "BottomSheetDialog";
    FirebaseAuth mAuth;
    DatabaseReference mDbReference;
    private String otp, phoneNumber, mVerificationId, seconds = "";
    private CountDownTimer timer;
    private PhoneAuthProvider.ForceResendingToken mResendCode;

    public BottomSheetDialog() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BottomSheetDialog newInstance(String phoneNumber) {
        BottomSheetDialog fragment = new BottomSheetDialog();
        Bundle args = new Bundle();
        args.putString("phoneNumber", phoneNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOtpLayoutBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        if (getArguments() != null)
            phoneNumber = this.getArguments().getString("phoneNumber");

        if (!phoneNumber.isEmpty()) {
            sendVerificationCode(phoneNumber);
        } else {
            Toast.makeText(getActivity(), "Provide Mobile No.", Toast.LENGTH_SHORT).show();
        }

        binding.resendOtp.setOnClickListener(v -> resendVerificationCode(phoneNumber));
        binding.closeDialog.setOnClickListener(v -> dismiss());

       et1 = binding.FirstOTPeT;
       et2 = binding.SecondOTPeT;
       et3 = binding.ThirdOTPeT;
       et4 = binding.FourthOTPeT;
       et5 = binding.FifthOTPeT;
       et6 = binding.SixthOTPeT;

        OtpTextWatcher(et1);
        OtpTextWatcher(et2);
        OtpTextWatcher(et3);
        OtpTextWatcher(et4);
        OtpTextWatcher(et5);
        OtpTextWatcher(et6);

        binding.verifyOtpBtn.setOnClickListener(v -> {
            otp = getOTP();
            if (!otp.isEmpty()) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
                signInWithPhoneAuthCredential(credential);
            }
        });
        return binding.getRoot();
    }

    private void OtpTextWatcher(EditText text) {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("NonConstantResourceId")
            @Override
            public void afterTextChanged(Editable s) {
                switch (text.getId()) {
                    case R.id.FirstOTPeT:
                        if (text.length() == 1) {
                            et2.requestFocus();
                        }
                        break;
                    case R.id.SecondOTPeT:
                        if (text.length() == 1) {
                            et3.requestFocus();
                        } else if (text.length() == 0) {
                            et1.requestFocus();
                        }
                        break;
                    case R.id.ThirdOTPeT:
                        if (text.length() == 1) {
                            et4.requestFocus();
                        } else if (text.length() == 0) {
                            et2.requestFocus();
                        }
                        break;
                    case R.id.FourthOTPeT:
                        if (text.length() == 1) {
                            et5.requestFocus();
                        } else if (text.length() == 0) {
                            et3.requestFocus();
                        }
                        break;
                    case R.id.FifthOTPeT:
                        if (text.length() == 1) {
                            et6.requestFocus();
                        } else if (text.length() == 0) {
                            et4.requestFocus();
                        }
                        break;
                    case R.id.SixthOTPeT:
                        if (text.length() == 1) {
                            et6.requestFocus();
                        } else if (text.length() == 0) {
                            et5.requestFocus();
                        }
                        break;
                }
            }
        });

    }

    private String getOTP() {
        return binding.FirstOTPeT.getText().toString().trim() +
                binding.SecondOTPeT.getText().toString().trim() +
                binding.ThirdOTPeT.getText().toString().trim() +
                binding.FourthOTPeT.getText().toString().trim() +
                binding.FifthOTPeT.getText().toString().trim() +
                binding.SixthOTPeT.getText().toString().trim();
    }

    private void resendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth).setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS).setActivity(requireActivity())
                .setCallbacks(mCallBacks).setForceResendingToken(mResendCode).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        binding.resendOtp.setEnabled(false);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            Log.d(TAG, "onVerificationComplete:"+credential);
            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.w(TAG, "onVerificationFailed ", e);
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Log.w(TAG, "InvalidCredentialException", e);
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Log.w(TAG, "TooManyRequest", e);
            } else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                Log.w(TAG, "MissingActivityForRecaptcha", e);
            }
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            mResendCode = forceResendingToken;
            Log.d(TAG, "onCodeSent: "+s);
            startCountDownTimer();
        }
    };

    private void startCountDownTimer() {
        timer = new CountDownTimer(59000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                binding.resendOtp.setEnabled(false);
                long secondRemaining = millisUntilFinished / 1000;
                if (secondRemaining < 10) {
                    seconds = "00:0" + secondRemaining;
                } else {
                    seconds = "00:" + secondRemaining;
                }
                binding.timer.setText(seconds);
                int onTickColor = ContextCompat.getColor(binding.resendOtp.getContext(), R.color.offWhite);
                binding.resendOtp.setTextColor(onTickColor);
            }

            @Override
            public void onFinish() {
                binding.resendOtp.setEnabled(true);
                int onFinishColor = ContextCompat.getColor(binding.resendOtp.getContext(), R.color.white);
                binding.resendOtp.setTextColor(onFinishColor);
            }
        };
        timer.start();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "signInWithCredential: Successful");
                FirebaseUser user = task.getResult().getUser();
                mDbReference = FirebaseDatabase.getInstance().getReference("Users");
                if (user != null) {
                    String userId = user.getUid();
                    String userNumber = user.getPhoneNumber();
                    checkUser(userId, userNumber);
                }
            } else {
                Log.w(TAG, "signInWithCredential: Failed", task.getException());
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(requireActivity(), "Provide correct credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkUser(String userId, String userNumber) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(userId)) {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    Log.d(TAG, "User Logged in Successfully");
                    dismiss();
                } else {
                    StoreFirebaseUser firebaseUser = new StoreFirebaseUser(userId, userNumber);
                    reference.child(userId).setValue(firebaseUser);
                    Log.d(TAG, "User Added Successfully");
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // error needs in exception block
                Log.w(TAG, "onCancelled: DatabaseError", error.toException());
            }
        });
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth).setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS).setActivity(requireActivity())
                .setCallbacks(mCallBacks).build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (timer != null)
            timer.cancel();
    }
}