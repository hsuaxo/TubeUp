package com.hsuaxo.tubeup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import static android.content.DialogInterface.OnClickListener;

public class OptionsDialog extends AlertDialog.Builder {

    private OptionsDialog(@NonNull Context context, String title, CharSequence[] options, OnClickListener callback) {
        super(context);
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.alert_dialog_title, null);
        final TextView text = view.findViewById(R.id.text_view_title);
        text.setText(title);
        setCustomTitle(view);
        setItems(options, callback);
    }

    private OptionsDialog(@NonNull Context context, String title, ListAdapter adapter, OnClickListener callback) {
        super(context);
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.alert_dialog_title, null);
        final TextView text = view.findViewById(R.id.text_view_title);
        text.setText(title);
        setCustomTitle(view);
        setAdapter(adapter, callback);
    }

    private OptionsDialog(@NonNull Context context, String title, int options, OnClickListener callback) {
        this(context, title, context.getResources().getStringArray(options), callback);
    }

    public static void show(@NonNull Context context, String title, CharSequence[] options, OnClickListener callback) {
        final OptionsDialog dialog = new OptionsDialog(context, title, options, callback);
        dialog.show();
    }

    public static void show(@NonNull Context context, String title, int options, OnClickListener callback) {
        final OptionsDialog dialog = new OptionsDialog(context, title, options, callback);
        dialog.show();
    }

    public static void show(@NonNull Context context, int title, CharSequence[] options, OnClickListener callback) {
        final OptionsDialog dialog = new OptionsDialog(context, context.getString(title), options, callback);
        dialog.show();
    }

    public static void show(@NonNull Context context, int title, int options, OnClickListener callback) {
        final OptionsDialog dialog = new OptionsDialog(context, context.getString(title), options, callback);
        dialog.show();
    }

    public static void show(@NonNull Context context, String title, ListAdapter adapter, OnClickListener callback) {
        final OptionsDialog dialog = new OptionsDialog(context, title, adapter, callback);
        dialog.show();
    }

    public static void show(@NonNull Context context, int title, ListAdapter adapter, OnClickListener callback) {
        final OptionsDialog dialog = new OptionsDialog(context, context.getString(title), adapter, callback);
        dialog.show();
    }
}

