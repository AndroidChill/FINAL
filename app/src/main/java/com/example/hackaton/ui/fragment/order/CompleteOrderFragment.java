package com.example.hackaton.ui.fragment.order;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hackaton.OnClick;
import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentCompleteOrderBinding;
import com.example.hackaton.ui.activity.MainActivity;
import com.example.hackaton.ui.adapter.CompleteOrderAdapter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;


public class CompleteOrderFragment extends Fragment implements OnClick {

    private FragmentCompleteOrderBinding binding;

    private static final String TAG = "PdfCreatorActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    public static CompleteOrderFragment newInstance() {

        Bundle args = new Bundle();

        CompleteOrderFragment fragment = new CompleteOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_complete_order, container, false);

        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(new CompleteOrderAdapter(this::onClick));

        return binding.getRoot();
    }

    @Override
    public void onClick() throws DocumentException {
        createMyPDF(null);
    }

    public void createMyPDF(View view){

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300,600,1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Paint myPaint = new Paint();
        String myString = "Suchka";
        int x = 10, y=25;

        myPage.getCanvas().drawText("Заголовок", x, y, myPaint);

        int y1 = y;
        y1+=myPaint.descent() - 2 * myPaint.ascent();
        myPage.getCanvas().drawText("Приоритет - Срочно", x, y1, myPaint);

        y1+=myPaint.descent() - 3 * myPaint.ascent();
        myPage.getCanvas().drawText("Исполните -ль/-ли :", x, y1, myPaint);

        y1+=myPaint.descent() - 2 * myPaint.ascent();
        myPage.getCanvas().drawCircle(x + 20, y1, 5, myPaint);
        myPage.getCanvas().drawText("Василий Петров", x + 30, y1 + 5, myPaint);

        y1+=myPaint.descent() - 2 * myPaint.ascent();
        myPage.getCanvas().drawCircle(x + 20, y1, 5, myPaint);
        myPage.getCanvas().drawText("Иннокентий Васильев", x + 30, y1 + 5, myPaint);

        y1+=myPaint.descent() - 4 * myPaint.ascent();
        myPage.getCanvas().drawText("Необходимый срок изготовления : 1 неделя", x, y1, myPaint);
        y1+=myPaint.descent() - 2 * myPaint.ascent();
        myPage.getCanvas().drawText("Реальный срок изготовления : 2 недели", x, y1, myPaint);


        myPaint.setAntiAlias(false);
        y1+=myPaint.descent() - 3 * myPaint.ascent();
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode("Full Info", BarcodeFormat.QR_CODE, 256, 256);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x2 = 0; x2 < width; x2++) {
                for (int y2 = 0; y2 < height; y2++) {
                    bmp.setPixel(x2, y2, bitMatrix.get(x2, y2) ? Color.BLACK : Color.WHITE);
                }
            }
            myPage.getCanvas().drawBitmap(bmp, x,y1,myPaint);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        x+=myPaint.descent() -  21 * myPaint.ascent();
        myPage.getCanvas().drawText("Автор :", x, y, myPaint);
        x-=myPaint.descent() - 8 * myPaint.ascent();
        y+=myPaint.descent() - 2 * myPaint.ascent();
        myPage.getCanvas().drawText("Евгений Александрович", x, y, myPaint);
//
//        for (String line:myString.split("\n")){
//            myPage.getCanvas().drawText(line, x, y, myPaint);
//            y+=myPaint.descent()-myPaint.ascent();
//        }

        myPdfDocument.finishPage(myPage);

        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + UUID.randomUUID().toString() + ".pdf";
        File myFile = new File(myFilePath);
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        myPdfDocument.close();
    }


}
