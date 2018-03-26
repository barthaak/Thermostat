package nl.tue.demothermostat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;
import org.thermostatapp.util.WeekProgram;
import org.w3c.dom.Text;


import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.os.Handler;


public class ThermostatActivity extends MainActivity {

    private static SeekBar seek_bar;
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
    private static double getTarTemp;
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
    private static LinearLayout bcur_temp;
    private static LinearLayout tcur_temp;
    private static ImageView statusImage;
    private static TextView statusText;
    private static Handler mHandler;
    private static GradientDrawable bgShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Home");


        sbutton = (Button)findViewById(R.id.setTemp);
        cur_temp = (TextView) findViewById(R.id.curTemp);
        bcur_temp = (LinearLayout) findViewById(R.id.backgroundCurrent);
        tcur_temp = (LinearLayout) findViewById(R.id.backgroundTarget);
        statusImage = (ImageView) findViewById(R.id.statusImage);
        text_view2 = (TextView) findViewById(R.id.textView2);
        nextTemp = (TextView) findViewById(R.id.next);
        statusText = (TextView)findViewById(R.id.status);
        background = (LinearLayout) findViewById(R.id.background);
        bgShape = (GradientDrawable)bcur_temp.getBackground();

        swl = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        this.mHandler = new Handler();
        m_Runnable.run();

        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCurrentTemp();
                getTargetTemp();
                getTheTime();
                heating();

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
        swl.setRefreshing(true);
        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/25";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        getCurrentTemp();
        getTargetTemp();
        getTheTime();
        heating();


    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            getCurrentTemp();
            getTargetTemp();
            getTheTime();
            heating();

