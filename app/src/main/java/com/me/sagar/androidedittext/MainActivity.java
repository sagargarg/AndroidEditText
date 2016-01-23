package com.me.sagar.androidedittext;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static EditText value1;
    public static EditText value2;
    public static double value1var;
    public static double value2var;
    public static DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NumberFormat nf = NumberFormat.getInstance();
        char decSeparator = '.';
        if(nf instanceof DecimalFormat) {
            DecimalFormatSymbols sym = ((DecimalFormat) nf).getDecimalFormatSymbols();
            decSeparator = sym.getDecimalSeparator();
        }

        if (decSeparator == ',') {
            df = new DecimalFormat("0.#####", new DecimalFormatSymbols(Locale.FRANCE));
        }
        else {
            df = new DecimalFormat("0.#####", new DecimalFormatSymbols(Locale.US));
        }

        value1 = (EditText)findViewById(R.id.value1);
        value2 = (EditText)findViewById(R.id.value2);

        value1.setText(df.format(1.0));
        value2.setText(df.format(1000));

        value1var = Double.parseDouble(value1.getText().toString());
        value2var = Double.parseDouble(value2.getText().toString());

        value1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    //value1.setText(df.format("0"));
                    value2.setText(df.format("0"));
                    return;
                }
                try {
                    double val1 = df.parse(s.toString()).doubleValue();
                    double val2 = df.parse(value2.getText().toString()).doubleValue();

                    if (val1 != val2) {
                        value2.setText(df.format(val1));
                    }
                } catch(ParseException e) {

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
            }
        });

        value2.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    value1.setText(df.format("0"));
                    //value2.setText(df.format("0"));
                    return;
                }

                try {
                    double val1 = df.parse(value1.getText().toString()).doubleValue();
                    double val2 = df.parse(editable.toString()).doubleValue();
                    if (val1 != val2) {
                        value1.setText(df.format(val2));
                    }
                } catch(ParseException e) {

                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {



            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
