package e.roel.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    String tag = "MenuRequest";
    private Context context;
    private Callback caller;
    private String urlBase = "https://resto.mprog.nl/menu?category=";

    public MenuRequest(Context c) {
        this.context = c;
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> categories);
        void gotMenuError(String message);
    }


    // Send error to the user if loading from server fails
    @Override
    public void onErrorResponse(VolleyError error) {
        caller.gotMenuError(error.getMessage());
    }

    // When the server responds, parse the JSON object and transfer the data into MenuItem objects
    // If errors occur while parsing send a message to the user
    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonMenu;
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();

        try {
            jsonMenu = response.getJSONArray("items");

            int menuLength = jsonMenu.length();
            for (int i = 0; i < menuLength; i++) {
                JSONObject menuEntry = (JSONObject) jsonMenu.get(i);
                String name = menuEntry.getString("name");
                String description = menuEntry.getString("description");
                String imageUrl = menuEntry.getString("image_url");
                String price = menuEntry.getString("price");
                String category = menuEntry.getString("category");
                MenuItem menuItem = new MenuItem(name, description, imageUrl, price, category);
                menu.add(menuItem);
            }

            caller.gotMenu(menu);
        }
        catch(Exception e) {
            caller.gotMenuError(e.getMessage());
        }
    }

    // Get the menu items of this category
    // Makes a call to the server
    void getMenu(Callback activity, String category) {
        caller = activity;
        String url = urlBase + category;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this,this);
        queue.add(jsonObjectRequest);
    }

}
