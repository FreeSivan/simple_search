package com.xiwen.yxw.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.xiwen.yxw.build.CreateIndex;
import com.xiwen.yxw.search.SearchIndex;

public class TestMain {
	
	private ApplicationContext ac = (ApplicationContext) new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml");
	
	@Test
	public void test1() {
		try {
			CreateIndex createIndex = (CreateIndex)ac.getBean("createIndex");
			createIndex.createIndex();
			
			SearchIndex searchIndex = (SearchIndex)ac.getBean("searchIndex");
			searchIndex.searchIndex("content", "xi");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
