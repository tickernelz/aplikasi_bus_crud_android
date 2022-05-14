package upr.uas.pedro;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import upr.uas.pedro.databinding.ActivityMainBinding;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.User;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        upr.uas.pedro.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create User to DB
        DBHandler db = new DBHandler(this);
        User user = new User();
        user.setName("Pedro");
        user.setUsername("admin");
        user.setPassword("admin");
        db.InsertUser(user);

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_login, R.id.nav_pemesanan, R.id.nav_bus)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    protected void onResume() {
        DBHandler db = new DBHandler(this);
        User user = new User();
        user.setIsLogin(1);
        super.onResume();
        new Handler().postDelayed(() -> {
            LinearLayout linearLayout = findViewById(R.id.nav_header_main);
            TextView textNavName = findViewById(R.id.textNavName);
            TextView textNavUsername = findViewById(R.id.textNavUsername);
            NavigationView navigationView = findViewById(R.id.nav_view);
            Menu menu = navigationView.getMenu();
            MenuItem nav_bus = menu.findItem(R.id.nav_bus);
            if (db.checkIsLogin(user)) {
                nav_bus.setEnabled(true);
                textNavName.setText("Welcome " + db.getName(user));
                textNavUsername.setText(db.getUsername(user));
            } else {
                textNavName.setText("Login dulu ngab");
                textNavUsername.setText("");
            }
            linearLayout.setVisibility(LinearLayout.VISIBLE);
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}