            ThermostatActivity.this.mHandler.postDelayed(m_Runnable,2800);
        }

    };

    public void heating(){
        if (getCurTemp < getTarTemp){
            statusImage.setImageResource(R.drawable.fire);
            statusText.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)statusImage.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            statusImage.setLayoutParams(params); //causes layout update
            statusText.setText("Heating...");
        } else if (getCurTemp > getTarTemp){
            statusImage.setImageResource(R.drawable.snowflake);
            statusText.setVisibility(View.VISIBLE);
            statusText.setText("Cooling...");
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)statusImage.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            statusImage.setLayoutParams(params); //causes layout update
        } else {
            statusImage.setImageResource(R.drawable.window_minimize);
            statusText.setVisibility(View.GONE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)statusImage.getLayoutParams();
            params.removeRule(RelativeLayout.ALIGN_PARENT_START);
            statusImage.setLayoutParams(params); //causes layout update

        }
    }
    public void setRefreshFalse() {
        swl.setRefreshing(false);

    }

    public void onSetTempClicked(View view) {
        if (mp_button != null) {
            mp_button.reset();
            mp_button.release();
        }
        mp_button = MediaPlayer.create(this, R.raw.buttontick);

        mp_button.start();

        Intent myIntent = new Intent(this, ThermostatActivity2.class);
        startActivity(myIntent);
    }


    public void colorback(double cur) {
        String colorcold1 = "#ddffe6";
        String colorcold2 = "#ecffdd";
        String colormid1 = "#fdffdd";
        String colormid2 = "#fff6c3";
        String colormid3 = "#ffedca";
        String colorhot1 = "#ffdec3";
        String colorhot2 = "#ffd2ca";

        if (cur < 8) {
            bgShape.setColor(Color.parseColor(colorcold1));
        } else if (cur < 11) {
            bgShape.setColor(Color.parseColor(colorcold2));
        } else if (cur < 14) {
            bgShape.setColor(Color.parseColor(colormid1));
        } else if (cur < 17) {
            bgShape.setColor(Color.parseColor(colormid2));
        } else if (cur < 20) {
            bgShape.setColor(Color.parseColor(colormid3));
        } else if (cur < 25) {
            bgShape.setColor(Color.parseColor(colorhot1));
        } else {
            bgShape.setColor(Color.parseColor(colorhot2));
        }
    }

    public void colorbackTarget(double cur) {
        String colorcold1 = "#ddffe6";
        String colorcold2 = "#ecffdd";
        String colormid1 = "#fdffdd";
        String colormid2 = "#fff6c3";
        String colormid3 = "#ffedca";
        String colorhot1 = "#ffdec3";
        String colorhot2 = "#ffd2ca";

        if (cur < 8) {
            tcur_temp.setBackgroundColor(Color.parseColor(colorcold1));
        } else if (cur < 11) {
            tcur_temp.setBackgroundColor(Color.parseColor(colorcold2));
        } else if (cur < 14) {
            tcur_temp.setBackgroundColor(Color.parseColor(colormid1));
        } else if (cur < 17) {
            tcur_temp.setBackgroundColor(Color.parseColor(colormid2));
        } else if (cur < 20) {
            tcur_temp.setBackgroundColor(Color.parseColor(colormid3));
        } else if (cur < 25) {
            tcur_temp.setBackgroundColor(Color.parseColor(colorhot1));
        } else {
            tcur_temp.setBackgroundColor(Color.parseColor(colorhot2));
        }
    }

    public void getCurrentTemp() {

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
                            cur_temp.setText(Html.fromHtml("<strong>" + getCurTemp + "</strong>" + " \u00B0C"));
                            colorback(getCurTemp);
                            heating();

                        }
                    });
                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
    }


    public Double getParsedTime(String t) {
        String[] sep = t.split(":");
        return Double.parseDouble(sep[0].toString()) * 100 + (Double.parseDouble(sep[1].toString()) / 60.0) * 100.0;
    }

    public void getTheTime() {
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
                        for (int i = 0; i < wpg.valid_days.length - 1; i++) {
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
                        if (mylist.get(j).equals(mylist.get(j + 1))) {
                            mylist.remove(j);
                            mylistInt.remove(j);
                            mylistType.remove(j);
                        }

                    }
                    int jo = 0;
                    while (jo < mylist.size() - 1) {
                        if (mylistType.get(jo).equals(mylistType.get(jo + 1))) {
                            mylist.remove(jo + 1);
                            mylistInt.remove(jo + 1);
                            mylistType.remove(jo + 1);
                        } else {
                            jo++;
                        }
                    }

                    int jo2 = 0;
                    while (jo2 < mylist.size()) {
                        if (jo2 == 0) {
                            if (mylistType.get(jo2).equals("night")) {
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
                        if (mylist2.get(j).equals(mylist2.get(j + 1))) {
                            mylist2.remove(j);
                            mylistInt2.remove(j);
                            mylistType2.remove(j);
                        }

                    }
                    int jo3 = 0;
                    while (jo3 < mylist2.size() - 1) {
                        if (mylistType2.get(jo3).equals(mylistType2.get(jo3 + 1))) {
                            mylist2.remove(jo3 + 1);
                            mylistInt2.remove(jo3 + 1);
                            mylistType2.remove(jo3 + 1);
                        } else {
                            jo3++;
                        }
                    }

                    int jo4 = 0;
                    while (jo4 < mylist2.size()) {
                        if (jo4 == 0) {
                            if (mylistType2.get(jo4).equals("night")) {
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
                                getTimeInt < mylistInt.get(j + 1)) {

                            getNextTime = mylist.get(j + 1);
                            getNextType = mylistType.get(j + 1);
                            break;
                        } else {
                            if (getTimeInt < mylistInt.get(j)) {
                                getNextTime = mylist.get(0);
                                getNextType = mylistType.get(0);
                            } else {
                                hours24 = true;
                                if (mylist2.size() > 0) {
                                    if (mylistType.get(mylist.size() - 1).equals("night")) {
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


                    if (getNextType.equals("day")) {
                        getNextTemp = HeatingSystem.get("dayTemperature");
                    } else if (getNextType.equals("night")) {
                        getNextTemp = HeatingSystem.get("nightTemperature");
                    }
                    getParamWeek = HeatingSystem.get("weekProgramState");

                    nextTemp.post(new Runnable() {
                        @Override
                        public void run() {
                            if (getParamWeek.equals("off")) {
                                nextTemp.setText(Html.fromHtml("LOCKED"));

                            } else {
                                if ((getTimeInt < getParsedTime(getNextTime)) && hours24) {
                                    nextTemp.setText(Html.fromHtml("Next: " + getNextTemp + "\u00B0C over 24h+"));
                                } else {
                                    nextTemp.setText(Html.fromHtml("Next: " + getNextTemp + " \u00B0C from " + getNextTime));
                                }
                            }


                            swl.setRefreshing(false);


                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
    }

    public void getTargetTemp() {

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
                    getTarTemp = Double.parseDouble(getParam);
                    text_view2.post(new Runnable() {
                        @Override
                        public void run() {
                            text_view2.setText(Html.fromHtml( "<strong>" + getParam + "</strong>" + " \u00B0C"));
                            colorbackTarget(getTarTemp);
                            heating();

                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
    }
}


