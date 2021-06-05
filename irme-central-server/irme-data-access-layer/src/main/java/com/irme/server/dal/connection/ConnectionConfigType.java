package com.irme.server.dal.connection;

public enum ConnectionConfigType {
    PRODUCTION {
        @Override
        public String configFilename() {

            return "app.prod.properties";
        }
    },
    DEVLOPMENT {
        @Override
        public String configFilename() {

            return "app.dev.properties";
        }
    },
    TESTING {
        @Override
        public String configFilename() {

            return "app.test.properties";
        }
    };

    public abstract String configFilename();
}
