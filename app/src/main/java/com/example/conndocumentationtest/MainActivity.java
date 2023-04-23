package com.example.conndocumentationtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.conndocumentationtest.database.DbHelper;
import com.example.conndocumentationtest.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String idTechnicien, nameTechnicien, surnameTechnicien, test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DbHelper dbHelper = DbHelper.getInstance(this);

        binding.addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTechnicien = binding.IdTechnicien.getText().toString().trim();
                nameTechnicien = binding.nameTechnicien.getText().toString().trim();
                surnameTechnicien = binding.surnameTechnicien.getText().toString().trim();
                if(idTechnicien.length()<=0) {
                    binding.IdTechnicien.setError("salaud");
                    binding.IdTechnicien.requestFocus();
                } else if (nameTechnicien.length()<=0) {
                    binding.nameTechnicien.setError("fdp");
                    binding.nameTechnicien.requestFocus();
                }
                else if(surnameTechnicien.length()<=0) {
                    binding.surnameTechnicien.setError("connard");
                    binding.surnameTechnicien.requestFocus();
                } else {
                    long req = dbHelper.insertData(idTechnicien, nameTechnicien, surnameTechnicien);
                    Toast.makeText(MainActivity.this, "Result: "+req, Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.readData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> stringList = dbHelper.readData();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stringList);
                binding.ListView.setAdapter(arrayAdapter);
            }
        });
    }
}