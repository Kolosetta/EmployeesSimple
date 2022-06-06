package com.example.employeessimple.screens.employees;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeessimple.api.ApiFactory;
import com.example.employeessimple.api.ApiService;
import com.example.employeessimple.data.AppDatabase;
import com.example.employeessimple.pojo.Employee;
import com.example.employeessimple.pojo.Response;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends AndroidViewModel {

    private static AppDatabase db;
    private LiveData<List<Employee>> employees;
    Disposable disposable;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        employees = db.employeeDao().getAllEmployees();
    }

    private void insertEmployees(List<Employee> employees){
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

    private void deleteAllEmployees(){
        new DeleteAllEmployeesTask().execute();
    }

    private static class DeleteAllEmployeesTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            db.employeeDao().getAllEmployees();
            return null;
        }
    }

    public void loadData(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io()) //Указываем поток загрузки данных
                .observeOn(AndroidSchedulers.mainThread()) //Указываем прием данных в главном потоке
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception { // Метод срабатывает при успешном приеме данных
                        deleteAllEmployees();
                        insertEmployees(response.getEmployees());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    @Override
    protected void onCleared() { //Вызывается в конце ЖЦ ViewModel
        super.onCleared();
        if(disposable != null){
            disposable.dispose();
        }
    }
}
