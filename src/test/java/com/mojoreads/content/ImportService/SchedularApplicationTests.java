package com.mojoreads.content.ImportService;

 

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.incl.content.schedular.bean.Log;
import com.incl.content.schedular.repository.LogRepository;

 

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedularApplicationTests {
	
	
	@Autowired
	@Lazy
	LogRepository logRepository;
	
	@Test
	public void contextLoads() {
		PageRequest pageRequest = new PageRequest(0, 30, Sort.Direction.DESC, "id");
		List<Log> page = logRepository.findByDropboxId(1,pageRequest);
		
		System.out.println("size--------------->"+page.size());
	}

}
