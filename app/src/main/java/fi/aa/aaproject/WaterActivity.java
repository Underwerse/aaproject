package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

         Button nappiNayta;
         Button nappiResetoi;
         ImageView getKuva0;
         ImageView getKuva1;
         ImageView getKuva2;
         ImageView getKuva3;
         ImageView getKuva4;
         ImageView getKuva5;
         ImageView getKuva6;
         ImageView getKuva7;
         ImageView getKuva8;


         TextView textML1;
         TextView textML2;
         TextView textML3;
         TextView textML4;
         TextView textML5;
         TextView textML6;
         TextView textML7;
         TextView textML8;
         TextView textML9;


        getKuva0 = (ImageView) findViewById(R.id.kuva0);
        getKuva1 = (ImageView) findViewById(R.id.kuva1);
        getKuva2 = (ImageView) findViewById(R.id.kuva2);
        getKuva3 = (ImageView) findViewById(R.id.kuva3);
        getKuva4 = (ImageView) findViewById(R.id.kuva4);
        getKuva5 = (ImageView) findViewById(R.id.kuva5);
        getKuva6 = (ImageView) findViewById(R.id.kuva6);
        getKuva7 = (ImageView) findViewById(R.id.kuva7);
        getKuva8 = (ImageView) findViewById(R.id.kuva8);



        textML1 = (TextView) findViewById(R.id.textView);
        textML2 = (TextView) findViewById(R.id.textView2);
        textML3 = (TextView) findViewById(R.id.textView3);
        textML4 = (TextView) findViewById(R.id.textView5);
        textML5 = (TextView) findViewById(R.id.textView6);
        textML6 = (TextView) findViewById(R.id.textView7);
        textML7 = (TextView) findViewById(R.id.textView9);
        textML8 = (TextView) findViewById(R.id.textView10);
        textML9 = (TextView) findViewById(R.id.textView11);



        nappiNayta = (Button) findViewById(R.id.btnPlus);





        nappiNayta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getKuva0.setVisibility(View.INVISIBLE);
                textML1.setVisibility(View.VISIBLE);

                nappiNayta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getKuva1.setVisibility(View.INVISIBLE);
                        textML1.setVisibility(View.INVISIBLE);
                        textML2.setVisibility(View.VISIBLE);
                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getKuva2.setVisibility(View.INVISIBLE);
                                textML2.setVisibility(View.INVISIBLE);
                                textML3.setVisibility(View.VISIBLE);
                                nappiNayta.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getKuva3.setVisibility(View.INVISIBLE);
                                        textML3.setVisibility(View.INVISIBLE);
                                        textML4.setVisibility(View.VISIBLE);
                                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                getKuva4.setVisibility(View.INVISIBLE);
                                                textML4.setVisibility(View.INVISIBLE);
                                                textML5.setVisibility(View.VISIBLE);
                                                nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        getKuva5.setVisibility(View.INVISIBLE);
                                                        textML5.setVisibility(View.INVISIBLE);
                                                        textML6.setVisibility(View.VISIBLE);
                                                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                getKuva6.setVisibility(View.INVISIBLE);
                                                                textML6.setVisibility(View.INVISIBLE);
                                                                textML7.setVisibility(View.VISIBLE);
                                                                nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        getKuva7.setVisibility(View.INVISIBLE);
                                                                        textML7.setVisibility(View.INVISIBLE);
                                                                        textML8.setVisibility(View.VISIBLE);
                                                                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                getKuva8.setVisibility(View.INVISIBLE);
                                                                                textML8.setVisibility(View.INVISIBLE);
                                                                                textML9.setVisibility(View.VISIBLE);


                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });

                                    }
                                });
                            }
                        });
                    }
                });

            }
        });
    }
}