package com.example.flagdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flags")
public class FeatureFlagController {

    private final FeatureFlagService service;

    public FeatureFlagController(FeatureFlagService service) {
        this.service = service;
    }

    // 1. ADD FLAG
    @PostMapping
    public ResponseEntity<FeatureFlag> createFlag(@RequestBody FeatureFlag flag) {
        return ResponseEntity.ok(service.createFlag(flag));
    }

    // 2. READ FLAGS & EVALUATE
    @GetMapping
    public List<FeatureFlag> getAllFlags() {
        return service.getAllFlags();
    }

    @GetMapping("/{name}/evaluate")
    public ResponseEntity<Map<String, Object>> evaluateFlag(@PathVariable String name) {
        boolean isEnabled = service.isFlagEnabled(name);
        return ResponseEntity.ok(Map.of("flag", name, "enabled", isEnabled));
    }

    // 3. TOGGLE FLAG (UPDATE)
    @PutMapping("/{name}/toggle")
    public ResponseEntity<FeatureFlag> toggleFlag(@PathVariable String name, @RequestParam boolean enabled) {
        return service.toggleFlag(name, enabled)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. DELETE FLAG
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteFlag(@PathVariable String name) {
        if (service.deleteFlag(name)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // BUSINESS DEMO ENDPOINT
    @GetMapping("/checkout-demo")
    public ResponseEntity<String> runCheckoutDemo() {
        return ResponseEntity.ok(service.getCheckoutFlowMessage());
    }
}
