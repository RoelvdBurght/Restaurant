package e.roel.restaurant;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback{

    String tag = "MainActivity";
    ListView categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the listview containing the categorys and set color
        categoryList = findViewById(R.id.categoryList);
        categoryList.setBackgroundColor(Color.parseColor("#FFFBE6"));

        // Get categories from server
        CategoriesRequest cats = new CategoriesRequest(this);
        cats.getCategories(this);
    }


    // When the server responds, fill the listview using the adapter
    @Override
    public void gotCategories(ArrayList<String> categories) {

        // Init custom adapter and set it to the listview
        ArrayAdapter<String> arrayAdapter = new CategoryAdapter(this,
                R.layout.category_item, categories, categoryList.getHeight());
        categoryList.setAdapter(arrayAdapter);

        // Start listening for clicks
        CategoryListener categoryListener = new CategoryListener();
        categoryList.setOnItemClickListener(categoryListener);
    }

    // When an error occurs in finding the categorys, display the error in a toast
    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    // Listener class for the category listview, sends us to the corresponding category when clicked
    public class CategoryListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String categoryClicked = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("category", categoryClicked);
            startActivity(intent);
        }
    }
}
