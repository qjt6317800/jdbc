package com.qujia.controller;


import java.util.Date;

import com.qujia.dao.GoddessDao;
import com.qujia.model.Goddess;

public class GoddessController {
	public static void main(String[] args) throws Exception {
		GoddessDao g=new GoddessDao();
//		//把GoddessDao类中的query()方法付给List对象
//		List<Goddess> gs=g.query();
//		//遍历集合，打印数据到控制台
//		
//		for (Goddess goddess2 : gs) {
//			System.out.println(goddess2.getUser_name()+","+goddess2.getAge());
//		}
		Goddess  g1=new Goddess ();
		g1.setUser_name("小黄");
		g1.setAge(26);
		g1.setSex(1);
		g1.setBrithday(new Date());
		g1.setEmail("xiaolv@imooc.com");
		g1.setMobile("7456532");
		g1.setCreate_user("ADMIN");
		g1.setUpdate_user("ADMIN");
//		g1.setUpdate_user("qujia");
		g1.setIsder(3);
		//添加人函数调用
//		g.addGoddess(g1);
		//更新人函数调用
//		g1.setId(3);
//		g.updateGoddess(g1);
		//删除人调用
//		g.delGoddess(3);
		//根据ID查询单个人信息
		Goddess g2=g.get(4);
		System.out.println(g2.toString());
		
		
	}
}
