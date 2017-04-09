package com.example.ilabafrica.volleylr;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    EditText et_name,et_username,et_age,et_password;
    Button register;
    String name,username,password;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = (EditText) findViewById(R.id.et_name);
        et_username = (EditText) findViewById(R.id.et_username);
        et_age = (EditText) findViewById(R.id.et_age);
        et_password = (EditText) findViewById(R.id.et_password);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_name.getText().toString();
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                age = Integer.parseInt(et_age.getText().toString());

                Toast.makeText(Register.this,"qwerty",Toast.LENGTH_SHORT).show();

                Response.Listener<String> listener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(Register.this,MainActivity.class);
                                startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Registration Failed.")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name,username,age,password,listener);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
            }
        });
    }
}
