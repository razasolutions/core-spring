package net.raza.core.enums;

import lombok.Getter;

/**
 * The Enum YesOrNoEnum.
 * 
 * Handles
 * 
 * @author tazabreu
 * 
 */
public enum YesOrNoEnum {

	YES("enums.yesOrNo.yes", 1, true), NO("enums.yesOrNo.no", 0, false);

	/**
	 * Enum default constructor.
	 *
	 * @param messageCode
	 *            pre-defining each enumerable property it's default message code.
	 * @param integerCode
	 *            pre-defining each enumerable property it's default integer code.
	 * @param booleanCode
	 *            pre-defining each enumerable property it's default boolean code.
	 */
	YesOrNoEnum(String messageCode, int integerCode, boolean booleanCode) {
		this.messageCode = messageCode;
		this.integerCode = integerCode;
		this.booleanCode = booleanCode;
	}


	/** The message code. */
	@Getter
	private final String messageCode;


	/** The integer code. */
	@Getter
	private final int integerCode;


	/** The boolean code. */
	private final boolean booleanCode;
	
	/**
	 * Gets the boolean code.
	 *
	 * @return the boolean code
	 */
	public boolean getBooleanCode() {
		return this.booleanCode;
	}

}
