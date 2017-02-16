package com.permission.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.permission.core.enums.StateEnum;


@Entity
@Table(name="sys_dictionary")
@DynamicUpdate(true)
public class Dictionary extends IdEntity {
	
	private String name;							//名称，非空，同一个字典类别下无重复，长度20
	private String code;							//编码，可以为空，长度20
	private Integer seq;							//排序，可以为空，长度4
	private StateEnum state; 						//字典状态，非空，长度4，实际保存的是数字
	private DictionaryType dictionaryType;			//关联的字典类别
	
	@Column(name="dic_name",nullable=false,length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="dic_code",length=20)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="dic_seq",length=4)
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="dic_state",length=4,nullable=false)
	public StateEnum getState() {
		return state;
	}
	public void setState(StateEnum state) {
		this.state = state;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dic_type_id",nullable=false,foreignKey=@ForeignKey(name="permission_foreignKey_reference_dic_type_id"))
	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}
	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}
}
