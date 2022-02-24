package cnam.smb116.tp2_composantlogin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button cancel;
    private Button login;

    private int RESULT_OK = 1;
    private int RESULT_CANCELED = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureView();
    }

    private void configureView(){
        this.userName = findViewById(R.id.userNameTxt);
        this.password = findViewById(R.id.passwordTxt);
        this.cancel = findViewById(R.id.cancelBtn);
        this.login = findViewById(R.id.loginBtn);
    }

    public void onClickLogin(View view){
        Intent intent = new Intent();
        intent.putExtra("reponse", "user: "+userName.getText().toString()+" password: "+password.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickCancel(View view){
        Intent intent = new Intent();
        intent.putExtra("reponse", "canceled opération");
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}