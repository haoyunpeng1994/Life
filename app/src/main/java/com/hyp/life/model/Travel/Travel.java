package com.hyp.life.model.Travel;

/**
 * Created by acer on 2015/11/15.
 */
public class Travel {
    private boolean ret;

    private int errcode;

    private String errmsg;

    private int ver;

    private TravelData data;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public TravelData getTravelData() {
        return data;
    }

    public void setTravelData(TravelData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "ret=" + ret +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", ver=" + ver +
                ", data=" + data +
                '}';
    }
}


