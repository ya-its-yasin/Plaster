package com.example.plaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    float x,y,z,x1,y1,z1,a,b;
    float volume,dryVolume,cement,cemBags,sand;
    EditText xfeet,xinch,yfeet,yinch,zfeet,zinch,RcemBags,Rsand;
    EditText Ea,Eb;
    Button calc,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DecimalFormat df = new DecimalFormat("#0.0000");
        final DecimalFormat tdf = new DecimalFormat("#0");

        xfeet=(EditText)findViewById(R.id.xfeet);
        xinch=(EditText)findViewById(R.id.xinch);
        yfeet=(EditText)findViewById(R.id.yfeet);
        yinch=(EditText)findViewById(R.id.yinch);
        zfeet=(EditText)findViewById(R.id.zfeet);
        zinch=(EditText)findViewById(R.id.zinch);

        Ea=(EditText)findViewById(R.id.a);
        Eb=(EditText)findViewById(R.id.b);

        RcemBags=(EditText)findViewById(R.id.cemBags);
        Rsand=(EditText)findViewById(R.id.sand);



        calc=(Button) findViewById(R.id.calculate);
        reset=(Button) findViewById(R.id.reset);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                x=Float.parseFloat(xfeet.getText().toString());
                x1=Float.parseFloat(xinch.getText().toString());
                y=Float.parseFloat(yfeet.getText().toString());
                y1=Float.parseFloat(yinch.getText().toString());
                z=Float.parseFloat(zfeet.getText().toString());
                z1=Float.parseFloat(zinch.getText().toString());

                a=Float.parseFloat(Ea.getText().toString());
                b=Float.parseFloat(Eb.getText().toString());

                x = getMetre(x,x1);
                y = getMetre(y,y1);
                z = getMetre(z,z1);


                volume=getVolume(x,y,z);
                //System.out.println(volume);

                dryVolume = getDryVolume(volume);
                //System.out.println(dryVolume);


                cement = getCement(a,b,dryVolume);
                //System.out.println(cement);

                cemBags = getCemBags(cement);
                //System.out.println("Number of cement bags needed : "+df.format(cemBags));

                sand = getSand(a,b,dryVolume);
               // System.out.println("Quantity of sand needed : "+df.format(sand)+" metre cube");

                //ScemBags=sand;

                RcemBags.setText(df.format(cemBags));
                Rsand.setText(df.format(sand));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xfeet.setText("");
                yfeet.setText("");
                zfeet.setText("");
                xinch.setText(tdf.format(0));
                yinch.setText(tdf.format(0));
                zinch.setText(tdf.format(0));
            }
        });
    }



    static float getMetre(float x, float x1)
    {
        float inches=(float)(x*12)+x1;
        return (float)inches*0.0254f;
    }

    static float getVolume(float x,float y,float z)
    {
        return x*y*z;
    }

    static float getDryVolume(float vol)
    {
        return vol*1.35f;
    }

    static float getCement(float a,float b, float dryVol)
    {
        return (float)a/(a+b)*dryVol;
    }

    static float getCemBags(float cement)
    {
        return (float)(cement*1440)/50;
    }

    static float getSand(float a,float b, float dryVol)
    {
        return (float)b/(a+b)*dryVol;
    }

}
