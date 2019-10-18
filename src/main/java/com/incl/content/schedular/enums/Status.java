package com.incl.content.schedular.enums;

public enum Status {
	SUBMITTED(0), INPROGRESS(1), FAILED(2), COMPLETED(3), DUPLICATE(4), INVALID(5), PARSED(6), PREVALIDATE(7), ZIPPROCESSINGFAIL(8);

	private final int value;

	Status(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
