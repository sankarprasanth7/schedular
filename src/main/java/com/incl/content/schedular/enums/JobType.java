package com.incl.content.schedular.enums;

public enum JobType {
	ONIX(0), BUILDER(1), DEFAULT(-1);
	
	private final int value;

	JobType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
