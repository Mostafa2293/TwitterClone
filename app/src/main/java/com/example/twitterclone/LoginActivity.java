package com.example.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // UI components
    private EditText edtLoginEmail;
    private EditText edtLoginpassword;
    private Button btnLoginActivity;
    private Button btnSignUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.log_in_activity_title);

        //init UI component
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginpassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpActivity = findViewById(R.id.btnSignUpLoginActivity);

        edtLoginpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });

        // OnClickListeners
        btnLoginActivity.setOnClickListener(this);
        btnSignUpActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser() !=null){
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.btnLoginActivity:
                ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginpassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(LoginActivity.this, user.getUsername() + " Is Logged In " , Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            transitionToSocialMediaActivity();
                        } else {
                            FancyToast.makeText(LoginActivity.this, "Error: something went wrong !!" , Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                    }
                });

                break;

            case R.id.btnSignUpLoginActivity:

                Intent intent = new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);

                break;

        }

    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager  = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this method if for the transition to the social media activity
    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LoginActivity.this,TwitterUsers.class);
        startActivity(intent);
        finish();
    }
}
