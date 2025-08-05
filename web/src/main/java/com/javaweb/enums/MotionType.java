package com.javaweb.enums;


public enum MotionType {
    person("Chuyển động của người"),
    unknown("Chuyển động khác");
    private final String name;
    MotionType(String name) {
        this.name = name;
    }
    public static String getMotionTypeName(String status) {
        if (status != null && !status.isEmpty()){
            return MotionType.valueOf(status).name;
        }
        return null;
    }
}
