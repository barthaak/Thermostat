package nl.tue.demothermostat;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.WeekProgram;

import java.util.ArrayList;
import nl.tue.demothermostat.SeekArc;
import nl.tue.demothermostat.SeekArc.OnSeekArcChangeListener;

public class ThermostatActivity2 extends MainActivity {

    private static SeekArc seek_bar;
    private static TextView text_view;
    private static TextView text_view2;
    private static LinearLayout background;
    private static ToggleButton tbutton;
    private static Button wbutton;
    private static Button cbutton;
    private static Button sbutton;
    private static String getParam;
    private static String getParamCur;
    private static String getParamWeek;
    private static String getTime;
    private static double getTimeInt;
    private static String getNextTime;
    private static String getDay;
    private static String getDay2;
    private static double getTemp;
    private static int getTempInt;
    private static double getCurTemp;
    private static TextView cur_temp;
    private static SwipeRefreshLayout swl;
    private static TextView timeView;
    private static MediaPlayer mp_button;
    private static MediaPlayer mp_bar;
    private static ArrayList<String> mylist;
    private static ArrayList<Integer> mylistInt;
    private static ArrayList<String> mylist2;
    private static ArrayList<Integer> mylistInt2;
    private static ArrayList<String> mylistType;
    private static ArrayList<String> mylistType2;
    private static Boolean hours24;
    private static Thread thread;
    private static String getNextType;
    private static String getNextTemp;
    private static TextView nextTemp;
    private static TextView currrent;
    private static RelativeLayout backgroundNewTar;
    private static RelativeLayout backgroundWCButtons;

