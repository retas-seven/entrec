/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.live_on.itariya.entity;

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
public class CheckHistoryPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "check_year")
    private short checkYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "check_month")
    private short checkMonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "check_day")
    private short checkDay;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "chack_date_seq")
    private String chackDateSeq;

    public CheckHistoryPK() {
    }

    public CheckHistoryPK(short checkYear, short checkMonth, short checkDay, String chackDateSeq) {
        this.checkYear = checkYear;
        this.checkMonth = checkMonth;
        this.checkDay = checkDay;
        this.chackDateSeq = chackDateSeq;
    }

    public short getCheckYear() {
        return checkYear;
    }

    public void setCheckYear(short checkYear) {
        this.checkYear = checkYear;
    }

    public short getCheckMonth() {
        return checkMonth;
    }

    public void setCheckMonth(short checkMonth) {
        this.checkMonth = checkMonth;
    }

    public short getCheckDay() {
        return checkDay;
    }

    public void setCheckDay(short checkDay) {
        this.checkDay = checkDay;
    }

    public String getChackDateSeq() {
        return chackDateSeq;
    }

    public void setChackDateSeq(String chackDateSeq) {
        this.chackDateSeq = chackDateSeq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) checkYear;
        hash += (int) checkMonth;
        hash += (int) checkDay;
        hash += (chackDateSeq != null ? chackDateSeq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckHistoryPK)) {
            return false;
        }
        CheckHistoryPK other = (CheckHistoryPK) object;
        if (this.checkYear != other.checkYear) {
            return false;
        }
        if (this.checkMonth != other.checkMonth) {
            return false;
        }
        if (this.checkDay != other.checkDay) {
            return false;
        }
        if ((this.chackDateSeq == null && other.chackDateSeq != null) || (this.chackDateSeq != null && !this.chackDateSeq.equals(other.chackDateSeq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.live_on.itariya.CheckHistoryPK[ checkYear=" + checkYear + ", checkMonth=" + checkMonth + ", checkDay=" + checkDay + ", chackDateSeq=" + chackDateSeq + " ]";
    }

}
