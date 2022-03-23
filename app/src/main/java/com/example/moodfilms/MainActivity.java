package com.example.moodfilms;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText title, realisateur, release, soundtrack, mood, trailer, synopsis, image;
    Button btnInsert, btnUpdate, btnDelete, btnView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("MainActivity", "Mood Films");
        title = findViewById(R.id,title);
        realisateur = findViewById(R.id,realisateur);
        release = findViewById(R.id,release);
        soundtrack = findViewById(R.id,soundtrack);
        mood = findViewById(R.id,mood);
        trailer = findViewById(R.id,trailer);
        synopsis = findViewById(R.id,synopsis);
        image = findViewById(R.id,image);
        btnInsert = findViewById(R.id,btnInsert);
        btnUpdate = findViewById(R.id,btnUpdate);
        btnDelete = findViewById(R.id,btnDelete);
        btnView = findViewById(R.id,btnView);
        db = new DBHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTXT = title.getText().toString();
                String realisateurTXT = realisateur.getText().toString();
                String releaseTXT = release.getText().toString();
                String soundtrackTXT = soundtrack.getText().toString();
                String moodTXT = mood.getText().toString();
                String trailerTXT = trailer.getText().toString();
                String synopsisTXT = synopsis.getText().toString();
                String imageTXT = image.getText().toString();

                Boolean checkinsertdata = db.insertfilmdata(titleTXT, realisateurTXT, releaseTXT, soundtrackTXT, moodTXT, trailerTXT, synopsisTXT, imageTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTXT = title.getText().toString();
                String realisateurTXT = realisateur.getText().toString();
                String releaseTXT = release.getText().toString();
                String soundtrackTXT = soundtrack.getText().toString();
                String moodTXT = mood.getText().toString();
                String trailerTXT = trailer.getText().toString();
                String synopsisTXT = synopsis.getText().toString();
                String imageTXT = image.getText().toString();

                Boolean checkupdatedata = db.updatefilmdata(titleTXT, realisateurTXT, releaseTXT, soundtrackTXT, moodTXT, trailerTXT, synopsisTXT, imageTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTXT = title.getText().toString();
                Boolean checkdeletedata = db.deletefilmdata(titleTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                if (res.getCount() == 0)
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            }

            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append("Titre :" + getString(0) + "\n");
                buffer.append("Réalisateur :" + getString(1) + "\n");
                buffer.append("Date de sortie :" + getString(2) + "\n");
                buffer.append("Bande originale :" + getString(3) + "\n");
                buffer.append("Humeur(s) :" + getString(4) + "\n");
                buffer.append("Trailer :" + getString(5) + "\n");
                buffer.append("Synopsis :" + getString(6) + "\n");
                buffer.append("Image :" + getString(7) + "\n");
            }

//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setCancelable(true);
//            builder.setTitle("Film Entries");
//            builder.setMessage(buffer.toString());
//            builder.show();
        });
    }
}