package e.roel.restaurant;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    String tag = "CatRequest";
    private Context context;
    private Callback caller;
    private static final String URL = "https://resto.mprog.nl/categories";

    // Constructor saving the context
    public CategoriesRequest(Context c) {
        this.context = c;
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Sends the error back to the calling activity
    @Override
    public void onErrorResponse(VolleyError error) {
        caller.gotCategoriesError(error.getMessage());
    }

    // On response, parses the categorys from the json object and returs these to the calling
    // activity. If the JSON object is empty it returns this to the error handling method.
    @Override
    public void onResponse(JSONObject response) {
        ArrayList<String> categories = new ArrayList<String>();
        JSONArray jsonCategories;

        try {
            jsonCategories = response.getJSONArray("categories");

            int catLength = jsonCategories.length();
            for (int i = 0; i < catLength; i++) {
                categories.add(jsonCategories.getString(i));
            }

            caller.gotCategories(categories);
        }
        catch(Exception e) {
            caller.gotCategoriesError(e.getMessage());
        }
    }

    // Calls the server to give the categorys, adds a request to the queue
    void getCategories(Callback activity) {
        caller = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, this,this);
        queue.add(jsonObjectRequest);
    }
}
