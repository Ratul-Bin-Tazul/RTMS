package com.drbt.onlinedatacollector;

import android.provider.BaseColumns;

public final class internalDatabase {

    private internalDatabase() {}

    public static class internalDatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "test_table1";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DNSO = "name_of_DNSO";
        public static final String COLUMN_NAME_DISTRICT = "district";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_FIRST_LINE = "first_line";
        public static final String COLUMN_NAME_FRONT_LINE = "front_line";
        public static final String COLUMN_NAME_MALE = "male";
        public static final String COLUMN_NAME_FEMALE = "female";
        public static final String COLUMN_NAME_ANTHROPOCENTRI = "anthropocentric_measurement";
        public static final String COLUMN_NAME_SYNC_STATE = "Synced";

        public static final String SERVER_NAME = "https://rafathossain.com/androidupdate.php";
        public static final String UI_UPDATE_BROADCAST = "com.drbt.onlinedatacollector.uiupdatebroadcast";

    }

    public static class draftDatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "draft_table";
        public static final String DRAFT_NAME = "Draft_Name";
        public static final String DRAFT_TIME = "Draft_Time";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DNSO = "name_of_DNSO";
        public static final String COLUMN_NAME_DISTRICT = "district";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_FIRST_LINE = "first_line";
        public static final String COLUMN_NAME_FRONT_LINE = "front_line";
        public static final String COLUMN_NAME_MALE = "male";
        public static final String COLUMN_NAME_FEMALE = "female";
        public static final String COLUMN_NAME_ANTHROPOCENTRI = "anthropocentric_measurement";
        public static final String COLUMN_NAME_SYNC_STATE = "Synced";
    }

}
