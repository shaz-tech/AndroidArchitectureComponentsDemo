package shaz.architectureroomdemo.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Random;

import shaz.architectureroomdemo.persistence.AppDatabase;
import shaz.architectureroomdemo.persistence.entities.Employee;

/**
 * Created by ${Shahbaz} on 07-11-2017
 */

public class EmployeeViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<Employee>> allEmployees;
    private LiveData<Employee> employeeOfTheDay;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        allEmployees = appDatabase.employeeDao().getAllEmployees();
        employeeOfTheDay = Transformations.map(allEmployees, new Function<List<Employee>, Employee>() {
            @Override
            public Employee apply(List<Employee> input) {
                //TODO business logic for best employee
                if (input != null && input.size() > 1)
                    return input.get(new Random().nextInt(input.size() - 1));
                return null;
            }
        });
    }

    public LiveData<List<Employee>> getEmployees() {
        return allEmployees;
    }

    public LiveData<Employee> getEmployeeOfTheDay() {
        return employeeOfTheDay;
    }

    public List<Employee> getAllEmployees() {
        return allEmployees.getValue();
    }

    public void insertEmployee(Employee... employees) {
        new InsertEmployee(appDatabase).execute(employees);
    }

    public void deleteEmployee(Employee employees) {
        new DeleteEmployee(appDatabase).execute(employees);
    }

    private class InsertEmployee extends AsyncTask<Employee, Void, Void> {

        private AppDatabase database;

        public InsertEmployee(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            database.employeeDao().insertEmployees(employees);
            return null;
        }
    }

    private class DeleteEmployee extends AsyncTask<Employee, Void, Void> {

        private AppDatabase database;

        public DeleteEmployee(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            database.employeeDao().deleteEmployees(employees);
            return null;
        }
    }
}
