package cnam.smb116.tp2_composantlogin;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureView();
    }

    private void configureView(){
        this.userName = findViewById(R.id.txtUname);
        this.password = findViewById(R.id.txtPwd);
    }

    public void onClickLogin(View view){
        Intent intent = new Intent();
        intent.putExtra("feedback", "User Name: "+userName.getText().toString()+" Password: "+password.getText().toString());
        setResult(1, intent);
        finish();
    }

    public void onClickCancel(View view){
        Intent intent = new Intent();
        intent.putExtra("feedback", "Annulation");
        setResult(-1, intent);
        finish();
    }
}