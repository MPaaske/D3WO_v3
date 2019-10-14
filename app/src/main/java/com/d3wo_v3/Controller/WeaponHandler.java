package com.d3wo_v3.Controller;

import com.d3wo_v3.Modul.DpsCalculator;
import com.d3wo_v3.Modul.Weapon;
import com.d3wo_v3.Modul.WeaponValidator;

public class WeaponHandler {
    private DpsCalculator dpsCalculator;
    private WeaponValidator validate;

    public WeaponHandler(){
        validate = new WeaponValidator();
        dpsCalculator = new DpsCalculator();
    }

    public boolean validateWeapProps(Weapon weap){
        boolean validWeapState = validate.CheckNumLvls(weap.get_minDmg(), weap.get_maxDmg(), weap.get_minEleDmg(), weap.get_maxEleDmg(), weap.get_pctDmg(), weap.get_pctAs());

        if(validWeapState) validWeapState = validate.CheckWeapTypeDmgNums(weap.get_weapType(), weap.get_weapHand(), weap.get_weapQuality(), weap.get_minEleDmg(), weap.get_maxEleDmg());

        return validWeapState;
    }

    public DpsCalculator getDpsCalculator() {
        return dpsCalculator;
    }
}
