package com.d3wo_v3;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.d3wo_v3.Controller.WeaponHandler;
import com.d3wo_v3.Modul.Weapon;

public class ResultActivity extends AppCompatActivity {

    private WeaponHandler weapHandler;
    private Weapon weaponA;
    private Weapon weaponB;

    private TextView optOne, optOne_avgDmg, optOne_dmg, optOne_eleDmg, optOne_weapAs, optOne_pctDmg, optOne_pctAs;
    private TextView optTwo, optTwo_avgDmg, optTwo_dmg, optTwo_eleDmg, optTwo_weapAs, optTwo_pctDmg, optTwo_pctAs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //initiate controller
        weapHandler = new WeaponHandler();

        //region prep UI Connection Var's
        optOne = findViewById(R.id.tv_weapOptOne);
        optOne_avgDmg = findViewById(R.id.tv_optOne_avgDmg);
        optOne_dmg = findViewById(R.id.tv_optOne_dmg);
        optOne_eleDmg = findViewById(R.id.tv_optOne_eleDmg);
        optOne_weapAs = findViewById(R.id.tv_optOne_weapAs);
        optOne_pctDmg = findViewById(R.id.tv_optOne_pctDmg);
        optOne_pctAs = findViewById(R.id.tv_optOne_pctAs);

        optTwo = findViewById(R.id.tv_weapOptTwo);
        optTwo_avgDmg = findViewById(R.id.tv_optTwo_avgDmg);
        optTwo_dmg = findViewById(R.id.tv_optTwo_dmg);
        optTwo_eleDmg = findViewById(R.id.tv_optTwo_eleDmg);
        optTwo_weapAs = findViewById(R.id.tv_optTwo_weapAs);
        optTwo_pctDmg = findViewById(R.id.tv_optTwo_pctDmg);
        optTwo_pctAs = findViewById(R.id.tv_optTwo_pctAs);
        //endregion

        if(getIntent().getExtras() != null) {
            Weapon weap = (Weapon) getIntent().getSerializableExtra("validWeap");
            if(weap != null){
                weaponA = weapHandler.getDpsCalculator().WeapOpt_1(weap.get_avgDmg(), weap.get_minDmg(), weap.get_maxDmg(), weap.get_minEleDmg(), weap.get_maxEleDmg(), weap.get_weapAs(), weap.get_pctDmg(), weap.get_pctAs(), weap.get_weapHand(), weap.get_weapQuality(), weap.get_weapType());
                weaponB = weapHandler.getDpsCalculator().WeapOpt_2(weap.get_avgDmg(), weap.get_minDmg(), weap.get_maxDmg(), weap.get_minEleDmg(), weap.get_maxEleDmg(), weap.get_weapAs(), weap.get_pctDmg(), weap.get_pctAs(), weap.get_weapHand(), weap.get_weapQuality(), weap.get_weapType());

                /** Check if there is more then a 100 dps advantage **/
                if((weaponA.get_avgDmg() - 100) > weaponB.get_avgDmg()) optOne.setTextColor(Color.GREEN); else optTwo.setTextColor(Color.GREEN);

                /** current **/
                //set up original weap from weap.avgDmg

                /** Display Option 1 **/
                optOne.setText("With Elem Dmg");
                optOne_avgDmg.setText("Avg Dmg : " + Double.toString(weaponA.get_avgDmg()));
                optOne_dmg.setText("Weap dmg : " + Math.round(weaponA.get_minDmg()) + " - " + Math.round(weaponA.get_maxDmg()));
                optOne_eleDmg.setText("Ele Dmg : " + weaponA.get_minEleDmg() + " - " + weaponA.get_maxEleDmg());
                Double y = Math.round(weaponA.get_weapAs() * 100.0) / 100.0;
                optOne_weapAs.setText("Att Spe : " + Double.toString(y));
                optOne_pctDmg.setText("Inc Dmg : " + Integer.toString(weaponA.get_pctDmg()));// we need to cast this to an int to avoid an error that course the App to crash. Something about an Id, with this in mind we properly should cast the eleDmg as well
                optOne_pctAs.setText("Inc AtS : " + Integer.toString(weaponA.get_pctAs()));

                /** Display Option 2 **/
                optTwo.setText("With Inc Damage");
                optTwo_avgDmg.setText("Avg Dmg : " + Double.toString(weaponB.get_avgDmg()));
                optTwo_dmg.setText("Weap dmg : " + Math.round(weaponB.get_minDmg()) + " - " + Math.round(weaponB.get_maxDmg()));
                optTwo_eleDmg.setText("Ele Dmg : " + weaponB.get_minEleDmg() + " - " + weaponB.get_maxEleDmg());
                Double t = Math.round(weaponB.get_weapAs() * 100.0) / 100.0;
                optTwo_weapAs.setText("Att Spe : " + Double.toString(t));
                optTwo_pctDmg.setText("Inc Dmg : " + Integer.toString(weaponB.get_pctDmg()));
                optTwo_pctAs.setText("Inc AtS : " + Integer.toString(weaponB.get_pctAs()));
            }
        }
    }
}
