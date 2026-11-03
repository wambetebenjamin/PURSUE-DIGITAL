package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.apm.battery.config.c;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class CpUploadInfo {
    @SerializedName("balance")
    private String balance;
    @SerializedName("balanceids")
    private List<String> balanceids;
    @SerializedName("balancenames")
    private List<String> balancenames;
    @SerializedName("balancenums")
    private List<String> balancenums;
    @SerializedName("coins")
    private List<String> coins;
    @SerializedName("costs")
    private List<String> costs;
    @SerializedName("friendlist")
    private String friendlist;
    @SerializedName("gender")
    private String gender;
    @SerializedName("intimacys")
    private List<String> intimacys;
    @SerializedName("listids")
    private List<String> listids;
    @SerializedName("listnames")
    private List<String> listnames;
    @SerializedName("nexusids")
    private List<String> nexusids;
    @SerializedName("nexusnames")
    private List<String> nexusnames;
    @SerializedName("nums")
    private List<String> nums;
    @SerializedName("partyid")
    private String partyid;
    @SerializedName("partyname")
    private String partyname;
    @SerializedName("partyroleid")
    private String partyroleid;
    @SerializedName("partyrolename")
    private String partyrolename;
    @SerializedName(c.BATTERY_POWER_LOCK)
    private String power;
    @SerializedName("profession")
    private String profession;
    @SerializedName("professionid")
    private String professionid;
    @SerializedName("professionroleid")
    private String professionroleid;
    @SerializedName("professionrolename")
    private String professionrolename;
    @SerializedName("ranking")
    private String ranking;
    @SerializedName("roleid")
    private String roleid;
    @SerializedName("roleids")
    private List<String> roleids;
    @SerializedName("rolelevel")
    private String rolelevel;
    @SerializedName("rolename")
    private String rolename;
    @SerializedName("type")
    private String type;
    @SerializedName("vip")
    private String vip;
    @SerializedName("zoneid")
    private String zoneid;
    @SerializedName("zonename")
    private String zonename;

    public List<String> getBalanceids() {
        return this.balanceids;
    }

    public List<String> getBalancenames() {
        return this.balancenames;
    }

    public List<String> getBalancenums() {
        return this.balancenums;
    }

    public List<String> getRoleids() {
        return this.roleids;
    }

    public List<String> getIntimacys() {
        return this.intimacys;
    }

    public List<String> getNexusids() {
        return this.nexusids;
    }

    public List<String> getNexusnames() {
        return this.nexusnames;
    }

    public List<String> getListids() {
        return this.listids;
    }

    public List<String> getListnames() {
        return this.listnames;
    }

    public List<String> getNums() {
        return this.nums;
    }

    public List<String> getCoins() {
        return this.coins;
    }

    public List<String> getCosts() {
        return this.costs;
    }

    public String getType() {
        return this.type;
    }

    public String getZoneid() {
        return this.zoneid;
    }

    public String getZonename() {
        return this.zonename;
    }

    public String getRoleid() {
        return this.roleid;
    }

    public String getRolename() {
        return this.rolename;
    }

    public String getProfessionid() {
        return this.professionid;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getGender() {
        return this.gender;
    }

    public String getProfessionroleid() {
        return this.professionroleid;
    }

    public String getProfessionrolename() {
        return this.professionrolename;
    }

    public String getRolelevel() {
        return this.rolelevel;
    }

    public String getPower() {
        return this.power;
    }

    public String getVip() {
        return this.vip;
    }

    public String getBalance() {
        return this.balance;
    }

    public String getPartyid() {
        return this.partyid;
    }

    public String getPartyname() {
        return this.partyname;
    }

    public String getPartyroleid() {
        return this.partyroleid;
    }

    public String getPartyrolename() {
        return this.partyrolename;
    }

    public String getFriendlist() {
        return this.friendlist;
    }

    public String getRanking() {
        return this.ranking;
    }

    CpUploadInfo(Builder builder) {
        this.balanceids = builder.balanceids;
        this.balancenames = builder.balancenames;
        this.balancenums = builder.balancenums;
        this.roleids = builder.roleids;
        this.intimacys = builder.intimacys;
        this.nexusids = builder.nexusids;
        this.nexusnames = builder.nexusnames;
        this.listids = builder.listids;
        this.listnames = builder.listnames;
        this.nums = builder.nums;
        this.coins = builder.coins;
        this.costs = builder.costs;
        this.type = builder.type;
        this.zoneid = builder.zoneid;
        this.zonename = builder.zonename;
        this.roleid = builder.roleid;
        this.rolename = builder.rolename;
        this.professionid = builder.professionid;
        this.profession = builder.profession;
        this.gender = builder.gender;
        this.professionroleid = builder.professionroleid;
        this.professionrolename = builder.professionrolename;
        this.rolelevel = builder.rolelevel;
        this.power = builder.power;
        this.vip = builder.vip;
        this.balance = builder.balance;
        this.partyid = builder.partyid;
        this.partyname = builder.partyname;
        this.partyroleid = builder.partyroleid;
        this.partyrolename = builder.partyrolename;
        this.friendlist = builder.friendlist;
        this.ranking = builder.ranking;
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        String balance;
        List<String> balanceids;
        List<String> balancenames;
        List<String> balancenums;
        List<String> coins;
        List<String> costs;
        String friendlist;
        String gender;
        List<String> intimacys;
        List<String> listids;
        List<String> listnames;
        List<String> nexusids;
        List<String> nexusnames;
        List<String> nums;
        String partyid;
        String partyname;
        String partyroleid;
        String partyrolename;
        String power;
        String profession;
        String professionid;
        String professionroleid;
        String professionrolename;
        String ranking;
        String roleid;
        List<String> roleids;
        String rolelevel;
        String rolename;
        String type;
        String vip;
        String zoneid;
        String zonename;

        public Builder setBalanceids(List<String> list) {
            this.balanceids = list;
            return this;
        }

        public Builder setBalancenames(List<String> list) {
            this.balancenames = list;
            return this;
        }

        public Builder setBalancenums(List<String> list) {
            this.balancenums = list;
            return this;
        }

        public Builder setRoleids(List<String> list) {
            this.roleids = list;
            return this;
        }

        public Builder setIntimacys(List<String> list) {
            this.intimacys = list;
            return this;
        }

        public Builder setNexusids(List<String> list) {
            this.nexusids = list;
            return this;
        }

        public Builder setNexusnames(List<String> list) {
            this.nexusnames = list;
            return this;
        }

        public Builder setListids(List<String> list) {
            this.listids = list;
            return this;
        }

        public Builder setListnames(List<String> list) {
            this.listnames = list;
            return this;
        }

        public Builder setNums(List<String> list) {
            this.nums = list;
            return this;
        }

        public Builder setCoins(List<String> list) {
            this.coins = list;
            return this;
        }

        public Builder setCosts(List<String> list) {
            this.costs = list;
            return this;
        }

        public Builder setType(String str) {
            this.type = str;
            return this;
        }

        public Builder setZoneid(String str) {
            this.zoneid = str;
            return this;
        }

        public Builder setZonename(String str) {
            this.zonename = str;
            return this;
        }

        public Builder setRoleid(String str) {
            this.roleid = str;
            return this;
        }

        public Builder setRolename(String str) {
            this.rolename = str;
            return this;
        }

        public Builder setProfessionid(String str) {
            this.professionid = str;
            return this;
        }

        public Builder setProfession(String str) {
            this.profession = str;
            return this;
        }

        public Builder setGender(String str) {
            this.gender = str;
            return this;
        }

        public Builder setProfessionroleid(String str) {
            this.professionroleid = str;
            return this;
        }

        public Builder setProfessionrolename(String str) {
            this.professionrolename = str;
            return this;
        }

        public Builder setRolelevel(String str) {
            this.rolelevel = str;
            return this;
        }

        public Builder setPower(String str) {
            this.power = str;
            return this;
        }

        public Builder setVip(String str) {
            this.vip = str;
            return this;
        }

        public Builder setBalance(String str) {
            this.balance = str;
            return this;
        }

        public Builder setPartyid(String str) {
            this.partyid = str;
            return this;
        }

        public Builder setPartyname(String str) {
            this.partyname = str;
            return this;
        }

        public Builder setPartyroleid(String str) {
            this.partyroleid = str;
            return this;
        }

        public Builder setPartyrolename(String str) {
            this.partyrolename = str;
            return this;
        }

        public Builder setFriendlist(String str) {
            this.friendlist = str;
            return this;
        }

        public Builder setRanking(String str) {
            this.ranking = str;
            return this;
        }

        public CpUploadInfo build() {
            return new CpUploadInfo(this);
        }
    }
}
