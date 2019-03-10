package com.example.demo.models.enums;

public enum State {
	NEW("Нов"),USED("Употребяван"),FOR_PARTS("За части");
	
	private String value;

	State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
