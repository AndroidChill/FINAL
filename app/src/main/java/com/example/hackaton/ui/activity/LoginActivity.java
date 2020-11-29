package com.example.hackaton.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.hackaton.R;
import com.example.hackaton.databinding.ActivityLoginBinding;

import java.util.UUID;

import ru.yandex.speechkit.Error;
import ru.yandex.speechkit.Language;
import ru.yandex.speechkit.OnlineModel;
import ru.yandex.speechkit.OnlineRecognizer;
import ru.yandex.speechkit.Recognition;
import ru.yandex.speechkit.Recognizer;
import ru.yandex.speechkit.RecognizerListener;
import ru.yandex.speechkit.SpeechKit;
import ru.yandex.speechkit.Track;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import com.chaquo.python.*;
import com.example.hackaton.*;

public class LoginActivity extends AppCompatActivity implements RecognizerListener {
    private static final String API_KEY_FOR_TESTS_ONLY = "069b6659-984b-4c5f-880e-aaedcfd84102";

    private static final int REQUEST_PERMISSION_CODE = 31;
    private Recognizer recognizer;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        index.Hel();
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));

        }
        Python py = Python.getInstance();
        PyObject pym = py.getModule("hello");

        Log.d("tag", "onCreate: " + pym);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        try {
            SpeechKit.getInstance().init(this, API_KEY_FOR_TESTS_ONLY);
            SpeechKit.getInstance().setUuid(UUID.randomUUID().toString());
        } catch (SpeechKit.LibraryInitializationException ignored) {
            //do not ignore in a prod version!
        }

        recognizer = new OnlineRecognizer.Builder(Language.RUSSIAN, OnlineModel.QUERIES,
                LoginActivity.this).build();

        startRecognizer();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != REQUEST_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PERMISSION_GRANTED) {
            startRecognizer();
        } else {
            updateStatus("Record audio permission was not granted");
        }
    }

    @Override
    public void onRecordingBegin(@NonNull Recognizer recognizer) {
        updateStatus("Recording begin");
        updateProgress(0);
        updateResult("");
    }

    @Override
    public void onSpeechDetected(@NonNull Recognizer recognizer) {
        updateStatus("Speech detected");
    }

    @Override
    public void onSpeechEnds(@NonNull Recognizer recognizer) {
        updateStatus("Speech ends");
    }

    @Override
    public void onRecordingDone(@NonNull Recognizer recognizer) {
        updateStatus("Recording done");
    }

    @Override
    public void onPowerUpdated(@NonNull Recognizer recognizer, float power) {
//        updateProgress((int) (power * progressBar.getMax()));
    }

    @Override
    public void onPartialResults(@NonNull Recognizer recognizer, @NonNull Recognition recognition, boolean eOfU) {
        updateStatus("Partial results " + recognition.getBestResultText() + " endOfUtterrance = " + eOfU);

        if (eOfU) {
            updateResult(recognition.getBestResultText());
        }
    }

    @Override
    public void onRecognitionDone(@NonNull Recognizer recognizer) {
        updateStatus("Recognition done");
        updateProgress(0);
    }

    @Override
    public void onRecognizerError(@NonNull Recognizer recognizer, @NonNull Error error) {
        updateStatus("Error occurred " + error);
        updateProgress(0);
        updateResult("");
    }

    @Override
    public void onMusicResults(@NonNull Recognizer recognizer, @NonNull Track track) {
    }


    private void startRecognizer() {
        if (ContextCompat.checkSelfPermission(LoginActivity.this, RECORD_AUDIO) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO}, REQUEST_PERMISSION_CODE);
        } else {
            recognizer.startRecording();
        }
    }

    private void updateResult(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//        recognitionResult.setText(text);
    }

    private void updateStatus(final String text) {
//        currentStatus.setText(text);
    }

    private void updateProgress(int progress) {
//        progressBar.setProgress(progress);
    }
}