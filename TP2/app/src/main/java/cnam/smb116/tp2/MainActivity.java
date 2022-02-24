package cnam.smb116.tp2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText tp2Text;
    private ImageButton callBtn;
    private ImageButton loginBtn;
    private ImageButton internetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
    }

    private void initializeView(){
        this.tp2Text = findViewById(R.id.tp2Text);
        this.callBtn = findViewById(R.id.callBtn);
        this.loginBtn = findViewById(R.id.loginBtn);
        this.internetBtn = findViewById(R.id.internetBtn);
    }

    public void onClickCallBtn(View view){
        String url = "tel:"+tp2Text.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
        startActivity(intent);
    }

    public void onClickInternetBtn(View view){
        String url = "http://www."+tp2Text.getText().toString()+".com";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void onClickLogin(View view){
        Intent intent = new Intent();


        if (ContextCompat.checkSelfPermission(this, "com.example.permission.CALL_LOGIN") == PackageManager.PERMISSION_GRANTED) {
            //Permission already granted!
            String pkg = "cnam.smb116.tp2_composantlogin";
            String clazz = pkg + ".MainActivity";
            intent.setComponent(new ComponentName(pkg,clazz));

            startActivityForResult(intent, 1);

//        }if (ActivityCompat.shouldShowRequestPermissionRationale(this, "com.example.permission.CALL_LOGIN")) {
//            //Display a screen saying why you need PERMISSION_X
//            Toast.makeText(getApplicationContext(),"The permission is needed to launch the next activity!",Toast.LENGTH_LONG).show();

        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{"com.example.permission.CALL_LOGIN"}, 2);
        }

        //implicite
//        intent.setAction("login.ACTION");

        //explicite
//        String pkg = "com.example.tp2login";
//        String clazz = pkg + ".MainActivity";
//        intent.setComponent(new ComponentName(pkg,clazz));
//
//        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null){
            Toast.makeText(getApplicationContext(), data.getStringExtra("reponse"),Toast.LENGTH_LONG).show();
        }
        if (requestCode == -1 && data != null){
            Toast.makeText(getApplicationContext(), data.getStringExtra("reponse"),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 2:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    Intent intent = new Intent();
                    String pkg = "cnam.smb116.tp2_composantlogin";
                    String clazz = pkg + ".MainActivity";
                    intent.setComponent(new ComponentName(pkg,clazz));

                    startActivityForResult(intent, 1);
                }  else {
                    Toast.makeText(getApplicationContext(),"The permission is needed to launch the next activity!",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }
}