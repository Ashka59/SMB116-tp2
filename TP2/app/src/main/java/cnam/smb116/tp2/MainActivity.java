package cnam.smb116.tp2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureView();
    }

    private void configureView(){
        this.inputText = findViewById(R.id.inputText);
    }

    public void onClickCallBtn(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+inputText.getText().toString()));
        startActivity(intent);
    }

    public void onClickInternetBtn(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www."+inputText.getText().toString()+".com"));
        startActivity(intent);
    }

    public void onClickLogin(View view){
        Intent intent = new Intent();

//        //Intent explicite
//        String pkg = "cnam.smb116.tp2_composantlogin";
//        String clazz = pkg + ".MainActivity";
//        intent.setComponent(new ComponentName(pkg,clazz));
//
//        //Intent implicite
//        intent.setAction("login.ACTION");
//        startActivityForResult(intent, 1);

        if (ContextCompat.checkSelfPermission(this, "com.example.permission.CALL_LOGIN") == PackageManager.PERMISSION_GRANTED) {
            intent.setComponent(new ComponentName("cnam.smb116.tp2_composantlogin", "cnam.smb116.tp2_composantlogin.MainActivity"));
            startActivityForResult(intent, 1);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{"com.example.permission.CALL_LOGIN"}, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null){
            Toast.makeText(getApplicationContext(), data.getStringExtra("feedback"),Toast.LENGTH_SHORT).show();
        }
        if (requestCode == -1 && data != null){
            Toast.makeText(getApplicationContext(), data.getStringExtra("feedback"),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 2) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("cnam.smb116.tp2_composantlogin", "cnam.smb116.tp2_composantlogin.MainActivity"));
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(getApplicationContext(), "The permission is needed to launch the next activity!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}