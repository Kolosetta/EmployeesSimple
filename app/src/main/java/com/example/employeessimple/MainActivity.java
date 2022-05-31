package com.example.employeessimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.employeessimple.adapter.EmployeesAdapter;
import com.example.employeessimple.api.ApiFactory;
import com.example.employeessimple.api.ApiService;
import com.example.employeessimple.pojo.Employee;
import com.example.employeessimple.pojo.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EmployeesAdapter employeesAdapter;
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeesAdapter = new EmployeesAdapter();
        employeesAdapter.setEmployeesList(new ArrayList<>());
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(employeesAdapter);

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io()) //Указываем поток загрузки данных
                .observeOn(AndroidSchedulers.mainThread()) //Указываем прием данных в главном потоке
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception { // Метод срабатывает при успешном приеме данных
                        employeesAdapter.setEmployeesList(response.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG ).show();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null) {
            disposable.dispose(); //Уничтожаем процесс, если он незавершен
        }
    }
}