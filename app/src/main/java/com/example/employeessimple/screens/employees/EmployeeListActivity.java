package com.example.employeessimple.screens.employees;

import static com.google.gson.reflect.TypeToken.get;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import com.example.employeessimple.R;
import com.example.employeessimple.adapter.EmployeesAdapter;
import com.example.employeessimple.pojo.Employee;

import java.util.ArrayList;
import java.util.List;


public class EmployeeListActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private EmployeesAdapter employeesAdapter;
    private EmployeeViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(EmployeeViewModel.class);
        viewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
       // viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(EmployeeViewModel.class);

         viewModel.getEmployees().observe(this, new Observer<List<Employee>>(){
            @Override
            public void onChanged(List<Employee> employees) { //Вызывается при изменении данных в lifecycle
                employeesAdapter.setEmployeesList(employees);
            }
        });
        viewModel.loadData();

        employeesAdapter = new EmployeesAdapter();
        employeesAdapter.setEmployeesList(new ArrayList<>());
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(employeesAdapter);


    }
}