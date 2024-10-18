package com.example.lab2bai6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Employee> employees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        EditText etId = findViewById(R.id.etId);
        EditText etFullName = findViewById(R.id.etFullName);
        CheckBox cbIsManager = findViewById(R.id.cbIsManager);
        Button btnAdd = findViewById(R.id.btnAdd);
        RecyclerView rvEmployees = findViewById(R.id.rvEmployees);

        employees = new ArrayList<>();
        adapter = new EmployeeAdapter(this, employees);
        rvEmployees.setAdapter(adapter);

        // Set layout cho RecyclerView
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));

        // Xử lý sự kiện nhấn nút ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin nhập vào
                String id = etId.getText().toString();
                String fullName = etFullName.getText().toString();
                boolean isManager = cbIsManager.isChecked();

                // Tạo đối tượng Employee mới và thêm vào danh sách
                Employee employee = new Employee(id, fullName, isManager);
                employees.add(employee);

                // Cập nhật RecyclerView
                adapter.notifyDataSetChanged();

                // Xóa nội dung các ô nhập sau khi thêm
                etId.setText("");
                etFullName.setText("");
                cbIsManager.setChecked(false);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}