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

    public CategoriesRequest(Context c) {
        this.context = c;
    }

    public interface Callback {

        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

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

            Log.d(tag, "except");
            Log.d(tag, e.getMessage());

            caller.gotCategoriesError(e.getMessage());
        }
    }

    void getCategories(Callback activity) {
        Log.d(tag, "getCategories");
        caller = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, this,this);
        queue.add(jsonObjectRequest);
    }
}
