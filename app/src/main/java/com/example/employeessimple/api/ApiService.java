package com.example.employeessimple.api;

import com.example.employeessimple.pojo.Response;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<Response> getEmployees();
}
