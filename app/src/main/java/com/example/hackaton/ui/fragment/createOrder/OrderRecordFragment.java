package com.example.hackaton.ui.fragment.createOrder;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentOrderRecordBinding;
import com.example.hackaton.ui.activity.CheckTaskActivity;
import com.example.hackaton.ui.activity.CreateOrderActivity;
import com.example.hackaton.ui.activity.MainActivity;
import com.icaksama.rapidsphinx.RapidCompletionListener;
import com.icaksama.rapidsphinx.RapidPreparationListener;
import com.icaksama.rapidsphinx.RapidSphinx;
import com.icaksama.rapidsphinx.RapidSphinxListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import edu.cmu.pocketsphinx.Config;
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
import static android.app.Activity.RESULT_OK;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class OrderRecordFragment extends Fragment  {

    private FragmentOrderRecordBinding binding;

    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;

    public static OrderRecordFragment newInstance() {

        Bundle args = new Bundle();

        OrderRecordFragment fragment = new OrderRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_record, container, false);

        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                binding.recordFilename.setText("Я вас слушаю");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                binding.recordBtn.setImageResource(R.drawable.record_btn_stopped);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                binding.recordFilename.setText(data.get(0));
                binding.btn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                algorithm(binding.recordFilename.getText().toString());

            }
        });

        binding.recordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                    binding.recordTimer.stop();
                    binding.recordTimer.setBase(SystemClock.elapsedRealtime());
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    binding.recordTimer.start();
                    binding.recordBtn.setImageResource(R.drawable.record_btn_recording);
                    binding.recordTimer.setBase(SystemClock.elapsedRealtime());
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(getContext(),"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

    //обработка голоса и разбиение по строкам
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void algorithm(String answer){

        List<Example> list = new ArrayList<>();

        String name = "имя титл наименование название заголовок хедер хедэр";
        int i = answer.indexOf(name);

        String prioritet = "приоритет срочность";
        int j = answer.indexOf(prioritet);

        String ispolnitel = "исполнитель работник";
        int k =answer.indexOf(ispolnitel);

        String text = "текст описание задание выполнить сделать";
        int m = answer.indexOf(text);

        if(i != -1){
            list.add(new Example("i", i));
        }
        if(j != -1){
            list.add(new Example("j", j));
        }
        if(k != -1){
            list.add(new Example("k", k));
        }
        if(m != -1){
            list.add(new Example("m", m));
        }

        list.sort(Comparator.comparing(Example::getValue));

        for(int q = 0; q < list.size() - 1; q++){
            String z = answer.substring(list.get(q).value, list.get(q+1).value);

            if(list.get(q).key.equals("i")){
                name = z;
                continue;
            } else {
                name = null;
            }

            if(list.get(q).key.equals("j")){
                prioritet = z;
                continue;
            } else {
                prioritet = null;
            }

            if(list.get(q).key.equals("k")){
                ispolnitel = z;
                continue;
            } else {
                ispolnitel = null;
            }

            if(list.get(q).key.equals("m")){
                text = z;
            } else {
                text = null;
            }

        }

        Intent intent = new Intent(getContext(), CheckTaskActivity.class);
        Bundle args = new Bundle();

        if(name != null){
            args.putString("name", name);
        }
        if(prioritet != null){
            args.putString("prioritet", prioritet);
        }
        if(ispolnitel != null){
            args.putString("ispolnitel", name);
        }
        if(text != null){
            args.putString("text", name);
        }

    }

    class Example{
        String key;
        Integer value;

        public Example(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

}
