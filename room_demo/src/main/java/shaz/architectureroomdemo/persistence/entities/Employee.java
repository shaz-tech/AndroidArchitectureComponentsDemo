package shaz.architectureroomdemo.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import shaz.architectureroomdemo.Constants;

/**
 * Created by ${Shahbaz} on 07-11-2017
 */

@Entity(tableName = Constants.ENTITY_TBL_EMPLOYEE)
public class Employee {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER, collate = ColumnInfo.BINARY)
    private int employeeId;

    @ColumnInfo(index = true, typeAffinity = ColumnInfo.TEXT, collate = ColumnInfo.NOCASE)
    private String firstName;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT, collate = ColumnInfo.NOCASE)
    private String lastName;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER, collate = ColumnInfo.NOCASE)
    private int age;

    public Employee(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
