package net.live_on.itariya.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SystemDate implements Serializable {
    private static final long serialVersionUID = 1L;

    /** システム日時 */
    private Date systemDate;

    @Id
    @Column(name = "system_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((systemDate == null) ? 0 : systemDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemDate other = (SystemDate) obj;
		if (systemDate == null) {
			if (other.systemDate != null)
				return false;
		} else if (!systemDate.equals(other.systemDate))
			return false;
		return true;
	}
}
