package com.example.nouran.taallam.UI.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.Users;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    private Button mNextButton;
    private EditText mMailtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        mNextButton = findViewById(R.id.forget_next_button);
        mMailtxt = findViewById(R.id.ForgetPassword_mail);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(getResources().getString(R.string.forget_password));

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mMailtxt.getText().toString()))
                    mMailtxt.setError(getString(R.string.missing_email));
                else {
                    boolean check = android.util.Patterns.EMAIL_ADDRESS.matcher(mMailtxt.getText().toString()).matches();
                    if (check) {
                        Users api = RetrofitClient.getClient(ForgetPasswordActivity.this).create(Users.class);
                        Call<BaseResponse> call = api.ForgetPassword(mMailtxt.getText().toString());
                        call.enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                Toast.makeText(ForgetPasswordActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                if (response.body().getIsSuccess()) {
                                    Intent mainactivityIntent = new Intent(ForgetPasswordActivity.this, ForgetPassword2Activity.class);
                                    mainactivityIntent.putExtra("Email",mMailtxt.getText().toString());
                                    startActivity(mainactivityIntent);
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {

                            }
                        });

                    } else {
                        Toast.makeText(ForgetPasswordActivity.this, R.string.not_valid_mail_msg, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
