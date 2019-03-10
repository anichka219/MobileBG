package com.example.demo.models.enums;

public enum EngineType {
	GASOLINE("Бензинов"),DIESEL("Дизелов"),ELECTRICAL("Електрически"),HYBRID("Хибриден");
	
	private String value;

    EngineType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
