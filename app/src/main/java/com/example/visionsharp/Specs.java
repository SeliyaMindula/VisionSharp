package com.example.visionsharp;

import android.provider.BaseColumns;

public class Specs {
    private Specs() {}

    public static class spec implements BaseColumns {
        public static final String TABLE_NAME = "Spec";
        public static final String COLUMN_1 = "type";
        public static final String COLUMN_2 = "phone";

    }
}
