package com.wego.web.adm;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @Component
@AllArgsConstructor
@NoArgsConstructor
public class Admin  {

	
	private String eid,pwd,eName,job,mgr,hireDate,sal,comm,deptNo;

}
