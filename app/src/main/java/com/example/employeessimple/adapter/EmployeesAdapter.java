package com.example.employeessimple.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeessimple.R;
import com.example.employeessimple.pojo.Employee;
import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder> {

    private List<Employee> employees;

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.secondName.setText(employee.getlName());
        holder.firstName.setText(employee.getfName());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    static class EmployeesViewHolder extends RecyclerView.ViewHolder{
        private TextView firstName;
        private TextView secondName;

        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstName);
            secondName = itemView.findViewById(R.id.secondName);
        }
    }

    public void setEmployeesList(List<Employee> employees){
        this.employees = employees;
        notifyDataSetChanged();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
