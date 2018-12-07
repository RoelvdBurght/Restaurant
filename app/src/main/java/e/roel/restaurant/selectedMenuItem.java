package e.roel.restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static java.security.AccessController.getContext;

public class selectedMenuItem extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_menu_item);
        context = this;

        // Retrieve the menuItemObject containing the info to be displayed
        Bundle clicked = getIntent().getBundleExtra("selectedMenuItem");
        MenuItem thisItem = (MenuItem) clicked.get("temp");

        // Find the relevant views
        ImageView image = findViewById(R.id.image);
        TextView nameView = findViewById(R.id.name);
        TextView contentView = findViewById(R.id.content);
        TextView priceView = findViewById(R.id.price);
        TextView categoryView = findViewById(R.id.category);

        // Set content from MenuItem to activity
        nameView.setText(thisItem.getName());
        contentView.setText(thisItem.getDescription());
        priceView.setText(thisItem.getPrice());
        categoryView.setText(thisItem.getCategory());

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        contentView.setWidth(width);
        int widthHeight = 300;
        loadImage(image, thisItem.getImageUrl(), widthHeight, widthHeight);
    }

    // Loads an image from given url into given imageView while resizing it
    // according to phone screen size. If no image is present it puts a placeholder image in the
    // view
    private void loadImage(final ImageView view, String url, final int width, final int height) {
        Picasso.with(this)
                .load(url)
                .resize(width, height)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                        Bitmap bMap = BitmapFactory.decodeResource(getResources(),
                                R.drawable.not_availabe);
                        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap,
                                width, height, true);
                        view.setImageBitmap(bMapScaled);
                    }
                });
    }
}
