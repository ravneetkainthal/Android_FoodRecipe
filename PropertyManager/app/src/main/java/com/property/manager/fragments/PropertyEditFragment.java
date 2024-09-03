package com.property.manager.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.property.manager.R;
import com.property.manager.lab.PropertyLab;
import com.property.manager.model.Property;

import java.util.Date;
import java.util.UUID;

public class PropertyEditFragment extends DialogFragment {

    public static final String ARG_PROPERTY_ID = "property_id";

    public static final String ARG_PROPERTY_NAME = "property_name";

    public static final String ARG_PROPERTY_DESCRIPTION = "property_description";

    public static final String EXTRA_EDIT = "property_edit_dialog_extra";

    private EditText propertyNameText;

    private EditText propertyDescriptionText;

    public PropertyEditFragment() {
        // Required empty public constructor
    }

    public static PropertyEditFragment newInstance(UUID propertyId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROPERTY_ID, propertyId);
        PropertyEditFragment fragment = new PropertyEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        UUID propertyId = (UUID)getArguments().getSerializable(ARG_PROPERTY_ID);
        Property property = PropertyLab.get().getProperty(propertyId);
        View v = getLayoutInflater().inflate(R.layout.fragment_property_edit, null);
        propertyNameText = (EditText) v.findViewById(R.id.property_name);
        propertyDescriptionText = (EditText) v.findViewById(R.id.property_description);
        propertyNameText.setText(property.getPropertyName());
        propertyDescriptionText.setText(property.getPropertyDescription());

        return new AlertDialog.Builder(requireActivity())
                .setView(v)
                .setTitle(R.string.edit_property)
                .setPositiveButton(android.R.string.ok, null)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String propertyName = propertyNameText.getText().toString();
                                String propertyDescription = propertyDescriptionText.getText().toString();
                                sendResult(PropertyDetailsFragment.REQUEST_EDIT, propertyName, propertyDescription);
                            }
                        }).create();

    }

    private void sendResult(String resultCode, String propertyName, String propertyDescription) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PROPERTY_NAME, propertyName);
        bundle.putString(ARG_PROPERTY_DESCRIPTION, propertyDescription);
        getParentFragmentManager().setFragmentResult(resultCode, bundle);
    }
}
