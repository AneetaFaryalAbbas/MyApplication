package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends Activity {

    EditText t1,t2,t31,t32,t33,t4,t5,t6,t7;
    TextView txt1,txt2;
    Button b1;
    SQLiteDatabase db;
    AlertDialog alertDialog;
    ProgressDialog pd;
    String url=MainActivity.UrlAddress+"signUp.php";
    StringRequest stringRequest;
    RequestQueue queue;
    public static String cnicno="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        pd= new ProgressDialog(this );
        pd.setMessage("");
        pd.setTitle("Creating Account");
        pd.setProgressStyle(pd.STYLE_SPINNER);

        alertDialog= new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Message");


        queue= Volley.newRequestQueue(this);
        t1=(EditText) findViewById(R.id.Ac_username);
        t2=(EditText) findViewById(R.id.Ac_password);
        t31=(EditText) findViewById(R.id.Ac_CnicNo1);
        t32=(EditText) findViewById(R.id.Ac_CnicNo2);
        t33=(EditText) findViewById(R.id.Ac_CnicNo3);



        t4=(EditText) findViewById(R.id.Ac_license);
        t5=(EditText) findViewById(R.id.Ac_contactNo);
        t6=(EditText) findViewById(R.id.Ac_address);
        t7=(EditText) findViewById(R.id.Ac_fullname);
    }



    private void signUp(){

        String s1= t1.getText().toString();
        String s2= t2.getText().toString();
        String s31= t31.getText().toString();
        String s32= t32.getText().toString();
        String s33= t33.getText().toString();
         cnicno= cnicno+""+s31+"-"+s32+"-"+s33;






        String s4= t4.getText().toString();
        String s5= t5.getText().toString();
        String s6= t6.getText().toString();
        String s7= t7.getText().toString();

        if(s1.equals("")||s2.equals("")||cnicno.equals("") ||s4.equals("") || s5.equals("")|| s6.equals("")|| s7.equals("")){

            Toast.makeText(getApplicationContext(),"Please Fill All the Field First",Toast.LENGTH_SHORT).show();
        }
        else
        {
            pd.show();
            stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    pd.dismiss();
                    pd.cancel();
                    if(response==null){
                        alertDialog.setMessage("No Response From the server __  !");
                        alertDialog.show();
                    }
                    else {

                            clear();
                            alertDialog.setMessage("Has Been Craeted Successfully __  !");
                            alertDialog.show();


                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError){
                    pd.dismiss();
                    pd.cancel();
                    String message = null;
                    if (volleyError instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (volleyError instanceof ServerError) {
                        message = "The server could not be found. Please try again after some time!!";
                    } else if (volleyError instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (volleyError instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                    } else if (volleyError instanceof NoConnectionError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (volleyError instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                    }
                    alertDialog.setMessage("" + message);
                    alertDialog.show();
                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("s1", t1.getText().toString());
                    params.put("s2", t2.getText().toString());
                    params.put("s3", cnicno);
                    params.put("s4", t4.getText().toString());
                    params.put("s5", t5.getText().toString());
                    params.put("s6", t6.getText().toString());
                    params.put("s7", t7.getText().toString());

                    return params;
                }
            };
            queue.add(stringRequest);
        }
    }

    public void signup_page(View view) {
        signUp();
    }


    private void clear(){
        t1.setText("");
        t2.setText("");
        t31.setText("");
        t32.setText("");
        t33.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        cnicno="";
    }
}
