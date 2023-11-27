package com.example.pocketguide;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;
import androidx.appcompat.app.*;
import com.google.zxing.*;
import com.google.zxing.common.*;
import com.journeyapps.barcodescanner.*;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    Button chooseimgbtn;
    private static final int IMAGE_PICK_CODE =1000;
    private static final int PERMISSION_CODE =1000;
    private ImageView Imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String myText="";

        try {
            URL imageURL = new URL("https://upload.wikimedia.org//wikipedia//commons//" +
                    "thumb//b//b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png");
            myText = imageURL.toString();
            System.out.println(myText);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        //Button for generating QR code
        Button btnGenerate = findViewById(R.id.btnGenerate);
        //Text will be entered here to generate QR code
        EditText etText = findViewById(R.id.etText);
        String finalMyText = myText;
        //chooseimgbtn = findViewById(R.id.Upload);
        //ImageView for generated QR code
        ImageView imageCode = findViewById(R.id.imageCode);
//        chooseimgbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
//                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//                    requestPermissions(permissions, PERMISSION_CODE);
//                }
//                else {
//                    pickImageFromGallery();
//                }
//            }
//        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //String myText = etText.getText().toString().trim();
                //initializing MultiFormatWriter for QR code
                MultiFormatWriter mWriter = new MultiFormatWriter();
                try {
                    //BitMatrix class to encode entered text and set Width & Height
                    BitMatrix mMatrix = mWriter.encode(finalMyText, BarcodeFormat.QR_CODE, 400,400);
                    BarcodeEncoder mEncoder = new BarcodeEncoder();
                    Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
                    imageCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView
                    // to hide the keyboard
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
            });

        }
//    private void pickImageFromGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent , IMAGE_PICK_CODE);
//    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_CODE: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    pickImageFromGallery();
//                }
//                else {
//                    Toast.makeText(this, "Permission denied!" , Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
//            Imageview.setImageURI(data.getData());
//        }
//    }
}

