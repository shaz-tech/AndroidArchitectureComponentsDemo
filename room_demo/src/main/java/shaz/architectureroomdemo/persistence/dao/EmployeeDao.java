package shaz.architectureroomdemo.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import shaz.architectureroomdemo.Constants;
import shaz.architectureroomdemo.persistence.entities.Employee;

/**
 * Created by ${Shahbaz} on 07-11-2017
 */

@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployees(Employee... employees);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployees(List<Employee> employees);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEmployees(Employee... employees);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEmployees(List<Employee> employees);

    @Query("select * from " + Constants.ENTITY_TBL_EMPLOYEE + " order by employeeId desc")
    LiveData<List<Employee>> getAllEmployees();

    @Query("select * from " + Constants.ENTITY_TBL_EMPLOYEE + " where employeeId = :employeeId")
    Employee getEmployee(int employeeId);

    @Delete
    void deleteEmployees(Employee... employee);

    @Delete
    void deleteEmployees(List<Employee> employees);

}
