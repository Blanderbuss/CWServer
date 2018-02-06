package com.cw.models;

public class ActionResult {

    public boolean result;
    public String msg;

    public ActionResult(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public ActionResult(){
        this.result = true;
        this.msg = "";
    }

    public void set(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

}
