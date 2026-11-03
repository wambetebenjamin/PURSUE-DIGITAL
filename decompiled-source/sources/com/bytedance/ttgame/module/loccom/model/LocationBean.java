package com.bytedance.ttgame.module.loccom.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes6.dex */
public class LocationBean {
    private City City;
    private Continent Continent;
    private Country Country;
    private District District;
    private String ISP;
    private String LocateMethod;
    private Place Place;
    @SerializedName("Subdivisions")
    private List<Subdivisions> Subdivisions;

    public Continent getContinent() {
        return this.Continent;
    }

    public void setContinent(Continent continent) {
        this.Continent = continent;
    }

    public Country getCountry() {
        return this.Country;
    }

    public void setCountry(Country country) {
        this.Country = country;
    }

    public City getCity() {
        return this.City;
    }

    public void setCity(City city) {
        this.City = city;
    }

    public District getDistrict() {
        return this.District;
    }

    public void setDistrict(District district) {
        this.District = district;
    }

    public Place getPlace() {
        return this.Place;
    }

    public void setPlace(Place place) {
        this.Place = place;
    }

    public String getISP() {
        return this.ISP;
    }

    public void setISP(String str) {
        this.ISP = str;
    }

    public String getLocateMethod() {
        return this.LocateMethod;
    }

    public void setLocateMethod(String str) {
        this.LocateMethod = str;
    }

    public List<Subdivisions> getSubdivisions() {
        return this.Subdivisions;
    }

    public void setSubdivisions(List<Subdivisions> list) {
        this.Subdivisions = list;
    }

    public String toString() {
        return "Location{Continent=" + this.Continent + ", Country=" + this.Country + ", City=" + this.City + ", District=" + this.District + ", Place=" + this.Place + ", ISP='" + this.ISP + "', LocateMethod='" + this.LocateMethod + "', Subdivisions=" + this.Subdivisions + '}';
    }

    /* loaded from: classes6.dex */
    public static class Continent {
        private String ASCIName;
        private String Code;
        private int GeoNameID;
        private String Name;

        public String getCode() {
            return this.Code;
        }

        public void setCode(String str) {
            this.Code = str;
        }

        public int getGeoNameID() {
            return this.GeoNameID;
        }

        public void setGeoNameID(int i2) {
            this.GeoNameID = i2;
        }

        public String getASCIName() {
            return this.ASCIName;
        }

        public void setASCIName(String str) {
            this.ASCIName = str;
        }

        public String getName() {
            return this.Name;
        }

        public void setName(String str) {
            this.Name = str;
        }

        public String toString() {
            return "Continent{Code='" + this.Code + "', GeoNameID=" + this.GeoNameID + ", ASCIName='" + this.ASCIName + "', Name='" + this.Name + "'}";
        }
    }

    /* loaded from: classes6.dex */
    public static class Country {
        private String ASCIName;
        private String Code;
        private int GeoNameID;
        private String Name;

        public String getCode() {
            return this.Code;
        }

        public void setCode(String str) {
            this.Code = str;
        }

        public int getGeoNameID() {
            return this.GeoNameID;
        }

        public void setGeoNameID(int i2) {
            this.GeoNameID = i2;
        }

        public String getASCIName() {
            return this.ASCIName;
        }

        public void setASCIName(String str) {
            this.ASCIName = str;
        }

        public String getName() {
            return this.Name;
        }

        public void setName(String str) {
            this.Name = str;
        }

        public String toString() {
            return "Country{Code='" + this.Code + "', GeoNameID=" + this.GeoNameID + ", ASCIName='" + this.ASCIName + "', Name='" + this.Name + "'}";
        }
    }

    /* loaded from: classes6.dex */
    public static class City {
        private String ASCIName;
        private int GeoNameID;
        private String LocalID;
        private String Name;

        public int getGeoNameID() {
            return this.GeoNameID;
        }

        public void setGeoNameID(int i2) {
            this.GeoNameID = i2;
        }

        public String getASCIName() {
            return this.ASCIName;
        }

        public void setASCIName(String str) {
            this.ASCIName = str;
        }

        public String getName() {
            return this.Name;
        }

        public void setName(String str) {
            this.Name = str;
        }

        public String getLocalID() {
            return this.LocalID;
        }

        public void setLocalID(String str) {
            this.LocalID = str;
        }

        public String toString() {
            return "City{GeoNameID=" + this.GeoNameID + ", ASCIName='" + this.ASCIName + "', Name='" + this.Name + "', LocalID='" + this.LocalID + "'}";
        }
    }

    /* loaded from: classes6.dex */
    public static class District {
        private String ASCIName;
        private int GeoNameID;
        private String LocalID;
        private String Name;

        public int getGeoNameID() {
            return this.GeoNameID;
        }

        public void setGeoNameID(int i2) {
            this.GeoNameID = i2;
        }

        public String getASCIName() {
            return this.ASCIName;
        }

        public void setASCIName(String str) {
            this.ASCIName = str;
        }

        public String getName() {
            return this.Name;
        }

        public void setName(String str) {
            this.Name = str;
        }

        public String getLocalID() {
            return this.LocalID;
        }

        public void setLocalID(String str) {
            this.LocalID = str;
        }

        public String toString() {
            return "District{GeoNameID=" + this.GeoNameID + ", ASCIName='" + this.ASCIName + "', Name='" + this.Name + "', LocalID='" + this.LocalID + "'}";
        }
    }

    /* loaded from: classes6.dex */
    public static class Place {
        private String PostalCode;
        private String TimeZone;

        public String getPostalCode() {
            return this.PostalCode;
        }

        public void setPostalCode(String str) {
            this.PostalCode = str;
        }

        public String getTimeZone() {
            return this.TimeZone;
        }

        public void setTimeZone(String str) {
            this.TimeZone = str;
        }

        public String toString() {
            return "Place{PostalCode='" + this.PostalCode + "', TimeZone='" + this.TimeZone + "'}";
        }
    }

    /* loaded from: classes6.dex */
    public static class Subdivisions {
        private String ASCIName;
        private int GeoNameID;
        private String LocalID;
        private String Name;

        public int getGeoNameID() {
            return this.GeoNameID;
        }

        public void setGeoNameID(int i2) {
            this.GeoNameID = i2;
        }

        public String getASCIName() {
            return this.ASCIName;
        }

        public void setASCIName(String str) {
            this.ASCIName = str;
        }

        public String getName() {
            return this.Name;
        }

        public void setName(String str) {
            this.Name = str;
        }

        public String getLocalID() {
            return this.LocalID;
        }

        public void setLocalID(String str) {
            this.LocalID = str;
        }

        public String toString() {
            return "Subdivisions{GeoNameID=" + this.GeoNameID + ", ASCIName='" + this.ASCIName + "', Name='" + this.Name + "', LocalID='" + this.LocalID + "'}";
        }
    }
}
