package com.example.visionsharp;

import android.provider.BaseColumns;

public class UserProfile {

    private UserProfile() {}

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "userName";
        public static final String COLUMN_2 = "userEmail";
        public static final String COLUMN_3 = "phone";
        public static final String COLUMN_4 = "password";


    }


}
