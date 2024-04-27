package com.kazuha.mireport.playercommandmodule;

import com.kazuha.mireport.main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class miraireport extends Command {
    public miraireport(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] arg) {
        if(arg.length < 1){
            sender.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "Mirai" + ChatColor.YELLOW + "Reports" + ChatColor.GRAY + " By " + ChatColor.AQUA + "Frk" + ChatColor.GOLD + "ovo"));
            sender.sendMessage(new TextComponent("§7//深山踏红叶 耳畔闻鹿鸣"));
            sender.sendMessage(new TextComponent("§f§l游戏内可用命令列表:"));
            sender.sendMessage(new TextComponent("§2/report <目标> <理由> §f 举报一个玩家"));
            if(sender.hasPermission("miraireport.admin")){
                sender.sendMessage(new TextComponent("§2/miraireport reload §f重载配置文件"));
            }
            sender.sendMessage(new TextComponent("§f§lQQ群内可用命令列表"));
            sender.sendMessage(new TextComponent("§2.unban §f自助解封(请先绑定QQ)"));
            sender.sendMessage(new TextComponent("§2.bind §f绑定QQ"));
            sender.sendMessage(new TextComponent("§f§lQQ内可用命令列表(私聊机器人)"));
            sender.sendMessage(new TextComponent("§2.举报 <目标> <理由> §f 举报一个玩家"));
            if(sender.hasPermission("miraireport.admin")){
                sender.sendMessage(new TextComponent("§2.ban <目标> §f 封禁一个玩家"));
                sender.sendMessage(new TextComponent("§2.unban <目标> §f 解封一个玩家"));
                sender.sendMessage(new TextComponent("§2.mute <目标> §f 禁言一个玩家"));
                sender.sendMessage(new TextComponent("§2.kick <目标> §f 踢出一个玩家"));
                sender.sendMessage(new TextComponent("§2.cmd <命令> §f (BC)执行一个命令"));
            }
            return;
        }
        if(arg.length > 0 || arg[0] == "reload"){
            if(!sender.hasPermission("miraireport.admin")){
                sender.sendMessage(new TextComponent("§c你没有权限"));
                return;
            }
            long now = System.currentTimeMillis();
            try {
                main.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(main.instance.getDataFolder(), "config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sender.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "Mirai" + ChatColor.YELLOW + "Reports" + "§f重载完成. 用时" + (System.currentTimeMillis() - now) + "ms"));
        }
    }
}
