package me.JollyPerson.BanManager.MySQL;


import me.JollyPerson.BanManager.BanManager;

public class MySQLHandler {

    public String host, database, username, password, table;
    public int port;


    private BanManager main;

    public MySQLHandler(BanManager main) {
        this.main = main;
        host = main.getConfig().getString("host");
        port = main.getConfig().getInt("port");
        database = main.getConfig().getString("database");
        username = main.getConfig().getString("username");
        password = main.getConfig().getString("password");
        table = main.getConfig().getString("table");

        main.saveConfig();
    }

}
