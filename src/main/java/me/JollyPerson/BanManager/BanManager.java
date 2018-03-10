package me.JollyPerson.BanManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.JollyPerson.BanManager.MySQL.MySQL;
import me.JollyPerson.BanManager.MySQL.MySQLHandler;
import me.JollyPerson.BanManager.commands.*;
import org.bukkit.plugin.java.JavaPlugin;


public class BanManager extends JavaPlugin {


    public MySQLHandler mysqlHandler;
    public MySQL mySQL;
    public HikariDataSource dataSource;

    @Override
    public void onEnable() {
        this.mysqlHandler = new MySQLHandler(this);
        this.mySQL = new MySQL(this);
        loadConfig();
        getCommand("ban").setExecutor(new banCommand(mySQL));
        getCommand("tempban").setExecutor(new tempbanCommand());
        getCommand("kick").setExecutor(new kickCommand(this));
        getCommand("mute").setExecutor(new muteCommand());
        getCommand("tempmute").setExecutor(new tempmuteCommand());
        getCommand("warn").setExecutor(new warnCommand());
        saveConfig();
        setupPool();
        mySQL.createTableBanned();
        mySQL.createTableMuted();
        mySQL.createTableTempBanned();
        mySQL.createTableTempMuted();
    }

    @Override
    public void onDisable() {
    }


    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void setupPool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + mysqlHandler.host + ":" + mysqlHandler.port + "/" + mysqlHandler.database);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername(mysqlHandler.username);
        config.setPassword(mysqlHandler.password);

        try {
            this.dataSource = new HikariDataSource(config);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
