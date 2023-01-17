package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.kadi;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class AccountKadai {
	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int tou(kadi roku) {
		String sql = "INSERT INTO kadai2 values(?,?,?,?,?,?,?)";
		int result = 0;
		
		String salt = GenerateSalt.getSalt(32);
		
		String hashedPw = GenerateHashedPw.getSafetyPassword(roku.getPass(), salt);
		
		System.out.println("ハッシュに使うソルトは"  + salt);
		System.out.println("登録されるハッシュ済みのPWは" + hashedPw);
		
		try(
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)	
				){
			pstmt.setString(1,roku.getName());
			pstmt.setInt(2,roku.getAge());
			pstmt.setString(3,roku.getSei());
			pstmt.setInt(4,roku.getNumber());
			pstmt.setString(5,roku.getMail());
			pstmt.setString(6,salt);
			pstmt.setString(7,hashedPw);
			
			result= pstmt.executeUpdate();
	}catch (SQLException e){
		e.printStackTrace();
	}catch(URISyntaxException e) {
		e.printStackTrace();
	}finally {
		System.out.println(result + "件更新しました");
	}
	return result;
	}
	
	public static String getSalt(String mail) {
		String sql = "SELECT salt FROM kadai2 WHERE mail = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String salt = rs.getString("salt");
					return salt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ログイン処理
	public static kadi login(String mail, String hashedPw) {
		String sql = "SELECT * FROM kadai2 WHERE mail = ? AND hash = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, hashedPw);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String sei = rs.getString("sei"); 
					int number = rs.getInt("number");
					String salt = rs.getString("salt");
					
					return new kadi(name,age,sei,number,mail,null,salt,null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
}
