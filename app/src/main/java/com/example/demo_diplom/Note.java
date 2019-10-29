package com.example.demo_diplom;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {

    @PrimaryKey
    @Nullable
    private String idNote;

    private String title;
    private String subtitle;
    private Date deadline;

    public Note() {

    }

    public Note(String title, String subtitle, Date deadline) {
        this.title = title;
        this.subtitle = subtitle;
        this.deadline = deadline;
        this.idNote = String.valueOf(Objects.hash(this.title, this.subtitle, this.deadline));
    }

    public Note (String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.idNote = String.valueOf(Objects.hash(this.title, this.subtitle));
    }

    public String getIdNote() {
        return idNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(title, note.title) &&
                Objects.equals(subtitle, note.subtitle) &&
                Objects.equals(deadline, note.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, subtitle, deadline);
    }*/
}
