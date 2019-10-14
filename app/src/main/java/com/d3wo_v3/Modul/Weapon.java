package com.d3wo_v3.Modul;

import java.io.Serializable;

// we have to implement Serializable in order to send WeaponObj's with putExtra method
public class Weapon implements Serializable {
    private double _avgDmg;
    private double _minDmg;
    private double _maxDmg;
    private int _minEleDmg;
    private int _maxEleDmg;
    private double _weapAs;
    private int _pctDmg;
    private int _pctAs;
    private int _weapHand;
    private int _weapQuality;
    private int _weapType;

    public Weapon()
    {
        _avgDmg     = 0;
        _minDmg     = 1970;
        _maxDmg     = 2568;
        _minEleDmg  = 1337;
        _maxEleDmg  = 1623;
        _weapAs     = 1.15;
        _pctDmg     = 0;
        _pctAs      = 0;
        _weapHand   = 2;
        _weapQuality = 1;
        _weapType   = 1;
    }

    public Weapon(double avgDmg, double minDmg, double maxDmg, int minEleDmg, int maxEleDmg, double weapAs, int pctDmg, int pctAs, int weapHand, int weapQuality, int weapType)
    {
        _avgDmg = avgDmg;
        _minDmg = minDmg;
        _maxDmg = maxDmg;
        _minEleDmg = minEleDmg;
        _maxEleDmg = maxEleDmg;
        _weapAs = weapAs;
        _pctDmg = pctDmg;
        _pctAs = pctAs;
        _weapHand = weapHand;
        _weapQuality = weapQuality;
        _weapType = weapType;
    }

    //region get / Set
    public double get_avgDmg() {
        return _avgDmg;
    }

    public void set_avgDmg(double _avgDmg) {
        this._avgDmg = _avgDmg;
    }

    public double get_minDmg() {
        return _minDmg;
    }

    public void set_minDmg(double _minDmg) {
        this._minDmg = _minDmg;
    }

    public double get_maxDmg() {
        return _maxDmg;
    }

    public void set_maxDmg(double _maxDmg) {
        this._maxDmg = _maxDmg;
    }

    public int get_minEleDmg() {
        return _minEleDmg;
    }

    public void set_minEleDmg(int _minEleDmg) {
        this._minEleDmg = _minEleDmg;
    }

    public int get_maxEleDmg() {
        return _maxEleDmg;
    }

    public void set_maxEleDmg(int _maxEleDmg) {
        this._maxEleDmg = _maxEleDmg;
    }

    public double get_weapAs() {
        return _weapAs;
    }

    public void set_weapAs(double _weapAs) {
        this._weapAs = _weapAs;
    }

    public int get_pctDmg() {
        return _pctDmg;
    }

    public void set_pctDmg(int _pctDmg) {
        this._pctDmg = _pctDmg;
    }

    public int get_pctAs() {
        return _pctAs;
    }

    public void set_pctAs(int _pctAs) {
        this._pctAs = _pctAs;
    }

    public int get_weapHand() {
        return _weapHand;
    }

    public void set_weapHand(int _weapHand) {
        this._weapHand = _weapHand;
    }

    public int get_weapQuality() {
        return _weapQuality;
    }

    public void set_weapQuality(int _weapQuality) {
        this._weapQuality = _weapQuality;
    }

    public int get_weapType() {
        return _weapType;
    }

    public void set_weapType(int _weapType) {
        this._weapType = _weapType;
    }
    //endregion
}
