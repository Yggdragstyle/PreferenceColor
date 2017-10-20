package com.example.ygg.personnalisation_fragment_v4;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final HSLColorPicker colorPicker = (HSLColorPicker) findViewById(R.id.colorPickerPikolo);
        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                // Do whatever you want with the color
//                ((View)findViewById(R.id.bgMain)).getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

                getSupportActionBar().setBackgroundDrawable( new ColorDrawable( color ));
                View bg = (View) findViewById(R.id.mainBG);

//                bg.setBackgroundColor( getContrastColor(color) );
//                bg.setBackgroundColor( 0xFFAA22 );

                TextView test_1 = (TextView) findViewById(R.id.test_1);
                TextView test_2 = (TextView) findViewById(R.id.test_2);

                test_1.setText( Integer.toString( color ) );
                test_2.setText( Integer.toString( getContrastColor(color) ) );

//                Log.d("Mes_test", Integer.toString(getContrastColor(color)) );


//                float r = 128 + ( 128 - Color.red(color) );
//                float g = 128 + ( 128 - Color.green(color) );
//                float b = 128 + ( 128 - Color.blue(color) );

//                test_2.setText( Integer.toString( Color.rgb( (int)r, (int)g, (int)b ) ) );
//                test_2.setText( Float.toString( r ) );

//                txt.setTextColor(  );
//                Color c = getContrastColor( Color.valueOf( color ) );
////                Color c = getContrastColor( Color( color ) );
//                Color c = Color.valueOf( color );
//                txt.setText( c.toString() );
            }
        });

    }

    public static int getContrastColor(int color) {

        float r = 128 + ( 128 - Color.red(color) ),
              g = 128 + ( 128 - Color.green(color) ),
              b = 128 + ( 128 - Color.blue(color) );

        return Color.rgb( (int)r, (int)g, (int)b );
    }
}
