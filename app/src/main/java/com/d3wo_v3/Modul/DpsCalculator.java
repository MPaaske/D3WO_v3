package com.d3wo_v3.Modul;

public class DpsCalculator {
    /** WeapOpt_1 is ussed when we are calculating the new avgDmg for a weapon where we change the elemental dmg **/
    public Weapon WeapOpt_1 (double avgDmg, double minDmg, double maxDmg, int minEleDmg, int maxEleDmg, double weapAs, int pctDmg, int pctAs, int weapHand, int weapQuality, int weapType){
        Weapon rWeapon;

        //region cast weapType
        /** this is a copy in WeaponValidator.java under the CheckWeapTypeDmgNums method - it should be possible to do it in MainActivity when creating the weapon object. **/
        int wType = 0;
        int weaponType = weapType;
        switch (weaponType)
        {
            case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:case 10:case 14:case 15:case 16:
            wType = 1;
            break;
            case 11:case 12:case 13: /** This cases is our X-bow, bows and dagger's **/
            wType = 2;
        }
        //endregion

        minDmg = minDmg - minEleDmg;
        maxDmg = maxDmg - maxEleDmg;

        /** One hand X-bows of non-ancient quality -- weapType referes to switch results **/
        if(weapHand == 1 && wType == 2)
        {
            if(weapQuality == 1)
            {
                maxEleDmg = 1304;
                minEleDmg = 1049;
            }
            else
            {
                maxEleDmg = 1700;
                minEleDmg = 1365;
            }
        }/** One hand weapons and/or two handed X-bows/bows **/
        else if(weapHand == 1 && wType == 1 || weapHand == 2 && wType == 2)
        {
            if(weapQuality == 1)
            {
                maxEleDmg = 1490;
                minEleDmg = 1199;
            }
            else
            {
                maxEleDmg = 1940;
                minEleDmg = 1560;
            }
        }/** Two handed weapons **/
        else if(weapHand == 2 && wType == 1)
        {
            if(weapQuality == 1)
            {
                maxEleDmg = 1788;
                minEleDmg = 1439;
            }
            else
            {
                maxEleDmg = 2325;
                minEleDmg = 1870;
            }
        }

        minDmg = minDmg + minEleDmg;
        maxDmg = maxDmg + maxEleDmg;

        /** adding % scales to calculate the dmg **/
        if(pctDmg != 0)
        {
            if(pctDmg == 10)
            {
                minDmg = minDmg * Double.parseDouble("1." + pctDmg); /** could just have done Double.parseDouble("1.10") or even minDmg * 1.10 **/
                maxDmg = maxDmg * Double.parseDouble("1." + pctDmg);
            }
            else
            {
                minDmg = minDmg * Double.parseDouble("1.0" + pctDmg);
                maxDmg = maxDmg * Double.parseDouble("1.0" + pctDmg);
            }
        }

        /** Calculate the avgDmg **/
        avgDmg = (minDmg + maxDmg) / 2;

        /** Now we can apply the attack speed increase **/
        /**
         * We have shorted this as much as possible
         * it looks kind of backwords, first the condition (pctAs == 10)
         * the snipper after ? is applied if conditions is true
         * the snippet after : is applied if conditions is false
         * * we can do this since we just need to set the value of a single variable
         * */
        if(pctAs != 0) weapAs = (pctAs == 7) ? weapAs * 1.07 : weapAs * Double.parseDouble("1.0" + pctAs);

        avgDmg = avgDmg * weapAs;

        avgDmg = Math.round(avgDmg*10.0)/10.0;

        rWeapon = new Weapon(avgDmg, minDmg, maxDmg, minEleDmg, maxEleDmg, weapAs, pctDmg, pctAs, weapHand, weapQuality, weapType);

        return rWeapon;
    }

    /** WeapOpt_2 is used when we want a weapon with 10% dmg roll **/
    public Weapon WeapOpt_2 (double avgDmg, double minDmg, double maxDmg, int minEleDmg, int maxEleDmg, double weapAs, int pctDmg, int pctAs, int weapHand, int weapQuality, int weapType)
    {
        Weapon rWeapon;

        pctDmg = 10;

        minDmg = minDmg * 1.10;
        maxDmg = maxDmg * 1.10;

        /** Calculate the avgDmg **/
        avgDmg = (minDmg + maxDmg) / 2;

        if(pctAs != 0) weapAs = (pctAs == 7) ? weapAs * 1.07 : weapAs * Double.parseDouble("1.0" + pctAs);

        avgDmg = avgDmg * weapAs;

        avgDmg = Math.round(avgDmg*10.0)/10.0;

        rWeapon = new Weapon(avgDmg, minDmg, maxDmg, minEleDmg, maxEleDmg, weapAs, pctDmg, pctAs, weapHand, weapQuality, weapType);

        return rWeapon;
    }
}
