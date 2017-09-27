/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.live_on.itariya.entrec.entity;

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

import lombok.ToString;

/**
 *
 * @author osawa
 */
@ToString
@Entity
@Table(name = "check_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CheckHistory.findAll", query = "SELECT c FROM CheckHistory c")
    , @NamedQuery(name = "CheckHistory.findByCheckYear", query = "SELECT c FROM CheckHistory c WHERE c.checkHistoryPK.checkYear = :checkYear")
    , @NamedQuery(name = "CheckHistory.findByCheckMonth", query = "SELECT c FROM CheckHistory c WHERE c.checkHistoryPK.checkMonth = :checkMonth")
    , @NamedQuery(name = "CheckHistory.findByCheckDay", query = "SELECT c FROM CheckHistory c WHERE c.checkHistoryPK.checkDay = :checkDay")
    , @NamedQuery(name = "CheckHistory.findByChackDateSeq", query = "SELECT c FROM CheckHistory c WHERE c.checkHistoryPK.chackDateSeq = :chackDateSeq")
    , @NamedQuery(name = "CheckHistory.findByEntryUserId", query = "SELECT c FROM CheckHistory c WHERE c.entryUserId = :entryUserId")
    , @NamedQuery(name = "CheckHistory.findByEntryTime", query = "SELECT c FROM CheckHistory c WHERE c.entryTime = :entryTime")
    , @NamedQuery(name = "CheckHistory.findByExitUserId", query = "SELECT c FROM CheckHistory c WHERE c.exitUserId = :exitUserId")
    , @NamedQuery(name = "CheckHistory.findByExitTime", query = "SELECT c FROM CheckHistory c WHERE c.exitTime = :exitTime")
    , @NamedQuery(name = "CheckHistory.findByPcPower", query = "SELECT c FROM CheckHistory c WHERE c.pcPower = :pcPower")
    , @NamedQuery(name = "CheckHistory.findByClearDesk", query = "SELECT c FROM CheckHistory c WHERE c.clearDesk = :clearDesk")
    , @NamedQuery(name = "CheckHistory.findByStorageLock", query = "SELECT c FROM CheckHistory c WHERE c.storageLock = :storageLock")
    , @NamedQuery(name = "CheckHistory.findByLightOff", query = "SELECT c FROM CheckHistory c WHERE c.lightOff = :lightOff")
    , @NamedQuery(name = "CheckHistory.findByDoorWindowLock", query = "SELECT c FROM CheckHistory c WHERE c.doorWindowLock = :doorWindowLock")
    , @NamedQuery(name = "CheckHistory.findByRegistDate", query = "SELECT c FROM CheckHistory c WHERE c.registDate = :registDate")
    , @NamedQuery(name = "CheckHistory.findByRegistUserId", query = "SELECT c FROM CheckHistory c WHERE c.registUserId = :registUserId")
    , @NamedQuery(name = "CheckHistory.findByUpdateDate", query = "SELECT c FROM CheckHistory c WHERE c.updateDate = :updateDate")
    , @NamedQuery(name = "CheckHistory.findByUpdateUserId", query = "SELECT c FROM CheckHistory c WHERE c.updateUserId = :updateUserId")
    , @NamedQuery(name = "CheckHistory.findByVersion", query = "SELECT c FROM CheckHistory c WHERE c.version = :version")})
public class CheckHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CheckHistoryPK checkHistoryPK;
    @Size(max = 12)
    @Column(name = "entry_user_id")
    private String entryUserId;
    @Column(name = "entry_time")
    @Temporal(TemporalType.TIME)
    private Date entryTime;
    @Size(max = 12)
    @Column(name = "exit_user_id")
    private String exitUserId;
    @Column(name = "exit_time")
    @Temporal(TemporalType.TIME)
    private Date exitTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pc_power")
    private boolean pcPower;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clear_desk")
    private boolean clearDesk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storage_lock")
    private boolean storageLock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "light_off")
    private boolean lightOff;
    @Basic(optional = false)
    @NotNull
    @Column(name = "door_window_lock")
    private boolean doorWindowLock;
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
    @Version
    private int version;

    public CheckHistory() {
    }

    public CheckHistory(CheckHistoryPK checkHistoryPK) {
        this.checkHistoryPK = checkHistoryPK;
    }

    public CheckHistory(CheckHistoryPK checkHistoryPK, boolean pcPower, boolean clearDesk, boolean storageLock, boolean lightOff, boolean doorWindowLock, Date registDate, String registUserId, Date updateDate, String updateUserId, int version) {
        this.checkHistoryPK = checkHistoryPK;
        this.pcPower = pcPower;
        this.clearDesk = clearDesk;
        this.storageLock = storageLock;
        this.lightOff = lightOff;
        this.doorWindowLock = doorWindowLock;
        this.registDate = registDate;
        this.registUserId = registUserId;
        this.updateDate = updateDate;
        this.updateUserId = updateUserId;
        this.version = version;
    }

    public CheckHistory(short checkYear, short checkMonth, short checkDay, String chackDateSeq) {
        this.checkHistoryPK = new CheckHistoryPK(checkYear, checkMonth, checkDay, chackDateSeq);
    }

    public CheckHistoryPK getCheckHistoryPK() {
        return checkHistoryPK;
    }

    public void setCheckHistoryPK(CheckHistoryPK checkHistoryPK) {
        this.checkHistoryPK = checkHistoryPK;
    }

    public String getEntryUserId() {
        return entryUserId;
    }

    public void setEntryUserId(String entryUserId) {
        this.entryUserId = entryUserId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitUserId() {
        return exitUserId;
    }

    public void setExitUserId(String exitUserId) {
        this.exitUserId = exitUserId;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public boolean getPcPower() {
        return pcPower;
    }

    public void setPcPower(boolean pcPower) {
        this.pcPower = pcPower;
    }

    public boolean getClearDesk() {
        return clearDesk;
    }

    public void setClearDesk(boolean clearDesk) {
        this.clearDesk = clearDesk;
    }

    public boolean getStorageLock() {
        return storageLock;
    }

    public void setStorageLock(boolean storageLock) {
        this.storageLock = storageLock;
    }

    public boolean getLightOff() {
        return lightOff;
    }

    public void setLightOff(boolean lightOff) {
        this.lightOff = lightOff;
    }

    public boolean getDoorWindowLock() {
        return doorWindowLock;
    }

    public void setDoorWindowLock(boolean doorWindowLock) {
        this.doorWindowLock = doorWindowLock;
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
        hash += (checkHistoryPK != null ? checkHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckHistory)) {
            return false;
        }
        CheckHistory other = (CheckHistory) object;
        if ((this.checkHistoryPK == null && other.checkHistoryPK != null) || (this.checkHistoryPK != null && !this.checkHistoryPK.equals(other.checkHistoryPK))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "net.live_on.itariya.CheckHistory[ checkHistoryPK=" + checkHistoryPK + " ]";
//    }

}
