package com.d3wo_v3.Modul;

public class WeaponValidator {
    public boolean CheckNumLvls(double minDmg, double maxDmg, int minEleDmg, int maxEleDmg, int pctDmg, int pctAs)
    {
        boolean checkNums = false;

        if(minDmg < maxDmg && minEleDmg < maxEleDmg)
        {
            if(pctDmg <=10 && pctDmg >= 6 || pctDmg == 0)
            {
                if(pctAs <= 7 && pctAs >=5 || pctAs == 0) checkNums = true;
            }
        }


        return checkNums;
    }

    public boolean CheckWeapTypeDmgNums(int weapType, int weapHand, int weapQuality, int minEleDmg, int maxEleDmg)
    {
        boolean checkNums = true;

        //region cast weapType
        /** this is a copy in DpsCalculator.java under the WeapOpt_1 method - it should be possible to do it in MainActivity when creating the weapon object. **/
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

        /** this is a make shift solution, it worked before and will work know, but a better solution should be created **/
        /** Checking to see if any of the EleDmg numbers is higher then what is possible through the game -- if non are return true **/
        if(wType == 1 && weapHand == 2 && weapQuality == 1)
        {
            if(maxEleDmg > 1788 || minEleDmg > 1439) checkNums = false;
        }
        else if(wType == 1 && weapHand == 2 && weapQuality == 2)
        {
            if(maxEleDmg > 2325 || minEleDmg > 1870) checkNums = false;
        }
        else if(wType == 1 && weapHand == 1 && weapQuality == 1 || wType == 2 && weapHand == 2 && weapQuality == 1)
        {
            if(maxEleDmg > 1490 || minEleDmg > 1199) checkNums = false;
        }
        else if(wType == 1 && weapHand == 1 && weapQuality == 2 || wType == 2 && weapHand == 2 && weapQuality == 2)
        {
            if(maxEleDmg > 1940 || minEleDmg > 1560) checkNums = false;
        }
        else if(wType == 2 && weapHand == 1 && weapQuality == 1)
        {
            if(maxEleDmg > 1304 || minEleDmg > 1049) checkNums = false;
        }
        else if(wType == 2 && weapHand == 1 && weapQuality == 2)
        {
            if(maxEleDmg > 1700 || minEleDmg > 1365) checkNums = false;
        }

        return checkNums;
    }
}
