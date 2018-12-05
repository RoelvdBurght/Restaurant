package e.roel.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/* Adapter for the menuItems, inflates menu_entry to the listview of menuactivity*/
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    String tag = "MenuItemAdapter";
    private ArrayList<MenuItem> entryList;
    private Context mContext;
    private int viewHeight;
    private int viewWidth;

    // Constructor saving the entrylist containing the MenuItem objects
    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> entryList,
                           int height, int width) {
        super(context, resource, entryList);
        this.entryList = entryList;
        this.mContext = context;
        this.viewHeight = height;
        this.viewWidth = width;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_entry, parent, false);
        }

        // Get the menu item at this position
        MenuItem thisEntry = entryList.get(position);

        // Find the relevant views and set them to the values found in the MenuItem object
        final ImageView foodPic = convertView.findViewById(R.id.foodPic);
        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        LinearLayout linLay = convertView.findViewById(R.id.textLinLayout);
        View bar = convertView.findViewById(R.id.seperationbar);
        name.setText(thisEntry.getName());
        price.setText(thisEntry.getPrice());

        // Resize views according to phone screen dimensions
        ViewGroup.LayoutParams tempParams = linLay.getLayoutParams();
        tempParams.width = viewWidth/4;
        linLay.setLayoutParams(tempParams);
        tempParams = bar.getLayoutParams();
        tempParams.width = viewWidth/3;

        // Resize the individual inflated views to fit the screen nicely
        // Standard items show on screen is 3, if there are less items in this category
        // the items will fill the entire screen.
        int itemsOnScreen;
        if (entryList.size() >= 3) {
            itemsOnScreen = 3;
        }
        else {
            itemsOnScreen = entryList.size();
        }
        ViewGroup.LayoutParams layoutparams = convertView.getLayoutParams();
        layoutparams.height = viewHeight/itemsOnScreen;
        convertView.setLayoutParams(layoutparams);

        // Try getting the image for the menu item, on error set stock image
        // Image width and height are scaled to screen size
        int imageWidthHeight = viewWidth/4;
        loadImage(foodPic, thisEntry.getImageUrl(), imageWidthHeight, imageWidthHeight);
        return convertView;
    }

    // Loads an image from given url into given imageView while resizing it
    // according to phone screen size. If no image is present it puts a placeholder image in the
    // view
    private void loadImage(final ImageView view, String url, final int width, final int height) {
        Picasso.with(mContext)
                .load(url)
                .resize(width, height)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        ;
                    }

                    @Override
                    public void onError() {

                        Bitmap bMap = BitmapFactory.decodeResource(mContext.getResources(),
                                R.drawable.not_availabe);
                        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap,
                                width, height, true);
                        view.setImageBitmap(bMapScaled);
                    }
                });
    }
}
