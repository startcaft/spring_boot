package com.permission.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的实体类的基类。
 * 基类统一定义id的属性名，数据类型，列名映射以及主键生成策略为主键自增。
 * 这里不考虑Oracle，这年头谁还用Oracle。。。
 */
@MappedSuperclass
public abstract class IdEntity {
	
	protected Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
