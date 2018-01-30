package com.qujia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qujia.db.DBUtil;
import com.qujia.model.Goddess;

public class GoddessDao {
	//添加人
	public void addGoddess(Goddess goddess) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql=""+"insert into imooc_goddess"+"(user_name,sex,age,birthday,email,mobile,"
		+"create_user,create_date,update_user,update_date,isdel)"+
				"values("+
		"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
		//prepareStatement()方法把sql语句加载到PreparedStatement对象中，暂时不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		//给sql语句传递参数
		ptmt.setString(1,goddess.getUser_name());
		ptmt.setInt(2, goddess.getSex());
		ptmt.setInt(3,goddess.getAge());
		//因为getBrithday原始定义的是util包下的类，现在从数据库中获取数据，
		//需要定义一个sql包下的date类,如下：
		ptmt.setDate(4, new Date(goddess.getBrithday().getTime()));
		ptmt.setString(5,goddess.getEmail());
		ptmt.setString(6, goddess.getMobile());
		ptmt.setString(7,goddess.getCreate_user());

		ptmt.setString(8, goddess.getUpdate_user());
	
		ptmt.setInt(9,goddess.getIsder());
		
		//等到PreparedStatement的ptmt对象调用execute()方法时才执行
		ptmt.execute();
		
	}
	//更新人
	public void updateGoddess(Goddess goddess) throws ClassNotFoundException, SQLException {
		Connection conn=DBUtil.getConnection();
		String sql=""+" update imooc_goddess"+" set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?,"
		+" update_user=?,update_date=current_date(),isdel=?"+
				" where id=?";
		
		//prepareStatement()方法把sql语句加载到PreparedStatement对象中，暂时不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		//给sql语句传递参数
		ptmt.setString(1,goddess.getUser_name());
		ptmt.setInt(2, goddess.getSex());
		ptmt.setInt(3,goddess.getAge());
		//因为getBrithday原始定义的是util包下的类，现在从数据库中获取数据，
		//需要定义一个sql包下的date类,如下：
		ptmt.setDate(4, new Date(goddess.getBrithday().getTime()));
		ptmt.setString(5,goddess.getEmail());
		ptmt.setString(6, goddess.getMobile());
		ptmt.setString(7, goddess.getUpdate_user());
		ptmt.setInt(8,goddess.getIsder());
		ptmt.setInt(9, goddess.getId());
		//等到PreparedStatement的ptmt对象调用execute()方法时才执行
		ptmt.execute();
	}
	//删除人
	public void delGoddess(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn=DBUtil.getConnection();
		String sql=""+
		" delete from imooc_goddess"
				+" where id=?";
		
		//prepareStatement()方法把sql语句加载到PreparedStatement对象中，暂时不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		//给sql语句传递参数
		ptmt.setInt(1, id);
		//等到PreparedStatement的ptmt对象调用execute()方法时才执行
		ptmt.execute();
	}
	//查询人
	public List<Goddess> query() throws Exception{
		Connection conn=DBUtil.getConnection();
		//3通过数据库的连接操作数据库，实现增删该查
		Statement stmt= conn.createStatement();
		ResultSet rs=stmt.executeQuery("select user_name,age from imooc_goddess");
//		System.out.println("rs:"+rs);
		List<Goddess> gs=new ArrayList<Goddess>();
		Goddess g=null;
		while(rs.next()) {
			g=new Goddess();
			//把年龄，姓名等从数据库中获取加入到List中使用
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			
			gs.add(g);
//			System.out.println("gs"+gs);
		}
		return gs;
	}
	//按照人的姓名来查询
		public List<Goddess> query(String name) throws Exception{
			Connection conn=DBUtil.getConnection();
			//3通过数据库的连接操作数据库，实现增删该查
			Statement stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from imooc_goddess");
//			System.out.println("rs:"+rs);
			List<Goddess> gs=new ArrayList<Goddess>();
			Goddess g=null;
			while(rs.next()) {
				g=new Goddess();
				//把年龄，姓名等从数据库中获取加入到List中使用
				g.setUser_name(rs.getString("user_name"));
				g.setAge(rs.getInt("age"));
				
				gs.add(g);
//				System.out.println("gs"+gs);
			}
			return gs;
		}
	//查询单个人:根据ID来查
	public Goddess get(Integer id) throws ClassNotFoundException, SQLException {
		Goddess goddess=null;
		Connection conn=DBUtil.getConnection();
		String sql=""
		+" select * from imooc_goddess"+
				" where id=?";
		
		//prepareStatement()方法把sql语句加载到PreparedStatement对象中，暂时不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		//给sql语句传递参数
		
		ptmt.setInt(1, id);
		//等到PreparedStatement的ptmt对象调用execute()方法时才执行
		//单个人的信息查询是通过executeQuery()方法
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			goddess=new Goddess();
			goddess.setId(rs.getInt("id"));
			goddess.setUser_name(rs.getString("user_name"));
			goddess.setAge(rs.getInt("age"));
			goddess.setSex(rs.getInt("sex"));
			//这里sql中的date类就不用在转成util包下的date类了
			goddess.setBrithday(rs.getDate("birthday"));
			goddess.setEmail(rs.getString("email"));
			goddess.setMobile(rs.getString("mobile"));
			goddess.setCreate_date(rs.getDate("create_date"));
			goddess.setCreate_user(rs.getString("create_user"));
			goddess.setIsder(rs.getInt("isdel"));
			
		}
		return goddess;
	}
	
}
