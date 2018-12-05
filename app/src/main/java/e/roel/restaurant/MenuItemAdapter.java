package e.roel.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/* Adapter for the menuItems, inflates menu_entry to the listview of menuactivity*/
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    String tag = "MenuItemAdapter";
    private ArrayList<MenuItem> entryList;

    // Constructor saving the entrylist containing the MenuItem objects
    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> entryList) {
        super(context, resource, entryList);
        this.entryList = entryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_entry, parent, false);
        }

        // Get the menu item at this position
        MenuItem thisEntry = entryList.get(position);

        // Set the text and image views to the values found in the MenuItem object
        ImageView foodPic = convertView.findViewById(R.id.foodPic);
        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);

        name.setText(thisEntry.getName());
        price.setText(thisEntry.getPrice());
        return convertView;
    }
}
