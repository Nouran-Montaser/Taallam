package com.example.nouran.taallam.UI.ForgetPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Login;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Login.LoginActivity;
import com.example.nouran.taallam.UI.Main.MainActivity;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.UI.Wellcome.WellcomeActivity;
import com.example.nouran.taallam.Users;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword2Activity extends AppCompatActivity {

    private EditText mCPass;
    private EditText mCode;
    private EditText mPass;
    private Button mFinishButton;
    private String email;
    private ProgressDialog mLogInProgress;
    private static SharedPreferences sharedPrefs;
    private final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);
        mCPass = findViewById(R.id.ForgetPassword_CPass);
        mCode = findViewById(R.id.ForgetPassword_code);
        mPass = findViewById(R.id.ForgetPassword_pass);
        mFinishButton = findViewById(R.id.forget_finish_button);

        email = getIntent().getStringExtra("Email");


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(getResources().getString(R.string.forget_password));

        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mCode.getText().toString())) {
                    mCode.setError(getString(R.string.missing_code));
                    mCode.requestFocus();
                }if (TextUtils.isEmpty(mPass.getText().toString())) {
                    mPass.setError(getString(R.string.missing_password));
                    mPass.requestFocus();
                }if (TextUtils.isEmpty(mCPass.getText().toString())) {
                    mCPass.setError(getString(R.string.Missing_confirmed_password));
                    mCPass.requestFocus();
                }if (mPass.length() < 6)
                {
                    mPass.setError(getString(R.string.too_small));
                    mPass.requestFocus();
                }else {
                    String mConfirmedPass = mCPass.getText().toString(), mPassword = mPass.getText().toString();
                    if (!mConfirmedPass.equals(mPassword)) {
                        mCPass.setError(getString(R.string.not_matched));
                        mPass.setError(getString(R.string.not_matched));
                        mPass.requestFocus();
                        Toast.makeText(ForgetPassword2Activity.this, getString(R.string.not_matched), Toast.LENGTH_SHORT).show();
                    } else {

                        mLogInProgress = new ProgressDialog(ForgetPassword2Activity.this);
                        mLogInProgress.setTitle(getString(R.string.logging_in));
                        mLogInProgress.setMessage(getString(R.string.prograss_msg));
                        mLogInProgress.setCanceledOnTouchOutside(false);//prevenr user from touch on screen
                        mLogInProgress.show();

                        Users api = RetrofitClient.getClient(ForgetPassword2Activity.this).create(Users.class);
                        Call<BaseResponse> call = api.verifyPassword(email, mCode.getText().toString(), mPass.getText().toString(),
                                mCPass.getText().toString());

                        call.enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                mLogInProgress.dismiss();
                                if (response.body() != null) {
                                    if (response.body().getIsSuccess()) {
                                        logIn(email,mPass.getText().toString());
//                                        Intent mMainIntent = new Intent(ForgetPassword2Activity.this, LoginActivity.class);
//                                        startActivity(mMainIntent);
                                    } else
                                        Toast.makeText(ForgetPassword2Activity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {
                                mLogInProgress.dismiss();
                            }
                        });
                    }
                }
            }
        });
    }

    public void logIn(String email , String password)
    {
        Users api = RetrofitClient.getClient(ForgetPassword2Activity.this).create(Users.class);
        Call<Login> call = api.login(email, password, "123456789", "", false);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if (response.body().getIsSuccess()) {
                        if (response.body().getIsFirstTime()) {
                            Intent wellcomeIntent = new Intent(ForgetPassword2Activity.this, WellcomeActivity.class);
                            startActivity(wellcomeIntent);
                        } else {
                            Intent mainactivityIntent = new Intent(ForgetPassword2Activity.this, MainActivity.class);
                            startActivity(mainactivityIntent);
                            finish();
                        }
                        Toast.makeText(ForgetPassword2Activity.this, "Done", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor sharedPrefsEditor;
                        sharedPrefsEditor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        sharedPrefsEditor.putString("UserID", response.body().getUserID());
                        sharedPrefsEditor.putBoolean("IsFirstTime", false);
                        sharedPrefsEditor.apply();

                        Log.i("LLLL", response.body().getUserID());
                    } else {
                        Log.i("LLLL", response.body().getErrorMessage());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.i("FAILUER :", t.getMessage());
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
