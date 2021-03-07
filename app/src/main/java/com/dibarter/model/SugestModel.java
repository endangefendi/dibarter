package com.dibarter.model;

public class SugestModel {
    private String suggestion;
    private String values;

    public SugestModel(String suggestion, String values){
        this.suggestion=suggestion;
        this.values=values;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
