package fr.skilldev.uhc.methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.skilldev.uhc.Main;

public class Values {
	
	public static void setMagasin(String uuid, String name) {
		try {
			
			String maga;
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update user set Magasin = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			maga = res.getString("Magasin");
			
			ps1.setString(1, maga + " " + name);
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	public static void setGrade(String uuid, String name) {
		try {
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update user set Grade = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			
			ps1.setString(1, name);
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	public static void setOptions(String uuid, String name) {
		try {
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update user set Options = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			
			ps1.setString(1, name);
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	public static void setOptions2(String uuid, String name) {
		try {
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update user set Options2 = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			
			ps1.setString(1, name);
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	public static void setOptions3(String uuid, String name) {
		try {
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update user set Options3 = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			
			ps1.setString(1, name);
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	public static void setDaily(String uuid) {
		try {
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update user set Daily = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			ps1.setString(1, "1");
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
			PreparedStatement ps2 = Main.c.prepareStatement("update user set Tokens = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			ps2.setInt(1, getTokens(uuid) + 5);
			ps2.setString(2, uuid);
			ps2.executeUpdate();
			statement.close();
			ps2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	public static void setAllDaily() {
		try {
			Statement statement;
			PreparedStatement ps = Main.c.prepareStatement("update user set Daily = ?");
			statement = Main.c.createStatement();
			ps.setString(1, "0");
			ps.executeUpdate();
			statement.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	public static int getDaily(String uuid) {
		int money = 0;
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			money = res.getInt("Daily");
			if(res.getString("PlayerName") == null) {
				return 0;
				} else {
					return money;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return money;
		}
	
	public static String getMagasin(String uuid) {
		String money = "";
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			money = res.getString("Magasin");
			if(res.getString("PlayerName") == null) {
				return "";
				} else {
					return money;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return money;
		}
	
	public static String getOptions(String uuid) {
		String money = "";
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			money = res.getString("Options");
			if(res.getString("PlayerName") == null) {
				return "";
				} else {
					return money;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return money;
		}
	
	public static String getOptions2(String uuid) {
		String money = "";
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			money = res.getString("Options2");
			if(res.getString("PlayerName") == null) {
				return "";
				} else {
					return money;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return money;
		}
	
	public static String getOptions3(String uuid) {
		String money = "";
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			money = res.getString("Options3");
			if(res.getString("PlayerName") == null) {
				return "";
				} else {
					return money;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return money;
		}
	
	public static String getGrade(String uuid) {
		String money = "";
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			money = res.getString("Grade");
			if(res.getString("PlayerName") == null) {
				return "";
				} else {
					return money;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return money;
		}
	
	public static void instantiate(String uuid) {
		try {
			Statement statement;
			PreparedStatement ps = Main.c.prepareStatement("INSERT INTO `user` (PlayerName, Tokens, Grade, Daily, Magasin, Options, Options2, Options3) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			if(!playerDataContainsPlayer(uuid)) {
			ps.setString(1, uuid);
			ps.setString(2, "1");
			ps.setString(3, "Aucun");
			ps.setString(4, "0");
			ps.setString(5, "");
			ps.setString(6, "");
			ps.setString(7, "");
			ps.setString(8, "");
			ps.executeUpdate();
			res.close();
			statement.close();
			ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	/*     */   public synchronized static boolean playerDataContainsPlayer(String player) {
		/*     */     try {
		/*  66 */       PreparedStatement sql = Main.c.prepareStatement("SELECT * FROM `user` WHERE PlayerName=?;");
		/*  67 */       sql.setString(1, player);
		/*  68 */       ResultSet resultSet = sql.executeQuery();
		/*  69 */       boolean containsPlayer = resultSet.next();
		/*     */ 
		/*  71 */       sql.close();
		/*  72 */       resultSet.close();
		/*     */ 
		/*  74 */       return containsPlayer;
		/*     */     }
		/*     */     catch (Exception e) {
		/*  77 */       e.printStackTrace();
		/*  78 */     }return false;
		/*     */   }
	
	
	public static int getTokens(String uuid) {
		int tokens = 0;
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM user WHERE PlayerName = '" + uuid + "';");
			res.next();
			tokens = res.getInt("Tokens");
			if(res.getString("PlayerName") == null) {
				return 0;
				} else {
					return tokens;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return tokens;
		}

	public static void setTokens(String uuid, int amm) {
		try {
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update user set Tokens = ? where PlayerName = ?");
			statement = Main.c.createStatement();
			ps1.setInt(1, amm);
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	public static int getFactionMoney(String uuid) {
		int tokens = 0;
		Statement statement;
		try {
			statement = Main.c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM faction_accounts WHERE uuid = '" + uuid + "';");
			res.next();
			tokens = res.getInt("money");
			if(res.getString("uuid") == null) {
				return 0;
				} else {
					return tokens;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return tokens;
		}

	public static void setFactionMoney(String uuid, int amm) {
		try {
			
			Statement statement;
			PreparedStatement ps1 = Main.c.prepareStatement("update faction_accounts set money = ? where uuid = ?");
			statement = Main.c.createStatement();
			ps1.setInt(1, amm);
			ps1.setString(2, uuid);
			ps1.executeUpdate();
			statement.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}

	}
