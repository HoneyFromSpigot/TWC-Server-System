package io.github.thewebcode.twcwaterfall.command;

import io.github.thewebcode.twcwaterfall.TWCWaterfall;
import io.github.thewebcode.twcwaterfall.utils.FileService;
import io.github.thewebcode.twcwaterfall.utils.setting.Setting;
import io.github.thewebcode.twcwaterfall.utils.setting.SettingManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class MaintenanceModeCommand extends Command {

    public MaintenanceModeCommand() {
        super("maintenance");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("twc.maintenance")) {
            sender.sendMessage(TWCWaterfall.PREFIX + "§cDu kannst diesen Command nicht benutzen!");
            return;
        }

        if (args.length == 0) {
            boolean maintenanceMode = SettingManager.get().getValue(Setting.MAINTENANCE_MODE);
            SettingManager.get().set(Setting.MAINTENANCE_MODE, !maintenanceMode);
            sender.sendMessage(TWCWaterfall.PREFIX + "§aDer Wartungs-Modus ist nun " + (maintenanceMode ? "§causgeschaltet" : "§aeingeschaltet"));
            return;
        }

        if (args[0].equalsIgnoreCase("on")) {

            if (args.length == 2) {
                String date = args[1];
                SettingManager.get().set(Setting.MAINTENANCE_MODE, true);

                FileService service = FileService.getService();
                Configuration config = service.getConfig();
                config.set("MaintenanceMode.date", date);


                sender.sendMessage(TWCWaterfall.PREFIX + "§aDer Wartungs-Modus ist nun §aeingeschaltet§a, und sollte am §6" + date + "§a wieder ausgeschaltet werden! §6(nicht automatisch)");
                return;
            }else{
                SettingManager.get().set(Setting.MAINTENANCE_MODE, true);
                sender.sendMessage(TWCWaterfall.PREFIX + "§aDer Wartungs-Modus ist nun §aeingeschaltet");
                return;
            }
        }

        if (args[0].equalsIgnoreCase("off")) {
            SettingManager.get().set(Setting.MAINTENANCE_MODE, false);
            FileService service = FileService.getService();
            Configuration config = service.getConfig();
            config.set("MaintenanceMode.date", null);
            sender.sendMessage(TWCWaterfall.PREFIX + "§aDer Wartungs-Modus ist nun §causgeschaltet");
            return;
        }
    }
}
