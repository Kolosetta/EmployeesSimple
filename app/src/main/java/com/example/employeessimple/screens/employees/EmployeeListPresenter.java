package com.example.employeessimple.screens.employees;

import com.example.employeessimple.api.ApiFactory;
import com.example.employeessimple.api.ApiService;
import com.example.employeessimple.pojo.Response;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {
    private Disposable disposable;
    private final EmployeesListView viewInterface;

    public EmployeeListPresenter(EmployeesListView viewInterface) {
        this.viewInterface = viewInterface;
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
                        viewInterface.showData(response.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        viewInterface.showErrorMessage();
                    }
                });
    }

    public void dispose(){
        if(disposable != null){
            disposable.dispose(); //Уничтожаем процесс, если он незавершен
        }
    }
}
