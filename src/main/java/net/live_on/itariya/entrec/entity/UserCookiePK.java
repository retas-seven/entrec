/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.live_on.itariya.entrec.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author osawa
 */
@Embeddable
public class UserCookiePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "cookie_name")
    private String cookieName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cookie_value")
    private String cookieValue;

    public UserCookiePK() {
    }

    public UserCookiePK(String userId, String cookieName, String cookieValue) {
        this.userId = userId;
        this.cookieName = cookieName;
        this.cookieValue = cookieValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        hash += (cookieName != null ? cookieName.hashCode() : 0);
        hash += (cookieValue != null ? cookieValue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCookiePK)) {
            return false;
        }
        UserCookiePK other = (UserCookiePK) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.cookieName == null && other.cookieName != null) || (this.cookieName != null && !this.cookieName.equals(other.cookieName))) {
            return false;
        }
        if ((this.cookieValue == null && other.cookieValue != null) || (this.cookieValue != null && !this.cookieValue.equals(other.cookieValue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.live_on.itariya.enetity.UserCookiePK[ userId=" + userId + ", cookieName=" + cookieName + ", cookieValue=" + cookieValue + " ]";
    }

}
