package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WaterActivity extends AppCompatActivity {

    private Button nappiNayta;
    private Button nappiResetoi;
    private ImageView getKuva0;
    private ImageView getKuva1;
    private ImageView getKuva2;
    private ImageView getKuva3;
    private ImageView getKuva4;
    private ImageView getKuva5;
    private ImageView getKuva6;
    private ImageView getKuva7;
    private ImageView getKuva8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

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
                getKuva1.setVisibility(View.INVISIBLE);
                getKuva2.setVisibility(View.INVISIBLE);
                getKuva3.setVisibility(View.INVISIBLE);
                getKuva4.setVisibility(View.INVISIBLE);
                getKuva5.setVisibility(View.INVISIBLE);
                getKuva6.setVisibility(View.INVISIBLE);
                getKuva7.setVisibility(View.INVISIBLE);
                getKuva8.setVisibility(View.INVISIBLE);
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