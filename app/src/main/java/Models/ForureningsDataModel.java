package Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForureningsDataModel {

    public ForureningsDataModel(Integer i, Integer j, Integer x_utm, Integer y_utm, double no2, double o3, double PM2_5, double PM10, float hour, Integer day) {
        this.i = i;
        this.j = j;
        this.x_utm = x_utm;
        this.y_utm = y_utm;
        No2 = no2;
        O3 = o3;
        this.PM2_5 = PM2_5;
        this.PM10 = PM10;
        Hour = hour;
        Day = day;
    }

    @Override
    public String toString() {
        return "ForureningsDataModel{" +
                "i=" + i +
                ", j=" + j +
                ", x_utm=" + x_utm +
                ", y_utm=" + y_utm +
                ", No2=" + No2 +
                ", O3=" + O3 +
                ", PM2_5=" + PM2_5 +
                ", PM10=" + PM10 +
                ", Hour=" + Hour +
                ", Day=" + Day +
                '}';
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public Integer getX_utm() {
        return x_utm;
    }

    public void setX_utm(Integer x_utm) {
        this.x_utm = x_utm;
    }

    public Integer getY_utm() {
        return y_utm;
    }

    public void setY_utm(Integer y_utm) {
        this.y_utm = y_utm;
    }

    public double getNo2() {
        return No2;
    }

    public void setNo2(double no2) {
        No2 = no2;
    }

    public double getO3() {
        return O3;
    }

    public void setO3(double o3) {
        O3 = o3;
    }

    public double getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(double PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public double getPM10() {
        return PM10;
    }

    public void setPM10(double PM10) {
        this.PM10 = PM10;
    }

    public float getHour() {
        return Hour;
    }

    public void setHour(Integer hour) {
        Hour = hour;
    }

    public Integer getDay() {
        return Day;
    }

    public void setDay(Integer day) {
        Day = day;
    }

    @SerializedName("i")
    @Expose
    private Integer i;
    @SerializedName("j")
    @Expose
    private Integer j;
    @SerializedName("x_utm")
    @Expose
    private Integer x_utm;
    @SerializedName("y_utm")
    @Expose
    private Integer y_utm;
    @SerializedName("no2")
    @Expose
    private double No2;
    @SerializedName("o3")
    @Expose
    private double O3;
    @SerializedName("pm2_5")
    @Expose
    private double PM2_5;
    @SerializedName("pm10")
    @Expose
    private double PM10;
    @SerializedName("hour")
    @Expose
    private float Hour;
    @SerializedName("day")
    @Expose
    private Integer Day;

}

