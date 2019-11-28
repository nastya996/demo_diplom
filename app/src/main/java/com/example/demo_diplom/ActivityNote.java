package com.example.demo_diplom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityNote extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvMessage;
    private EditText inputTitle;
    private EditText inputSubtitle;
    private EditText inputDeadlineDate;
    private LinearLayout llDeadlineBox;
    private CheckBox chbDeadLine;

    private NotesRepository noteRepository;
    private int noteid;
    private Note currentNote;
    private SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        noteRepository = App.getInstance().getNotesRepository();
        initView();
        //получение данных из intent
        Intent intent = getIntent();
        noteid = intent.getIntExtra("id", -1);
        if (noteid >= 0) {
            initNoteValue();
        }

    }

    //заполнение полей данными существующей записи
    private void initNoteValue() {
        currentNote = noteRepository.getNoteById(noteid);
        inputTitle.setText(currentNote.getNoteTitle());
        inputSubtitle.setText(currentNote.getNoteDescription());
        Date dateDeadline = currentNote.getDateDeadline();
        if (dateDeadline != null) {
            chbDeadLine.setChecked(true);
            llDeadlineBox.setVisibility(View.VISIBLE);
            inputDeadlineDate.setText(formatter.format(dateDeadline));
        }
    }

    //сохраняем запись
    public void onSaveNoteClick() {
        String nameStr = inputTitle.getText().toString();
        String descStr = inputSubtitle.getText().toString();
        String deadlineDateStr = inputDeadlineDate.getText().toString();
        Date date = Calendar.getInstance().getTime();

        Date dateDeadline = null;
        int isDeadline = 0;
        try {
            dateDeadline = formatter.parse(deadlineDateStr);
            isDeadline = 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //проверка на пустые данные
        if (nameStr.equals("") && descStr.equals("") && deadlineDateStr.equals("")) {
            //все данные пустые, показывем сообщение об ошибке
            showErrorMessage(R.string.msg_note_error);
        } else {
            //проверяем данные получены из intent
            if (noteid >= 0) {
                // обновляем существующие данные
                currentNote.setNoteTitle(nameStr);
                currentNote.setNoteDescription(descStr);
                currentNote.setDatePub(date);
                currentNote.setDateDeadline(dateDeadline);
                currentNote.setIsDeadLine(isDeadline);
                noteRepository.updateNote(currentNote);
            } else {
                // добавляем новые данные
                Note note = new Note(nameStr, date, dateDeadline, isDeadline, descStr);
                noteRepository.addNote(note);
            }
            finish();
        }
    }

    //показ диалогового окна с выбором даты
    public void onAddDateDeadlineClick(View view) throws ParseException {
        showCalendarDialog();
    }

    private void showCalendarDialog() throws ParseException {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_datepicker, null);
        dialogBuilder.setView(dialogView);

        final DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
        Calendar currentDeteOfDeadline = Calendar.getInstance();
        //проверяем, если текущее значение даты не пустое, присваиваем календарю существующую дату
        if (!inputDeadlineDate.getText().toString().equals("")) {
            Date currentDete = formatter.parse(inputDeadlineDate.getText().toString());
            currentDeteOfDeadline.setTime(currentDete);
        }
        datePicker.init(
                currentDeteOfDeadline.get(Calendar.YEAR),
                currentDeteOfDeadline.get(Calendar.MONTH),
                currentDeteOfDeadline.get(Calendar.DATE),
                null);
        //обработка нажатий диалогового окна
        dialogBuilder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                inputDeadlineDate.setText(new StringBuilder()
                        .append(datePicker.getYear()).append("-")
                        .append(datePicker.getMonth() + 1).append("-")
                        .append(datePicker.getDayOfMonth())
                );
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        AlertDialog calendarDialog = dialogBuilder.create();
        calendarDialog.show();
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputTitle = findViewById(R.id.input_title);
        inputSubtitle = findViewById(R.id.input_subtitle);
        inputDeadlineDate = findViewById(R.id.deadlinedate);
        llDeadlineBox = findViewById(R.id.ll_deadline_box);
        chbDeadLine = findViewById(R.id.chb_deadline);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityNote.this, MainActivity.class);
                startActivity(intent);
            }
        });
        chbDeadLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    Date date = Calendar.getInstance().getTime();
                    inputDeadlineDate.setText(formatter.format(date));
                    llDeadlineBox.setVisibility(View.VISIBLE);
                } else {
                    llDeadlineBox.setVisibility(View.GONE);
                    inputDeadlineDate.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                onSaveNoteClick();
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("plain/text");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "your body");
                startActivity(Intent.createChooser(sharingIntent, "share using"));
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    private void showErrorMessage(final int message) {
        tvMessage = findViewById(R.id.tv_message);
        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText(getResources().getText(message));
            }

            public void onFinish() {
                tvMessage.setVisibility(View.GONE);
            }
        };
        timer.start();
    }
}
