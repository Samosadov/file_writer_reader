package ru.synergy.filewriterreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveText(View view) {
        FileOutputStream fos = null;
        try {
            EditText textBox = (EditText) findViewById(R.id.editor);
            String text = textBox.getText().toString();

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "File saved successfuly",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
        finally {
            try {
                if (fos != null) fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}