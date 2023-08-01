package com.samples.phoneverification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.samples.phoneverification.databinding.ActivityFeedbackBinding;

public class FeedbackActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
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
                String userEmail = user.getEmail();
                binding.editTextEmail.setText(userEmail);
                binding.editTextEmail.setEnabled(false);
            } else  if (user.getProviderData().get(1).getProviderId().equals("phone")){
                binding.editTextEmail.setEnabled(true);
            } else
                Toast.makeText(this, "User must login to application", Toast.LENGTH_SHORT).show();
        }

        binding.back.setOnClickListener(v -> onBackPressed());
        binding.btnSubmit.setOnClickListener(v -> {
            submitFeedback();
        });
    }

    private void submitFeedback() {

        String email = binding.editTextEmail.getText().toString().trim();
        String feedback = binding.editTextFeedback.getText().toString().trim();
    }
}