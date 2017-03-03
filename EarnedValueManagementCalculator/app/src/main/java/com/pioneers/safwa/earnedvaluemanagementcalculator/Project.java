package com.pioneers.safwa.earnedvaluemanagementcalculator;

/**
 * Created by safwa on 2/16/2017.
 */

public class Project {
    int _id ;
    String _name;
    String _pprice ;
    String _start ;
    // String _DURATION ;
    String _manhours ;
    String _finish ;

    public Project(int id, String name, String pprice, String start, String manhours, String finish) {
        this._id=id;
        this._name=name;
        this._pprice=pprice;
        this._start=start;
        //   this._DURATION=_DURATION;
        this._manhours=manhours;
        this._finish=finish;
    }
    public Project( String name, String pprice, String start, String manhours, String finish) {
        this._name=name;
        this._pprice=pprice;
        this._start=start;
        this._manhours=manhours;
        this._finish=finish;
    }
    public int getprojid(){
        return this._id;
    }
    public String getprojName(){
        return this._name;
    }
    public String getpvalue(){
        return this._pprice;
    }
    public String getstartdate(){ return this._start; }
    public String getmanhours(){
        return this._manhours;
    }
    public String getfinishdate(){ return this._finish; }

    public void setprojid(int projid){ this._id = projid;}
    public void setprojname(String projname){ this._name = projname;}
    public void setprojvalue(String ppprice){ this._pprice = ppprice;}
    public void setstartdate(String startdate){this._start = startdate;}
    //   public void setduration(String duration){this._DURATION = duration;}
    public void setprojmanhours(String pmanhours){
        this._manhours = pmanhours;
    }
    public void setfinishdate(String finishdate){
        this._finish = finishdate;
    }

}