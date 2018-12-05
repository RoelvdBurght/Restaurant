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
import android.widget.AdapterView;
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
    private ListView menuItemList;
    private ArrayList<MenuItem> menuItemObjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuItemList = findViewById(R.id.menuList);

        // Get the clicked category from the intent
        String category = " ";
        Bundle catClicked = getIntent().getExtras();
        if (catClicked != null) {
            category = catClicked.getString("category");
        }

        // Set the header text to the clicked category
        TextView header = findViewById(R.id.headerMenu);
        header.setText(category);

        // Find the menu corresponding to the clicked category
        MenuRequest menu = new MenuRequest(this);
        menu.getMenu(this, category);

        // Set the onItemListener for the listview
        MenuListener clickListener = new MenuListener();
        menuItemList.setOnItemClickListener(clickListener);

    }

    // When the menu is retrieved from the server, fill the listview using the listadapter
    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        menuItemObjectList = menuItems;
        ListView menuList = findViewById(R.id.menuList);
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_entry,
                menuItems, menuItemList.getHeight(), menuItemList.getWidth());
        menuList.setAdapter(adapter);
    }

    // On error, make toast to display to the user
    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }


    // Listener class for the menu listview, sends us to the corresponding category when clicked
    public class MenuListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //String clicked = parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this, selectedMenuItem.class);
            MenuItem menuItemClicked = menuItemObjectList.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("temp", menuItemClicked);
            intent.putExtra("selectedMenuItem", bundle);
            startActivity(intent);
        }
    }

}
