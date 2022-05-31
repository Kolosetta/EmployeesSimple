package com.example.employeessimple.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder> {



    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class EmployeesViewHolder extends RecyclerView.ViewHolder{

        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
