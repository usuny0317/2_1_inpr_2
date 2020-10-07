package mysns.member;

import java.sql.*;
import java.util.*;
import java.sql.Date;


public class MemberDAO {
    ResultSet rs;

    Connection conn;
    PreparedStatement pstmt;

    String jdbc_driver="com.mysql.cj.jdbc.Driver";
    String jdbc_url="jdbc:mysql://localhost:3306/jspdb?characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    void connect() {
    	conn=null;
    	pstmt=null;
        try {
            Class.forName(jdbc_driver);

            conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void disconnect() {
        if(pstmt!=null) {
            try {
                pstmt.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
    public boolean addMember(Member member) {
    	connect();
        String sql = "insert into s_member(uid, name, passwd, email,date) values(?,?,?,?,now())";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getUid());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getPasswd());
            pstmt.setString(4, member.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e+ "오류");
            return false;
        }
        finally {
        	disconnect();
        }
        return true;
    }
	
	public boolean login(String uid, String passwd) {
		connect();
        String sql = "select uid, passwd from s_member where uid = ?";
        boolean result = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uid);
            rs = pstmt.executeQuery();
            rs.next();
            if(rs.getString("passwd").equals(passwd))
                result=true;
        } catch (SQLException e) {
        	System.out.print(e+ "오류");
            return false;
        }
        finally {
            disconnect();
        }
    
        return result;
    }
	
	public ArrayList<String> getNewMembers() {
        ArrayList<String> nmembers = new ArrayList<String>();
        connect();
        // 회원 목록은 7개 까지만 가져옴
        String sql = "select * from s_member order by date desc limit 0,7";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                nmembers.add(rs.getString("uid"));
            }
        } catch (SQLException e) {
        	System.out.print(e+ "오류");
        }
        finally {
           disconnect();
        }
        return nmembers;
    }
	
	//기능추가2
	public boolean delete(Member member) {
        connect();

        String sql="delete from s_member where uid = ?";
        String sql1="delete from s_message where uid = ?";
        String sql2="delete from s_reply where uid = ?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,member.getUid());
            pstmt.executeUpdate();
            pstmt=conn.prepareStatement(sql1);
            pstmt.setString(1,member.getUid());
            pstmt.executeUpdate();
            pstmt=conn.prepareStatement(sql2);
            pstmt.setString(1,member.getUid());
            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            disconnect();
        }
        return true;
    }
	//기능추가3
	public String time(String uid) {
		String a="";
		connect();
		String sql="select * from s_member where uid=? ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a= a +rs.getString("date");
			}
		}catch (SQLException e) {
        	System.out.print(e+ "오류");
        }
        finally {
           disconnect();
        }
		return a;
	}
	
}
