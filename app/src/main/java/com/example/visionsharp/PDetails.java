package com.example.visionsharp;

import android.provider.BaseColumns;

public class PDetails {

    private PDetails() {}

    public static class Payments implements BaseColumns {
        public static final String TABLE_NAME = "PaymenetDet";
        public static final String COLUMN_1 = "holderName";
        public static final String COLUMN_2 = "cardNum";
        public static final String COLUMN_3 = "exDate";
        public static final String COLUMN_4 = "cvvNum";
    }

}
