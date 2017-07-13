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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author osawa
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
    , @NamedQuery(name = "User.findByFamilyName", query = "SELECT u FROM User u WHERE u.familyName = :familyName")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findByMail", query = "SELECT u FROM User u WHERE u.mail = :mail")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByRegistDate", query = "SELECT u FROM User u WHERE u.registDate = :registDate")
    , @NamedQuery(name = "User.findByRegistUserId", query = "SELECT u FROM User u WHERE u.registUserId = :registUserId")
    , @NamedQuery(name = "User.findByUpdateDate", query = "SELECT u FROM User u WHERE u.updateDate = :updateDate")
    , @NamedQuery(name = "User.findByUpdateUserId", query = "SELECT u FROM User u WHERE u.updateUserId = :updateUserId")
    , @NamedQuery(name = "User.findByVersion", query = "SELECT u FROM User u WHERE u.version = :version")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "user_id")
    private String userId;
    @Size(max = 5)
    @Column(name = "family_name")
    private String familyName;
    @Size(max = 5)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
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
    @Column(name = "version")
    private int version;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String password, Date registDate, String registUserId, Date updateDate, String updateUserId, int version) {
        this.userId = userId;
        this.password = password;
        this.registDate = registDate;
        this.registUserId = registUserId;
        this.updateDate = updateDate;
        this.updateUserId = updateUserId;
        this.version = version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.live_on.itariya.enetity.User[ userId=" + userId + " ]";
    }
    
}
