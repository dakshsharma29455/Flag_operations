package com.example.flagdemo;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureFlagService {

    private final FeatureFlagRepository repository;

    public FeatureFlagService(FeatureFlagRepository repository) {
        this.repository = repository;
    }

    public FeatureFlag createFlag(FeatureFlag flag) {
        return repository.save(flag);
    }

    public List<FeatureFlag> getAllFlags() {
        return repository.findAll();
    }

    public boolean isFlagEnabled(String name) {
        return repository.findById(name)
                .map(FeatureFlag::isEnabled)
                .orElse(false);
    }

    public Optional<FeatureFlag> toggleFlag(String name, boolean enabled) {
        return repository.findById(name)
                .map(flag -> {
                    flag.setEnabled(enabled);
                    return repository.save(flag);
                });
    }

    public boolean deleteFlag(String name) {
        if (repository.existsById(name)) {
            repository.deleteById(name);
            return true;
        }
        return false;
    }

    public String getCheckoutFlowMessage() {
        boolean isNewFlow = isFlagEnabled("NEW_CHECKOUT_FLOW");
        if (isNewFlow) {
            return "🟢 Running NEW 1-Click Checkout Flow!";
        } else {
            return "🔴 Running LEGACY 3-Step Checkout Flow.";
        }
    }
}