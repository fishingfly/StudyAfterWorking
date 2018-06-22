package com.zy.test;

public class EnumDemo2 {
    public static void main(String[] args) {
        System.out.println(Week.MONDAY.getValue());
    }
}

enum Week {
    MONDAY("MON", 1),TUESDAT("TUES", 2),WEDNESDAY("WED", 3),THURSDAY("THURS", 4);
    private String value;
    private Integer typeId;
    Week(String value, Integer typeId) {
        this.value = value;
        this.typeId = typeId;
    }
    public String getValue() {
        return value;
    }

    public Integer getTypeId() {
        return typeId;
    }

}