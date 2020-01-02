package com.example.notesapp.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.notesapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialog bottomSheetDialog;

    ImageView img;
    int imgR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        findViewById(R.id.title).requestFocus();
        createBoteSheeteDialog();

        img = findViewById(R.id.image_view);
        imgR = R.drawable.book1;

    }

    private void createBoteSheeteDialog() {
        if (bottomSheetDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.color, null);
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
            view.findViewById(R.id.imgc1).setOnClickListener(this);
            view.findViewById(R.id.imgc2).setOnClickListener(this);
            view.findViewById(R.id.imgc3).setOnClickListener(this);
            view.findViewById(R.id.imgc4).setOnClickListener(this);
            view.findViewById(R.id.imgc5).setOnClickListener(this);
            view.findViewById(R.id.imgc6).setOnClickListener(this);
            view.findViewById(R.id.imgc7).setOnClickListener(this);
            view.findViewById(R.id.imgc8).setOnClickListener(this);
            view.findViewById(R.id.imgc9).setOnClickListener(this);
            view.findViewById(R.id.imgc10).setOnClickListener(this);
            view.findViewById(R.id.imgc11).setOnClickListener(this);
            view.findViewById(R.id.imgc12).setOnClickListener(this);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgc1:
                img.setImageResource(R.drawable.book1);
                imgR = R.drawable.book1;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc2:
                img.setImageResource(R.drawable.book6);
                imgR = R.drawable.book6;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc3:
                img.setImageResource(R.drawable.book3);
                imgR = R.drawable.book3;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc4:
                img.setImageResource(R.drawable.book5);
                imgR = R.drawable.book5;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc5:
                img.setImageResource(R.drawable.book29);
                imgR = R.drawable.book29;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc6:
                img.setImageResource(R.drawable.book7);
                imgR = R.drawable.book7;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc7:
                img.setImageResource(R.drawable.book22);
                imgR = R.drawable.book22;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc8:
                img.setImageResource(R.drawable.book11);
                imgR = R.drawable.book11;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc9:
                img.setImageResource(R.drawable.book30);
                int imgR = R.drawable.book30;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc10:
                img.setImageResource(R.drawable.book8);
                imgR = R.drawable.book8;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc11:
                img.setImageResource(R.drawable.book23);
                imgR = R.drawable.book23;
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc12:
                img.setImageResource(R.drawable.book25);
                imgR = R.drawable.book25;
                bottomSheetDialog.dismiss();
                break;


        }
    }

    public void show(View view) {
        bottomSheetDialog.show();
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        this.finish();
    }

    public void save(View view) {
        EditText title = findViewById(R.id.title);
        String titleTxt = title.getText().toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String id = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Book").push().getKey();
        NoteBook book = new NoteBook(id, imgR, titleTxt);
        FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Book").child(String.valueOf(id)).setValue(book);

        this.finish();

    }
}
