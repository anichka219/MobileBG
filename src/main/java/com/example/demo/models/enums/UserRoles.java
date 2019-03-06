package com.example.demo.models.enums;

public enum UserRoles {
	ROLE_ADMIN(1), ROLE_USER(2);
    private int value;

    UserRoles(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
