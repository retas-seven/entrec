package net.live_on.itariya.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import net.live_on.itariya.auth.LoginSession;
import net.live_on.itariya.dao.CheckHistoryDao;
import net.live_on.itariya.entity.CheckHistory;
import net.live_on.itariya.entity.CheckHistoryPK;
import net.live_on.itariya.form.ChecklistForm;
import net.live_on.itariya.form.databean.ChecklistRow;
import net.live_on.itariya.util.ApDateUtil;

@Stateless
public class ChecklistLogic {

	@Inject
    ChecklistForm form;

	@Inject
	LoginSession loginSession;

	@Inject
	CheckHistoryDao checkHistoryDao;

	/**
	 * チェックリスト画面の一覧情報を作成する
	 * @param targetDate 作成対象の年月
	 */
	public void initChecklist(Date targetDate) {
		Calendar targetCalendar = ApDateUtil.getCalendar(targetDate);
		searchCheckHistory(targetCalendar.get(Calendar.YEAR), targetCalendar.get(Calendar.MONTH) + 1);
	}

	/**
	 *チェックリスト画面の一覧の各行の情報を作成する
	 * @param year 作成対象年
	 * @param month 作成対象月
	 */
    public void searchCheckHistory(int year, int month) {
    	List<ChecklistRow> checklistRowList = new ArrayList<>();
    	Calendar targetYM = ApDateUtil.getCalendar(year, month);
    	Map<Short, ChecklistRow> tmpChecklistRowMap = new HashMap<>();

    	// 指定された年月のチェック履歴リストをDBから取得する
    	List<CheckHistory> checkHistoryList = checkHistoryDao.searchCheckHistory(year, month);

    	// DBに存在した日付の行情報を保持する
    	for (CheckHistory ch : checkHistoryList) {
    		ChecklistRow row = new ChecklistRow();
    		row.setUpdateRow(true);
    		row.setCheckHistory(ch);
    		tmpChecklistRowMap.put(ch.getCheckHistoryPK().getCheckDay(), row);
    	}

    	// DBに存在しなかった日は新規に行情報を作成する
    	for (short day = 1; day <= targetYM.getActualMaximum(Calendar.DATE); day++) {
    		if (tmpChecklistRowMap.containsKey(day)) {
    			checklistRowList.add(tmpChecklistRowMap.get(day));
    			continue;
    		}

    		ChecklistRow row = new ChecklistRow();
    		CheckHistory ch = new CheckHistory();
    		CheckHistoryPK pk = new CheckHistoryPK();
    		pk.setCheckYear((short) targetYM.get(Calendar.YEAR));
    		pk.setCheckMonth((short) (targetYM.get(Calendar.MONTH) + 1));
    		pk.setCheckDay(day);
    		pk.setChackDateSeq("0");
    		ch.setCheckHistoryPK(pk);
    		row.setNewRow(true);
    		row.setCheckHistory(ch);
    		checklistRowList.add(row);
    	}

		// 各行の曜日を設定
    	for (ChecklistRow row : checklistRowList) {
    		targetYM.set(Calendar.DATE, row.getCheckHistory().getCheckHistoryPK().getCheckDay());
    		row.setWeek(targetYM.get(Calendar.DAY_OF_WEEK));
    	}

    	form.setChecklistRowList(checklistRowList);
    }

    /**
     * チェックリスト画面の一覧情報を保存する
     */
    public void registChecklist() {
    	CheckHistory ch;

    	try {
	    	for (ChecklistRow row : form.getChecklistRowList()) {
	    		if (row.isNewRow()) {
		    		ch = row.getCheckHistory();

		    		// 新規行の場合、何らかの入力がされている場合のみ登録する
		    		if (ChecklistRow.isInput(ch)) {
			    		ch.setRegistDate(ApDateUtil.getSystemDate());
			    		ch.setRegistUserId(loginSession.getLoginUser().getUserId());
			    		ch.setUpdateDate(ApDateUtil.getSystemDate());
			    		ch.setUpdateUserId(loginSession.getLoginUser().getUserId());
			    		checkHistoryDao.create(ch);

			    		row.setUpdateRow(true);
			    		row.setNewRow(false);
		    		}

	    		} else if (row.isUpdateRow()) {
		    		ch = row.getCheckHistory();
		    		ch.setUpdateDate(ApDateUtil.getSystemDate());
		    		ch.setUpdateUserId(loginSession.getLoginUser().getUserId());
		    		checkHistoryDao.edit(ch);
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
    }
 }