    private static Handler mHandler2;
    private static TextView text_newTar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh12);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Set Target Temperature");

        tbutton = (ToggleButton)findViewById(R.id.toggleButton) ;
        wbutton = (Button)findViewById(R.id.button);
        cbutton = (Button)findViewById(R.id.button2);
        sbutton = (Button)findViewById(R.id.setTemp12);
        cur_temp = (TextView)findViewById(R.id.curTemp12);
        text_view2 = (TextView)findViewById(R.id.textView212);
        nextTemp = (TextView)findViewById(R.id.next12);
        seek_bar = (SeekArc)findViewById(R.id.seekBar);
        text_view = (TextView)findViewById(R.id.textView12);
        background = (LinearLayout)findViewById(R.id.background12);
        backgroundNewTar = (RelativeLayout)findViewById(R.id.includeSetTar);
        backgroundWCButtons = (RelativeLayout)findViewById(R.id.includeWCButtons);

        swl = (SwipeRefreshLayout) findViewById(R.id.swiperefresh12);
        text_newTar = (TextView) findViewById(R.id.newTarget);

        this.mHandler2 = new Handler();
        m_Runnable2.run();
        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh()
            {

                getCurrentTemp();
                getTargetTemp();

                if (tbutton.isChecked() == false) {
                    getTheTime();
                }
                swl.setRefreshing(false);
            }
        });

        mylist = new ArrayList<String>();
        mylistType = new ArrayList<String>();
        mylistInt = new ArrayList<Integer>();

        mylist2 = new ArrayList<String>();
        mylistType2 = new ArrayList<String>();
        mylistInt2 = new ArrayList<Integer>();

        background.setBackgroundColor(Color.parseColor("#f9f9f9"));

        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/25";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        seekbarr();


        new Thread(new Runnable() {
            @Override
            public void run() {
                getParamWeek = "";
                try {
                    getParamWeek = HeatingSystem.get("weekProgramState");
                    if (getParamWeek.equals("off")) {
                        tbutton.post(new Runnable() {
                            @Override
                            public void run() {
                                tbutton.setChecked(true);
                                toggleItem(tbutton);
                                getTargetTemp();
                                getCurrentTemp();

                            }
                        });

                    } else {
                        tbutton.post(new Runnable() {
                            @Override
                            public void run() {
                                tbutton.setChecked(false);
                                toggleItem(tbutton);
                                getTheTime();
                                getTargetTemp();
                                getCurrentTemp();
                            }
                        });

                    }

                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
        colorback((seek_bar.getProgress()+50.0)/10.0);



    }
    private final Runnable m_Runnable2 = new Runnable()
    {
        public void run()

        {
            getCurrentTemp();
            getTargetTempRefresh();
            getTheTime();

            ThermostatActivity2.this.mHandler2.postDelayed(m_Runnable2,2800);
        }

    };
    public void setRefreshFalse(){
            swl.setRefreshing(false);

    }

    public void CancelBack(View view){
        if (mp_button != null) {
            mp_button.reset();
            mp_button.release();
        }
        mp_button = MediaPlayer.create(this, R.raw.buttontick);

        mp_button.start();

        Intent myIntent = new Intent(this, ThermostatActivity.class);
        startActivity(myIntent);
    }

    public void toggleItem(View view){
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            background.setBackgroundColor(Color.parseColor("#f0f0f0"));
            seek_bar.setAlpha(0.3f);
            wbutton.setAlpha(0.3f);
            cbutton.setAlpha(0.3f);
            text_view.setAlpha(0.3f);
            text_newTar.setAlpha(0.3f);
            sbutton.setAlpha(0.3f);
            sbutton.setEnabled(false);
            seek_bar.setEnabled(false);
            wbutton.setEnabled(false);
            cbutton.setEnabled(false);

            nextTemp.setAlpha(0.3f);
            nextTemp.setText("Next: ... from ...");
        } else {
            background.setBackgroundColor(Color.parseColor("#f9f9f9"));
            seek_bar.setAlpha(1f);
            wbutton.setAlpha(1f);
            cbutton.setAlpha(1f);
            text_view.setAlpha(1f);
            text_newTar.setAlpha(1f);
            sbutton.setAlpha(1f);
            sbutton.setEnabled(true);
            seek_bar.setEnabled(true);
            wbutton.setEnabled(true);
            cbutton.setEnabled(true);
            nextTemp.setAlpha(1f);


        }
    }
    public void onToggleClicked(View view) {
        // Is the toggle on?

        if (mp_button != null) {
            mp_button.reset();
            mp_button.release();
        }

        mp_button = MediaPlayer.create(this, R.raw.buttontick);
        mp_button.start();

        boolean on = ((ToggleButton) view).isChecked();
        toggleItem(tbutton);
        if (on) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HeatingSystem.put("weekProgramState", "off");
                        HeatingSystem.put("targetTemperature", Double.toString((seek_bar.getProgress()+50.0)/10.0));
                        getParam = HeatingSystem.get("targetTemperature");
                        getTemp = (Double.parseDouble(getParam)*10)-50;
                        getTempInt = (int) getTemp;
                        seek_bar.post(new Runnable() {
                            @Override
                            public void run() {
                                seek_bar.setProgress(getTempInt);
                                text_view2.setText(Html.fromHtml("Target: "+ "<strong>"+getParam+"</strong>"+" \u00B0C"));

                                Toast toast = Toast.makeText(getBaseContext(), "Week Schedule turned OFF",Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.show();
                            }
                        });
                    } catch (Exception e) {
                        System.err.println("Error from getdata " + e);
                    }
                }
            }).start();

        } else {


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        HeatingSystem.put("weekProgramState", "on");
                        getParam = HeatingSystem.get("targetTemperature");
                        getTemp = (Double.parseDouble(getParam)*10)-50;
                        getTempInt = (int) getTemp;
                        seek_bar.post(new Runnable() {
                            @Override
                            public void run() {
                                seek_bar.setProgress(getTempInt);
                                text_view2.setText(Html.fromHtml("Target: "+ "<strong>"+getParam+"</strong>"+" \u00B0C"));
                                getTheTime();
                                Toast toast = Toast.makeText(getBaseContext(), "Week Schedule turned ON",Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.show();
                            }
                        });
                    } catch (Exception e) {
                        System.err.println("Error from getdata " + e);
                    }
                }
            }).start();

        }

    }

    public void onSetTempClicked(View view){
        if (mp_button != null) {
            mp_button.reset();
            mp_button.release();
        }
        mp_button = MediaPlayer.create(this, R.raw.buttontick);

        mp_button.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HeatingSystem.put("targetTemperature", Double.toString((seek_bar.getProgress()+50.0)/10.0));
                    getParam = HeatingSystem.get("targetTemperature");
                    getTemp = (Double.parseDouble(getParam)*10)-50;
                    getTempInt = (int) getTemp;

                    seek_bar.post(new Runnable() {
                        @Override
                        public void run() {
                            seek_bar.setProgress(getTempInt);
                            text_view2.setText(Html.fromHtml("Target: "+ "<strong>"+getParam+"</strong>"+" \u00B0C"));


                        }
                    });
                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
        getTheTime();
        Toast toast = Toast.makeText(getBaseContext(), "Temperature set to " + (seek_bar.getProgress()+50.0)/10.0 +" \u00B0C" + " till "+ getNextTime,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

    }

    public void onWarmerClicked(View view) {
        seek_bar.setProgress(seek_bar.getProgress()+1);
    }

    public void onColderClicked(View view) {
        seek_bar.setProgress(seek_bar.getProgress()-1);
    }

    public void colorback(double cur){
        String colorcold1 = "#ddffe6";
        String colorcold2 = "#ecffdd";
        String colormid1 = "#fdffdd";
        String colormid2 = "#fff6c3";
        String colormid3 = "#ffedca";
        String colorhot1 = "#ffdec3";
        String colorhot2 = "#ffd2ca";

        if (cur < 8) {
            backgroundNewTar.setBackgroundColor(Color.parseColor(colorcold1));
            backgroundWCButtons.setBackgroundColor(Color.parseColor(colorcold1));

        } else if (cur < 11) {
            backgroundNewTar.setBackgroundColor(Color.parseColor(colorcold2));
            backgroundWCButtons.setBackgroundColor(Color.parseColor(colorcold2));

        }else if (cur < 14) {
            backgroundNewTar.setBackgroundColor(Color.parseColor(colormid1));
            backgroundWCButtons.setBackgroundColor(Color.parseColor(colormid1));
        } else if (cur < 17) {
            backgroundNewTar.setBackgroundColor(Color.parseColor(colormid2));
            backgroundWCButtons.setBackgroundColor(Color.parseColor(colormid2));

        } else if (cur < 20) {
            backgroundNewTar.setBackgroundColor(Color.parseColor(colormid3));
            backgroundWCButtons.setBackgroundColor(Color.parseColor(colormid3));

        } else if (cur < 25) {
            backgroundNewTar.setBackgroundColor(Color.parseColor(colorhot1));
            backgroundWCButtons.setBackgroundColor(Color.parseColor(colorhot1));

        }else {
            backgroundNewTar.setBackgroundColor(Color.parseColor(colorhot2));
            backgroundWCButtons.setBackgroundColor(Color.parseColor(colorhot2));

        }
    }
    public void getCurrentTemp(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                getParamCur = "";
                try {
                    getParamCur = HeatingSystem.get("currentTemperature");
                            /*
									HeatingSystem.get("day");
									HeatingSystem.get("time");
									HeatingSystem.get("targetTemperature");
									HeatingSystem.get("dayTemperature");
									HeatingSystem.get("nightTemperature");
									HeatingSystem.get("weekProgramState");
							*/
                    getCurTemp = Double.parseDouble(getParamCur);
                    //currrent.setText("Current: ");
                   // currrent.setTextSize(30);

                    cur_temp.post(new Runnable() {
                        @Override
                        public void run() {
                            cur_temp.setText(Html.fromHtml("Current: "+"<strong>" + getCurTemp + "</strong>" + " \u00B0C"));

                        }
                    });
                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);
                }
            }
        }).start();
    }


    public Double getParsedTime (String t){
        String[] sep = t.split(":");
        return Double.parseDouble(sep[0].toString())*100 + (Double.parseDouble(sep[1].toString())/60.0)*100.0;
    }

    public void getTheTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getNextTime = "00:00";
                getNextType = "...";
                getNextTemp = "...";
                try {

                    getDay = HeatingSystem.get("day");
                    getTime = HeatingSystem.get("time");
                    getTimeInt = getParsedTime(getTime);

                    WeekProgram wpg = HeatingSystem.getWeekProgram();

                        if (getDay.equals("Sunday")) {
                            getDay2 = wpg.valid_days[0];

                            } else {
                                for (int i = 0; i < wpg.valid_days.length -1; i++) {
                                    if (getDay.equals(wpg.valid_days[i])) {
                                        getDay2 = wpg.valid_days[i + 1];
                                    }
                            }
                        }


                    for (int j = 0; j < wpg.data.get(getDay).size(); j++) {

                        if (wpg.data.get(getDay).get(j).getState()) {
                            mylist.add(wpg.data.get(getDay).get(j).getTime());
                            mylistInt.add(wpg.data.get(getDay).get(j).getTime_Int());
                            mylistType.add(wpg.data.get(getDay).get(j).getType());

                        }
                    }


                    for (int j = 0; j < wpg.data.get(getDay2).size(); j++) {

                        if (wpg.data.get(getDay2).get(j).getState()) {
                            mylist2.add(wpg.data.get(getDay2).get(j).getTime());
                            mylistInt2.add(wpg.data.get(getDay2).get(j).getTime_Int());
                            mylistType2.add(wpg.data.get(getDay2).get(j).getType());

                        }
                    }



                    for (int j = 0; j < mylist.size() - 1; j++) {
                        if (mylist.get(j).equals(mylist.get(j+1))){
                            mylist.remove(j);
                            mylistInt.remove(j);
                            mylistType.remove(j);
                        }

                    }
                    int jo = 0;
                    while (jo < mylist.size()-1){
                        if (mylistType.get(jo).equals(mylistType.get(jo+1))){
                            mylist.remove(jo+1);
                            mylistInt.remove(jo+1);
                            mylistType.remove(jo+1);
                        } else {
                            jo++;
                        }
                    }

                    int jo2 = 0;
                    while (jo2 < mylist.size()){
                        if (jo2==0) {
                            if (mylistType.get(jo2).equals("night")){
                                mylist.remove(jo2);
                                mylistInt.remove(jo2);
                                mylistType.remove(jo2);
                            } else {
                                jo2++;
                            }
                        } else {
                            jo2++;
                        }
                    }

                    for (int j = 0; j < mylist2.size() - 1; j++) {
                        if (mylist2.get(j).equals(mylist2.get(j+1))){
                            mylist2.remove(j);
                            mylistInt2.remove(j);
                            mylistType2.remove(j);
                        }

                    }
                    int jo3 = 0;
                    while (jo3 < mylist2.size()-1){
                        if (mylistType2.get(jo3).equals(mylistType2.get(jo3+1))){
                            mylist2.remove(jo3+1);
                            mylistInt2.remove(jo3+1);
                            mylistType2.remove(jo3+1);
                        } else {
                            jo3++;
                        }
                    }

                    int jo4 = 0;
                    while (jo4 < mylist2.size()){
                        if (jo4==0) {
                            if (mylistType2.get(jo4).equals("night")){
                                mylist2.remove(jo4);
                                mylistInt2.remove(jo4);
                                mylistType2.remove(jo4);
                            } else {
                                jo4++;
                            }
                        } else {
                            jo4++;
                        }
                    }

                    Log.i("TAGGY", getDay);
                    Log.i("TAGGY", getTime);
                        for (int j = 0; j < mylist.size() - 1; j++) {
                            hours24 = false;
                                if (mylistInt.get(j) <= getTimeInt &&
                                        getTimeInt < mylistInt.get(j+1)) {

                                    getNextTime = mylist.get(j+1);
                                    getNextType = mylistType.get(j+1);
                                    break;
                                } else {
                                    if (getTimeInt < mylistInt.get(j)) {
                                        getNextTime = mylist.get(0);
                                        getNextType = mylistType.get(0);
                                    } else {
                                        hours24 = true;
                                        if (mylist2.size()>0) {
                                            if (mylistType.get(mylist.size()-1).equals("night")){
                                                getNextTime = mylist2.get(0);
                                                getNextType = mylistType2.get(0);
                                        } else {
                                                if (mylistType2.get(0).equals("day") && mylist2.get(0).equals("00:00")) {
                                                    getNextTime = mylist2.get(1);
                                                    getNextType = mylistType2.get(1);
                                                } else {
                                                    getNextTime = "00:00";
                                                    getNextType = "night";
                                                }
                                        }
                                        } else {
                                            getNextTime = "23:59";
                                            getNextType = "night";
                                        }
                                    }
                                }
                            }



                    if (getNextType.equals("day")){
                        getNextTemp = HeatingSystem.get("dayTemperature");
                    } else if (getNextType.equals("night")){
                        getNextTemp = HeatingSystem.get("nightTemperature");
                    }

                    nextTemp.post(new Runnable() {
                        @Override
                        public void run() {
                            if ((getTimeInt < getParsedTime(getNextTime)) && hours24) {
                                nextTemp.setText(Html.fromHtml("Next: " + getNextTemp + "\u00B0C over 24h+"));
                            } else {
                                nextTemp.setText(Html.fromHtml("Next: "+ getNextTemp+ " \u00B0C from " + getNextTime));

                            }

                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);
                }
            }
        }).start();
    }
    public void getTargetTemp(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                getParam = "";
                try {
                    getParam = HeatingSystem.get("targetTemperature");
                    /*getDay = HeatingSystem.get("day");
                    WeekProgram wpg = HeatingSystem.getWeekProgram();
                    getTime = wpg.timer(getDay);*/

                            /*
									HeatingSystem.get("day");
									HeatingSystem.get("time");
									HeatingSystem.get("targetTemperature");
									HeatingSystem.get("dayTemperature");
									HeatingSystem.get("nightTemperature");
									HeatingSystem.get("weekProgramState");
							*/
                    getTemp = (Double.parseDouble(getParam)*10)-50;
                    getTempInt = (int) getTemp;
                    seek_bar.post(new Runnable() {
                        @Override
                        public void run() {
                            seek_bar.setProgress(getTempInt);
                            text_view2.setText(Html.fromHtml("Target: " + "<strong>"+getParam+"</strong>"+" \u00B0C"));

                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);
                }
            }
        }).start();
    }

    public void getTargetTempRefresh(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                getParam = "";
                try {
                    getParam = HeatingSystem.get("targetTemperature");
                    /*getDay = HeatingSystem.get("day");
                    WeekProgram wpg = HeatingSystem.getWeekProgram();
                    getTime = wpg.timer(getDay);*/

                            /*
									HeatingSystem.get("day");
									HeatingSystem.get("time");
									HeatingSystem.get("targetTemperature");
									HeatingSystem.get("dayTemperature");
									HeatingSystem.get("nightTemperature");
									HeatingSystem.get("weekProgramState");
							*/
                    getTemp = (Double.parseDouble(getParam)*10)-50;
                    getTempInt = (int) getTemp;
                    text_view2.post(new Runnable() {
                        @Override
                        public void run() {
                            text_view2.setText(Html.fromHtml("Target: " + "<strong>"+getParam+"</strong>"+" \u00B0C"));

                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);
                }
            }
        }).start();
    }

    public void seekbarr(){

        if (mp_bar != null) {
            mp_bar.reset();
            mp_bar.release();
        }
        mp_bar = MediaPlayer.create(this, R.raw.ticking);

        text_view.setText(Html.fromHtml((seek_bar.getProgress()+50.0)/10.0 + " \u00B0C"));

        seek_bar.setOnSeekArcChangeListener(
                new OnSeekArcChangeListener(){
                    double progress_value;
                    @Override
                    public void onProgressChanged(SeekArc seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view.setText(Html.fromHtml((progress_value+50.0)/10.0 + " \u00B0C"));
                        mp_bar.start();
                        colorback((progress_value+50.0)/10.0);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekArc seekBar) {
                        swl.setEnabled(false);

                    }

                    @Override
                    public void onStopTrackingTouch(SeekArc seekBar) {
                        text_view.setText(Html.fromHtml((progress_value+50.0)/10.0 + " \u00B0C"));
                        swl.setEnabled(true);


                    }
                }
        );
    }
}
