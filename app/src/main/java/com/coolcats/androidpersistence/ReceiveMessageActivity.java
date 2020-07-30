package com.coolcats.androidpersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    private Button send;
    private EditText messageEditText;
    private TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        send = findViewById(R.id.button);
        messageEditText = findViewById(R.id.editTextTextPersonName);
        displayTextView = findViewById(R.id.message_textview);

        String messge = getIntent().getStringExtra("_MESSAGE_");

        if (messge != null) {
            displayTextView.setText(messge);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = messageEditText.getText().toString().trim();
                Intent result = new Intent();
                result.putExtra("_MESSAGE_", "A2: "+msg);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }
}