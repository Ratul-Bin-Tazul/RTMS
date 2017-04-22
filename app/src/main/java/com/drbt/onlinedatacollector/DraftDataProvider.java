package com.drbt.onlinedatacollector;

/**
 * Created by SAMSUNG on 3/24/2017.
 */

public class DraftDataProvider {
    private String draftName,saveTime;

    public DraftDataProvider(String draftName, String saveTime) {
        this.draftName = draftName;
        this.saveTime = saveTime;
    }

    public String getDraftName() {
        return draftName;
    }

    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }
}
