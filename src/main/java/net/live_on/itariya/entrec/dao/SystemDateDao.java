package net.live_on.itariya.entrec.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.live_on.itariya.entrec.entity.SystemDate;

@Stateless
public class SystemDateDao extends AbstractFacade<SystemDate> {

	/** エンティティマネージャ */
    //@PersistenceContext(unitName = "entrecUnit")
	@Inject
	protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SystemDateDao() {
        super(SystemDate.class);
    }

	/**
	 * システム日付を取得する
	 * @return システム日付
	 */
	public SystemDate getSystemDate() {
		SystemDate systemDate;
	    Query query = em.createNativeQuery(
	            "select current_timestamp as system_date from dual", SystemDate.class);
		systemDate = (SystemDate) query.getSingleResult();
	    return systemDate;
	}

	/**
	 * システム年を取得する
	 * @return システム年
	 */
	public int getSystemYear() {
		int year;
		Date nowDate = getSystemDate().getSystemDate();
		GregorianCalendar gc = (GregorianCalendar)GregorianCalendar.getInstance();
		gc.setGregorianChange(nowDate);
		year = gc.get(Calendar.YEAR);
		return year;
	}
}
