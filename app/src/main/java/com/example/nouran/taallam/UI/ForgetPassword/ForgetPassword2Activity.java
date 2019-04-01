package com.example.nouran.taallam.UI.ForgetPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.MainActivity;
import com.example.nouran.taallam.R;
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
                if (TextUtils.isEmpty(mCode.getText().toString()))
                    mCode.setError(getString(R.string.missing_code));
                if (TextUtils.isEmpty(mPass.getText().toString()))
                    mPass.setError(getString(R.string.missing_password));
                if (TextUtils.isEmpty(mCPass.getText().toString()))
                    mCPass.setError(getString(R.string.Missing_confirmed_password));
                else {
                    String mConfirmedPass = mCPass.getText().toString(), mPassword = mPass.getText().toString();
                    if (!mConfirmedPass.equals(mPassword)) {
                        mCPass.setError(getString(R.string.not_matched));
                        mPass.setError(getString(R.string.not_matched));
                        Toast.makeText(ForgetPassword2Activity.this, getString(R.string.not_matched), Toast.LENGTH_SHORT).show();
                    } else {

                        mLogInProgress = new ProgressDialog(ForgetPassword2Activity.this);
                        mLogInProgress.setTitle("Logging In");
                        mLogInProgress.setMessage("Please wait while we check your credentials!");
                        mLogInProgress.setCanceledOnTouchOutside(false);//prevenr user from touch on screen
                        mLogInProgress.show();

                        Users api = RetrofitClient.getClient(ForgetPassword2Activity.this).create(Users.class);
                        Call<BaseResponse> call = api.verifyPassword(email, mCode.getText().toString(), mPass.getText().toString(),
                                mCPass.getText().toString());

                        call.enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                if (response.body() != null) {
                                    if (response.body().getIsSuccess()) {
                                        Intent mMainIntent = new Intent(ForgetPassword2Activity.this, MainActivity.class);
                                        startActivity(mMainIntent);
                                    } else
                                        Toast.makeText(ForgetPassword2Activity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {

                            }
                        });
                        Intent mMainIntent = new Intent(ForgetPassword2Activity.this, MainActivity.class);
                        startActivity(mMainIntent);
                    }
                }
            }
        });
    }
}
