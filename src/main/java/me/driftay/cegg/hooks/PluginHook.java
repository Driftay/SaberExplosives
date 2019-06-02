package me.driftay.cegg.hooks;

import me.driftay.cegg.dCeggs;

public interface PluginHook<T> {

    T setup(dCeggs plugin);

    String getName();


}
