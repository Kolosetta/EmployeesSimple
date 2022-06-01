package com.example.employeessimple.screens.employees;

import com.example.employeessimple.pojo.Employee;

import java.util.List;

public interface EmployeesListView {
    void showData(List<Employee> list);
    void showErrorMessage();
}
