package com.permission.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="sys_dictionary_type")
@DynamicUpdate(true)
public class DictionaryType extends IdEntity {		//字典类别
	
	private String name;							//名称，非空，唯一，长度20
	private String code;							//编码，长度20
	private Integer seq;							//排序，可以为空
	private String description;						//描述，可以为空，数据库字段类型为text
	private DictionaryType parentType;				//关联的父节点
	
	@Column(name="dic_type_name",unique=true,nullable=false,length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="dic_type_code",length=20)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="dic_type_seq",length=4)
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Column(name="dic_type_desc",columnDefinition="text")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid",foreignKey=@ForeignKey(name="permission_foreignKey_reference_self_dic_type_id"))
	public DictionaryType getParentType() {
		return parentType;
	}
	public void setParentType(DictionaryType parentType) {
		this.parentType = parentType;
	}
}
