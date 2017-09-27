package net.live_on.itariya.entrec.constant;

/**
 * EjbException発生原因のEnumクラス
 */
public enum EjbExceptionCause {
	/** その他 */
	OTHER,

	/** Version値による排他例外 */
	UPDATED,

	/** 一意制約例外 */
	UNIQUE_CONSTRAINT;
}
