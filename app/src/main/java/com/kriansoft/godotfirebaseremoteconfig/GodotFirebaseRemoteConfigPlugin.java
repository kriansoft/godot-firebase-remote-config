package com.kriansoft.godotfirebaseremoteconfig;

import androidx.annotation.NonNull;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import java.util.HashSet;
import java.util.Set;

public class GodotFirebaseRemoteConfigPlugin extends GodotPlugin {
    private final FirebaseRemoteConfig config;

    public GodotFirebaseRemoteConfigPlugin(Godot godot) {
        super(godot);
        config = createConfig();
        config.fetchAndActivate().addOnCompleteListener(task ->
                emitSignal("remote_config_received")
        ).addOnFailureListener(task ->
            emitSignal("remote_config_fetch_error", task.getMessage())
        );
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "GodotFirebaseRemoteConfig";
    }

    @UsedByGodot
    public boolean getBoolean(final String key) {
        return config.getBoolean(key);
    }

    @UsedByGodot
    public String getString(final String key) {
        return config.getString(key);
    }

    @UsedByGodot
    public int getLong(final String key) {
        return (int)config.getLong(key);
    }

    @UsedByGodot
    public float getDouble(final String key) {
        return (float)config.getDouble(key);
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        final Set<SignalInfo> signals = new HashSet<>();
        signals.add(new SignalInfo("remote_config_received"));
        signals.add(new SignalInfo("remote_config_fetch_error", String.class));
        return signals;
    }

    private FirebaseRemoteConfig createConfig() {
        final FirebaseRemoteConfig currentConfig = FirebaseRemoteConfig.getInstance();
        final FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        currentConfig.setConfigSettingsAsync(configSettings);
        return currentConfig;
    }
}
