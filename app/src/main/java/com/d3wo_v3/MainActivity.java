package com.d3wo_v3;

import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.d3wo_v3.Controller.WeaponHandler;
import com.d3wo_v3.Modul.Weapon;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SeekBar sb_pctDmg, sb_pctAs;
    private TextView tv_pctDmg, tv_pctAs, tv_numValidate;
    private Spinner spin;
    private Switch switch_weapAncient, switch_weapHand;
    private EditText minDmg, maxDmg, minEleDmg, maxEleDmg, weapAs;

    private Weapon weapon;
    private WeaponHandler weapHandler;

    private AdView myAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate controller
        weapHandler = new WeaponHandler();

        //region Prep UI Connections Var's
        spin = findViewById(R.id.spinner_weapType);

        switch_weapAncient = findViewById(R.id.switch_weapAncient);
        switch_weapHand = findViewById(R.id.switch_weapHand);

        minDmg = findViewById(R.id.tb_minDmg);
        maxDmg = findViewById(R.id.tb_maxDmg);
        minEleDmg = findViewById(R.id.tb_minEleDmg);
        maxEleDmg = findViewById(R.id.tb_maxEleDmg);
        weapAs = findViewById(R.id.tb_weapAs);

        sb_pctDmg = findViewById(R.id.sB_pctDmg);
        tv_pctDmg = findViewById(R.id.tv_pctDmg);

        sb_pctAs = findViewById(R.id.sB_pctAs);
        tv_pctAs = findViewById(R.id.tv_pctAs);

        tv_numValidate = findViewById(R.id.tv_numValidate);
        //endregion

        //region Spinner setup
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.weapType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(this);
        //endregion

        //region Seeker setup
        sb_pctDmg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i != 0) i += 5;
                tv_pctDmg.setText(i + " % Damage");
            }

            // These are needed but have no real function for us atm
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_pctAs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i != 0) i += 4;
                tv_pctAs.setText(i + " % Attack Speed");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //endregion

        //region AdSetup
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        myAdView = findViewById(R.id.myAdView);
        AdRequest adReq = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        myAdView.loadAd(adReq);
        //endregion
    }

    //region Spinner support ItemSelectClasse's
    //These are needed for the AdapterView.onItemSelectedListener to work
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

        int handType = parent.getSelectedItemPosition()+1;
        switch (handType){
            case 1:case 2:case 3:case 4:case 5:case 6:case 11:
                switch_weapHand.setClickable(true);
                switch_weapHand.setChecked(false);
                switch_weapHand.setTextColor(-1);
                break;
            case 9:case 13:case 14:case 15:case 16: /** one-hand only**/
                switch_weapHand.setChecked(false);
                switch_weapHand.setClickable(false);
                switch_weapHand.setTextColor(Color.GRAY);
                break;
            case 7:case 8:case 10:case 12:  /** two-hand only**/
                switch_weapHand.setChecked(true);
                switch_weapHand.setClickable(false);
                switch_weapHand.setTextColor(Color.GRAY);
                break;
        }
    }

    // This Method HAVE to be ther, but at the moment does nothing
    public void onNothingSelected(AdapterView<?> parent){

    }
    //endregion

    //OnButtonClickAction Calculate DPS
    public void doCalculation(View view){
        //region get UI Values
        int weapType = spin.getSelectedItemPosition()+1;

        int weapAncient = (switch_weapAncient.isChecked())? 2 : 1;
        int weapHand = (switch_weapHand.isChecked())? 2 : 1;

        int min_Dmg = (minDmg.getText().length() == 0)? 1495 : Integer.parseInt(minDmg.getText().toString()) ;
        int max_dmg = (maxDmg.getText().length() == 0)? 1966 : Integer.parseInt(maxDmg.getText().toString());
        int min_eleDmg = (minEleDmg.getText().length() == 0)? 1191 : Integer.parseInt(minEleDmg.getText().toString());
        int max_eleDmg = (maxEleDmg.getText().length() == 0)? 1395 : Integer.parseInt(maxEleDmg.getText().toString());
        double weap_as = (weapAs.getText().length() == 0)? 1.40 : Double.parseDouble(weapAs.getText().toString());


        int pct_dmg = sb_pctDmg.getProgress();
        pct_dmg = (pct_dmg == 0) ? pct_dmg : pct_dmg + 5;
        int pct_as = sb_pctAs.getProgress();
        pct_as = (pct_as == 0) ? pct_as : pct_as + 4;
        //endregion

        // Prep weapon for calculation
        weapon = new Weapon(0, min_Dmg, max_dmg, min_eleDmg, max_eleDmg, weap_as, pct_dmg, pct_as, weapHand, weapAncient, weapType);

        // process weapon with information
        boolean isValid = weapHandler.validateWeapProps(weapon);

        if(isValid == true){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("validWeap", weapon);
            startActivity(intent);
        } else {
            Toast.makeText(this.getApplicationContext(), "Something Doesn't Match a valid input", Toast.LENGTH_LONG).setGravity(Gravity.CENTER, Gravity.CENTER, 0);
            tv_numValidate.setText("The Numbers your put in, is not within the game limits.");
        }
    }
}
