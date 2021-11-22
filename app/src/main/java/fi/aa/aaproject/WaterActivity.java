package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

        getKuva0 = (ImageView) findViewById(R.id.kuva0);
        getKuva1 = (ImageView) findViewById(R.id.kuva1);
        getKuva2 = (ImageView) findViewById(R.id.kuva2);
        getKuva3 = (ImageView) findViewById(R.id.kuva3);
        getKuva4 = (ImageView) findViewById(R.id.kuva4);
        getKuva5 = (ImageView) findViewById(R.id.kuva5);
        getKuva6 = (ImageView) findViewById(R.id.kuva6);
        getKuva7 = (ImageView) findViewById(R.id.kuva7);
        getKuva8 = (ImageView) findViewById(R.id.kuva8);


        nappiNayta = (Button) findViewById(R.id.btnPlus);
        nappiResetoi = (Button) findViewById(R.id.resetoi);


        nappiResetoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getKuva0.setVisibility(View.VISIBLE);
                getKuva1.setVisibility(View.VISIBLE);
                getKuva2.setVisibility(View.VISIBLE);
                getKuva3.setVisibility(View.VISIBLE);
                getKuva4.setVisibility(View.VISIBLE);
                getKuva5.setVisibility(View.VISIBLE);
                getKuva6.setVisibility(View.VISIBLE);
                getKuva7.setVisibility(View.VISIBLE);
                getKuva8.setVisibility(View.VISIBLE);


            }
        });


        nappiNayta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getKuva0.setVisibility(View.INVISIBLE);
                nappiNayta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getKuva1.setVisibility(View.INVISIBLE);
                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getKuva2.setVisibility(View.INVISIBLE);
                                nappiNayta.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getKuva3.setVisibility(View.INVISIBLE);
                                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                getKuva4.setVisibility(View.INVISIBLE);
                                                nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        getKuva5.setVisibility(View.INVISIBLE);
                                                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                getKuva6.setVisibility(View.INVISIBLE);
                                                                nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        getKuva7.setVisibility(View.INVISIBLE);
                                                                        nappiNayta.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                getKuva8.setVisibility(View.INVISIBLE);


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