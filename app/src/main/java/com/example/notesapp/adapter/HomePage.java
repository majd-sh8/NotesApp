package com.example.notesapp.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notesapp.R;
import com.example.notesapp.SignIn_Up.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity  implements NoteBookItemClickListener,NoteItemClickListener {

    List<NoteBook> books;
    List<Note> notes;
    NoteBookAdapter bookAdapter;
    NoteAdapter noteAdapter;
    RecyclerView bookRV;
    RecyclerView noteRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        books = new ArrayList<>();
        books.add(new NoteBook("add", R.drawable.addbook, "Create Notebook"));
        bookRV = findViewById(R.id.bookRV);

        notes = new ArrayList<>();
        noteRV = findViewById(R.id.notesRV);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String path = "User/" + user.getUid() + "/Book";
        DatabaseReference ref = database.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                books.clear();
                notes.clear();
                books.add(new NoteBook("add", R.drawable.addbook, "Create Notebook"));

                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    String id = bookSnapshot.child("id").getValue().toString();
                    for (DataSnapshot noteSnapshot : bookSnapshot.child("Note").getChildren()) {
                        String noteId = noteSnapshot.child("id").getValue().toString();
                        int mark = noteSnapshot.child("mark").getValue(Integer.class);
                        String date = noteSnapshot.child("date").getValue(String.class);
                        String title = noteSnapshot.child("title").getValue(String.class);
                        String txt = noteSnapshot.child("txt").getValue(String.class);

                        Note note = new Note(noteId, mark, date, title, txt);
                        notes.add(note);
                    }

                    int img = bookSnapshot.child("img").getValue(Integer.class);
                    String title = bookSnapshot.child("title").getValue(String.class);
                    NoteBook book = new NoteBook(id, img, title);
                    books.add(book);
                }
                bookAdapter = new NoteBookAdapter(HomePage.this, books, HomePage.this);
                bookRV.setAdapter(bookAdapter);
                bookRV.setLayoutManager(new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false));

                noteAdapter = new NoteAdapter(HomePage.this, notes, HomePage.this);
                noteRV.setAdapter(noteAdapter);
                noteRV.setLayoutManager(new LinearLayoutManager(HomePage.this, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void showAll(View view) {
        Intent intent = new Intent(this, ViewNoteBooksActivity.class);
        startActivity(intent);
    }

    public void showNotes(View view) {
        Intent intent = new Intent(this, ShowAllNotesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(this, ViewMyNoteActivity.class);
        intent.putExtra("id", note.id);
        startActivity(intent);
    }

    public void SignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, SplashActivity.class));
    }

    @Override
    public void onNoteBookClick(NoteBook book) {
        if (book.getId().equals("add")) {
            Intent intent = new Intent(this,AddBookActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ViewNoteActivity.class);
            intent.putExtra("id", book.getId());
            startActivity(intent);
        }
    }
}
