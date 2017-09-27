package net.live_on.itariya.entrec.form.databean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import net.live_on.itariya.entrec.entity.CheckHistory;

@Data
@ViewScoped
public class ChecklistRow implements Serializable {
	private static final long serialVersionUID = 1;

    private int week;

    private boolean newRow;

    private boolean updateRow;

    private CheckHistory checkHistory;

    /**
     * 入力済の行であるか検証する
     */
    public static boolean isInput(CheckHistory ch) {
    	if (StringUtils.isNotEmpty(ch.getEntryUserId())) return true;
    	if (StringUtils.isNotEmpty(ch.getExitUserId())) return true;
    	if (ch.getEntryTime() != null) return true;
    	if (ch.getExitTime() != null) return true;
    	if (ch.getPcPower()) return true;
    	if (ch.getClearDesk()) return true;
    	if (ch.getStorageLock()) return true;
    	if (ch.getLightOff()) return true;
    	if (ch.getDoorWindowLock()) return true;
    	return false;
    }
}
