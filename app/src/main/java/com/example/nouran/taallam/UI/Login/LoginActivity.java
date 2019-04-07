package com.example.nouran.taallam.UI.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouran.taallam.Model.Login;
import com.example.nouran.taallam.UI.ForgetPassword.ForgetPasswordActivity;
import com.example.nouran.taallam.UI.Main.MainActivity;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.UI.Register.RegisterActivity;
import com.example.nouran.taallam.UI.Wellcome.WellcomeActivity;
import com.example.nouran.taallam.Users;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends Activity {

    private EditText mLoginPass;
    private Button mLoginButton;
    private Button mFbloginButton;
    private TextView mForgetPasswordTxt;
    private TextView mNoAccountTxt;
    private EditText mLoginMail;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private String id, name, birthday, gender, email;
    private Profile profile;
    private ProgressDialog mLogInProgress;
    private static SharedPreferences sharedPrefs;
    private final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("ResponseMainhash", FacebookSdk.getApplicationSignature(this));

        mLoginPass = findViewById(R.id.login_pass);
        mLoginButton = findViewById(R.id.login_button);
        mFbloginButton = findViewById(R.id.fblogin_button);
        mForgetPasswordTxt = findViewById(R.id.ForgetPassword_txt);
        mNoAccountTxt = findViewById(R.id.no_account_txt);
        mLoginMail = findViewById(R.id.login_mail);

        checkLogIn();

//        final List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
//        mFbloginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.v("LoginActivityoooooooo", "void");
//
//                if (v == mFbloginButton) {
//                    Log.v("LoginActivityoooooooo", "if");
//                    loginButton.performClick();
//                }
//            }
//        });
//        LoginManager.getInstance().logInWithReadPermissions(this,permissionNeeds);
//
////        FacebookSdk.sdkInitialize(getApplicationContext());
////        AppEventsLogger.activateApp(this);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//
//        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.FaceBook_login_button);

//        loginButton.registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//
//
//                        String accessToken = loginResult.getAccessToken()
//                                .getToken();
//                        Log.i("accessToken", accessToken);
//
//                        GraphRequest request = GraphRequest.newMeRequest(
//                                loginResult.getAccessToken(),
//                                new GraphRequest.GraphJSONObjectCallback() {
//                                    @Override
//                                    public void onCompleted(JSONObject object, GraphResponse response) {
//                                        Log.i("Logctivityooooooooooooo",
//                                                object.toString());
//
//                                        try {
//                                            id = object.getString("id");
//                                            email = object.getString("email");
//                                            Log.i("face book data", id +"  "+email);
//                                            Intent mFacebookIntent = new Intent(LoginActivity.this, RegisterActivity.class);
//                                            mFacebookIntent.putExtra("FaceBookEmail", email);
//                                            startActivity(mFacebookIntent);
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                });
//                        Bundle parameters = new Bundle();
//                        parameters.putString("fields",
//                                "id,name,email,gender, birthday");
//                        request.setParameters(parameters);
//                        request.executeAsync();
//                        }
//
//                    @Override
//                    public void onCancel() {
//                        Log.d("face book data", "onCancel");
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        Log.v("LoginActivityoooooooo", exception.getMessage());
////                        Log.v("LoginActivity", exception.getCause().toString());
//                    }
//                });

//        profile = Profile.getCurrentProfile().getCurrentProfile();
//        if (profile != null) {
//            LoginManager.getInstance().logOut();
//
//             user has logged in
//        } else {
//             user has not logged in
//        }


        ////////////

        mLogInProgress = new ProgressDialog(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mLoginMail.getText().toString())) {
                    mLoginMail.setError(getString(R.string.missing_email));
                    mLoginMail.requestFocus();
                }
                if (TextUtils.isEmpty(mLoginPass.getText().toString())) {
                    mLoginPass.setError(getString(R.string.missing_password));
                    mLoginPass.requestFocus();
                } else {
                    boolean check = android.util.Patterns.EMAIL_ADDRESS.matcher(mLoginMail.getText().toString()).matches();
                    if (check) {
                        mLogInProgress.setTitle("Logging In");
                        mLogInProgress.setMessage("Please wait while we check your credentials!");
                        mLogInProgress.setCanceledOnTouchOutside(false);//prevenr user from touch on screen
                        mLogInProgress.show();
                        signIn(mLoginMail.getText().toString(), mLoginPass.getText().toString());
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.not_valid_mail_msg, Toast.LENGTH_SHORT).show();
                        mLoginMail.requestFocus();
                    }
                }
            }
        });


        mNoAccountTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mRegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mRegisterIntent);
            }
        });

        mForgetPasswordTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mForgetPassIntent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(mForgetPassIntent);
            }
        });


    }

    private void signIn(String mail, String pass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yaken.cloudapp.net/Ta3llam/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Users api = retrofit.create(Users.class);
        Call<Login> call = api.login(mail, pass, "0000", "", false);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if (response.body().getIsSuccess()) {
                        mLogInProgress.dismiss();
                        if (response.body().getIsFirstTime()) {
                            Intent wellcomeIntent = new Intent(LoginActivity.this , WellcomeActivity.class);
                            startActivity(wellcomeIntent);
                        }
                        else
                        {
                            Intent mainactivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mainactivityIntent);
                            finish();
                        }
                        Toast.makeText(LoginActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor sharedPrefsEditor;
                        sharedPrefsEditor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        sharedPrefsEditor.putString("UserID", response.body().getUserID());
                        sharedPrefsEditor.apply();

                        Log.i("LLLL", response.body().getUserID());
                    } else {
                        Log.i("LLLL", response.body().getErrorMessage());
                        mLogInProgress.dismiss();
                        Toast.makeText(LoginActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                mLogInProgress.dismiss();
                Log.i("FAILUER :", t.getMessage());
                Toast.makeText(LoginActivity.this, R.string.login_msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }

    private void checkLogIn() {
        sharedPrefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String user_id_pref = sharedPrefs.getString("UserID", null);

        if (user_id_pref != null) {
            Intent StartIntent = new Intent(LoginActivity.this, MainActivity.class);
            StartIntent.putExtra("UserID", user_id_pref);
            startActivity(StartIntent);
            finish();
        }
    }

}
//    String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
//        InstanceID instanceID = InstanceID.getInstance(this);
//        String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
//                GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

