package www.fulldnnsolution.com.samedrawerallactivity_fragments;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import www.fulldnnsolution.com.samedrawerallactivity_fragments.fragments.WeatherFragment;
import www.fulldnnsolution.com.samedrawerallactivity_fragments.ui.DialogLogin;
import www.fulldnnsolution.com.samedrawerallactivity_fragments.ui.MessageFragment;
import www.fulldnnsolution.com.samedrawerallactivity_fragments.ui.ProfileFragment;
import www.fulldnnsolution.com.samedrawerallactivity_fragments.ui.UploadFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DialogLogin.DialogLoginListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));




        // To Open This Fragment When the app Run

        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MessageFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_message);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_message:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessageFragment()).commit();
                break;

            case R.id.nav_import:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UploadFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;

            case R.id.nav_weather:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WeatherFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_signin:

                /**
                 *  For Showing Overlay Dialog Box
                 */
                    openDialog();

                break;


            case R.id.nav_signput:
                Toast.makeText(this, "Signed Out!", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }


    /**
     *   Open Dialog Box Method
     */

    public void openDialog(){
        DialogLogin dialogLogin = new DialogLogin();
        dialogLogin.show(getSupportFragmentManager(), "dialog login");

    }


    @Override
    public void applyTexts(String username, String password) {

        Log.e("UserName: ", username);
        Log.e("Pass: ", password);
    }
}
