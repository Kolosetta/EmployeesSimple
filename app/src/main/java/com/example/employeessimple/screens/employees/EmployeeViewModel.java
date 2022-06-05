package com.example.employeessimple.screens.employees;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeessimple.data.AppDatabase;
import com.example.employeessimple.pojo.Employee;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private static AppDatabase db;
    private LiveData<List<Employee>> employees;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        employees = db.employeeDao().getAllEmployees();
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    @SuppressWarnings("unchecked")
    public void insertEmployees(List<Employee> employees){
        new InsertEmployeesTask().execute(employees);
    }

    private static class InsertEmployeesTask extends AsyncTask<List<Employee>, Void, Void>{
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Employee>... lists) {
            if(lists != null && lists.length > 0){
                db.employeeDao().insertEmployees(lists[0]);
            }
            return null;
        }
    }
}
