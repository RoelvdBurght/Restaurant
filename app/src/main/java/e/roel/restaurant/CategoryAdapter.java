package e.roel.restaurant;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// Custom adapter for the categorys
public class CategoryAdapter extends ArrayAdapter<String> {

    String tag = "CategoryAdapter";
    private ArrayList<String> categoryNames;
    private int height;

    public CategoryAdapter(Context context, int resource, ArrayList<String> cNames, int appHeight) {
        super(context, resource, cNames);
        this.categoryNames = cNames;
        this.height = appHeight;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_item, parent, false);
        }

        // Set the category text
        TextView catName = convertView.findViewById(R.id.category);
        catName.setText(categoryNames.get(position));


        // Because we know there are only 2 categorys we will set their height to be half the screen
        ViewGroup.LayoutParams layoutparams = convertView.getLayoutParams();
        int catsOnScreen = 2;
        layoutparams.height = height/catsOnScreen;
        convertView.setLayoutParams(layoutparams);

        // Set the color of the list items
        convertView.setBackgroundColor(Color.parseColor("#FFFBE6"));
        return convertView;
    }
}
