package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    String loginTxt;
    String pTxt;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username","").equals("")) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
        else  {
            setContentView(R.layout.activity_main);
        }


    }
    public void onLoginSelected(View v) {
        EditText Loginname = findViewById(R.id.editText);
        EditText password = findViewById(R.id.editText2);
        loginTxt = Loginname.getText().toString();
        pTxt = password.getText().toString();
        if(loginTxt.length() > 0 && password.length() > 0) {
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra(EXTRA_MESSAGE,loginTxt);
            intent.putExtra("loginTxt",loginTxt);
            intent.putExtra("pTxt",pTxt);
            sharedPreferences.edit().putString("username",loginTxt).apply();
            startActivity(intent);

        }

    }
    public void Login(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

    }
}
