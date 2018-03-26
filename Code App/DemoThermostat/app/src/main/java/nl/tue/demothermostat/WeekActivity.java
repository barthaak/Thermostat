package nl.tue.demothermostat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;
import org.thermostatapp.util.WeekProgram;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class WeekActivity extends MainActivity implements
        View.OnClickListener{

    private static int mHour, mMinute;
    private static String[] arraySpinner;
    private static String [] arraySpinnerTemp;
    private static String [] arraySpinnerTemp0;

    private static MediaPlayer mp_button;
    private static String time;
    private static String getDay;
    private static String weekday;
    private static Spinner s;
    private static String dataType;
    private static String dataTime;
    private static Boolean dataState;
    private static int countday;
    private static Button setWeekButton;
    private static TextView setWeekText;
    private static int place;


    private static ImageButton btnTimePicker1;
    private static ImageButton btnTimePicker2;
    private static ImageButton btnTimePicker3;
    private static ImageButton btnTimePicker4;
    private static ImageButton btnTimePicker5;
    private static ImageButton btnTimePicker6;
    private static ImageButton btnTimePicker7;
    private static ImageButton btnTimePicker8;
    private static ImageButton btnTimePicker9;
    private static ImageButton btnTimePicker10;


    private static TextView clocktemp;
    private static TextView clocktemp2;
    private static TextView clocktemp3;
    private static TextView clocktemp4;
    private static TextView clocktemp5;
    private static TextView clocktemp6;
    private static TextView clocktemp7;
    private static TextView clocktemp8;
    private static TextView clocktemp9;
    private static TextView clocktemp10;


    private static Spinner clocktype;
    private static Spinner clocktype2;
    private static Spinner clocktype3;
    private static Spinner clocktype4;
    private static Spinner clocktype5;
    private static Spinner clocktype6;
    private static Spinner clocktype7;
    private static Spinner clocktype8;
    private static Spinner clocktype9;
    private static Spinner clocktype10;

    private static String dayNightTemp;
    private static String dayNightTemp2;
    private static String dayNightTemp3;
    private static String dayNightTemp4;
    private static String dayNightTemp5;
    private static String dayNightTemp6;
    private static String dayNightTemp7;
    private static String dayNightTemp8;
    private static String dayNightTemp9;
    private static String dayNightTemp10;

    private static String getTime1;
    private static String getTime2;
    private static String getTime3;
    private static String getTime4;
    private static String getTime5;
    private static String getTime6;
    private static String getTime7;
    private static String getTime8;
    private static String getTime9;
    private static String getTime10;

    private static String time1;
    private static String time2;
    private static String time3;
    private static String time4;
    private static String time5;
    private static String time6;
    private static String time7;
    private static String time8;
    private static String time9;
    private static String time10;

    private static String time1o;
    private static String time2o;
    private static String time3o;
    private static String time4o;
    private static String time5o;
    private static String time6o;
    private static String time7o;
    private static String time8o;
    private static String time9o;
    private static String time10o;

    private static String getType1;
    private static String getType2;
    private static String getType3;
    private static String getType4;
    private static String getType5;
    private static String getType6;
    private static String getType7;
    private static String getType8;
    private static String getType9;
    private static String getType10;

    private static String item1;
    private static String item2;
    private static String item3;
    private static String item4;
    private static String item5;
    private static String item6;
    private static String item7;
    private static String item8;
    private static String item9;
    private static String item10;

    private static String item1o;
    private static String item2o;
    private static String item3o;
    private static String item4o;
    private static String item5o;
    private static String item6o;
    private static String item7o;
    private static String item8o;
    private static String item9o;
    private static String item10o;

    private static Boolean getState1;
    private static Boolean getState2;
    private static Boolean getState3;
    private static Boolean getState4;
    private static Boolean getState5;
    private static Boolean getState6;
    private static Boolean getState7;
    private static Boolean getState8;
    private static Boolean getState9;
    private static Boolean getState10;

    private static Boolean bool1;
    private static Boolean bool2;
    private static Boolean bool3;
    private static Boolean bool4;
    private static Boolean bool5;
    private static Boolean bool6;
    private static Boolean bool7;
    private static Boolean bool8;
    private static Boolean bool9;
    private static Boolean bool10;

    private static Boolean bool1o;
    private static Boolean bool2o;
    private static Boolean bool3o;
    private static Boolean bool4o;
    private static Boolean bool5o;
    private static Boolean bool6o;
    private static Boolean bool7o;
    private static Boolean bool8o;
    private static Boolean bool9o;
    private static Boolean bool10o;

    private static CheckBox chk1;
    private static CheckBox chk2;
    private static CheckBox chk3;
    private static CheckBox chk4;
    private static CheckBox chk5;
    private static CheckBox chk6;
    private static CheckBox chk7;
    private static CheckBox chk8;
    private static CheckBox chk9;
    private static CheckBox chk10;

    private static RelativeLayout background0;
    private static RelativeLayout background1;
    private static RelativeLayout background2;
    private static RelativeLayout background3;
    private static RelativeLayout background4;
    private static RelativeLayout background5;
    private static RelativeLayout background6;
    private static RelativeLayout background7;
    private static RelativeLayout background8;
    private static RelativeLayout background9;
    private static RelativeLayout background10;

    private static TextView text11;
    private static TextView text12;
    private static TextView text13;
    private static TextView text14;
    private static TextView text15;
    private static TextView text16;
    private static TextView text17;
    private static TextView text18;
    private static TextView text19;
    private static TextView text110;

    private static TextView text21;
    private static TextView text22;
    private static TextView text23;
    private static TextView text24;
    private static TextView text25;
    private static TextView text26;
    private static TextView text27;
    private static TextView text28;
    private static TextView text29;
    private static TextView text210;

    private static String getCurrentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Week Schedule");

       HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/25";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        setWeekButton = (Button)findViewById(R.id.setWeekSchedule);
        setWeekText = (TextView) findViewById(R.id.setWeekText);


        btnTimePicker1 =(ImageButton)findViewById(R.id.time_button);
        btnTimePicker1.setOnClickListener(this);
        btnTimePicker2 =(ImageButton)findViewById(R.id.time_button2);
        btnTimePicker2.setOnClickListener(this);
        btnTimePicker3 =(ImageButton)findViewById(R.id.time_button3);
        btnTimePicker3.setOnClickListener(this);
        btnTimePicker4 =(ImageButton)findViewById(R.id.time_button4);
        btnTimePicker4.setOnClickListener(this);
        btnTimePicker5 =(ImageButton)findViewById(R.id.time_button5);
        btnTimePicker5.setOnClickListener(this);
        btnTimePicker6 =(ImageButton)findViewById(R.id.time_button6);
        btnTimePicker6.setOnClickListener(this);
        btnTimePicker7 =(ImageButton)findViewById(R.id.time_button7);
        btnTimePicker7.setOnClickListener(this);
        btnTimePicker8 =(ImageButton)findViewById(R.id.time_button8);
        btnTimePicker8.setOnClickListener(this);
        btnTimePicker9 =(ImageButton)findViewById(R.id.time_button9);
        btnTimePicker9.setOnClickListener(this);
        btnTimePicker10 =(ImageButton)findViewById(R.id.time_button10);
        btnTimePicker10.setOnClickListener(this);

        clocktemp = (TextView)findViewById(R.id.clocktemp);
        clocktemp2 = (TextView)findViewById(R.id.clocktemp2);
        clocktemp3 = (TextView)findViewById(R.id.clocktemp3);
        clocktemp4 = (TextView)findViewById(R.id.clocktemp4);
        clocktemp5 = (TextView)findViewById(R.id.clocktemp5);
        clocktemp6 = (TextView)findViewById(R.id.clocktemp6);
        clocktemp7 = (TextView)findViewById(R.id.clocktemp7);
        clocktemp8 = (TextView)findViewById(R.id.clocktemp8);
        clocktemp9 = (TextView)findViewById(R.id.clocktemp9);
        clocktemp10 = (TextView)findViewById(R.id.clocktemp10);

        clocktype = (Spinner)findViewById(R.id.spinnerTemp);
        clocktype2 = (Spinner)findViewById(R.id.spinnerTemp2);
        clocktype3 = (Spinner)findViewById(R.id.spinnerTemp3);
        clocktype4 = (Spinner)findViewById(R.id.spinnerTemp4);
        clocktype5 = (Spinner)findViewById(R.id.spinnerTemp5);
        clocktype6 = (Spinner)findViewById(R.id.spinnerTemp6);
        clocktype7 = (Spinner)findViewById(R.id.spinnerTemp7);
        clocktype8 = (Spinner)findViewById(R.id.spinnerTemp8);
        clocktype9 = (Spinner)findViewById(R.id.spinnerTemp9);
        clocktype10 = (Spinner)findViewById(R.id.spinnerTemp10);

        background0 = (RelativeLayout)findViewById(R.id.background0);
        background1 = (RelativeLayout)findViewById(R.id.background1);
        background2 = (RelativeLayout)findViewById(R.id.background2);
        background3 = (RelativeLayout)findViewById(R.id.background3);
        background4 = (RelativeLayout)findViewById(R.id.background4);
        background5 = (RelativeLayout)findViewById(R.id.background5);
        background6 = (RelativeLayout)findViewById(R.id.background6);
        background7 = (RelativeLayout)findViewById(R.id.background7);
        background8 = (RelativeLayout)findViewById(R.id.background8);
        background9 = (RelativeLayout)findViewById(R.id.background9);
        background10 = (RelativeLayout)findViewById(R.id.background10);

        text11 = (TextView)findViewById(R.id.text1);
        text12 = (TextView)findViewById(R.id.text12);
        text13 = (TextView)findViewById(R.id.text13);
        text14 = (TextView)findViewById(R.id.text14);
        text15 = (TextView)findViewById(R.id.text15);
        text16 = (TextView)findViewById(R.id.text16);
        text17 = (TextView)findViewById(R.id.text17);
        text18 = (TextView)findViewById(R.id.text18);
        text19 = (TextView)findViewById(R.id.text19);
        text110 = (TextView)findViewById(R.id.text110);

        text21 = (TextView)findViewById(R.id.text2);
        text22 = (TextView)findViewById(R.id.text22);
        text23 = (TextView)findViewById(R.id.text23);
        text24 = (TextView)findViewById(R.id.text24);
        text25 = (TextView)findViewById(R.id.text25);
        text26 = (TextView)findViewById(R.id.text26);
        text27 = (TextView)findViewById(R.id.text27);
        text28 = (TextView)findViewById(R.id.text28);
        text29 = (TextView)findViewById(R.id.text29);
        text210 = (TextView)findViewById(R.id.text210);


        chk1 = (CheckBox) findViewById(R.id.chk);
        chk2 = (CheckBox) findViewById(R.id.chk2);
        chk3 = (CheckBox) findViewById(R.id.chk3);
        chk4 = (CheckBox) findViewById(R.id.chk4);
        chk5 = (CheckBox) findViewById(R.id.chk5);
        chk6 = (CheckBox) findViewById(R.id.chk6);
        chk7 = (CheckBox) findViewById(R.id.chk7);
        chk8 = (CheckBox) findViewById(R.id.chk8);
        chk9 = (CheckBox) findViewById(R.id.chk9);
        chk10 = (CheckBox) findViewById(R.id.chk10);

        this.arraySpinner = new String[] {
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        };

        this.arraySpinnerTemp = new String[] {
                "day", "night"
        };


        addListenerOnChk();
        SpinnerStuff();

     }

    public void setValues(){
        bool1 = chk1.isChecked();
        bool2 = chk2.isChecked();
        bool3 = chk3.isChecked();
        bool4 = chk4.isChecked();
        bool5 = chk5.isChecked();
        bool6 = chk6.isChecked();
        bool7 = chk7.isChecked();
        bool8 = chk8.isChecked();
        bool9 = chk9.isChecked();
        bool10 = chk10.isChecked();

        item1 = clocktype.getSelectedItem().toString();
        item2 = clocktype2.getSelectedItem().toString();
        item3 = clocktype3.getSelectedItem().toString();
        item4 = clocktype4.getSelectedItem().toString();
        item5 = clocktype5.getSelectedItem().toString();
        item6 = clocktype6.getSelectedItem().toString();
        item7 = clocktype7.getSelectedItem().toString();
        item8 = clocktype8.getSelectedItem().toString();
        item9 = clocktype9.getSelectedItem().toString();
        item10 = clocktype10.getSelectedItem().toString();


        time1 = clocktemp.getText().toString();
        time2 = clocktemp2.getText().toString();
        time3 = clocktemp3.getText().toString();
        time4 = clocktemp4.getText().toString();
        time5 = clocktemp5.getText().toString();
        time6 = clocktemp6.getText().toString();
        time7 = clocktemp7.getText().toString();
        time8 = clocktemp8.getText().toString();
        time9 = clocktemp9.getText().toString();
        time10 = clocktemp10.getText().toString();
    }

    public void onSetWeekSchedule(View view){
        if (mp_button != null) {
            mp_button.reset();
            mp_button.release();
        }
        mp_button = MediaPlayer.create(this, R.raw.buttontick);

        mp_button.start();
        Dialog dialog = onCreateDialogSingleChoice();
        dialog.show();
    }

    public Dialog onCreateDialogSingleChoice() {
        place = 0;
//Initialize the Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//Source of the data in the DIalog
        CharSequence[] array = {"Set for this day", "Set for workweek (mon-fri)", "Set for whole week"};

// Set the dialog title
        builder.setTitle("Set Schedule")
// Specify the list array, the items to be selected by default (null for none),
// and the listener through which to receive callbacks when items are selected

                .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        place = which;
                    }
                })



// Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
// User clicked OK, so save the result somewhere
// or return them to the component that opened the dialog
                        if (place == 0){
                            thisDay();
                        } else if (place == 1){
                            workWeek();

                        } else if (place == 2){
                            wholeWeek();

                        }


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
    public void workWeek(){
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    WeekProgram wpg = HeatingSystem.getWeekProgram();

                    for (int i = 0; i < 5; i++) {

                        setValues();

                        wpg.data.get(wpg.valid_days[i]).set(0, new Switch(item1, bool1, time1));
                        wpg.data.get(wpg.valid_days[i]).set(1, new Switch(item2, bool2, time2));
                        wpg.data.get(wpg.valid_days[i]).set(2, new Switch(item3, bool3, time3));
                        wpg.data.get(wpg.valid_days[i]).set(3, new Switch(item4, bool4, time4));
                        wpg.data.get(wpg.valid_days[i]).set(4, new Switch(item5, bool5, time5));
                        wpg.data.get(wpg.valid_days[i]).set(5, new Switch(item6, bool6, time6));
                        wpg.data.get(wpg.valid_days[i]).set(6, new Switch(item7, bool7, time7));
                        wpg.data.get(wpg.valid_days[i]).set(7, new Switch(item8, bool8, time8));
                        wpg.data.get(wpg.valid_days[i]).set(8, new Switch(item9, bool9, time9));
                        wpg.data.get(wpg.valid_days[i]).set(9, new Switch(item10, bool10, time10));


                    }

                    wpg.set_durations();


                    HeatingSystem.setWeekProgram(wpg);

                    getStates(weekday);
                    getTimes(weekday);
                    getTypes(weekday);

                    chk1.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(getBaseContext(), "Schedule workweek saved",Toast.LENGTH_LONG);
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

    public void wholeWeek(){
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    WeekProgram wpg = HeatingSystem.getWeekProgram();

                    for (int i = 0; i < wpg.valid_days.length; i++) {

                        setValues();

                        wpg.data.get(wpg.valid_days[i]).set(0, new Switch(item1, bool1, time1));
                        wpg.data.get(wpg.valid_days[i]).set(1, new Switch(item2, bool2, time2));
                        wpg.data.get(wpg.valid_days[i]).set(2, new Switch(item3, bool3, time3));
                        wpg.data.get(wpg.valid_days[i]).set(3, new Switch(item4, bool4, time4));
                        wpg.data.get(wpg.valid_days[i]).set(4, new Switch(item5, bool5, time5));
                        wpg.data.get(wpg.valid_days[i]).set(5, new Switch(item6, bool6, time6));
                        wpg.data.get(wpg.valid_days[i]).set(6, new Switch(item7, bool7, time7));
                        wpg.data.get(wpg.valid_days[i]).set(7, new Switch(item8, bool8, time8));
                        wpg.data.get(wpg.valid_days[i]).set(8, new Switch(item9, bool9, time9));
                        wpg.data.get(wpg.valid_days[i]).set(9, new Switch(item10, bool10, time10));


                    }

                    wpg.set_durations();


                    HeatingSystem.setWeekProgram(wpg);

                    getStates(weekday);
                    getTimes(weekday);
                    getTypes(weekday);

                    chk1.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(getBaseContext(), "Schedule whole week saved",Toast.LENGTH_LONG);
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

    public void thisDay(){
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    WeekProgram wpg = HeatingSystem.getWeekProgram();

                    for (int i = 0; i < wpg.valid_days.length; i++) {

                        if (wpg.valid_days[i].contains(weekday)) {
                            setValues();

                            wpg.data.get(weekday).set(0, new Switch(item1, bool1, time1));
                            wpg.data.get(weekday).set(1, new Switch(item2, bool2, time2));
                            wpg.data.get(weekday).set(2, new Switch(item3, bool3, time3));
                            wpg.data.get(weekday).set(3, new Switch(item4, bool4, time4));
                            wpg.data.get(weekday).set(4, new Switch(item5, bool5, time5));
                            wpg.data.get(weekday).set(5, new Switch(item6, bool6, time6));
                            wpg.data.get(weekday).set(6, new Switch(item7, bool7, time7));
                            wpg.data.get(weekday).set(7, new Switch(item8, bool8, time8));
                            wpg.data.get(weekday).set(8, new Switch(item9, bool9, time9));
                            wpg.data.get(weekday).set(9, new Switch(item10, bool10, time10));


                        } else {
                            for (int j = 0; j < wpg.data.get(wpg.valid_days[i]).size(); j++) {
                                dataTime = wpg.data.get(wpg.valid_days[i]).get(j).getTime();
                                dataType = wpg.data.get(wpg.valid_days[i]).get(j).getType();
                                dataState = wpg.data.get(wpg.valid_days[i]).get(j).getState();

                                if (j == 0) {
                                    bool1o = dataState;
                                    item1o = dataType;
                                    time1o = dataTime;

                                }
                                if (j == 1) {
                                    bool2o = dataState;
                                    item2o = dataType;
                                    time2o = dataTime;

                                }
                                if (j == 2) {
                                    bool3o = dataState;
                                    item3o = dataType;
                                    time3o = dataTime;

                                }
                                if (j == 3) {
                                    bool4o = dataState;
                                    item4o = dataType;
                                    time4o = dataTime;

                                }
                                if (j == 4) {
                                    bool5o = dataState;
                                    item5o = dataType;
                                    time5o = dataTime;

                                }
                                if (j == 5) {
                                    bool6o = dataState;
                                    item6o = dataType;
                                    time6o = dataTime;

                                }
                                if (j == 6) {
                                    bool7o = dataState;
                                    item7o = dataType;
                                    time7o = dataTime;

                                }
                                if (j == 7) {
                                    bool8o = dataState;
                                    item8o = dataType;
                                    time8o = dataTime;

                                }
                                if (j == 8) {
                                    bool9o = dataState;
                                    item9o = dataType;
                                    time9o = dataTime;

                                }
                                if (j == 9) {
                                    bool10o = dataState;
                                    item10o = dataType;
                                    time10o = dataTime;

                                }

                            }


                            wpg.data.get(wpg.valid_days[i]).set(0, new Switch(item1o, bool1o, time1o));
                            wpg.data.get(wpg.valid_days[i]).set(1, new Switch(item2o, bool2o, time2o));
                            wpg.data.get(wpg.valid_days[i]).set(2, new Switch(item3o, bool3o, time3o));
                            wpg.data.get(wpg.valid_days[i]).set(3, new Switch(item4o, bool4o, time4o));
                            wpg.data.get(wpg.valid_days[i]).set(4, new Switch(item5o, bool5o, time5o));
                            wpg.data.get(wpg.valid_days[i]).set(5, new Switch(item6o, bool6o, time6o));
                            wpg.data.get(wpg.valid_days[i]).set(6, new Switch(item7o, bool7o, time7o));
                            wpg.data.get(wpg.valid_days[i]).set(7, new Switch(item8o, bool8o, time8o));
                            wpg.data.get(wpg.valid_days[i]).set(8, new Switch(item9o, bool9o, time9o));
                            wpg.data.get(wpg.valid_days[i]).set(9, new Switch(item10o, bool10o, time10o));


                        }

                    }

                    wpg.set_durations();


                    HeatingSystem.setWeekProgram(wpg);

                    getStates(weekday);
                    getTimes(weekday);
                    getTypes(weekday);

                    chk1.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(getBaseContext(), "Schedule "+weekday+ " saved",Toast.LENGTH_LONG);
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
    public void addListenerOnChk() {


        chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();


                } else {
                    checkChecked();

                }

            }
        });

        chk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();

                } else {
                    checkChecked();

                }

            }
        });

        chk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();


                } else {
                    checkChecked();


                }

            }
        });

        chk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();

                } else {
                    checkChecked();


                }

            }
        });

        chk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();

                } else {
                    checkChecked();

                }

            }
        });

        chk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();

                } else {
                    checkChecked();


                }

            }
        });

        chk7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();

                } else {
                    checkChecked();

                }

            }
        });

        chk8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();

                } else {
                    checkChecked();


                }

            }
        });

        chk9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();

                } else {
                    checkChecked();


                }

            }
        });

        chk10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    checkChecked();
                } else {
                    checkChecked();

                }

            }
        });

    }

    public void checkChecked(){

        if (chk1.isChecked()) {
            background1.setAlpha(1f);
            clocktype.setEnabled(true);
            btnTimePicker1.setEnabled(true);

        } else if (chk1.isChecked() == false){
            background1.setAlpha(.3f);
           /* btnTimePicker1.setEnabled(false);
            clocktype.setEnabled(false);
            clocktype.getSelectedView().setEnabled(true);*/
        }
        if (chk2.isChecked()) {
            background2.setAlpha(1f);
            clocktype2.setEnabled(true);
            btnTimePicker2.setEnabled(true);
        } else if (chk2.isChecked() == false) {
            background2.setAlpha(.3f);
           /* btnTimePicker2.setEnabled(false);
            clocktype2.setEnabled(false);
            clocktype2.getSelectedView().setEnabled(true);*/


        }
        if (chk3.isChecked()) {
            background3.setAlpha(1f);
            clocktype3.setEnabled(true);
            btnTimePicker3.setEnabled(true);
        } else if (chk3.isChecked() == false) {
            background3.setAlpha(.3f);
           /* btnTimePicker3.setEnabled(false);
            clocktype3.setEnabled(false);
            clocktype3.getSelectedView().setEnabled(true);   */

        }
        if (chk4.isChecked()) {
            background4.setAlpha(1f);
            clocktype4.setEnabled(true);
            btnTimePicker4.setEnabled(true);
        } else if (chk4.isChecked() == false) {
            background4.setAlpha(.3f);
            /*clocktype4.setEnabled(false);
            btnTimePicker4.setEnabled(false);
            clocktype4.getSelectedView().setEnabled(true);*/

        }
        if (chk5.isChecked()) {
            background5.setAlpha(1f);
            clocktype5.setEnabled(true);
            btnTimePicker5.setEnabled(true);
        } else if (chk5.isChecked() == false) {
            background5.setAlpha(.3f);
           /* clocktype5.setEnabled(false);
            btnTimePicker5.setEnabled(false);
            clocktype5.getSelectedView().setEnabled(true);*/

        }
        if (chk6.isChecked()) {
            background6.setAlpha(1f);
            clocktype6.setEnabled(true);
            btnTimePicker6.setEnabled(true);
        } else if (chk6.isChecked() == false) {
            background6.setAlpha(.3f);
           /* clocktype6.setEnabled(false);
            btnTimePicker6.setEnabled(false);
            clocktype6.getSelectedView().setEnabled(true);*/


        }
        if (chk7.isChecked()) {
            background7.setAlpha(1f);
            clocktype7.setEnabled(true);
            btnTimePicker7.setEnabled(true);
        } else if (chk7.isChecked() == false) {
            background7.setAlpha(.3f);
           /* clocktype7.setEnabled(false);
            btnTimePicker7.setEnabled(false);
            clocktype7.getSelectedView().setEnabled(true);*/


        }
        if (chk8.isChecked()) {
            background8.setAlpha(1f);
            clocktype8.setEnabled(true);
            btnTimePicker8.setEnabled(true);
        } else if (chk8.isChecked() == false) {
            background8.setAlpha(.3f);
           /* clocktype8.setEnabled(false);
            btnTimePicker8.setEnabled(false);
            clocktype8.getSelectedView().setEnabled(true);*/


        }
        if (chk9.isChecked()) {
            background9.setAlpha(1f);
            clocktype9.setEnabled(true);
            btnTimePicker9.setEnabled(true);
        } else if (chk9.isChecked() == false) {
            background9.setAlpha(.3f);
          /*  clocktype9.setEnabled(false);
            btnTimePicker9.setEnabled(false);
            clocktype9.getSelectedView().setEnabled(true);*/


        }
        if (chk10.isChecked()) {
            background10.setAlpha(1f);
            clocktype10.setEnabled(true);
            btnTimePicker10.setEnabled(true);
        } else if (chk10.isChecked() == false) {
            background10.setAlpha(.3f);
           /* clocktype10.setEnabled(false);
            btnTimePicker10.setEnabled(false);
            clocktype10.getSelectedView().setEnabled(true);*/


        }
    }

    public void getStates(String w){
        final String thisDay = w;
        new Thread(new Runnable() {
            @Override
            public void run() {
                getState1 = false;
                getState2= false;
                getState3 = false;
                getState4 = false;
                getState5 = false;
                getState6 = false;
                getState7 = false;
                getState8 = false;
                getState9 = false;
                getState10 = false;

                try {
                    WeekProgram wpg = HeatingSystem.getWeekProgram();

                    for (int j = 0; j <= wpg.data.get(thisDay).size() - 1; j++) {

                        switch (j){
                            case 0:
                                getState1 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 1:
                                getState2 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 2:
                                getState3 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 3:
                                getState4 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 4:
                                getState5 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 5:
                                getState6 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 6:
                                getState7 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 7:
                                getState8 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 8:
                                getState9 = wpg.data.get(thisDay).get(j).getState();
                                break;
                            case 9:
                                getState10 = wpg.data.get(thisDay).get(j).getState();
                                break;
                        }

                    }

                    chk1.post(new Runnable() {
                        @Override
                        public void run() {
                            chk1.setChecked(getState1);
                            chk2.setChecked(getState2);
                            chk3.setChecked(getState3);
                            chk4.setChecked(getState4);
                            chk5.setChecked(getState5);
                            chk6.setChecked(getState6);
                            chk7.setChecked(getState7);
                            chk8.setChecked(getState8);
                            chk9.setChecked(getState9);
                            chk10.setChecked(getState10);

                            checkChecked();

                            }

                    });



                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();

    }

    public void getTypes(String w){
        final String thisDay = w;
        new Thread(new Runnable() {
            @Override
            public void run() {
                getType1 = "...";
                getType2= "...";
                getType3 = "...";
                getType4 = "...";
                getType5 = "...";
                getType6 = "...";
                getType7 = "...";
                getType8 = "...";
                getType9 = "...";
                getType10 = "...";

                try {
                    WeekProgram wpg = HeatingSystem.getWeekProgram();

                    for (int j = 0; j <= wpg.data.get(thisDay).size() - 1; j++) {

                        switch (j){
                            case 0:
                                getType1 = wpg.data.get(thisDay).get(j).getType();
                            case 1:
                                getType2 = wpg.data.get(thisDay).get(j).getType();
                            case 2:
                                getType3 = wpg.data.get(thisDay).get(j).getType();
                            case 3:
                                getType4 = wpg.data.get(thisDay).get(j).getType();
                            case 4:
                                getType5 = wpg.data.get(thisDay).get(j).getType();
                            case 5:
                                getType6 = wpg.data.get(thisDay).get(j).getType();
                            case 6:
                                getType7 = wpg.data.get(thisDay).get(j).getType();
                            case 7:
                                getType8 = wpg.data.get(thisDay).get(j).getType();
                            case 8:
                                getType9 = wpg.data.get(thisDay).get(j).getType();
                            case 9:
                                getType10 = wpg.data.get(thisDay).get(j).getType();
                        }

                    }

                    clocktype.post(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < clocktype.getCount(); i++) {
                                if (clocktype.getItemAtPosition(i).equals(getType1)) {
                                    clocktype.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype2.getCount(); i++) {
                                if (clocktype2.getItemAtPosition(i).equals(getType2)) {
                                    clocktype2.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype3.getCount(); i++) {
                                if (clocktype3.getItemAtPosition(i).equals(getType3)) {
                                    clocktype3.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype4.getCount(); i++) {
                                if (clocktype4.getItemAtPosition(i).equals(getType4)) {
                                    clocktype4.setSelection(i);
                                }

                            }
                            for (int i = 0; i < clocktype5.getCount(); i++) {
                                if (clocktype5.getItemAtPosition(i).equals(getType5)) {
                                    clocktype5.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype6.getCount(); i++) {
                                if (clocktype6.getItemAtPosition(i).equals(getType6)) {
                                    clocktype6.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype7.getCount(); i++) {
                                if (clocktype7.getItemAtPosition(i).equals(getType7)) {
                                    clocktype7.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype8.getCount(); i++) {
                                if (clocktype8.getItemAtPosition(i).equals(getType8)) {
                                    clocktype8.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype9.getCount(); i++) {
                                if (clocktype9.getItemAtPosition(i).equals(getType9)) {
                                    clocktype9.setSelection(i);

                                }

                            }
                            for (int i = 0; i < clocktype10.getCount(); i++) {
                                if (clocktype10.getItemAtPosition(i).equals(getType10)) {
                                    clocktype10.setSelection(i);

                                }

                            }
                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);
                }
            }
        }).start();

    }

    public void countTypes(){
        dayNightTemp = clocktype.getSelectedItem().toString();
        dayNightTemp2 = clocktype2.getSelectedItem().toString();
        dayNightTemp3 = clocktype3.getSelectedItem().toString();
        dayNightTemp4 = clocktype4.getSelectedItem().toString();
        dayNightTemp5 = clocktype5.getSelectedItem().toString();
        dayNightTemp6 = clocktype6.getSelectedItem().toString();
        dayNightTemp7 = clocktype7.getSelectedItem().toString();
        dayNightTemp8 = clocktype8.getSelectedItem().toString();
        dayNightTemp9 = clocktype9.getSelectedItem().toString();
        dayNightTemp10 = clocktype10.getSelectedItem().toString();
        countday = 0;

        if (dayNightTemp.equals("day")) {
            countday++;
        }
        if (dayNightTemp2.equals("day")) {
            countday++;

        }
        if (dayNightTemp3.equals("day")) {
            countday++;

        }
        if (dayNightTemp4.equals("day")) {
            countday++;

        }
        if (dayNightTemp5.equals("day")) {
            countday++;

        }
        if (dayNightTemp6.equals("day")) {
            countday++;

        }
        if (dayNightTemp7.equals("day")) {
            countday++;

        }
        if (dayNightTemp8.equals("day")) {
            countday++;

        }
        if (dayNightTemp9.equals("day")) {
            countday++;

        }
        if (dayNightTemp10.equals("day")) {
            countday++;

        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {


                    setWeekText.post(new Runnable() {
                        @Override
                        public void run() {
                            if (countday != 5) {
                                setWeekButton.setAlpha(0.1f);
                                setWeekButton.setEnabled(false);
                                setWeekText.setVisibility(View.VISIBLE);
                                setWeekText.bringToFront();
                                setWeekText.setText(Html.fromHtml("You need 5x 'day' and 6x 'night'" + "<br>"
                                        + "You have " + countday + "x 'day' and " + (11 - countday) + "x 'night'"));

                            } else {
                                setWeekButton.setAlpha(1f);
                                setWeekButton.bringToFront();
                                setWeekText.setVisibility(View.INVISIBLE);
                                setWeekButton.setEnabled(true);

                            }
                        }
                    });
                } catch (Exception e) {
                        System.err.println("Error from getdata "+e);
                    }
                }
            }).start();

    }


    public void getTimes(String w){
        final String thisDay = w;
        new Thread(new Runnable() {
            @Override
            public void run() {
                getTime1 = "...";
                getTime2= "...";
                getTime3 = "...";
                getTime4 = "...";
                getTime5 = "...";
                getTime6 = "...";
                getTime7 = "...";
                getTime8 = "...";
                getTime9 = "...";
                getTime10 = "...";

                try {
                    WeekProgram wpg = HeatingSystem.getWeekProgram();

                    for (int j = 0; j <= wpg.data.get(thisDay).size() - 1; j++) {

                        switch (j){
                            case 0:
                                getTime1 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 1:
                                getTime2 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 2:
                                getTime3 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 3:
                                getTime4 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 4:
                                getTime5 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 5:
                                getTime6 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 6:
                                getTime7 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 7:
                                getTime8 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 8:
                                getTime9 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                            case 9:
                                getTime10 = wpg.data.get(thisDay).get(j).getTime();
                                break;
                        }

                    }
                    clocktemp.post(new Runnable() {
                        @Override
                        public void run() {
                            clocktemp.setText(getTime1);
                            clocktemp2.setText(getTime2);
                            clocktemp3.setText(getTime3);
                            clocktemp4.setText(getTime4);
                            clocktemp5.setText(getTime5);
                            clocktemp6.setText(getTime6);
                            clocktemp7.setText(getTime7);
                            clocktemp8.setText(getTime8);
                            clocktemp9.setText(getTime9);
                            clocktemp10.setText(getTime10);

                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);
                }
            }
        }).start();

    }

    public int getHourTime (String t){
        String[] sep = t.split(":");
        return Integer.parseInt(sep[0].toString());
    }

    public int getMinTime (String t){
        String[] sep = t.split(":");
        return Integer.parseInt(sep[1].toString());
    }

    @Override
    public void onClick(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // Get Current Time
                    final int hourTime = getHourTime(HeatingSystem.get("time"));
                    final int minTime = getMinTime(HeatingSystem.get("time"));

                    mHour = hourTime;
                    mMinute = minTime;
                } catch (Exception e) {
                    System.err.println("Error from getdata "+e);
                }
            }
        }).start();
        // Launch Time Picker Dialog
        if (v == btnTimePicker1) {
            mHour = getHourTime(clocktemp.getText().toString());
            mMinute = getMinTime(clocktemp.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp.setText(sHour + ":" + sMinute);
                                    clocktemp.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();
        } else if (v == btnTimePicker2){
            mHour = getHourTime(clocktemp2.getText().toString());
            mMinute = getMinTime(clocktemp2.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10) {
                                sHour = "0" + Integer.toString(hourOfDay);
                            } else {
                                sHour = Integer.toString(hourOfDay);
                            }
                            if (minute < 10) {
                                sMinute = "0" + Integer.toString(minute);
                            } else {
                                sMinute = Integer.toString(minute);
                            }
                            clocktemp2.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp2.setText(sHour + ":" + sMinute);
                                    clocktemp2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker3){
            mHour = getHourTime(clocktemp3.getText().toString());
            mMinute = getMinTime(clocktemp3.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp3.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp3.setText(sHour + ":" + sMinute);
                                    clocktemp3.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });

                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker4){
            mHour = getHourTime(clocktemp4.getText().toString());
            mMinute = getMinTime(clocktemp4.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp4.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp4.setText(sHour + ":" + sMinute);
                                    clocktemp4.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker5){
            mHour = getHourTime(clocktemp5.getText().toString());
            mMinute = getMinTime(clocktemp5.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp5.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp5.setText(sHour + ":" + sMinute);
                                    clocktemp5.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker6){
            mHour = getHourTime(clocktemp6.getText().toString());
            mMinute = getMinTime(clocktemp6.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp6.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp6.setText(sHour + ":" + sMinute);
                                    clocktemp6.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker7){
            mHour = getHourTime(clocktemp7.getText().toString());
            mMinute = getMinTime(clocktemp7.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp7.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp7.setText(sHour + ":" + sMinute);
                                    clocktemp7.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker8){
            mHour = getHourTime(clocktemp8.getText().toString());
            mMinute = getMinTime(clocktemp8.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp8.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp8.setText(sHour + ":" + sMinute);
                                    clocktemp8.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker9){
            mHour = getHourTime(clocktemp9.getText().toString());
            mMinute = getMinTime(clocktemp9.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp9.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp9.setText(sHour + ":" + sMinute);
                                    clocktemp9.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        } else if (v == btnTimePicker10){
            mHour = getHourTime(clocktemp10.getText().toString());
            mMinute = getMinTime(clocktemp10.getText().toString());
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            final String sHour;
                            final String sMinute;
                            if (hourOfDay < 10){sHour = "0"+Integer.toString(hourOfDay);
                            } else {sHour = Integer.toString(hourOfDay); }
                            if (minute < 10){sMinute = "0"+Integer.toString(minute);
                            } else {sMinute = Integer.toString(minute);}
                            clocktemp10.post(new Runnable() {
                                @Override
                                public void run() {
                                    clocktemp10.setText(sHour + ":" + sMinute);
                                    clocktemp10.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkChecked();
                                        }
                                    });
                                }
                            });
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", timePickerDialog);
            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", timePickerDialog);
            timePickerDialog.show();

        }

    }

    public void SpinnerStuff(){

        s = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_main, arraySpinner);
        s.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.custom_spinner);


        ArrayAdapter<String> adapterTemp = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype.setAdapter(adapterTemp);
        adapterTemp.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype2.setAdapter(adapterTemp2);
        adapterTemp2.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp3 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype3.setAdapter(adapterTemp3);
        adapterTemp3.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp4 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype4.setAdapter(adapterTemp4);
        adapterTemp4.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp5 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype5.setAdapter(adapterTemp5);
        adapterTemp5.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp6 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype6.setAdapter(adapterTemp6);
        adapterTemp6.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp7 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype7.setAdapter(adapterTemp7);
        adapterTemp7.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp8 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype8.setAdapter(adapterTemp8);
        adapterTemp8.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp9 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype9.setAdapter(adapterTemp9);
        adapterTemp9.setDropDownViewResource(R.layout.custom_spinner);

        ArrayAdapter<String> adapterTemp10 = new ArrayAdapter<String>(this, R.layout.custom_spinner_temp, arraySpinnerTemp);
        clocktype10.setAdapter(adapterTemp10);
        adapterTemp10.setDropDownViewResource(R.layout.custom_spinner);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                weekday = s.getSelectedItem().toString();
                getTimes(weekday);
                getTypes(weekday);
                getStates(weekday);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        clocktype10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                countTypes();
                checkChecked();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

}

