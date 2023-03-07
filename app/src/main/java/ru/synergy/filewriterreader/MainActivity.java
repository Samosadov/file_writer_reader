package ru.synergy.filewriterreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
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

    public File getExternalFilePath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }

    public void saveText(View view) {
        FileOutputStream fos = null;
        try {
            EditText textBox = (EditText) findViewById(R.id.editor);
            String text = textBox.getText().toString();

//            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos = new FileOutputStream(getExternalFilePath());
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

    public void openText(View view) {
        FileInputStream fin = null;
        File file = getExternalFilePath();
        try {
            TextView textView = findViewById(R.id.text);
//            fin = openFileInput(FILE_NAME);
            fin = new FileInputStream(file);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
        finally {
            try {
                if (fin != null) fin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}