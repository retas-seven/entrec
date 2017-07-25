package net.live_on.itariya.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import lombok.Data;
import net.live_on.itariya.form.databean.ChecklistRow;

@SessionScoped
@Data
public class ChecklistForm implements Serializable {
	private static final long serialVersionUID = 1;

	private Date calendarDate;

	private List<ChecklistRow> checklistRowList;
}

