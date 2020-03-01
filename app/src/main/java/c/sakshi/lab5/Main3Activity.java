package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    int noteid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText textbox = findViewById(R.id.editText4);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid",-1);

        if(noteid != -1) {
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            textbox.setText(noteContent);
        }
    }
    public void saveMethod(View v) {
        EditText textbox = findViewById(R.id.editText4);
        String content = textbox.getText().toString();
        Context context = getApplicationContext();
        String title;
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        String username = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE).getString("username","");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        if(noteid == -1) {
            title = "NOTE_"+(Main2Activity.notes.size()+1);
            dbHelper.saveNotes(username,title,content,date);
        }
        else {
            title = "NOTE_"+(noteid+1);
            dbHelper.updateNote(title,date,content,username);
        }
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);

    }

}
