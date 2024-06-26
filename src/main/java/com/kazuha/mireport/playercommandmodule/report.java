package com.kazuha.mireport.playercommandmodule;

import com.kazuha.mireport.main;
import com.kazuha.mireport.playercommandmodule.ReportGui.drawGui;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;
import java.util.Map;

import static com.kazuha.mireport.main.config;
import static com.kazuha.mireport.utils.publicutisl.sendReportMessageGlobal;

public class report extends Command {
    public Map<CommandSender,Long> cd = new HashMap<>();
    public report(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] arg) {
        if(!sender.hasPermission("miraireport.report")){
            sender.sendMessage(new TextComponent("§c权限不足"));
            return;
        }
        if(arg.length < 1){
            sender.sendMessage(new TextComponent("§c参数错误: /report <玩家名> [理由]"));
            return;
        }
        if(ProxyServer.getInstance().getPlayer(arg[0]) == null){
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',main.config.getString("playernotOLmessage"))));
            return;
        }
        if(arg.length < 2){
            new drawGui(ProxyServer.getInstance().getPlayer(sender.getName()), ProxyServer.getInstance().getPlayer(arg[0]));
            return;
        }
        if(cd.containsKey(sender)){
            if((System.currentTimeMillis() - cd.get(sender)) < (main.config.getInt("cooldown")*1000L)){
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',main.config.getString("cooldownmsg").replace("%cd%", String.valueOf(main.config.getInt("cooldown") - (System.currentTimeMillis()-cd.get(sender))/1000L)))));
                return;
            }
        }else{
            cd.put(sender, System.currentTimeMillis());
        }
        cd.put(sender, System.currentTimeMillis());
        StringBuilder ReportMsg = new StringBuilder();
        for(int i = 1;i< arg.length; i++)ReportMsg.append(arg[i]).append(" ");
        sendReportMessageGlobal(ReportMsg.toString(),arg[0],sender.getName(), true);
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',config.getString("reportSuccessMessage"))));
    }
}
