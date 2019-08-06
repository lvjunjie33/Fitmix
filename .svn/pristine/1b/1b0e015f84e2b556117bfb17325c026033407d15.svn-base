package com.business.core.entity.address;

/**
 * Created by edward on 2017/7/26.
 *
 * 地理坐标
 */
public class Position {
    /**
     * 纬度
     */
    private Double lng;
    /**
     * 经度
     */
    private Double lat;
    /**
     * 海拔
     */
    private Double asl;

    /**
     * 构建坐标
     *
     * @param lng 纬度
     * @param lat 经度
     * @param asl 海拔
     */
    public static Position buildPosition(Double lng, Double lat, Double asl) {
        Position position = new Position();
        position.setLat(lat);
        position.setLng(lng);
        position.setAsl(asl);
        return position;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getAsl() {
        return asl;
    }

    public void setAsl(Double asl) {
        this.asl = asl;
    }
}
