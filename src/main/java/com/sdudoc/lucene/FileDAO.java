package com.sdudoc.lucene;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdudoc.bean.Book;


/**
 * 对file表进行dao操作
 * @author Administrator
 *
 */
public class FileDAO {
	/**
	 * 获取文件信息
	 * @return
	 * @throws SQLException 
	 */
	public List getFile() throws SQLException {
		List posts = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT bookID,bookTitle,summary,authors,bookStyle FROM book";
		BaseDAO baseDao = null;
		
		try{
			baseDao = new BaseDAO();
			con = baseDao.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Post post = new Post();
				post.setBookID(rs.getString("bookID"));
				post.setBookTitle(rs.getString("bookTitle"));
				System.out.println(rs.getString("bookTitle")+"----title");
				post.setSummary(rs.getString("summary"));
				post.setBookStyle(rs.getString("bookStyle"));
				post.setAuthors(rs.getString("authors"));
				posts.add(post);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ps.close();
			rs.close();
			con.close();
		}
		return posts;
		
	}

}
