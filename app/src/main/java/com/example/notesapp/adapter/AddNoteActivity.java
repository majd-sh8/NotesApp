package com.example.notesapp.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notesapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialog bottomSheetDialog;
    ImageView b1;
    ImageView b2;
    ImageView b3;
    ImageView b4;
    ImageView b5;
    ImageView b6;
    ImageView b7;
    ImageView b8;
    ImageView b9;
    ImageView b10;
    ImageView b11;
    ImageView b12;
    ConstraintLayout layout;
    int color;
    EditText title, txt;
    TextView date;
    String bookId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        createBoteSheeteDialog();

        layout=findViewById(R.id.noteLayout);


        date=findViewById(R.id.date);
        title=findViewById(R.id.Title);
        txt=findViewById(R.id.Txt);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour12hrs = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);

        String d=day+"/"+month+"/"+year+"     "+hour12hrs+":"+minutes;
        date.setText(d);

        color=getResources().getColor(R.color.color1);


        bookId=getIntent().getStringExtra("id");

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

    public void showDialog(View view) {
        bottomSheetDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgc1:
                color=getResources().getColor(R.color.color1);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc2:
                color=getResources().getColor(R.color.color2);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc3:
                color=getResources().getColor(R.color.color3);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc4:
                color=getResources().getColor(R.color.color4);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc5:
                color=getResources().getColor(R.color.color5);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc6:
                color=getResources().getColor(R.color.color6);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc7:
                color=getResources().getColor(R.color.color7);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc8:
                color=getResources().getColor(R.color.color8);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc9:
                color=getResources().getColor(R.color.color9);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc10:
                color=getResources().getColor(R.color.color10);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc11:
                color=getResources().getColor(R.color.color11);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.imgc12:
                color=getResources().getColor(R.color.color12);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;


        }
    }

    public void saveNote(View view) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String id = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Book").child(bookId).child("Note").push().getKey();
        Note note=new Note(id,color,date.getText().toString(),title.getText().toString(),txt.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Book").child(bookId).child("Note").child(String.valueOf(id)).setValue(note);


        this.finish();
    }
}
