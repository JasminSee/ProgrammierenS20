package de.haw_hamburg.dailymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        CalendarView calendarView = findViewById(R.id.calendarViewMain);

        final String[] date = new String[1];

        //OnDateChangeListener für calendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date[0] = Integer.toString(year)+"."+Integer.toString(month)+"."+Integer.toString(dayOfMonth);
                Log.i("dateTag",date[0]);
            }
        });

        //FloatButtonListener
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Want to add a new entry",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
                startActivity(intent);
            }
        });

        Object obj = ReadService.readObject(MainActivity.this);

        if (obj != null) {
            if (!(obj instanceof Event)) {
                obj = (Event) obj;
            }
            Log.i("checkEvent", obj.toString());
        }
    }
}
