package com.example.nouran.taallam.UI.Main.Profile_Fragment;

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

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.UserProfileDetails;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.MainActivity;
import com.example.nouran.taallam.Users;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickClick;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String mUserId;


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

        mEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void uploadToServer(String filePath) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading Image...");
        pd.setCancelable(false);
        pd.show();

        Users api = RetrofitClient.getClient(this).create(Users.class);
        //Create a file object using file path
        File file = new File(filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
        //
        Call<BaseResponse> call = api.editProfilePictureDetails(mUserId, part);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body() != null)
                {
                    if (response.body().getIsSuccess())
                    {
                        Toast.makeText(EditActivity.this, R.string.upload, Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(EditActivity.this, response.body().getErrorMessage() , Toast.LENGTH_SHORT).show();
                }
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(EditActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
                pd.dismiss();
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



        // TODO : edit profile pic

//        Users api = RetrofitClient.getClient(EditActivity.this).create(Users.class);
//        Call<BaseResponse> call = api.editProfilePictureDetails("0e98041e-95ab-4a84-8d5d-ad0abb71a11e", "0e98041e-95ab-4a84-8d5d-ad0abb71a11e");
//        call.enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//            }
//        });

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
                if ((!TextUtils.isEmpty(mEditName.getText().toString())) || !(TextUtils.isEmpty(mEditAbout.getText().toString()))) {
                    Users api = RetrofitClient.getClient(EditActivity.this).create(Users.class);
                    Call<BaseResponse> call = api.editProfileDetails(mUserId, mEditName.getText().toString(), mEditAbout.getText().toString());
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body() != null)
                            {
                                if (response.body().getIsSuccess())
                                {
                                    Toast.makeText(EditActivity.this, "Your profile has been updated", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(EditActivity.this, "Updating you profile has failed ,Please try again", Toast.LENGTH_SHORT).show();
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

}
