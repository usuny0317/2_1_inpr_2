package mysns.sns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MessageDAO {
	Statement stmt;
	ResultSet rs;
	 Connection conn=null;
	    PreparedStatement pstmt=null;

	    String jdbc_driver="com.mysql.cj.jdbc.Driver";
	    String jdbc_url="jdbc:mysql://localhost:3306/jspdb?characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

	    void connect() {
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
	
	public ArrayList<MessageSet> getAll(int cnt, String suid) {
		connect();
		ArrayList<MessageSet> datas = new ArrayList<MessageSet>();
		String sql;

		try {
			// 전체 게시물인 경우
			if((suid == null) || (suid.equals(""))) {
				sql = "select * from s_message order by date desc limit 0,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cnt);
			}
			// 특정 회원 게시물 only 인 경우
			else{
				sql = "select * from s_message where uid=? order by date desc limit 0,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,suid);
				pstmt.setInt(2,cnt);
			}

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MessageSet ms = new MessageSet();
				Message m = new Message();
				ArrayList<Reply> rlist = new ArrayList<Reply>();
				
				m.setMid(rs.getInt("mid"));
				m.setMsg(rs.getString("msg"));
				m.setDate(rs.getDate("date")+" / "+rs.getTime("date"));
				m.setFavcount(rs.getInt("favcount"));
				m.setUid(rs.getString("uid"));
				
				String rsql = "select *  from s_reply where mid=? order by date desc";
				pstmt = conn.prepareStatement(rsql,
						ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				pstmt.setInt(1,rs.getInt("mid"));
				ResultSet rrs = pstmt.executeQuery();
				while(rrs.next()) {
					Reply r = new Reply();
					r.setRid(rrs.getInt("rid"));
					r.setUid(rrs.getString("uid"));
					r.setRmsg(rrs.getString("rmsg"));
					r.setDate(rrs.getDate("date")+"/"+rrs.getTime("date"));
					rlist.add(r);
				}
				rrs.last();
				m.setReplycount(rrs.getRow());
				//System.out.println("r count"+rrs.getRow());
				
				ms.setMessage(m);
				ms.setRlist(rlist);
				datas.add(ms);
				rrs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
		}
		finally {
			disconnect();
		}		
		return datas;
	}
	
	/**
	 * 신규 메시지 등록
	 * @param msg
	 * @return
	 */
	public boolean newMsg(Message msg) {
		connect();
		String sql = "insert into s_message(uid, msg, date) values(?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msg.getUid());
			pstmt.setString(2, msg.getMsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		}
		finally {
			disconnect();
		}
		return true;	
}
	
	/**
	 * 메시지 삭제
	 * @param mid
	 * @return
	 */
	public boolean delMsg(int mid) {
		connect();
		String sql = "delete from s_message where mid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		}
		finally {
			disconnect();
		}
		return true;	
	}
		
	/**
	 * 게시글에 대한 답글 등록, 원 게시물에 대한 mid 필요
	 * @param mid
	 * @param rmsg
	 * @return
	 */
	public boolean newReply(Reply reply) {
		connect();
		String sql = "insert into s_reply(mid,uid,rmsg,date) values(?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getMid());
			pstmt.setString(2, reply.getUid());
			pstmt.setString(3, reply.getRmsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	/**
	 * 답글 삭제
	 * @param rid
	 * @return
	 */
	public boolean delReply(int rid) {
		connect();
		String sql = "delete from s_reply where rid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);;
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	/**
	 * 좋아요 추가
	 * @param mid
	 */
	public void favorite(int mid) {
		connect();
		// 좋아요 추가를 위해 favcount 를 +1 해서 update 함
		String sql = "update s_message set favcount=favcount+1 where mid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
		}
		finally {
			disconnect();
		}
	}
	//기능 추가 1
	public String find() {
		connect();
		int a=0;
		String b = null;
		String sql="select * from s_message order by date";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rss = pstmt.executeQuery();
			while(rss.next()) {
				if(a<rss.getInt("favcount")) {
					a=rss.getInt("favcount");
					b=rss.getString("uid");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
		}
		finally {
			disconnect();
		}
		return b;
	}
	//기능추가 4
	
		public boolean ediMSG(int mid, String a) {
			
			connect();
			String sql = "update s_message set msg=? where mid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, a);
				pstmt.setInt(2, mid);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getErrorCode());
				return false;
			}
			finally {
				disconnect();
			}
			return true;	
		}
	
}
