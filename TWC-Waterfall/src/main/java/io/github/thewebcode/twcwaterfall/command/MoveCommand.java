package io.github.thewebcode.twcwaterfall.command;

import io.github.thewebcode.twcwaterfall.TWCWaterfall;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MoveCommand extends Command {

    public MoveCommand() {
        super("move");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("twc.move")) {
            sender.sendMessage(TWCWaterfall.PREFIX + "§cDu kannst diesen Command nicht benutzen!");
            return;
        }

        if (args.length != 2) {
            sender.sendMessage(TWCWaterfall.PREFIX + "§cBitte benutze §6/move <Player> <Server>");
            return;
        }

        ProxiedPlayer target = TWCWaterfall.getInstance().getProxy().getPlayer(args[0]);
        ServerInfo server = TWCWaterfall.getInstance().getProxy().getServerInfo(args[1]);

        if (target == null) {
            sender.sendMessage(TWCWaterfall.PREFIX + "§cDer Spieler wurde nicht gefunden!");
            return;
        }

        if (server == null) {
            sender.sendMessage(TWCWaterfall.PREFIX + "§cDer Server wurde nicht gefunden!");
            return;
        }

        target.connect(server);
        target.sendMessage(TWCWaterfall.PREFIX + "§aDu wurdest auf diesen Server verschoben!");
        sender.sendMessage(TWCWaterfall.PREFIX + "§aDu hast §6" + target.getName() + "§a auf §6" + server.getName() + " §averschoben!");
    }
}
