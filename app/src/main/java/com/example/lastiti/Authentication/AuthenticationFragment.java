package com.example.lastiti.Authentication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lastiti.R;
import com.example.lastiti.databinding.ContentLoginBinding;
import com.example.lastiti.databinding.ContentSignupBinding;
import com.example.lastiti.databinding.FragmentAuthenticationBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AuthenticationFragment extends Fragment {
    FragmentAuthenticationBinding binding;
    ContentLoginBinding loginBinding;
    ContentSignupBinding signupBinding;
    String filename = "auth";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthenticationBinding.inflate(inflater, container, false);
        loginBinding = binding.login;
        signupBinding = binding.signUp;

        File file = new File(requireContext().getFilesDir(), filename);

        signupBinding.logIn.setOnClickListener(view -> {
            loginBinding.ConstraintLayoutLogin.setVisibility(View.VISIBLE);
            signupBinding.ConstraintLayoutSignup.setVisibility(View.GONE);
        });
        loginBinding.signup.setOnClickListener(view -> {
            loginBinding.ConstraintLayoutLogin.setVisibility(View.GONE);
            signupBinding.ConstraintLayoutSignup.setVisibility(View.VISIBLE);
        });
        loginBinding.loginBtn.setOnClickListener(view -> {
            if (checkAccount(loginBinding.usernameLabel.getText().toString(), loginBinding.passwordLabel.getText().toString())) {
                Navigation.findNavController(view).navigate(R.id.action_authenticationFragment_to_viewPagerFragment);
            } else {
                Toast.makeText(requireContext(), "please check your Email or password", Toast.LENGTH_LONG).show();
            }

        });
        signupBinding.signUpBtn.setOnClickListener(view -> {
            if (createAccount(signupBinding.usernameLabel.getText().toString(), signupBinding.passwordLabel.getText().toString()))
                Navigation.findNavController(view).navigate(R.id.action_authenticationFragment_to_viewPagerFragment);
        });
        if (file.exists()) {
            Navigation.findNavController(container).navigate(R.id.action_authenticationFragment_to_viewPagerFragment);
        }
        return binding.getRoot();
    }

    private boolean checkAccount(String username, String password) {
        String[] fileContents = new String[2];
        int c = 0;
        try {
            FileInputStream fis = requireContext().openFileInput(filename);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    fileContents[c] = line;
                    c++;
                    line = reader.readLine();
                }
            } catch (IOException e) {
                Log.i("AuthenticationFragment", e.getMessage());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!username.equals(fileContents[0])) {
            Toast.makeText(requireContext(), "please check your Email", Toast.LENGTH_LONG).show();
            return false;
        } else if (!password.equals(fileContents[1])) {
            Toast.makeText(requireContext(), "please check your password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Boolean createAccount(String username, String password) {
        File file = new File(requireContext().getFilesDir(), filename);
        String fileContents = username + "\n" + password;
        file.delete();
        try {
            file.createNewFile();
            FileOutputStream fos = requireContext().openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(fileContents.getBytes());
            return true;
        } catch (Exception ex) {
            Log.i("AuthenticationFragment", ex.getMessage());
            return false;
        }
    }
}