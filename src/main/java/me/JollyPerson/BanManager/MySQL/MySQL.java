package me.JollyPerson.BanManager.MySQL;

import me.JollyPerson.BanManager.BanManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class MySQL {

    public BanManager main;
    public String database;
    public Connection connection;

    public MySQL(BanManager main) {
        this.main = main;
        database = main.getConfig().getString("database");
    }

    public void createTableBanned() {
        String checkTable = "CREATE TABLE IF NOT EXISTS `banned` (`BannedPlayerName` char(16),`BannedPlayerUUID` char(36), `Reason` varchar(200), `Banner` char(16))";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(checkTable);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createTableTempBanned() {
        String checkTable = "CREATE TABLE IF NOT EXISTS `tempbanned` (`BannedPlayerName` char(16),`BannedPlayerUUID` char(36), `CurrentEpoch` bigint, `UnbannedEpoch` bigint,`Reason` varchar(200), `Banner` char(16));";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(checkTable);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createTableMuted() {
        String checkTable = "CREATE TABLE IF NOT EXISTS `muted` (`mutedPlayerName` char(16),`mutedPlayerUUID` char(36),`Reason` varchar(200), `Muter` char(16));";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(checkTable);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void createTableTempMuted() {
        String checkTable = "CREATE TABLE IF NOT EXISTS `tempmuted` (`mutedPlayerName` char(16),`mutedPlayerUUID` char(36), `CurrentEpoch` bigint, `UnmuttedEpoch` bigint,`Reason` varchar(200), `Muter` char(16));";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(checkTable);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isBanned(String playerUUID) {
        String isBanned = "SELECT * from `banned` WHERE BannedPlayerUUID = ?";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(isBanned);
            statement.setString(1, playerUUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                System.out.println("Found user");
                System.out.println(result.getString("BannedPlayerName"));
                System.out.println(result.getString("BannedPlayerUUID"));
                System.out.println(result.getString("Reason"));
                System.out.println(result.getString("Banner"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean isTempBanned(String playerUUID) {
        String isBanned = "SELECT * from tempbanned WHERE BannedPlayerUUID = ?";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(isBanned);
            statement.setString(1, playerUUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean isTempMuted(String playerUUID) {
        String isBanned = "SELECT * from tempmuted WHERE mutedPlayerUUID = ?";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(isBanned);
            statement.setString(1, playerUUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean isMuted(String playerUUID) {
        String isBanned = "SELECT * from muted WHERE mutedPlayerUUID = ?";
        PreparedStatement statement = null;

        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(isBanned);
            statement.setString(1, playerUUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }


    public void banPlayer(String bannedPlayer, String reason, String BannerName, String playerUUID) {
        if (!isBanned(playerUUID) || !isTempBanned(playerUUID)) {
            String checkBan = "INSERT INTO banned (BannedPlayerName, BannedPlayerUUID, Reason, Banner) VALUES(?,?,?,?);";
            PreparedStatement statement = null;
            try {
                Connection connection = main.dataSource.getConnection();
                statement = connection.prepareStatement(checkBan);
                statement.setString(1, bannedPlayer);
                statement.setString(2, playerUUID);
                statement.setString(3, reason);
                statement.setString(4, BannerName);
                statement.execute();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void tempBanPlayer(String bannedPlayer, String reason, String BannerName, Long unbanEpoch, String playerUUID) {
        if (!isBanned(playerUUID) || !isTempBanned(playerUUID)) {
            String checkBan = "INSERT INTO banned (BannedPlayerName, BannedPlayerUUID, CurrentEpoch, UnbannedEpoch, Reason, Banner) VALUES(?,?,?,?,?,?);";
            PreparedStatement statement = null;
            try {
                Connection connection = main.dataSource.getConnection();
                statement = connection.prepareStatement(checkBan);
                statement.setString(1, bannedPlayer);
                statement.setString(2, playerUUID);
                statement.setLong(3, System.currentTimeMillis());
                statement.setLong(4, unbanEpoch);
                statement.setString(5, reason);
                statement.setString(6, BannerName);
                statement.execute();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void mutePlayer(String mutedPlayer, String reason, String muterName, String playerUUID) {
        if (!isMuted(playerUUID) || !isTempMuted(playerUUID)) {
            String checkBan = "INSERT INTO banned (mutedPlayerName, mutedPlayerUUID, Reason, Muter) VALUES(?,?,?,?);";
            PreparedStatement statement = null;
            try {
                Connection connection = main.dataSource.getConnection();
                statement = connection.prepareStatement(checkBan);
                statement.setString(1, mutedPlayer);
                statement.setString(2, playerUUID);
                statement.setString(3, reason);
                statement.setString(4, muterName);
                statement.execute();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void tempMutePlayer(String mutedPlayer, String reason, String BannerName, Long unMutedEpoch, String playerUUID) {
        if (!isMuted(playerUUID) || !isTempMuted(playerUUID)) {
            String checkBan = "INSERT INTO banned (mutedPlayerName, mutedPlayerUUID, CurrentEpoch, UnmuttedEpoch, Reason, Mutter) VALUES(?,?,?,?,?,?);";
            PreparedStatement statement = null;
            try {
                Connection connection = main.dataSource.getConnection();
                statement = connection.prepareStatement(checkBan);
                statement.setString(1, mutedPlayer);
                statement.setString(2, playerUUID);
                statement.setLong(3, System.currentTimeMillis());
                statement.setLong(4, unMutedEpoch);
                statement.setString(5, reason);
                statement.setString(6, BannerName);
                statement.execute();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getBanReason(UUID bannedPlayerUUID) {
        String reason = null;
        String getBanReason = "SELECT * from banned WHERE BannedPlayerUUID = ?";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(getBanReason);
            statement.setString(1, bannedPlayerUUID.toString());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                reason = result.getString("Reason");
                OfflinePlayer target = Bukkit.getOfflinePlayer(bannedPlayerUUID);
                if(target.isOnline()){
                    Player toGet = (Player) target;
                    toGet.sendMessage(reason);
                }
            }
        } catch (SQLException e) {
            reason = "Error";
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    reason = "Error";
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    reason = "Error";
                }
            }
        }
        return reason;
    }

    public String getBanBanner(String bannedPlayerUUID) {
        String banner = new String();
        String getBanBanner = "SELECT * from banned WHERE BannedPlayerUUID = ?";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(getBanBanner);
            statement.setString(1, bannedPlayerUUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                banner = result.getString("Banner");
            }
        } catch (SQLException e) {
            banner = "Error";
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    banner = "Error";
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    banner = "Error";
                }
            }
        }
        return banner;
    }

    public String getTempBanReason(String bannedPlayerUUID) {
        String reason = new String();
        String getBanReason = "SELECT * from tempbanned WHERE BannedPlayerUUID = ?";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(getBanReason);
            statement.setString(1, bannedPlayerUUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                reason = result.getString("Reason");
            }
        } catch (SQLException e) {
            reason = "Error";
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    reason = "Error";
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    reason = "Error";
                }
            }
        }
        return reason;
    }

    public String getTempBanBanner(String bannedPlayer) {
        String banner = new String();
        return banner;
    }

    public String getMuteReason(String mutedPlayerUUID) {
        String reason = new String();
        String getMuteReason = "SELECT * from muted WHERE mutedPlayerUUID = ?";
        PreparedStatement statement = null;
        try {
            Connection connection = main.dataSource.getConnection();
            statement = connection.prepareStatement(getMuteReason);
            statement.setString(1, mutedPlayerUUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                reason = result.getString("Reason");
            }
        } catch (SQLException e) {
            reason = "Error";
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    reason = "Error";
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    reason = "Error";
                }
            }
        }
        return reason;
    }

    public String getMuteMuter(String mutedPlayer) {
        String muter = new String();
        return muter;
    }

    public String getTempMuteReason(String mutedPlayer) {
        String reason = new String();
        return reason;
    }

    public String getTempMuteMuter(String mutedPlayer) {
        String muter = new String();
        return muter;
    }

    public Long getTempBanLength(String bannedPlayer) {
        Long banLength = null;
        return banLength;
    }

    public Long getTempMuteLength(String mutedPlayer) {
        Long muteLength = null;
        return muteLength;
    }

    public Long getBanTime(String bannedPlayer) {
        Long banTime = null;
        return banTime;
    }

    public Long getTempBanTime(String bannedPlayer) {
        Long tempBanTime = null;
        return tempBanTime;
    }

    public Long getMuteTime(String mutedPlayer) {
        Long muteTime = null;
        return muteTime;
    }
    public Long getTempMuteTime(String mutedPlayer){
        Long muteTime = null;
        return muteTime;
    }
}
