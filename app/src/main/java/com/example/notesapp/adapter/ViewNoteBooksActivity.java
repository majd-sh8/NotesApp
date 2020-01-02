package com.example.notesapp.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.notesapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewNoteBooksActivity extends AppCompatActivity implements NoteBookItemClickListener {

    List<NoteBook> books;
    List<NoteBook> bkSearch;
    NoteBookAdapter bookAdapter;
    RecyclerView bookRV;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note_books);

        books=new ArrayList<>();
        bkSearch=new ArrayList<>();
        bookRV=findViewById(R.id.booksRe);

        search=findViewById(R.id.searsh);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchBook(s);
                return false;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String path = "User/"+user.getUid()+"/Book";
        DatabaseReference ref = database.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                books.clear();
                for (DataSnapshot bookSnapshot:dataSnapshot.getChildren()){
                    String id=bookSnapshot.child("id").getValue().toString();
                    int img=bookSnapshot.child("img").getValue(Integer.class);
                    String title=bookSnapshot.child("title").getValue(String.class);
                    NoteBook book=new NoteBook(id,img,title);
                    books.add(book);
                }
                setMyAdapter(books);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onNoteBookClick(NoteBook book) {
        Intent intent = new Intent(this, ViewNoteActivity.class);
        intent.putExtra("id", book.getId());
        startActivity(intent);
    }

    public void setMyAdapter(List<NoteBook> bks){
        bookAdapter = new NoteBookAdapter(ViewNoteBooksActivity.this, bks, ViewNoteBooksActivity.this);
        bookRV.setAdapter(bookAdapter);
        bookRV.setLayoutManager(new GridLayoutManager(ViewNoteBooksActivity.this, 3));
    }

    public void searchBook(String s){
        bkSearch.clear();
        for(NoteBook book:books){
            if (book.getTitle().contains(s)){
                bkSearch.add(book);
            }
        }
        setMyAdapter(bkSearch);
    }
}
