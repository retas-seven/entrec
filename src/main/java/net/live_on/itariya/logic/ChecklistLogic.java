package net.live_on.itariya.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import net.live_on.itariya.auth.LoginSession;
import net.live_on.itariya.dao.CheckHistoryDao;
import net.live_on.itariya.entity.CheckHistory;
import net.live_on.itariya.entity.CheckHistoryPK;
import net.live_on.itariya.form.ChecklistForm;
import net.live_on.itariya.form.databean.ChecklistRow;
import net.live_on.itariya.util.ApDateUtil;

@RequestScoped
public class ChecklistLogic {

	@Inject
    ChecklistForm form;

	@Inject
	LoginSession loginSession;

	@Inject
	CheckHistoryDao checkHistoryDao;

	public void initChecklist(Date targetDate) {
		Calendar targetCalendar = ApDateUtil.getCalendar(targetDate);
		searchCheckHistory(targetCalendar.get(Calendar.YEAR), targetCalendar.get(Calendar.MONTH) + 1);
	}

    public void searchCheckHistory(int year, int month) {
    	List<ChecklistRow> checklistRowList = new ArrayList<>();
    	List<CheckHistory> checkHistoryList = checkHistoryDao.searchCheckHistory(year, month);
    	Calendar targetYM = ApDateUtil.getCalendar(year, month);

    	int lastDay = targetYM.getActualMaximum(Calendar.DATE);
    	Map<Short, ChecklistRow> tmpChecklistRowMap = new HashMap<>();

    	for (CheckHistory c : checkHistoryList) {
    		ChecklistRow row = new ChecklistRow();
    		row.setUpdateRow(true);
    		row.setCheckHistory(c);
    		tmpChecklistRowMap.put(c.getCheckHistoryPK().getCheckDay(), row);
    	}

    	for (short day = 1; day <= lastDay; day++) {
    		if (tmpChecklistRowMap.containsKey(day)) {
    			checklistRowList.add(tmpChecklistRowMap.get(day));
    			continue;
    		}

    		ChecklistRow row = new ChecklistRow();
    		CheckHistory c = new CheckHistory();
    		CheckHistoryPK pk = new CheckHistoryPK();
    		pk.setCheckYear((short) targetYM.get(Calendar.YEAR));
    		pk.setCheckMonth((short) (targetYM.get(Calendar.MONTH) + 1));
    		pk.setCheckDay(day);
    		pk.setChackDateSeq("0");
    		c.setCheckHistoryPK(pk);
    		row.setNewRow(true);
    		row.setCheckHistory(c);
    		checklistRowList.add(row);
    	}

    	for (ChecklistRow row : checklistRowList) {
    		// 各行の曜日を設定
    		targetYM.set(Calendar.DATE, row.getCheckHistory().getCheckHistoryPK().getCheckDay());
    		row.setWeek(targetYM.get(Calendar.DAY_OF_WEEK));
    	}

    	form.setChecklistRowList(checklistRowList);
    }

    @Transactional(rollbackOn = Exception.class)
    public void registChecklist() {
    	CheckHistory c;

    	try {
	    	for (ChecklistRow row : form.getChecklistRowList()) {
	    		if (row.isNewRow()) {
		    		c = row.getCheckHistory();
		    		c.setRegistDate(ApDateUtil.getSystemDate());
		    		c.setRegistUserId(loginSession.getLoginUser().getUserId());
		    		c.setUpdateDate(ApDateUtil.getSystemDate());
		    		c.setUpdateUserId(loginSession.getLoginUser().getUserId());
		    		checkHistoryDao.create(c);

	    		} else if (row.isUpdateRow()) {
		    		c = row.getCheckHistory();
		    		c.setUpdateDate(ApDateUtil.getSystemDate());
		    		c.setUpdateUserId(loginSession.getLoginUser().getUserId());
		    		checkHistoryDao.edit(c);
	    		}
	    	}
    	} catch (ConstraintViolationException e) {
    		System.out.println("------------------");
    		for (ConstraintViolation cv : e.getConstraintViolations()) {
    			System.out.println("CONSTRAINT VIOLOATION : " + cv.toString());
    		}
    		System.out.println("------------------");
    		throw e;
    	}

    	System.out.println("end");
    }
 }
