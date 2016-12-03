package com.example.tanawat.tanawat_resturant;

/**
 * Created by Supanat on 12/2/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Pc on 23/11/2559.
 */
public class ProductAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] iconStrings, titleStrings,priceStrings;

    public ProductAdapter(Context context,
                          String[] iconStrings,
                          String[] titleStrings,
                          String[] priceStrings){
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
        this.priceStrings = priceStrings;
    }
    @Override
    public int getCount() { return iconStrings.length; }

    @Override
    public Object getItem(int i) { return 0; }

    @Override
    public long getItemId(int i) { return 0; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.food_list_viell, viewGroup, false);

        TextView titleTextView =(TextView) view.findViewById(R.id.textView2);
        titleTextView.setText(titleStrings[i]);

        TextView priceTextView  =(TextView) view.findViewById(R.id.textView3);
        priceTextView.setText(priceStrings[i]);

        ImageView iconImageView =(ImageView) view.findViewById(R.id.imageView2);
        Picasso.with(context).load(iconStrings[i]).resize(150,150).into(iconImageView);

        return view;
    }
}//minclass
