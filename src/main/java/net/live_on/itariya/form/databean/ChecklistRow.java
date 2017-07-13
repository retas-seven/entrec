package net.live_on.itariya.form.databean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;

import lombok.Data;
import net.live_on.itariya.entity.CheckHistory;

@Data
@ViewScoped
public class ChecklistRow implements Serializable {
	private static final long serialVersionUID = 1;

    private int week;

    private boolean newRow;

    private boolean updateRow;

    private CheckHistory checkHistory;
}
