package dev.rtt.dazcomc.skypvp.mysql;

import dev.rtt.dazcomc.skypvp.Skypvp;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter
{
    private Skypvp plugin;
    public SQLGetter(Skypvp plugin){
        this.plugin = plugin;
    }
    public void createTable(){
        PreparedStatement ps;
        try{
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS SkyPvP"
               + "(NAME VARCHAR(100),UUID VARCHAR(100),COINS INT(100),KILLS INT(100),DEATHS INT(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createPlayer(Player player){
        try{
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM SkyPvP WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();
            if(!exists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSET IGNORE INFO SkyPvP " +
                        "(NAME,UUID) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();

                return;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean exists(UUID uuid ){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM SkyPvP WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void addCoins(UUID uuid, int coins){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE SkyPvP SET COINS=? WHERE UUID=?");
            ps.setInt(1, (getCoins(uuid) + coins));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getCoins(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT COINS FROM SkyPvP WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs =ps.executeQuery();
            int coins = 0;
            if(rs.next()){
                coins = rs.getInt("Coins");
                return coins;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addKills(UUID uuid, int kills){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE SkyPvP SET KIILS=? WHERE UUID=?");
            ps.setInt(1, (getKills(uuid) + kills));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getKills(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT KILLS FROM SkyPvP WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs =ps.executeQuery();
            int kills = 0;
            if(rs.next()){
                kills = rs.getInt("Kills");
                return kills;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addDeaths(UUID uuid, int deaths){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE SkyPvP SET DEATHS=? WHERE UUID=?");
            ps.setInt(1, (getDeaths(uuid) + deaths));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getDeaths(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT DEATHS FROM SkyPvP WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs =ps.executeQuery();
            int deaths = 0;
            if(rs.next()){
                deaths = rs.getInt("deaths");
                return deaths;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
