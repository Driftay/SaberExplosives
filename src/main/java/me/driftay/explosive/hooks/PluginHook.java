package me.driftay.explosive.hooks;

import me.driftay.explosive.SaberExplosives;

public interface PluginHook<T> {

    T setup(SaberExplosives plugin);

    String getName();


}
