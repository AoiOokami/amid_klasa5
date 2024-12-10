package com.example.async_02;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class AssetItemAdapter extends ArrayAdapter<AssetItem> {

    public AssetItemAdapter(Context context, List<AssetItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        AssetItem item = getItem(position);
        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        text1.setText(item.getProductName() + "\n cena: " + item.getPrice() + " zł");
        text2.setText(item.getDescription());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), activity_add_edit_item.class);
            intent.putExtra("productName", item.getProductName());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("position", position);
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
