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

public class CategoryAdapter extends ArrayAdapter<String> {

    ArrayList<String> categoryNames;
    //Context mcontext;
    String tag = "CategoryAdapter";
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


        TextView catName = convertView.findViewById(R.id.category);
        catName.setText(categoryNames.get(position));
        ViewGroup.LayoutParams layoutparams = convertView.getLayoutParams();


        layoutparams.height = height/2;
        convertView.setLayoutParams(layoutparams);

        convertView.setBackgroundColor(Color.parseColor("#FFFBE6"));
        return convertView;
    }
}
