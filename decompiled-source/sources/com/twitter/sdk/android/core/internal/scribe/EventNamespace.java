package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import net.openid.appauth.AuthorizationRequest;
/* loaded from: classes10.dex */
public class EventNamespace {
    @SerializedName("action")
    public final String action;
    @SerializedName("client")
    public final String client;
    @SerializedName("component")
    public final String component;
    @SerializedName("element")
    public final String element;
    @SerializedName(AuthorizationRequest.Display.PAGE)
    public final String page;
    @SerializedName("section")
    public final String section;

    public EventNamespace(String str, String str2, String str3, String str4, String str5, String str6) {
        this.client = str;
        this.page = str2;
        this.section = str3;
        this.component = str4;
        this.element = str5;
        this.action = str6;
    }

    public String toString() {
        return "client=" + this.client + ", page=" + this.page + ", section=" + this.section + ", component=" + this.component + ", element=" + this.element + ", action=" + this.action;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventNamespace eventNamespace = (EventNamespace) obj;
        String str = this.action;
        if (str == null ? eventNamespace.action == null : str.equals(eventNamespace.action)) {
            String str2 = this.client;
            if (str2 == null ? eventNamespace.client == null : str2.equals(eventNamespace.client)) {
                String str3 = this.component;
                if (str3 == null ? eventNamespace.component == null : str3.equals(eventNamespace.component)) {
                    String str4 = this.element;
                    if (str4 == null ? eventNamespace.element == null : str4.equals(eventNamespace.element)) {
                        String str5 = this.page;
                        if (str5 == null ? eventNamespace.page == null : str5.equals(eventNamespace.page)) {
                            String str6 = this.section;
                            String str7 = eventNamespace.section;
                            return str6 == null ? str7 == null : str6.equals(str7);
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.client;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.page;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.section;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.component;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.element;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.action;
        return hashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    /* loaded from: classes10.dex */
    public static class Builder {
        private String action;
        private String client;
        private String component;
        private String element;
        private String page;
        private String section;

        public Builder setClient(String str) {
            this.client = str;
            return this;
        }

        public Builder setPage(String str) {
            this.page = str;
            return this;
        }

        public Builder setSection(String str) {
            this.section = str;
            return this;
        }

        public Builder setComponent(String str) {
            this.component = str;
            return this;
        }

        public Builder setElement(String str) {
            this.element = str;
            return this;
        }

        public Builder setAction(String str) {
            this.action = str;
            return this;
        }

        public EventNamespace builder() {
            return new EventNamespace(this.client, this.page, this.section, this.component, this.element, this.action);
        }
    }
}
