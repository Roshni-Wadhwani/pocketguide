package com.example.pocketguide;

import android.content.*;
import android.graphics.*;
import android.graphics.Path;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;
import androidx.appcompat.app.*;
import com.google.zxing.*;
import com.google.zxing.common.*;
import com.journeyapps.barcodescanner.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class temp extends AppCompatActivity {
    private static final int SELECT_PICTURE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button for generating QR code
        Button btnGenerate = findViewById(R.id.btnGenerate);
        //Button btnUpload = findViewById(R.id.Upload);
        //Text will be entered here to generate QR code
        EditText etText = findViewById(R.id.etText);
        //ImageView for generated QR code
        ImageView imageCode = findViewById(R.id.imageCode);
//        String imageFilePath="C:\\Users\\Roshni\\Downloads\\Screenshot from 2023-01-23 13-01-00.png";
//        Path pathToImage= null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            pathToImage = (Path) Paths.get(imageFilePath);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if(Files.notExists((java.nio.file.Path) pathToImage)){
//                throw new IllegalArgumentException("Image does not exist at specified path");
//            }
//            try{
//                byte[]imagebytes=Files.readAllBytes((java.nio.file.Path) pathToImage);
//                String base64EncodedImageBytes= Base64.getEncoder().encodeToString(imagebytes);
//                byte[] decodedImageBytes=Base64.getDecoder().decode(base64EncodedImageBytes);
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageChooser();
//            }
//        });
    }
    public void generate(String imageFilePath){
        Button btnGenerate = findViewById(R.id.btnGenerate);
        ImageView imageCode = findViewById(R.id.imageCode);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Path pathToImage = null;
                MultiFormatWriter mWriter = new MultiFormatWriter();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    pathToImage = (Path) Paths.get(imageFilePath);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (Files.notExists((java.nio.file.Path) pathToImage)) {
                        throw new IllegalArgumentException("Image does not exist at specified path");
                    }
                    try {
                        byte[] imagebytes = Files.readAllBytes((java.nio.file.Path) pathToImage);
                        String base64EncodedImageBytes = Base64.getEncoder().encodeToString(imagebytes);
                        byte[] decodedImageBytes = Base64.getDecoder().decode(base64EncodedImageBytes);
                        BitMatrix mMatrix = mWriter.encode(base64EncodedImageBytes, BarcodeFormat.QR_CODE, 400, 400);
                        BarcodeEncoder mEncoder = new BarcodeEncoder();
                        Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
                        imageCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView
                        // to hide the keyboard
//                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                        manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (WriterException e) {
//                    e.printStackTrace();
                    }
                }

            }
//                String myText = etText.getText().toString().trim();
//                //initializing MultiFormatWriter for QR code
//                MultiFormatWriter mWriter = new MultiFormatWriter();
//                try {
//                    //BitMatrix class to encode entered text and set Width & Height
//                    BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400,400);
//                    BarcodeEncoder mEncoder = new BarcodeEncoder();
//                    Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
//                    imageCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView
//                    // to hide the keyboard
//                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }
//            }
            //getting text from input text field.
        });
    }

    void imageChooser(){
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select picture"),SELECT_PICTURE);

    }
    public void onActivityResult(int req,int res,Intent data){
        super.onActivityResult(req,res,data);
        String imageFilePath="";
        if(res==RESULT_OK){
            if(req==SELECT_PICTURE){
                Uri selectedImageUri=data.getData();
                if(null!=selectedImageUri){
                    imageFilePath = String.valueOf(selectedImageUri);
                    generate(imageFilePath);
                }
            }
        }
    }
}