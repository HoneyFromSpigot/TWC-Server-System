package io.github.thewebcode.twcwaterfall.event;

import io.github.thewebcode.twcwaterfall.TWCWaterfall;
import io.github.thewebcode.twcwaterfall.utils.FileService;
import io.github.thewebcode.twcwaterfall.utils.setting.Setting;
import io.github.thewebcode.twcwaterfall.utils.setting.SettingManager;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class Eventhandler implements Listener {
    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        PendingConnection connection = event.getConnection();
        ServerPing response = event.getResponse();

        boolean maintenanceMode = SettingManager.get().getValue(Setting.MAINTENANCE_MODE);

        if (maintenanceMode) {
            FileService service = FileService.getService();
            Configuration config = service.getConfig();

            response.setVersion(new ServerPing.Protocol("§c§lWartungsarbeiten", -1));
            String description = config.contains("MaintenanceMode.date") ?
                    "§7§o§lTWC-NETWORK §8>> §c§lWartungsarbeiten\n" +
                            "§7Nicht verfügbar bis §6" + config.getString("MaintenanceMode.date") :
                    "§7§o§lTWC-NETWORK §8>> §c§lWartungsarbeiten\n" +
                            "§8>> §7Zurzeit leider nicht verfügbar!";
            response.setDescription(description);

            event.setResponse(response);
        }
    }

    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        boolean maintenanceMode = SettingManager.get().getValue(Setting.MAINTENANCE_MODE);

        if (maintenanceMode) {

            if (player.hasPermission("twc.forcejoin")) {
                player.sendMessage(TWCWaterfall.PREFIX + "§aDer Wartungsmodus ist aktiviert, aber du kannst weiterhin joinen!");
                return;
            }
            FileService service = FileService.getService();
            Configuration config = service.getConfig();

            String message = "§8----------------------------------------\n" +
                    "§4§lWartungsarbeiten\n\n" +
                    "§7Zurzeit befindet sich der Server in §6Wartungsarbeiten§7, und ist \n" +
                    "deswegen leider nicht verfügbar.\n\n" +
                    (config.contains("MaintenanceMode.date") ?
                            "§7Die Wartungsarbeiten enden vorraussichtlich am §6" +
                                    config.getString("MaintenanceMode.date") + "\n\n" : "") +
                    "§\n§7§o§lTWC-NETWORK\n" +
                    "§8----------------------------------------";

            player.disconnect(message);
            event.setCancelled(true);
        }
    }
}
