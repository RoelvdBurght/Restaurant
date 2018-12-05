package e.roel.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class selectedMenuItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_menu_item);

        // Retrieve the menuItemObject containing the info to be displayed
        Bundle clicked = getIntent().getBundleExtra("selectedMenuItem");
        MenuItem thisItem = (MenuItem) clicked.get("temp");

        // Find the relevant views
        ImageView image = findViewById(R.id.image);
        TextView name = findViewById(R.id.name);
        TextView content = findViewById(R.id.content);
        TextView price = findViewById(R.id.price);
        TextView category = findViewById(R.id.category);
        Toast.makeText(this, thisItem.toString(), Toast.LENGTH_LONG).show();
    }
}
