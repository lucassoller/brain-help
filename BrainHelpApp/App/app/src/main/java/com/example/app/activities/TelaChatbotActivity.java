package com.example.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.R;
import com.example.app.utils.BitmapUtils;
import com.example.app.utils.ImagePickerUtils;

import java.io.File;
import java.io.IOException;

public class TelaChatbotActivity extends AppCompatActivity {

    private ImageButton ibAddImgReceita;
    private ImageButton foto2;
    private Bitmap foto;
    private Button button;
    private String f;
    String captured_image;
    final int RES_IMAGE_CAPTURE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_chatbot);
        ibAddImgReceita = findViewById(R.id.foto);
        button = findViewById(R.id.button);
        foto2 = findViewById(R.id.foto2);

        ibAddImgReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = ImagePickerUtils.getPickImageIntent(TelaChatbotActivity.this);
                startActivityForResult(i, ImagePickerUtils.PICK_FOTO_FROM_AVATAR);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(foto == null){
                    foto = BitmapFactory.decodeResource(getResources(), R.drawable.logo_brain);
                }
                f = BitmapUtils.bitmapToBase64(foto);
                Bitmap b = BitmapUtils.base64ToBitmap(f);
                foto2.setImageBitmap(b);
                Toast.makeText(TelaChatbotActivity.this, f, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePickerUtils.PICK_FOTO_FROM_AVATAR && resultCode == RESULT_OK) {
            try {
                File f = ImagePickerUtils.parseReturningDataToFile(data, this);
                Bitmap bmp = BitmapUtils.getCompressor(this).compressToBitmap(f);
                foto = bmp;
                ibAddImgReceita.setImageBitmap(bmp);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
