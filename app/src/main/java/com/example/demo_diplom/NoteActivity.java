package com.example.demo_diplom;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NoteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText dataDeadline;
    private Bundle bundle;
    private EditText textNote, titleNote;
    private NoteRepository myNoteRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initViews();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        ++month;
        dataDeadline.setText((dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "." + (month < 10 ? "0" + month : month) + "." + year);
    }

    public void initViews() {

        myNoteRepository = App.getNoteRepository();
        myNoteRepository = new IsNoteRepository(this, MainActivity.FILE_NAME);

        myNoteRepository = App.getNoteRepository();

        titleNote = findViewById(R.id.input_title);
        textNote = findViewById(R.id.input_subtitle);
        dataDeadline = findViewById(R.id.input_deadline);


        {
            bundle = getIntent().getExtras();
            if (bundle == null) {
                return;
            }

            if (bundle.getInt(MainActivity.POSITION_LISTVIEW) != -1) {

                Note note = myNoteRepository.getNoteById(Integer.toString(bundle.getInt(MainActivity.POSITION_LISTVIEW)));

                if (note.getTitleNote().length() != 0) {
                    titleNote.setText(note.getTitleNote());
                }
                if (note.getTextNote().length() != 0) {
                    textNote.setText(note.getTextNote());
                }

                if (note.getDateDeadline().length() != 0) {

                    dataDeadline.setEnabled(true);
                    dataDeadline.setText(note.getDateDeadline());
                }
            }
        }
    }


    CheckBox checkBox = findViewById(R.id.deadlineCheckbox);

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                dataDeadline.setEnabled(true);
                dataDeadline.requestFocus();
            } else {
                dataDeadline.setEnabled(false);
                dataDeadline.setText("");
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.notes, menu);
        return true;
    }
}



