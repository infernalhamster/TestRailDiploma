package com.bercut.pltest.mashup.client;

public enum ApiName {
    GET_USERS("/get_users"),
    GET_USER("/get_user"),
    GET_PLANS("/get_plans"),
    GET_PLAN("/get_plan"),
    GET_RUNS("/get_runs"),
    GET_CASES("/get_cases"),
    GET_PROJECTS("/get_projects");

    private final String method;

    ApiName(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
