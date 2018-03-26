package nl.tue.demothermostat;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_current) {
            Intent myIntent = new Intent(MainActivity.this, ThermostatActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_change) {
            Intent myIntent2 = new Intent(MainActivity.this, ThermostatActivity2.class);
            startActivity(myIntent2);
        } else if (id == R.id.nav_day) {
            Intent myIntent3 = new Intent(MainActivity.this, DayActivity.class);
            startActivity(myIntent3);
        } else if (id == R.id.nav_night) {
            Intent myIntent4 = new Intent(MainActivity.this, NightActivity.class);
            startActivity(myIntent4);
        } else if (id == R.id.nav_week) {
            Intent myIntent5 = new Intent(MainActivity.this, WeekActivity.class);
            startActivity(myIntent5);
        } else if (id == R.id.nav_help) {
            Intent myIntent6 = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(myIntent6);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

