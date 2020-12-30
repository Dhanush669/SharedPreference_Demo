package com.example.sharedpreferencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText password;
    Button button;
    SharedPreferences sharpref;
    TextView textView;
    public static final String sharpref_Key="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=findViewById(R.id.password);
        button=findViewById(R.id.passbtn);
        textView=findViewById(R.id.textview);
        sharpref=this.getPreferences(MODE_PRIVATE);
        if(sharpref.getString(sharpref_Key,null)==null){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor=sharpref.edit();
                    editor.putString(sharpref_Key,password.getText().toString());
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Password saved", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                    startActivity(intent);
                }
            });

        }
        else {
            button.setText("Go");
            textView.setText("Enter your password ");
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(sharpref.getString(sharpref_Key, null).equals(password.getText().toString())){
                       Toast.makeText(MainActivity.this, "Logging in to second activity", Toast.LENGTH_SHORT).show();
                       Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                       startActivity(intent);
                   }
                   else{
                       Toast.makeText(MainActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                   }
               }
           });

        }
    }
}