package e.roel.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    String tag = "MenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get the clicked category from the intent
        String category = " ";
        Bundle catClicked = getIntent().getExtras();
        if (catClicked != null) {
            category = catClicked.getString("category");
        }

        // Find the menu corresponding to the clicked category
        MenuRequest menu = new MenuRequest(this);
        menu.getMenu(this, category);


    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        Log.d(tag, menuItems.get(0).getDescription());
        ListView menuList = findViewById(R.id.menuList);
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_entry, menuItems);
        menuList.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {

    }
}
