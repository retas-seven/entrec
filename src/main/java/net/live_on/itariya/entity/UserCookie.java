/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.live_on.itariya.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author osawa
 */
@Entity
@Table(name = "user_cookie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCookie.findAll", query = "SELECT u FROM UserCookie u")
    , @NamedQuery(name = "UserCookie.findByUserId", query = "SELECT u FROM UserCookie u WHERE u.userCookiePK.userId = :userId")
    , @NamedQuery(name = "UserCookie.findByCookieName", query = "SELECT u FROM UserCookie u WHERE u.userCookiePK.cookieName = :cookieName")
    , @NamedQuery(name = "UserCookie.findByCookieValue", query = "SELECT u FROM UserCookie u WHERE u.userCookiePK.cookieValue = :cookieValue")
    , @NamedQuery(name = "UserCookie.findByRegistDate", query = "SELECT u FROM UserCookie u WHERE u.registDate = :registDate")
    , @NamedQuery(name = "UserCookie.findByRegistUserId", query = "SELECT u FROM UserCookie u WHERE u.registUserId = :registUserId")
    , @NamedQuery(name = "UserCookie.findByUpdateDate", query = "SELECT u FROM UserCookie u WHERE u.updateDate = :updateDate")
    , @NamedQuery(name = "UserCookie.findByUpdateUserId", query = "SELECT u FROM UserCookie u WHERE u.updateUserId = :updateUserId")
    , @NamedQuery(name = "UserCookie.findByVersion", query = "SELECT u FROM UserCookie u WHERE u.version = :version")})
public class UserCookie implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserCookiePK userCookiePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regist_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "regist_user_id")
    private String registUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "update_user_id")
    private String updateUserId;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "version")
    private int version;

    public UserCookie() {
    }

    public UserCookie(UserCookiePK userCookiePK) {
        this.userCookiePK = userCookiePK;
    }

    public UserCookie(UserCookiePK userCookiePK, Date registDate, String registUserId, Date updateDate, String updateUserId, int version) {
        this.userCookiePK = userCookiePK;
        this.registDate = registDate;
        this.registUserId = registUserId;
        this.updateDate = updateDate;
        this.updateUserId = updateUserId;
        this.version = version;
    }

    public UserCookie(String userId, String cookieName, String cookieValue) {
        this.userCookiePK = new UserCookiePK(userId, cookieName, cookieValue);
    }

    public UserCookiePK getUserCookiePK() {
        return userCookiePK;
    }

    public void setUserCookiePK(UserCookiePK userCookiePK) {
        this.userCookiePK = userCookiePK;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getRegistUserId() {
        return registUserId;
    }

    public void setRegistUserId(String registUserId) {
        this.registUserId = registUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCookiePK != null ? userCookiePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCookie)) {
            return false;
        }
        UserCookie other = (UserCookie) object;
        if ((this.userCookiePK == null && other.userCookiePK != null) || (this.userCookiePK != null && !this.userCookiePK.equals(other.userCookiePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.live_on.itariya.enetity.UserCookie[ userCookiePK=" + userCookiePK + " ]";
    }

}
