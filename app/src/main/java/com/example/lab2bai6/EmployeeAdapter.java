package com.example.lab2bai6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context context;
    private ArrayList<Employee> employees;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);

        // Set thông tin cho TextViews
        holder.tvName.setText(employee.getFullName());
        if (employee.isManager()) {
            holder.tvRole.setVisibility(View.GONE);
            holder.imgManager.setVisibility(View.VISIBLE);
        } else {
            holder.tvRole.setVisibility(View.VISIBLE);
            holder.imgManager.setVisibility(View.GONE);
        }

        // Đổi màu nền cho các dòng chẵn và lẻ
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.light_blue));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvRole;
        ImageView imgManager;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEmployeeName);
            tvRole = itemView.findViewById(R.id.tvRole);
            imgManager = itemView.findViewById(R.id.imgManager);
        }
    }
}

