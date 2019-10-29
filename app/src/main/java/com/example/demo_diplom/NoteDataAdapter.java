package com.example.demo_diplom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

public class NoteDataAdapter  extends BaseAdapter {

    private List<Note> notes;
    private LayoutInflater inflater;
    private NoteRepository baseNotes = App.getBaseNotes();
    Context conxt;
    private DateFormat dateFormat = App.getDateFormat();


    public NoteDataAdapter(Context conxt, List<Note> notes) {
        this.notes = notes;
        this.conxt = conxt;
        inflater = (LayoutInflater) conxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void removeNote(int position) {
        notes.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Note getNote(int position) {
        return (Note) getItem(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.activity_list_view, parent, false);
        }

        Note note = getNote(position);

        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        TextView deadline = view.findViewById(R.id.dateDeadline);

        title.setText(note.getTitle());
        subtitle.setText(note.getSubtitle());
        if (note.getDeadline() != null) {
            deadline.setVisibility(View.VISIBLE);
            deadline.setText(dateFormat.format(note.getDeadline()));
        } else {
            deadline.setVisibility(View.GONE);
        }
        visibilityView(title, subtitle, deadline, note);

        return view;
    }

    private void visibilityView(TextView title, TextView subtitle, TextView deadline, Note note) {

        if (!title.getText().toString().equals("")) {
            title.setVisibility(View.VISIBLE);
            title.setText(note.getTitle());
        } else {
            title.setVisibility(View.GONE);
        }

        if (!subtitle.getText().toString().equals("")) {
            subtitle.setVisibility(View.VISIBLE);
            subtitle.setText(note.getSubtitle());
        } else {
            subtitle.setVisibility(View.GONE);
        }
    }
}
