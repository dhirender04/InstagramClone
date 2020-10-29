package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.security.Key;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


private EditText edtEmail,edtUsername,edtPassword;
private Button btnSignUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Sign Up");

        edtEmail=findViewById(R.id.edtEnterEmail);
        edtUsername=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtUserPassword);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });

        btnSignUp=findViewById(R.id.btnSignup);
        btnLogIn=findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser()!=null) {
//            ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }



    }
    @Override
    public void onClick(View view){

        switch (view.getId()){
            case R.id.btnSignup:

                if(edtEmail.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this,  "Email, Username,Password is required",
                            Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                }else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtUsername.getText().toString());
                    progressDialog.show();


                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, appUser.getUsername() + "is signed up",
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, "There was an error:" + e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }

                            progressDialog.dismiss();

                        }
                    });
                }

            break;
            case R.id.btnLogIn:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
        }

    }

    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity(){
        Intent intent=new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}