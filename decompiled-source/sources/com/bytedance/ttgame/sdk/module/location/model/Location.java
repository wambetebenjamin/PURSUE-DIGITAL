package com.bytedance.ttgame.sdk.module.location.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Location {
    private long administrativeAreaGeoNameId;
    private String city;
    private String cityAsci;
    private long cityGeoNameId;
    private String country;
    private String countryAsci;
    private String countryCode;
    private long countryGeoNameId;
    private String district;
    private String districtAsci;
    private long districtGeoNameId;
    private boolean isDisputed;
    private double latitude;
    private double longitude;
    @SerializedName("administrativeArea")
    private String province;
    @SerializedName("administrativeAreaAsci")
    private String provinceAsci;

    public long getDistrictGeoNameId() {
        return this.districtGeoNameId;
    }

    public void setDistrictGeoNameId(long j2) {
        this.districtGeoNameId = j2;
    }

    public long getCityGeoNameId() {
        return this.cityGeoNameId;
    }

    public void setCityGeoNameId(long j2) {
        this.cityGeoNameId = j2;
    }

    public long getAdministrativeAreaGeoNameId() {
        return this.administrativeAreaGeoNameId;
    }

    public void setAdministrativeAreaGeoNameId(long j2) {
        this.administrativeAreaGeoNameId = j2;
    }

    public long getCountryGeoNameId() {
        return this.countryGeoNameId;
    }

    public void setCountryGeoNameId(long j2) {
        this.countryGeoNameId = j2;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public String getDistrictAsci() {
        return this.districtAsci;
    }

    public void setDistrictAsci(String str) {
        this.districtAsci = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getCountryAsci() {
        return this.countryAsci;
    }

    public void setCountryAsci(String str) {
        this.countryAsci = str;
    }

    public String getProvinceAsci() {
        return this.provinceAsci;
    }

    public void setProvinceAsci(String str) {
        this.provinceAsci = str;
    }

    public String getCityAsci() {
        return this.cityAsci;
    }

    public void setCityAsci(String str) {
        this.cityAsci = str;
    }

    public boolean isDisputed() {
        return this.isDisputed;
    }

    public void setDisputed(boolean z) {
        this.isDisputed = z;
    }

    public String getCountry() {
        return this.country;
    }

    public String getProvince() {
        return this.province;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String toString() {
        return "Location{country='" + this.country + "', countryAsci='" + this.countryAsci + "', province='" + this.province + "', provinceAsci='" + this.provinceAsci + "', city='" + this.city + "', cityAsci='" + this.cityAsci + "', district='" + this.district + "', districtAsci='" + this.districtAsci + "', countryCode='" + this.countryCode + "', isDisputed=" + this.isDisputed + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", districtGeoNameId=" + this.districtGeoNameId + ", cityGeoNameId=" + this.cityGeoNameId + ", administrativeAreaGeoNameId=" + this.administrativeAreaGeoNameId + ", countryGeoNameId=" + this.countryGeoNameId + '}';
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d2) {
        this.longitude = d2;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d2) {
        this.latitude = d2;
    }
}
