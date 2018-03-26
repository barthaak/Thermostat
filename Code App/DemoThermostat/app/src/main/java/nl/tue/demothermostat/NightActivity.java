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

public class NightActivity extends MainActivity{

    private static SeekArc seek_bar_night;
    private static TextView text_view_night;
    private static TextView text_view2_night;
    private static LinearLayout background_current_night;
    private static RelativeLayout backgroundNewNight;
    private static RelativeLayout backgroundWCButtonsNight;
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
        setContentView(R.layout.refresh21);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Night Temperature");

        swl = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                getNightTemp();
                swl.setRefreshing(false);
            }
        });


        sbutton_night = (Button)findViewById(R.id.setTempnight);
        text_view2_night = (TextView)findViewById(R.id.textView2night);
        seek_bar_night = (SeekArc)findViewById(R.id.seekBarnight);
        text_view_night = (TextView)findViewById(R.id.textViewnight);
        background_current_night = (LinearLayout)findViewById(R.id.backgroundnight);
        background_current_night = (LinearLayout)findViewById(R.id.backgroundCurrentNight);

        background_current_night.setBackgroundColor(Color.parseColor("#f9f9f9"));

        backgroundNewNight = (RelativeLayout)findViewById(R.id.includeSetNight);
        backgroundWCButtonsNight = (RelativeLayout)findViewById(R.id.includeWCButtonsNight);

        getNightTemp();
        seekbarrNight();
        colorbackNight((seek_bar_night.getProgress()+50.0)/10.0);

    }


    public void onSetNightTempClicked(View view){

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
                    HeatingSystem.put("nightTemperature", Double.toString((seek_bar_night.getProgress()+50.0)/10.0));
                    getParam_night = HeatingSystem.get("nightTemperature");

                    getTemp_night = (Double.parseDouble(getParam_night)*10)-50;
                    getTempInt_night = (int) getTemp_night;
                    text_view2_night.post(new Runnable() {
                        @Override
                        public void run() {
                            text_view2_night.setText(Html.fromHtml("<strong>" + getParam_night + "</strong>" + " \u00B0C"));
                            seek_bar_night.setProgress(getTempInt_night);
                            colorbackCurrentNight(Double.parseDouble(getParam_night));
                            Toast toast = Toast.makeText(getBaseContext(), "Changed night temperature to "+getParam_night,Toast.LENGTH_LONG);
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

    public void onWarmerClickedNight(View view) {
        seek_bar_night.setProgress(seek_bar_night.getProgress()+1);
    }

    public void onColderClickedNight(View view) {
        seek_bar_night.setProgress(seek_bar_night.getProgress()-1);
    }

    public void colorbackCurrentNight(double night){
        String colorcold1 = "#ddffe6";
        String colorcold2 = "#ecffdd";
        String colormid1 = "#fdffdd";
        String colormid2 = "#fff6c3";
        String colormid3 = "#ffedca";
        String colorhot1 = "#ffdec3";
        String colorhot2 = "#ffd2ca";

        if (night < 8) {
            background_current_night.setBackgroundColor(Color.parseColor(colorcold1));
        } else if (night < 11) {
            background_current_night.setBackgroundColor(Color.parseColor(colorcold2));
        }else if (night < 14) {
            background_current_night.setBackgroundColor(Color.parseColor(colormid1));
        } else if (night < 17) {
            background_current_night.setBackgroundColor(Color.parseColor(colormid2));
        } else if (night < 20) {
            background_current_night.setBackgroundColor(Color.parseColor(colormid3));
        } else if (night < 25) {
            background_current_night.setBackgroundColor(Color.parseColor(colorhot1));
        }else {
            background_current_night.setBackgroundColor(Color.parseColor(colorhot2));
        }
    }

    public void colorbackNight(double cur){
        String colorcold1 = "#ddffe6";
        String colorcold2 = "#ecffdd";
        String colormid1 = "#fdffdd";
        String colormid2 = "#fff6c3";
        String colormid3 = "#ffedca";
        String colorhot1 = "#ffdec3";
        String colorhot2 = "#ffd2ca";

        if (cur < 8) {
            backgroundNewNight.setBackgroundColor(Color.parseColor(colorcold1));
            backgroundWCButtonsNight.setBackgroundColor(Color.parseColor(colorcold1));

        } else if (cur < 11) {
            backgroundNewNight.setBackgroundColor(Color.parseColor(colorcold2));
            backgroundWCButtonsNight.setBackgroundColor(Color.parseColor(colorcold2));

        }else if (cur < 14) {
            backgroundNewNight.setBackgroundColor(Color.parseColor(colormid1));
            backgroundWCButtonsNight.setBackgroundColor(Color.parseColor(colormid1));
        } else if (cur < 17) {
            backgroundNewNight.setBackgroundColor(Color.parseColor(colormid2));
            backgroundWCButtonsNight.setBackgroundColor(Color.parseColor(colormid2));

        } else if (cur < 20) {
            backgroundNewNight.setBackgroundColor(Color.parseColor(colormid3));
            backgroundWCButtonsNight.setBackgroundColor(Color.parseColor(colormid3));

        } else if (cur < 25) {
            backgroundNewNight.setBackgroundColor(Color.parseColor(colorhot1));
            backgroundWCButtonsNight.setBackgroundColor(Color.parseColor(colorhot1));

        }else {
            backgroundNewNight.setBackgroundColor(Color.parseColor(colorhot2));
            backgroundWCButtonsNight.setBackgroundColor(Color.parseColor(colorhot2));

        }
    }

    public void getNightTemp(){
        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/25";

        new Thread(new Runnable() {
            @Override
            public void run() {
                getParam_night = "";
                try {
                    getParam_night = HeatingSystem.get("nightTemperature");
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
                    getTemp_night = (Double.parseDouble(getParam_night)*10)-50;
                    getTempInt_night = (int) getTemp_night;

                    text_view2_night.post(new Runnable() {
                        @Override
                        public void run() {
                            text_view2_night.setText(Html.fromHtml("<strong>" + getParam_night + "</strong>" + " \u00B0C"));
                            seek_bar_night.setProgress(getTempInt_night);
                            colorbackCurrentNight(Double.parseDouble(getParam_night));



                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);

                }
            }
        }).start();
    }

    public void seekbarrNight(){
        if (mp_bar != null) {
            mp_bar.reset();
            mp_bar.release();
        }
        mp_bar = MediaPlayer.create(this, R.raw.ticking);

        text_view_night.setText(Html.fromHtml((seek_bar_night.getProgress()+50.0)/10.0 + " \u00B0C"));

        seek_bar_night.setOnSeekArcChangeListener(
                new SeekArc.OnSeekArcChangeListener(){
                    double progress_value;
                    @Override
                    public void onProgressChanged(SeekArc seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view_night.setText(Html.fromHtml((progress_value+50.0)/10.0 + " \u00B0C"));
                        mp_bar.start();
                        colorbackNight((progress_value+50.0)/10.0);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekArc seekBar) {
                        swl.setEnabled(false);

                    }

                    @Override
                    public void onStopTrackingTouch(SeekArc seekBar) {
                        text_view_night.setText(Html.fromHtml((progress_value+50.0)/10.0 + " \u00B0C"));
                        swl.setEnabled(true);

                    }
                }
        );
    }

}
