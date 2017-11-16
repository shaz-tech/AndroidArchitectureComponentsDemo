package shaz.architectureroomdemo.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import shaz.architectureroomdemo.persistence.dao.EmployeeDao;
import shaz.architectureroomdemo.persistence.entities.Employee;

/**
 * Created by ${Shahbaz} on 07-11-2017
 */

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "ArchRoomDemo_db";
    private static AppDatabase mInstance = null;

    public static AppDatabase getDatabase(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
        }
        return mInstance;
    }

    public abstract EmployeeDao employeeDao();
}
