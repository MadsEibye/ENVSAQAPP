package Models;

import com.esri.arcgisruntime.geometry.Geometry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Data {
    @SerializedName("gid")
    @Expose
    private Integer gid;

    public Data() {
    }

    @SerializedName("object_id")
    @Expose
    private BigInteger object_id;
    @SerializedName("long")
    @Expose
    private float longitude;
    @SerializedName("lat")
    @Expose
    private float latitude;
    @SerializedName("x_utm")
    @Expose
    private BigInteger x_utm;
    @SerializedName("y_utm")
    @Expose
    private BigInteger y_utm;

    public Data(Integer gid, BigInteger object_id, float longitude, float latitude,
                BigInteger x_utm, BigInteger y_utm, String x_y_utm, String street_nam, String house_num,
                String kommune, BigDecimal no2_street, BigInteger idnr, Geometry geom) {
        this.gid = gid;
        this.object_id = object_id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.x_utm = x_utm;
        this.y_utm = y_utm;
        this.x_y_utm = x_y_utm;
        this.street_nam = street_nam;
        this.house_num = house_num;
        this.kommune = kommune;
        this.no2_street = no2_street;
        this.idnr = idnr;
        this.geom = geom;
    }

    @SerializedName("x_y_utm")
    @Expose
    private String x_y_utm;
    @SerializedName("street_nam")
    @Expose
    private String street_nam;
    @SerializedName("house_num")
    @Expose
    private String house_num;
    @SerializedName("kommune")
    @Expose
    private String kommune;
    @SerializedName("no2_street")
    @Expose
    private BigDecimal no2_street;
    @SerializedName("idnr")
    @Expose
    private BigInteger idnr;
    @SerializedName("geom")
    @Expose
    private Geometry geom;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public BigInteger getObject_id() {
        return object_id;
    }

    public void setObject_id(BigInteger object_id) {
        this.object_id = object_id;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public BigInteger getX_utm() {
        return x_utm;
    }

    public void setX_utm(BigInteger x_utm) {
        this.x_utm = x_utm;
    }

    public BigInteger getY_utm() {
        return y_utm;
    }

    public void setY_utm(BigInteger y_utm) {
        this.y_utm = y_utm;
    }

    public String getX_y_utm() {
        return x_y_utm;
    }

    public void setX_y_utm(String x_y_utm) {
        this.x_y_utm = x_y_utm;
    }

    public String getStreet_nam() {
        return street_nam;
    }

    public void setStreet_nam(String street_nam) {
        this.street_nam = street_nam;
    }

    public String getHouse_num() {
        return house_num;
    }

    public void setHouse_num(String house_num) {
        this.house_num = house_num;
    }

    public String getKommune() {
        return kommune;
    }

    public void setKommune(String kommune) {
        this.kommune = kommune;
    }

    public BigDecimal getNo2_street() {
        return no2_street;
    }

    public void setNo2_street(BigDecimal no2_street) {
        this.no2_street = no2_street;
    }

    public BigInteger getIdnr() {
        return idnr;
    }

    public void setIdnr(BigInteger idnr) {
        this.idnr = idnr;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    @Override
    public String toString() {
        return "Data{" +
                "gid=" + gid +
                ", object_id=" + object_id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", x_utm=" + x_utm +
                ", y_utm=" + y_utm +
                ", x_y_utm='" + x_y_utm + '\'' +
                ", street_nam='" + street_nam + '\'' +
                ", house_num='" + house_num + '\'' +
                ", kommune='" + kommune + '\'' +
                ", no2_street=" + no2_street +
                ", idnr=" + idnr +
                ", geom=" + geom +
                '}';
    }
/*
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(street_nam + " " + house_num);

        return sb + " \n";

    }

 */
}
