package com.example.huynh.weatherapp;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;

import com.example.huynh.weatherapp.AddNewCityFragment.AddNewCityFragment;
import com.example.huynh.weatherapp.WeatherCity.WeatherCity;

public class MainActivity extends AppCompatActivity {
    public static MainActivity instance = null;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance  = this;
        //Transparent Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        // Toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_home);

        //Register Observe
        MyCityList.getInstance().register(WeatherCity.getInstance());

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).add(R.id.content_frame, WeatherCity.getInstance()).commit();
        MyCityList.getInstance().AddNewCity("London");
        MyCityList.getInstance().AddNewCity("Danang");
        MyCityList.getInstance().AddNewCity("Hanoi");
        MyCityList.getInstance().AddNewCity("Thanh pho ho chi minh");
//        MyCityList.getInstance().AddNewCity("Con Son");

        //Floating Action Button
//        FloatingActionButton addButton = findViewById(R.id.fab);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.content_frame, AddNewCityFragment.getInstance())
//                        .addToBackStack(null)
//                        .commit();
////                MyCityList.getInstance().AddNewCity("London");
//            }
//        });
    }

    public void GetToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.search:
//                Log.d("AAA", "search menu: ");
//                EditText edt = findViewById(R.id.edittext_city);
//                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new WeatherCity(edt.getText().toString())).commit();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void BackFragment(Fragment frag) {
        getSupportFragmentManager().popBackStack();
    }


    public void OpenAddNewFragment() {
        getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit)
                        .add(R.id.content_frame, AddNewCityFragment.getInstance())
                        .addToBackStack(null)
                        .commit();
    }
}
