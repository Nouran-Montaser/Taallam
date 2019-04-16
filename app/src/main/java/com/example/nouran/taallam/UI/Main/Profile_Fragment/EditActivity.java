package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.UserProfileDetails;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.MainActivity;
import com.example.nouran.taallam.Users;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickClick;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONObject;

import java.io.File;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class EditActivity extends AppCompatActivity {

    private EditText mEditName;
    private CircleImageView mProfilePic;
    private EditText mEditAbout;
    private ActionMode currentActionMode;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int GALLERY_PICK = 1;
    private ImageView mEditIcon;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private String mUserId, user_name, about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout

        mEditName = findViewById(R.id.edit_name);
        mProfilePic = findViewById(R.id.profile_pic);
        mEditAbout = findViewById(R.id.edit_about);

        mEditIcon = findViewById(R.id.edit_icon);

        sharedPrefs = EditActivity.this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        mUserId = sharedPrefs.getString("UserID", null);

        user_name = getIntent().getStringExtra("Name");
        about = getIntent().getStringExtra("About");

        mEditName.setText(user_name);
        mEditAbout.setText(about);

        AndroidNetworking.initialize(getApplicationContext());
        // Adding an Network Interceptor for Debugging purpose :
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        mEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                Permissions.check(EditActivity.this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        // do your task.
                        PickSetup setup = new PickSetup();

                        PickImageDialog.build(setup)
                                .setOnClick(new IPickClick() {
                                    @Override
                                    public void onGalleryClick() {
                                        Intent intent = new Intent();
                                        intent.setType("image/*");
                                        intent.setAction(Intent.ACTION_GET_CONTENT);
                                        startActivityForResult(Intent.createChooser(intent, "Select Image"), GALLERY_PICK);
                                    }

                                    @Override
                                    public void onCameraClick() {
                                        dispatchTakePictureIntent();
                                    }
                                }).show(EditActivity.this);
                    }
                });
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void tryr() {
//        String filePath = getRealPathFromURIPath(uri, MainActivity.this);
//        File file = new File(filePath);
//        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
//        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
//        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

    }

    private void uploadToServer(String filePath) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading Image...");
        pd.setCancelable(false);
        pd.show();

        //Create a file object using file path
        File file = new File(filePath);

        AndroidNetworking.upload("http://yaken.cloudapp.net/Ta3llam/Api/User/EditProfilePictureDetails")
                .addQueryParameter("UserID", mUserId)
                .addHeaders("UserID", mUserId)
                .addHeaders("Token", mUserId)
                .addMultipartFile("image", file)
                .addMultipartParameter("key", "value")
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.i("LOLOLOL", bytesUploaded + "");
                        // do anything with progress
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pd.dismiss();
                        Log.i("LOLOLOL", response.toString());
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        Log.i("LOLOLOL", error.toString());
                        // handle error
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            final String imagePath;
            imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            mProfilePic.setImageBitmap(bitmap);

            uploadToServer(imagePath);

        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mProfilePic.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_menu: {
                if ((TextUtils.isEmpty(mEditAbout.getText().toString()) && (TextUtils.isEmpty(mEditName.getText().toString())))) {
                    return false;
                }
                if ((!TextUtils.isEmpty(mEditName.getText().toString()))) {
                    user_name = mEditName.getText().toString();
                }
                if (!(TextUtils.isEmpty(mEditAbout.getText().toString()))) {
                    about = mEditAbout.getText().toString();
                }
                if (!(TextUtils.isEmpty(mEditAbout.getText().toString()) || !(TextUtils.isEmpty(mEditAbout.getText().toString())))) {
                    Users api = RetrofitClient.getClient(EditActivity.this).create(Users.class);
                    Call<BaseResponse> call = api.editProfileDetails(mUserId, mEditName.getText().toString(), mEditAbout.getText().toString());
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body() != null) {
                                if (response.body().getIsSuccess()) {
                                    Toast.makeText(EditActivity.this, R.string.update_msg, Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(EditActivity.this, R.string.update_error_msg, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                        }
                    });


                }
                Intent mEditIntent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(mEditIntent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
