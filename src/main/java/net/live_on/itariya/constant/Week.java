package net.live_on.itariya.constant;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 曜日のEnumクラス
 */
public enum Week {
	SUNDAY(String.valueOf(Calendar.SUNDAY), "日"),
	MONDAY(String.valueOf(Calendar.MONDAY), "月"),
	TUESDAY(String.valueOf(Calendar.TUESDAY), "火"),
	WEDNESDAY(String.valueOf(Calendar.WEDNESDAY), "水"),
	THURSDAY(String.valueOf(Calendar.THURSDAY), "木"),
	FRIDAY(String.valueOf(Calendar.FRIDAY), "金"),
	SATURDAY(String.valueOf(Calendar.SATURDAY), "土");

	/** コードをキーとして要素を保持するMap */
	private static final Map<String, Week> CODE_MAP;

	/** 名称をキーとして要素を保持するMap */
	private static final Map<String, Week> NAME_MAP;

	/** コード値 */
	private final String code;

	/** 名称 */
	private final String name;

    /**
     * コンストラクタ
     * @param code コード値
     * @param name 名称
     */
	private Week(String code, String str) {
		this.code = code;
		this.name = str;
	}

	static {
		Week[] elements = Week.values();
		Map<String, Week> codeMap = new HashMap<>();
		Map<String, Week> nameMap = new HashMap<>();

		for (Week element : elements) {
			codeMap.put(element.getCode(), element);
			nameMap.put(element.getName(), element);
		}

		CODE_MAP = codeMap;
		NAME_MAP = nameMap;
	}

    /**
     * コード値を取得する
     * @return コード値
     */
    public String getCode() {
        return code;
    }

    /**
     * 名前を取得する
     * @return コード値
     */
    public String getName() {
        return name;
    }

    /**
    * 名称に該当するコードを取得する
    * @param name 名称
    * @return コード
    */
	public static String nameToCode(String name) {
		String ret = null;

		if (NAME_MAP.containsKey(name)) {
			ret = NAME_MAP.get(name).getCode();
		}

		return ret;
	}

    /**
    * コードに該当する名称を取得する
    * @param code コード
    * @return 名称
    */
	public static String codeToName(String code) {
		String ret = null;

		if (CODE_MAP.containsKey(code)) {
			ret = CODE_MAP.get(code).getName();
		}

		return ret;
	}
}
