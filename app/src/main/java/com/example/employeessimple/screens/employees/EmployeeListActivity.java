package com.example.employeessimple.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.employeessimple.R;
import com.example.employeessimple.adapter.EmployeesAdapter;
import com.example.employeessimple.api.ApiFactory;
import com.example.employeessimple.api.ApiService;
import com.example.employeessimple.pojo.Employee;
import com.example.employeessimple.pojo.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private EmployeesAdapter employeesAdapter;
    private EmployeeViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewModel = ViewModelProvider(this).get(EmployeeViewModel.class);
        employeesAdapter = new EmployeesAdapter();
        employeesAdapter.setEmployeesList(new ArrayList<>());
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(employeesAdapter);


    }

    public void showData(List<Employee> list){
        employeesAdapter.setEmployeesList(list);
    }

    public void showErrorMessage(){
        Toast.makeText(this, "Ошибка получения данных", Toast.LENGTH_SHORT).show();
    }

}