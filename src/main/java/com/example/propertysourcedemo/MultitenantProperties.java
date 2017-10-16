package com.example.propertysourcedemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.UUID;

@ConfigurationProperties
public class MultitenantProperties {
    private Map<String, TenantProperties> tenants;

    public Map<String, TenantProperties> getTenants() {
        return tenants;
    }

    public void setTenants(Map<String, TenantProperties> tenants) {
        this.tenants = tenants;
    }

    public static class TenantProperties {
        private String name;
        private Boolean active;
        private String senderEmail;
        private DataSourceProperties datasource;

        public boolean isActive() {
            return active == null ? true : active;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public String getSenderEmail() {
            return senderEmail;
        }

        public void setSenderEmail(String senderEmail) {
            this.senderEmail = senderEmail;
        }

        public DataSourceProperties getDatasource() {
            return datasource;
        }

        public void setDatasource(DataSourceProperties datasource) {
            this.datasource = datasource;
        }
    }

    public static class DataSourceProperties {
        private String name;
        private String url;
        private String username;
        private String password;
        private String driverClassName;

        public String getName() {
            if (name == null || name.isEmpty()) name = UUID.randomUUID().toString();
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }
}

