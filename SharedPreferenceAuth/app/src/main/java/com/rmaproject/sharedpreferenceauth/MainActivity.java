package com.rmaproject.sharedpreferenceauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkRemember;
    private Button btnLogin;
    private EditText userName, passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.checkSavedCredentials();

        this.initComponents();
    }

    private void checkSavedCredentials() {
        SharedPreferences handler;
        handler = this.getPreferences(Context.MODE_PRIVATE);

        String Username = handler.getString("username", "");
        String Password = handler.getString("password", "");

        boolean loginCorrect;
        loginCorrect = this.checkCredentials(Username, Password);
        if (loginCorrect)
            this.openHome(Username);
    }

    private void openHome(String username) {
        Intent i = new Intent(this.getApplicationContext(), HomeActivity.class);

        i.putExtra("USERNAME", username);
        this.startActivity(i);
    }

    private boolean checkCredentials(String username, String password) {
        if (username.equals("mamang@gmail.com") && password.equals("1234"))
            return true;
        else
            return false;

    }

    private void initComponents() {
        this.chkRemember = findViewById(R.id.chk_remember);
        this.btnLogin = findViewById(R.id.btn_login);
        this.userName = findViewById(R.id.txt_username);
        this.passWord = findViewById(R.id.txt_password);
    }

    public void onClick(View view) {
        this.login();
    }

    private void login() {
        String username;
        String password;
        username = this.userName.getText().toString();
        password = this.passWord.getText().toString();

        boolean loginCorrect;
        loginCorrect = this.checkCredentials(username, password);
        if (loginCorrect){
            boolean remember = this.chkRemember.isChecked();
            if (remember){
                this.saveCredentials();
            }
            this.openHome(username);
        } else {
            Toast.makeText(this, "Invalid username/password", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveCredentials() {
        SharedPreferences handler = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();

        editor.putString("username", this.userName.getText().toString());
        editor.putString("password", this.passWord.getText().toString());

        editor.commit();
    }
}