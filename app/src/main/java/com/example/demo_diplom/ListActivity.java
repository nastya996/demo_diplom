package com.example.demo_diplom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private NoteDataAdapter adapter;
    private ListView listView;

    private List<Note> notes = new ArrayList<>();
    private NoteRepository baseNotes;
    private String idNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        initView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.settings:
                Intent intentSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        listView = findViewById(R.id.listView);

        baseNotes = App.getBaseNotes();
        notes = baseNotes.getNotes();

        adapter = new NoteDataAdapter(this, notes);
        listView.setAdapter(adapter);

        deleteNote();
        changeNote();

        adapter.notifyDataSetChanged();
    }

    public void deleteNote() {
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title).setIcon(R.drawable.ic_delete_black_24dp);

            builder.setNegativeButton(R.string.cancel, (dialog, which) ->
                    dialog.cancel());

            builder.setPositiveButton(R.string.delete, (dialog, which) -> {
                        Note note = (Note) adapter.getItem(position);
                        idNote = note.getIdNote();
                        baseNotes.deleteNote(note.getIdNote());
                        adapter.removeNote(position);
                    }

            );

            AlertDialog alert = builder.create();
            alert.show();

            return true;
        });
    }

    public void changeNote() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Note note = (Note) adapter.getItem(position);
            Intent intent = new Intent(ListActivity.this, NoteActivity.class);
            intent.putExtra("idNote", note.getIdNote());
            startActivity(intent);
        });
    }

    public void createNote(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
}
