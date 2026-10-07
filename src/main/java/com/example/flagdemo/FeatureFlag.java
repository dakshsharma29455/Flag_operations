package com.example.flagdemo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FeatureFlag {

    @Id
    private String name;
    private boolean enabled;
    private String description;

    public FeatureFlag() {}

    public FeatureFlag(String name, boolean enabled, String description) {
        this.name = name;
        this.enabled = enabled;
        this.description = description;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
