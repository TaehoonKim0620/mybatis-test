package com.test.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.test.config.SessionFactory;


public class FoodInfoRepository {
	public Map<String,String> selectFoodInfo(int fiNum){
		try(SqlSession session = SessionFactory.getSSF().openSession()){
			return session.selectOne("com.test.repository.FoodInfoMapper.selectFoodInfo",fiNum);
		}
	}
	public List<Map<String,String>> selectFoodInfos(Map<String,String> param){
		try(SqlSession session = SessionFactory.getSSF().openSession()){
			return session.selectList("com.test.repository.FoodInfoMapper.selectFoodInfos",param);
			
		}
	}
	public int deleteFoodInfo(int fiNum) {
		try(SqlSession session = SessionFactory.getSSF().openSession(true)){
			return session.delete("com.test.repository.FoodInfoMapper.deleteFoodInfo",fiNum);
		}
	}
	public int updateFoodInfo(Map<String,String> param) {
		try(SqlSession session = SessionFactory.getSSF().openSession(true)){
			return session.update("com.test.repository.FoodInfoMapper.updateFoodInfo",param);
		}
	}
	public int insertFoodInfo(Map<String,String> param) {
		try(SqlSession session = SessionFactory.getSSF().openSession(true)){
			return session.insert("com.test.repository.FoodInfoMapper.insertFoodInfo",param);
		}
	}
	public static void main(String[] args) {
		FoodInfoRepository fiRepo = new FoodInfoRepository();
		Map<String,String> foodInfo = new HashMap<>();
		foodInfo.put("fiName","김김김");
		foodInfo.put("fiPrice", "10");
		int result = fiRepo.insertFoodInfo(foodInfo);
		System.out.println("insert: "+ result);
		System.out.println(foodInfo);
		List<Map<String,String>> foodInfos = fiRepo.selectFoodInfos(foodInfo);
		System.out.println(foodInfos);
		foodInfo = fiRepo.selectFoodInfo(2);
		System.out.println(foodInfo);
		for(Map<String,String> food : foodInfos) {
			System.out.println(food);
		}
		result = fiRepo.deleteFoodInfo(1);
		System.out.println("삭제 : "+ result);
		result = fiRepo.updateFoodInfo(foodInfo);
		System.out.println("업뎃 : " +result);
	}
	
}
