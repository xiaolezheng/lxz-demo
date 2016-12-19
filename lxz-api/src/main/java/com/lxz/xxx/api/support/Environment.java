package com.lxz.xxx.api.support;

public enum Environment {
    TEST(Config.TEST_HOST), PROD(Config.PROD_HOST);

    private String host;

    Environment(String host) {
        this.host = host;
    }

    public String value() {
        return host;
    }
}