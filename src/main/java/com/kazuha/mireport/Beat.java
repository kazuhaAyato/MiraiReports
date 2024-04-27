package com.kazuha.mireport;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.MiraiMC;
import me.dreamvoid.miraimc.bungee.event.bot.MiraiBotOfflineEvent;
import me.dreamvoid.miraimc.bungee.event.bot.MiraiBotReloginEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Beat implements Listener {
    @EventHandler
    public void onDeath(MiraiBotOfflineEvent e){
        e.getBot().doOnline();
        main.instance.getLogger().warning("§4§l警告 §cQQBot已掉线。 已尝试自救.");
        for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()){
            if(p.hasPermission("miraireport.notify")){
                p.sendMessage(new TextComponent("§4§l警告 §cQQBot已掉线。 已尝试自救，如果频繁收到此条消息请立刻联系Frk."));
            }
        }
    }
    @EventHandler
    public void onEJD(MiraiBotReloginEvent e){
        for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()){
            if(p.hasPermission("miraireport.notify")){
                p.sendMessage(new TextComponent("§aQQBot已重新上线。"));
            }
        }

    }
}
