package net.live_on.itariya.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

/**
 * YYYY/MM/DD形式コンバータ
 */
@FacesConverter("SlashDateConverter")
public class SlashDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value.toString().replace("/", "");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	String str = value.toString();
    	String ret;

    	if (str.matches("^[0-9]{8}$")) {
//    		ret = StringUtils.mid(str, 0, 4) + "/" + StringUtils.mid(str, 4, 2) + "/" +  StringUtils.mid(str, 6, 2);
    		ret = Integer.parseInt(StringUtils.mid(str, 4, 2)) + "/" +  Integer.parseInt(StringUtils.mid(str, 6, 2));
    	} else {
    		ret = str;
    	}

    	return ret;
    }
}
