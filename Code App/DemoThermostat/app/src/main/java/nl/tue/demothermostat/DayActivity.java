package nl.tue.demothermostat;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class DayActivity extends MainActivity{

    private static SeekArc seek_bar_day;
    private static TextView text_view_day;
    private static TextView text_view2_day;
    private static LinearLayout background_day;
    private static LinearLayout background_current_day;
    private static RelativeLayout backgroundNewDay;
    private static RelativeLayout backgroundWCButtonsDay;


    private static Button wbutton_day;
    private static Button cbutton_day;
    private static Button sbutton_day;
    private static String getParam_day;
    private static double getTemp_day;
    private static int getTempInt_day;

    private static SeekBar seek_bar_night;
    private static TextView text_view_night;
    private static TextView text_view2_night;
    private static LinearLayout background_night;
    private static Button wbutton_night;
    private static Button cbutton_night;
    private static Button sbutton_night;
    private static String getParam_night;
    private static double getTemp_night;
    private static int getTempInt_night;
    private static MediaPlayer mp_button;
    private static MediaPlayer mp_bar;

    private static SwipeRefreshLayout swl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Day Temperature");

        swl = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                getDayTemp();
                swl.setRefreshing(false);
            }
        });


        sbutton_day = (Button)findViewById(R.id.setTempday);
        text_view2_day = (TextView)findViewById(R.id.textView2day);
        seek_bar_day = (SeekArc)findViewById(R.id.seekBarday);
        text_view_day = (TextView)findViewById(R.id.textViewday);
        background_day = (LinearLayout)findViewById(R.id.backgroundday);
        background_current_day = (LinearLayout)findViewById(R.id.backgroundCurrentDay);

        background_day.setBackgroundColor(Color.parseColor("#f9f9f9"));

        backgroundNewDay = (RelativeLayout)findViewById(R.id.includeSetDay);
        backgroundWCButtonsDay = (RelativeLayout)findViewById(R.id.includeWCButtonsDay);

        getDayTemp();
        seekbarrDay();
        colorbackDay((seek_bar_day.getProgress()+50.0)/10.0);

    }

    public void onSetDayTempClicked(View view){

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
                    HeatingSystem.put("dayTemperature", Double.toString((seek_bar_day.getProgress()+50.0)/10.0));
                    getParam_day = HeatingSystem.get("dayTemperature");

                    getTemp_day = (Double.parseDouble(getParam_day)*10)-50;
                    getTempInt_day = (int) getTemp_day;
                    text_view2_day.post(new Runnable() {
                        @Override
                        public void run() {
                            text_view2_day.setText(Html.fromHtml("<strong>" + getParam_day + "</strong>" + " \u00B0C"));
                            seek_bar_day.setProgress(getTempInt_day);
                            colorbackCurrentDay(Double.parseDouble(getParam_day));
                            Toast toast = Toast.makeText(getBaseContext(), "Changed day temperature to "+getParam_day,Toast.LENGTH_LONG);
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

    public void onWarmerClickedDay(View view) {
        seek_bar_day.setProgress(seek_bar_day.getProgress()+1);
    }

    public void onColderClickedDay(View view) {
        seek_bar_day.setProgress(seek_bar_day.getProgress()-1);
    }

    public void colorbackCurrentDay(double day){
        String colorcold1 = "#ddffe6";
        String colorcold2 = "#ecffdd";
        String colormid1 = "#fdffdd";
        String colormid2 = "#fff6c3";
        String colormid3 = "#ffedca";
        String colorhot1 = "#ffdec3";
        String colorhot2 = "#ffd2ca";

        if (day < 8) {
            background_current_day.setBackgroundColor(Color.parseColor(colorcold1));
        } else if (day < 11) {
            background_current_day.setBackgroundColor(Color.parseColor(colorcold2));
        }else if (day < 14) {
            background_current_day.setBackgroundColor(Color.parseColor(colormid1));
        } else if (day < 17) {
            background_current_day.setBackgroundColor(Color.parseColor(colormid2));
        } else if (day < 20) {
            background_current_day.setBackgroundColor(Color.parseColor(colormid3));
        } else if (day < 25) {
            background_current_day.setBackgroundColor(Color.parseColor(colorhot1));
        }else {
            background_current_day.setBackgroundColor(Color.parseColor(colorhot2));
        }
    }
    public void colorbackDay(double cur){
        String colorcold1 = "#ddffe6";
        String colorcold2 = "#ecffdd";
        String colormid1 = "#fdffdd";
        String colormid2 = "#fff6c3";
        String colormid3 = "#ffedca";
        String colorhot1 = "#ffdec3";
        String colorhot2 = "#ffd2ca";

        if (cur < 8) {
            backgroundNewDay.setBackgroundColor(Color.parseColor(colorcold1));
            backgroundWCButtonsDay.setBackgroundColor(Color.parseColor(colorcold1));

        } else if (cur < 11) {
            backgroundNewDay.setBackgroundColor(Color.parseColor(colorcold2));
            backgroundWCButtonsDay.setBackgroundColor(Color.parseColor(colorcold2));

        }else if (cur < 14) {
            backgroundNewDay.setBackgroundColor(Color.parseColor(colormid1));
            backgroundWCButtonsDay.setBackgroundColor(Color.parseColor(colormid1));
        } else if (cur < 17) {
            backgroundNewDay.setBackgroundColor(Color.parseColor(colormid2));
            backgroundWCButtonsDay.setBackgroundColor(Color.parseColor(colormid2));

        } else if (cur < 20) {
            backgroundNewDay.setBackgroundColor(Color.parseColor(colormid3));
            backgroundWCButtonsDay.setBackgroundColor(Color.parseColor(colormid3));

        } else if (cur < 25) {
            backgroundNewDay.setBackgroundColor(Color.parseColor(colorhot1));
            backgroundWCButtonsDay.setBackgroundColor(Color.parseColor(colorhot1));

        }else {
            backgroundNewDay.setBackgroundColor(Color.parseColor(colorhot2));
            backgroundWCButtonsDay.setBackgroundColor(Color.parseColor(colorhot2));

        }
    }

    public void getDayTemp(){
        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/25";

        new Thread(new Runnable() {
            @Override
            public void run() {
                getParam_day = "";
                try {
                    getParam_day = HeatingSystem.get("dayTemperature");
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
                    getTemp_day = (Double.parseDouble(getParam_day)*10)-50;
                    getTempInt_day = (int) getTemp_day;

                    text_view2_day.post(new Runnable() {
                        @Override
                        public void run() {
                            text_view2_day.setText(Html.fromHtml("<strong>" + getParam_day + "</strong>" + " \u00B0C"));
                            seek_bar_day.setProgress(getTempInt_day);
                            colorbackCurrentDay(Double.parseDouble(getParam_day));



                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);

                }
            }
        }).start();
    }

    public void seekbarrDay(){
        if (mp_bar != null) {
            mp_bar.reset();
            mp_bar.release();
        }
        mp_bar = MediaPlayer.create(this, R.raw.ticking);

        text_view_day.setText(Html.fromHtml((seek_bar_day.getProgress()+50.0)/10.0 + " \u00B0C"));

        seek_bar_day.setOnSeekArcChangeListener(
                new SeekArc.OnSeekArcChangeListener(){
                    double progress_value;
                    @Override
                    public void onProgressChanged(SeekArc seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view_day.setText(Html.fromHtml((progress_value+50.0)/10.0 + " \u00B0C"));
                        mp_bar.start();
                        colorbackDay((progress_value+50.0)/10.0);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekArc seekBar) {
                        swl.setEnabled(false);

                    }

                    @Override
                    public void onStopTrackingTouch(SeekArc seekBar) {
                        text_view_day.setText(Html.fromHtml((progress_value+50.0)/10.0 + " \u00B0C"));
                        swl.setEnabled(true);

                    }
                }
        );
    }



}

