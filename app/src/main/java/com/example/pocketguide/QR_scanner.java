package com.example.pocketguide;

import android.annotation.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.speech.tts.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.*;

import com.google.android.gms.tasks.*;
import com.google.mlkit.common.model.*;
import com.google.mlkit.nl.translate.*;
import com.google.zxing.integration.android.*;
import com.journeyapps.barcodescanner.*;
import java.util.*;

// implements onClickListener for the onclick behaviour of button
public class QR_scanner extends AppCompatActivity implements View.OnClickListener {
    Button scanBtn;
    TextView messageText;
    Button playBtn,pauseBtn,translate,feedback,translateMar;
    TextToSpeech textToSpeech;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        // referencing and initializing
        // the button and textviews
        scanBtn = findViewById(R.id.scanBtn);
        messageText = findViewById(R.id.textContent);
        playBtn=findViewById(R.id.playBtn);
        pauseBtn=findViewById(R.id.pauseBtn);
        translate=findViewById(R.id.button_translate_hindi);
        translateMar=findViewById(R.id.button_translate_marathi);
        feedback=findViewById(R.id.button_feedback);

        // adding listener to the button
        scanBtn.setOnClickListener(this);
//        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int i) {
//                if(i!=TextToSpeech.ERROR) {
//                    textToSpeech.setLanguage(Locale.ENGLISH);
//                }
////
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(CaptureActivityPotrait.class);
        intentIntegrator.initiateScan();
    }

    public static class CaptureActivityPotrait extends CaptureActivity{

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message

                messageText.setText(intentResult.getContents());
                messageText.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
                translate.setVisibility(View.VISIBLE);
                translateMar.setVisibility(View.VISIBLE);
                feedback.setVisibility(View.VISIBLE);

                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
//                        textToSpeech.setLanguage(Locale.forLanguageTag(String.valueOf(new Locale("hin", "IN"))));\
                        //Language="hindi";
//                        textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                        textToSpeech.setLanguage(Locale.ENGLISH);
                    }
                });


                translate.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint({"SdCardPath", "UnsafeDynamicallyLoadedCode"})
                    @Override
                    public void onClick(View v) {
                        textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                        TranslatorOptions options = new TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.ENGLISH).setTargetLanguage(TranslateLanguage.HINDI).build();
                        final Translator englishHindi = Translation.getClient(options);

                        DownloadConditions conditions = new DownloadConditions.Builder()
                                .requireWifi()
                                .build();
                        englishHindi.downloadModelIfNeeded(conditions)
                                .addOnSuccessListener(
                                        new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void v) {
                                                // Model downloaded successfully. Okay to start translating.
                                                // (Set a flag, unhide the translation UI, etc.)
                                            }
                                        })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Model couldn’t be downloaded or other internal error.
                                                // ...
                                            }
                                        });
                        englishHindi.translate(messageText.getText().toString()).addOnSuccessListener(
                                        new OnSuccessListener<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                messageText.setText(s);
                                            }
                                        })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e(null, "Error");
                                            }
                                        });

                    }
                });

                translateMar.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint({"SdCardPath", "UnsafeDynamicallyLoadedCode"})
                    @Override
                    public void onClick(View v) {
                        textToSpeech.setLanguage(Locale.forLanguageTag(String.valueOf(new Locale("mr", "IN"))));
                        TranslatorOptions options = new TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.HINDI).setTargetLanguage(TranslateLanguage.MARATHI).build();
                        final Translator englishMarathi = Translation.getClient(options);

                        DownloadConditions conditions = new DownloadConditions.Builder()
                                .requireWifi()
                                .build();
                        englishMarathi.downloadModelIfNeeded(conditions)
                                .addOnSuccessListener(
                                        new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void v) {
                                                // Model downloaded successfully. Okay to start translating.
                                                // (Set a flag, unhide the translation UI, etc.)
                                            }
                                        })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Model couldn’t be downloaded or other internal error.
                                                // ...
                                            }
                                        });
                        englishMarathi.translate(messageText.getText().toString()).addOnSuccessListener(
                                        new OnSuccessListener<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                messageText.setText(s);
                                            }
                                        })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e(null, "Error");
                                            }
                                        });

                    }
                });

//                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//                    @Override
//                    public void onInit(int i) {
//                        Language="marathi";
//                        textToSpeech.setLanguage(Locale.forLanguageTag("mr"));
//                    }
//                });

                playBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String toSpeak=messageText.getText().toString();
                        //Toast.makeText(getApplicationContext(),toSpeak,Toast.LENGTH_SHORT).show();
                        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
                pauseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textToSpeech.stop();
                    }
                });

                feedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Intent.ACTION_SENDTO);
                        String UriText="mailto:"+ Uri.encode("simmilahrani@gmail.com")+"?subject="+
                                Uri.encode("Feedback")+"$body="+Uri.encode("");
                        Uri uri=Uri.parse(UriText);
                        intent.setData(uri);
                        startActivity(Intent.createChooser(intent,"send email"));


                    }
                });

            }
            }
        else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
