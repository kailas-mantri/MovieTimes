package com.samples.phoneverification.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samples.phoneverification.databinding.ActivityFeedbackBinding;
import com.samples.phoneverification.dbmodel.StoreFirebaseFeedback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FeedbackActivity extends BaseActivity {

    private String userEmail;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private DatabaseReference reference;
    private ActivityFeedbackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if (user != null) {
            if (user.getProviderData().get(1).getProviderId().equals("google.com")) {
                userEmail = user.getEmail();
                binding.editTextEmail.setText(userEmail);
                binding.editTextEmail.setEnabled(false);
            } else  if (user.getProviderData().get(1).getProviderId().equals("phone")){
                binding.editTextEmail.setEnabled(true);
                userEmail = binding.editTextEmail.getText().toString().trim();
            } else
                Toast.makeText(this, "User must login to application", Toast.LENGTH_SHORT).show();
        }

        binding.back.setOnClickListener(v -> onBackPressed());
//        binding.editTextFeedback.set

        if (userEmail != null) {
            submitButton(userEmail);
            reference = FirebaseDatabase.getInstance().getReference("Feedbacks");
        } else {
            binding.btnSubmit.setEnabled(false);
        }
    }

    private void submitButton(String userEmail) {
        binding.btnSubmit.setOnClickListener(v -> {
            String feedback = binding.feedbackBox.getText().toString().trim();
            if (!feedback.isEmpty()) {
                String timeStamp = getFormattedTimeStamp();
                StoreFirebaseFeedback f = new StoreFirebaseFeedback();
                f.setEmail(userEmail);
                f.setTimeStamp(timeStamp);
                f.setFeedback(feedback);

                String key = reference.push().getKey();
                if(key != null) {
                    reference.child(key).setValue(f).addOnSuccessListener(aVoid -> {
                        Log.d("Feedback Submitted", "submitButton: "+f);
                        feedbackSubmitted();
                    }).addOnFailureListener(e ->  {
                        Log.d("Feedback Failed", "submitButton: "+f);
                        feedbackForm();
                    });
                }
            } else {
                Toast.makeText(this, "Please enter the feedback", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFormattedTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yy HH:mm:ss z", Locale.getDefault());
        Date dateTime = new Date(System.currentTimeMillis());
        return dateFormat.format(dateTime);
    }

    private void feedbackForm() {
        binding.feedbackForm.setVisibility(View.VISIBLE);
        binding.textViewThankYou.setVisibility(View.GONE);
    }

    private void feedbackSubmitted() {
        binding.feedbackForm.setVisibility(View.GONE);
        binding.textViewThankYou.setVisibility(View.VISIBLE);
    }
}