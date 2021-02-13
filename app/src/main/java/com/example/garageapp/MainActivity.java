package com.example.garageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;

public abstract class MainActivity extends AppCompatActivity {

    private static final String TAG = "My garage";
   //private GarageModel mGarageModel;
    //private Session mCurrentSession;
    private TextView txt_title, txt_Last_Session, txt_total_time, txt_Garage_Name, txt_Garage_Address, txt_Cars, txt_Garage_open;
    private RelativeLayout main_MainLay;
    private final SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.US);
    private UseSession currentSession;
    //private RelativeLayout mMainLay;
    //private final SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.US);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fetchDataFromServer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentSession = new UseSession();
        currentSession.setStart(System.currentTimeMillis());
        RoomSQL.getInstance().getLastSession(session->{
            String msg;
            if(session == null)
                msg = "First Time!";
            else
                msg = format.format(session.getEnd());
            runOnUiThread(()->txt_Last_Session.setText(msg));
        });
        RoomSQL.getInstance().getTotalSpendsTime(time->
                runOnUiThread(()->
                        txt_total_time.setText(prettyTimeParser(time.intValue()))));
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentSession.setEnd(System.currentTimeMillis());
        currentSession.setTotal(currentSession.getEnd() - currentSession.getStart());
        RoomSQL.getInstance().insertSession(currentSession);
        currentSession = null;
    }

    protected void setBackgroundColor(int color) {
        main_MainLay.setBackgroundColor(color);

    }

    protected void setAppName(String name) {
        txt_title.setText(name);
    }

    public void init(){
        txt_title = findViewById(R.id.welcomePage_textView_title);
        txt_Last_Session = findViewById(R.id.TXT_Last_Session);
        txt_total_time = findViewById(R.id.TXT_total_time);
        txt_Garage_Name = findViewById(R.id.TXT_Garage_Name);
        txt_Garage_open = findViewById(R.id.TXT_Garage_open);
        txt_Garage_Address = findViewById(R.id.TXT_Garage_Address);
        txt_Cars = findViewById(R.id.TXT_Cars);
        main_MainLay = findViewById(R.id.main_layout);
    }

    private String arrayToString(String[] arr){
        StringBuilder sb = new StringBuilder();
        for(String s : arr){
            sb.append(s);
            sb.append("\n");
        }
        sb.substring(0,sb.length()-1);
        return sb.toString();
    }

    protected void fetchDataFromServer(){
        WebController controller = new WebController(garage -> {
            txt_Garage_Name.setText(garage.getName());
            txt_Cars.setText(arrayToString(garage.getCars()));
            txt_Garage_open.setText(garage.isOpen() ? "Open" : "Closed");
            txt_Garage_Address.setText(garage.getAddress());
        });
        controller.fetchGarage();
    }

    String prettyTimeParser(int time){
        time /= 1000;
        int seconds = time % 60;
        time /= 60;
        int minutes = time % 60;
        time /= 60;
        int hours = time % 24;
        time /= 24;
        int days = time;
        return String.format(Locale.ENGLISH,"%d days\n%02dH:%02dM:%02dS",days,hours,minutes,seconds);
    }


}