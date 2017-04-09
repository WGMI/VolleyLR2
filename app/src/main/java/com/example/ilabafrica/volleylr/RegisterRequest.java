package com.example.ilabafrica.volleylr;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iLabAfrica on 4/8/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String URL = "http://172.16.40.70/Register2.php";
    private Map<String,String> params;

    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);
        params.put("age",age+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
