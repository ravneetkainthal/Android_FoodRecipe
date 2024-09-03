package com.property.manager.recycler_view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.property.manager.MainActivity;
import com.property.manager.R;
import com.property.manager.model.Property;

public class PropertyViewHolder extends RecyclerView.ViewHolder {

    private TextView propertyIdText;

    private TextView propertyNameText;

    private Context context;

    private  Property property;

    public static String EXTRA_PROPERTY_ID = "property_id";

    public PropertyViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        propertyIdText = (TextView) itemView.findViewById(R.id.list_item_property_id_text_view);
        propertyNameText = (TextView) itemView.findViewById(R.id.list_item_property_name_text_view);
        itemView.setOnClickListener(listItemClickListener);
    }

    public void bindProperty (Property property){
        this.property = property;
        propertyIdText.setText(property.getPropertyId().toString());
        propertyNameText.setText(property.getPropertyName());
    }

    private final View.OnClickListener listItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(EXTRA_PROPERTY_ID, property.getPropertyId());
            context.startActivity(intent);
        }
    };
}
