package shaz.architectureroomdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import shaz.architectureroomdemo.persistence.entities.Employee;
import shaz.architectureroomdemo.viewmodel.EmployeeViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView1, mTextView2;
    private EmployeeViewModel mEmployeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        mEmployeeViewModel.getEmployees().observe(MainActivity.this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                showEmployees(employees);
            }
        });
        mEmployeeViewModel.getEmployeeOfTheDay().observe(MainActivity.this, new Observer<Employee>() {
            @Override
            public void onChanged(@Nullable Employee employee) {
                showEmployeeOfTheDay(employee);
            }
        });
    }

    public void addEmployee(View view) {
        Random random = new Random();
        int num = random.nextInt(100);
        Employee employee = new Employee("Shahbaz", "Akhtar", num);
        mEmployeeViewModel.insertEmployee(employee);
    }

    public void deleteEmployee(View view) {
        List<Employee> employees = mEmployeeViewModel.getAllEmployees();
        if (employees != null && employees.size() > 0) {
            mEmployeeViewModel.deleteEmployee(employees.get(0));
        }
    }

    private void showEmployees(List<Employee> employees) {
        if (employees == null)
            return;
        if (mTextView1 == null)
            mTextView1 = findViewById(R.id.textView1);
        String tempData = "";
        for (Employee employee : employees) {
            tempData += employee.getEmployeeId() + ", " + employee.getFirstName() + ", " + employee.getLastName() + ", " + employee.getAge() + "\n\n";
        }
        mTextView1.setText(tempData);
    }

    private void showEmployeeOfTheDay(Employee employee) {
        if (mTextView2 == null)
            mTextView2 = findViewById(R.id.textView2);
        if (employee == null) {
            mTextView2.setText(null);
            return;
        }
        mTextView2.setText("__Employee of the day__\n" + employee.getEmployeeId() + ", " + employee.getFirstName() + ", " + employee.getLastName() + ", " + employee.getAge());
    }
}
