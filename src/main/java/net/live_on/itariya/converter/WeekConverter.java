package net.live_on.itariya.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import net.live_on.itariya.constant.Week;

/**
 * 曜日コンバータ
 */
@FacesConverter("WeekConverter")
public class WeekConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return Week.nameToCode(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	return Week.codeToName(value.toString());
    }
}
