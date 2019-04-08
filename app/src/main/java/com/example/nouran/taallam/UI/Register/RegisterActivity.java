package com.example.nouran.taallam.UI.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nouran.taallam.UI.ForgetPassword.ForgetPassword2Activity;
import com.example.nouran.taallam.Users;
import com.example.nouran.taallam.Model.Register;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.UI.Wellcome.WellcomeActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private EditText mRegisterName;
    private EditText mRegisterMail;
    private EditText mRegisterPass;
    private String mUserName, mPassword, mConfirmedpass, mFacebookID, mEmail;
    private EditText mConfirmedRegisterPass;
    private ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterName = findViewById(R.id.register_name);
        mRegisterMail = findViewById(R.id.register_mail);
        mRegisterPass = findViewById(R.id.register_pass);
        Button mRegisterButton = findViewById(R.id.register_button);
        mConfirmedRegisterPass = findViewById(R.id.confirmed_register_pass);

        mRegProgress = new ProgressDialog(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.registration);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName = mRegisterName.getText().toString();
                mPassword = mRegisterPass.getText().toString();
                mEmail = mRegisterMail.getText().toString();
                mConfirmedpass = mConfirmedRegisterPass.getText().toString();

                if (TextUtils.isEmpty(mRegisterName.getText().toString())) {
                    mRegisterName.setError(getString(R.string.required));
                    mRegisterName.requestFocus();
                }
                if (TextUtils.isEmpty(mRegisterMail.getText().toString())) {
                    mRegisterMail.setError(getString(R.string.required));
                    mRegisterMail.requestFocus();
                }
                if (TextUtils.isEmpty(mRegisterPass.getText().toString())) {
                    mRegisterPass.setError(getString(R.string.required));
                    mRegisterPass.requestFocus();
                }
                if (TextUtils.isEmpty(mConfirmedRegisterPass.getText().toString())) {
                    mConfirmedRegisterPass.setError(getString(R.string.required));
                    mConfirmedRegisterPass.requestFocus();
                } if (mPassword.length() < 6)
                {
                    mConfirmedRegisterPass.setError(getString(R.string.too_small));
                    mConfirmedRegisterPass.requestFocus();
                }else {
                    String mConfirmedPass = mRegisterPass.getText().toString(), mPassword = mConfirmedRegisterPass.getText().toString();
                    if (!mConfirmedPass.equals(mPassword)) {
                        mConfirmedRegisterPass.setError(getString(R.string.not_matched));
                        mRegisterPass.setError(getString(R.string.not_matched));
                        mRegisterPass.setFocusable(true);
                        Toast.makeText(RegisterActivity.this, getString(R.string.not_matched), Toast.LENGTH_SHORT).show();
                    } else {
                        boolean check = Patterns.EMAIL_ADDRESS.matcher(mRegisterMail.getText().toString()).matches();
                        if (check) {
                            mRegProgress.setTitle(getString(R.string.register));
                            mRegProgress.setMessage(getString(R.string.prograss_msg));
                            mRegProgress.setCanceledOnTouchOutside(false);//prevenr user from touch on screen
                            mRegProgress.show();
                            getData(mEmail, mUserName, mPassword, mConfirmedpass);
                        } else {
                            Toast.makeText(RegisterActivity.this, R.string.not_valid_mail_msg, Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });


    }

    private void getData(String mEmail, String mUserName, String mPassword, String mConfirmedpass) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yaken.cloudapp.net/Ta3llam/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Users api = retrofit.create(Users.class);
        Call<Register> call = api.register(mEmail, mUserName, mPassword, mConfirmedpass, "", false);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                try {
                    mRegProgress.dismiss();
                    if (response.body().getIsSuccess()) {
                        Intent mWellcomeIntent = new Intent(RegisterActivity.this, WellcomeActivity.class);
                        startActivity(mWellcomeIntent);
                        finish();
                        SharedPreferences.Editor sharedPrefsEditor;
                        final String MY_PREFS_NAME = "MyPrefsFile";
                        sharedPrefsEditor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        sharedPrefsEditor.putString("UserID", response.body().getUserID());
                        sharedPrefsEditor.apply();

                        Log.i("LLLL", response.body().getUserID());
                    } else
                    {
                        Toast.makeText(RegisterActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("LLLL", response.body().getErrorMessage());
                } }catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                mRegProgress.dismiss();

            }
        });
    }

}
