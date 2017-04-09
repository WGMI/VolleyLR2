package com.example.ilabafrica.volleylr;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText et_username,et_password;
    Button login;
    TextView register;
    String username,password;
    String ipString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(MainActivity.this,Profile.class);
                                intent.putExtra("name",name);
                                intent.putExtra("username",username);
                                intent.putExtra("age",age);
                                startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login Failed. \nPlease recheck your login details.")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username,password,listener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

        login.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Dialog ipDialog = new Dialog(MainActivity.this);
                ipDialog.setContentView(R.layout.ipdialog);
                final EditText ip = (EditText) ipDialog.findViewById(R.id.ip);
                Button set = (Button) ipDialog.findViewById(R.id.set);
                ipDialog.show();
                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ipString = ip.getText().toString();
                        ipDialog.cancel();
                    }
                });
                return false;
            }
        });
    }
}
