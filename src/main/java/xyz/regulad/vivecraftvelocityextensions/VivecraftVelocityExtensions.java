package xyz.regulad.vivecraftvelocityextensions;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.bstats.velocity.Metrics;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "vivecraftvelocityextensions", name = "VivecraftVelocityExtensions", version = "1.0.0-SNAPSHOT",
        description = "Adds support for Vivecraft clients connecting to servers with Vivecraft extensions via a Velocity proxy.",
        url = "https://github.com/regulad/VelocityGradleTemplate", authors = {"regulad"})
public class VivecraftVelocityExtensions {
    public static final @NotNull String VIVECRAFT_CHANNEL = "vivecraft:data";

    @Inject
    private @NotNull ProxyServer proxyServer;
    @Inject
    private @NotNull Logger logger;
    @Inject
    private @NotNull Metrics.Factory metricsFactory;
    @Inject
    @DataDirectory
    private @NotNull Path path;

    @Subscribe
    public void onProxyInitialization(final @NotNull ProxyInitializeEvent proxyInitializeEvent) {
        final @NotNull Metrics metrics = this.metricsFactory.make(this, 13691);
    }

    @Subscribe
    public void onPluginMessagingChannelMessage(final @NotNull PluginMessageEvent pluginMessageEvent) {
        if (pluginMessageEvent.getIdentifier().getId().equals(VIVECRAFT_CHANNEL)) {
            pluginMessageEvent.setResult(PluginMessageEvent.ForwardResult.forward());
        }
    }
}
