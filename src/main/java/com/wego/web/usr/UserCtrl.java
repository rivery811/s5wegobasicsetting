package com.wego.web.usr;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.wego.web.cmm.IConsumer;
import com.wego.web.cmm.IFunction;
import com.wego.web.utl.Printer;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/users")
@Log4j
public class UserCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	@Autowired User user;

	@Autowired Map<String, Object> map;
	@Autowired Printer printer;
	@Autowired UserMapper userMapper;
	


	@PostMapping("/")
	public String join(@RequestBody  User param) {
		printer.accept("람다프린터가 출력한 값"+param.getUid()+ param.getPwd());
		logger.info("ajax가 보낸 아이디와 비번{}"+param.getUid()+param.getPwd());
		IConsumer<User> c = t -> userMapper.insertUser(param);	
		c.accept(param);

		return "SUCCESS";
	}
	@PostMapping("/{uid}")
	public  User login(@PathVariable String uid,@RequestBody User param){		
		IFunction<User,User> f = t-> userMapper.selectById(param);
		return f.apply(param); // 형변환 필요 없음  (User)f.apply(param);
	}
	@GetMapping("/{uid}")
	public User searchUserbyId(@PathVariable String uid,@RequestBody User param) {
		IFunction<User,User> f = t-> userMapper.selectById(param);
		return f.apply(param); 
	}
	@PutMapping("/{uid}")
	public String updateUser(@PathVariable String uid,@RequestBody User param) {
		IConsumer<User> c = t -> userMapper.insertUser(param);	
		c.accept(param);

		return "SUCCESS";
	}
	
	@DeleteMapping("/{uid}")
	public String removeUser(@PathVariable String uid,@RequestBody User param) {
		IConsumer<User> c = t -> userMapper.insertUser(param);	
		c.accept(param);
		return "SUCCESS";
	}
	

}
