package com.example.demo_diplom;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

public interface SettingsDao {
    @Insert
    void addSettings(Settings settings);

    @Update
    void updateSettings(Settings settings);

    @Delete
    void deleteSettings(Settings settings);

    @Query("SELECT * FROM Settings")
    Settings getSettings();
}
