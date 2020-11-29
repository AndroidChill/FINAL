package com.example.hackaton.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ActivityChatBinding;
import com.example.hackaton.model.Message;
import com.example.hackaton.ui.adapter.ChatAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;

    private ChatAdapter adapter;
    private DatabaseReference myRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private boolean isRecording = false;

    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;

    private MediaRecorder mediaRecorder;
    private String recordFile;

    private Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        binding.messagesView.setLayoutManager(new LinearLayoutManager(this));

        timer = binding.time;

        adapter = new ChatAdapter();
        binding.messagesView.setAdapter(adapter);
        myRef = database.getReference("Chat").child("Messages");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Message message3 = postSnapshot.getValue(Message.class);
//                    Toast.makeText(getContext(), , Toast.LENGTH_SHORT).show();
                    adapter.addItem(message3);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("tag", "Failed to read value.", error.toException());
            }
        });

        sendMessage();

    }

    public void sendMessage() {

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String message = binding.editText.getText().toString();
                if (message.length() > 0) {

//                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                    binding.send.setVisibility(View.VISIBLE);
                    binding.sendVoice.setVisibility(View.GONE);
                } else {
                    binding.sendVoice.setVisibility(View.VISIBLE);
                    binding.send.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message(UUID.randomUUID().toString(), "Nikita", binding.editText.getText().toString());
                myRef.child(message.getId()).setValue(message);
                binding.editText.getText().clear();
            }
        });

        binding.sendVoice.setOnClickListener(v -> {
            binding.voice.setVisibility(View.VISIBLE);
            binding.editText.setVisibility(View.GONE);
            binding.send.setVisibility(View.GONE);
            binding.sendVoice.setVisibility(View.GONE);
        });

        binding.jumpText.setOnClickListener(v -> {
            binding.voice.setVisibility(View.GONE);
            binding.editText.setVisibility(View.VISIBLE);
            binding.send.setVisibility(View.GONE);
            binding.sendVoice.setVisibility(View.VISIBLE);
        });

        onClick();

    }

    public void onClick() {
        /*  Check, which button is pressed and do the task accordingly
         */
        binding.record.setOnClickListener(v -> {
            if(isRecording) {
                //Stop Recording
                stopRecording();

                // Change button image and set Recording state to false
                binding.record.setImageDrawable(getResources().getDrawable(R.drawable.record_btn_stopped, null));
                isRecording = false;
            } else {
                //Check permission to record audio
                if(checkPermissions()) {
                    //Start Recording
                    startRecording();

                    // Change button image and set Recording state to false
                    binding.record.setImageDrawable(getResources().getDrawable(R.drawable.record_btn_recording, null));
                    isRecording = true;
                }
            }
        });

    }

    private void stopRecording() {
        //Stop Timer, very obvious
        timer.stop();
        timer.setBase(SystemClock.elapsedRealtime());

        //Change text on page to file saved

        //Stop media recorder and set it to null for further use to record new audio
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void startRecording() {
        //Start timer from 0
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        //Get app external directory path
        String recordPath = getExternalFilesDir("/").getAbsolutePath();

        //Get current date and time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date now = new Date();

        //initialize filename variable with date and time at the end to ensure the new file wont overwrite previous file
        recordFile = "Recording_" + formatter.format(now) + ".3gp";

        //Setup Media Recorder for recording
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start Recording
        mediaRecorder.start();
    }

    private boolean checkPermissions() {
        //Check permission
        if (ActivityCompat.checkSelfPermission(this, recordPermission) == PackageManager.PERMISSION_GRANTED) {
            //Permission Granted
            return true;
        } else {
            //Permission not granted, ask for permission
            ActivityCompat.requestPermissions(this, new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(isRecording){
            stopRecording();
        }
    }
}