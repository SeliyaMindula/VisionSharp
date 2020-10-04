package com.example.visionsharp;

import android.provider.BaseColumns;

public class BookProfile {
    private BookProfile(){}

    public static class Res implements BaseColumns {
        public static final String TABLE_NAME = "BookDoc";
        public static final String COL_1 = "date";
        public static final String COL_2 = "doctor";
        public static final String COL_3 = "phone";

    }

}
