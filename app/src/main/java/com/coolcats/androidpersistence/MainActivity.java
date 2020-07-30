package com.coolcats.androidpersistence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG_X";

    private String messages = "no messages...";

    private Button send;
    private EditText messageEditText;
    private TextView displayTextView;

    @BindView(R.id.button2)
    Button openCameraButton;

    @BindView(R.id.imageView)
    ImageView cameraImageView;

    private SharedPreferences sharedPreferences;

    public static final int REQUEST_CODE = 707;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final Intent secondActivityIntent = new Intent(this, ReceiveMessageActivity.class);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        send = findViewById(R.id.button);
        messageEditText = findViewById(R.id.editTextTextPersonName);
        displayTextView = findViewById(R.id.message_textview);
        send.setOnClickListener(
                view -> {
                    String msg = messageEditText.getText().toString().trim();
                    messages += "\n" + msg;
                    messageEditText.setText("");

                    secondActivityIntent.putExtra("_MESSAGE_", "A1: " + msg);
                    startActivityForResult(secondActivityIntent, REQUEST_CODE);
                }
        );
        openCameraButton.setOnClickListener(view ->{
            startActivityForResult(cameraIntent, 111);
        });

    }

    private void readSharedPref() {
        String spMessage = sharedPreferences.getString("MY_MESSAGE", messages);
        messages = spMessage;
        displayTextView.setText(spMessage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: 1");
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

            Log.d(TAG, "onActivityResult: 2");
            String ms = data.getStringExtra("_MESSAGE_");

            Log.d(TAG, "onActivityResult: " + ms);
            if (ms != null)
                displayTextView.setText(ms);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
}