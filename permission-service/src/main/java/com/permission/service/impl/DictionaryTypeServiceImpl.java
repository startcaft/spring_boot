package com.permission.service.impl;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.DictionaryType;
import com.permission.core.exception.ParameterNullException;
import com.permission.core.exception.RecordExistException;
import com.permission.core.exception.ServiceException;
import com.permission.core.vo.DictionaryTypeVo;
import com.permission.core.vo.NodeTree;
import com.permission.repository.DictionaryTypeRepository;
import com.permission.service.DictionaryTypeService;

@Service
public class DictionaryTypeServiceImpl extends TreeService implements DictionaryTypeService {
	
	private static final Logger logger = LoggerFactory.getLogger(DictionaryTypeServiceImpl.class);
	
	@Autowired
	private DictionaryTypeRepository typeRepo;
	
	@Override
	public void insertRecord(DictionaryTypeVo vo) throws Exception {
		{
			if(vo == null){
				throw new ParameterNullException("vo", DictionaryTypeVo.class);
			}
		}
		{
			//先检查name是否重复
			if(this.checkNameExists(vo.getName())){
				throw new RecordExistException("字典类型名称");
			}
			//不抛出异常则执行insert
			DictionaryType model = new DictionaryType();
			BeanUtils.copyProperties(vo, model);
			if (vo.getPid() != null) {
				DictionaryType parent = new DictionaryType();
				parent.setId(vo.getPid());
				
				model.setParentType(parent);
			}
			//捕获异常
			try {
				typeRepo.save(model);
			} catch (Exception e) {
				String error = "insert数据到数据字典类别表时错误";
				if(logger.isErrorEnabled()){
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//insert成功，回传id给vo对象，以供缓存时使用
			if(model.getId() != null){
				vo.setId(model.getId());
			}
		}
	}
	
	@Override
	public void getDicTypeTree(NodeTree node) throws Exception {
		{
			this.recursiveTree(node);
		}
	}
	
	@Override
	public DictionaryTypeVo getById(Integer id) throws Exception {
		{
			if (id == null) {
				throw new ParameterNullException("id", DictionaryType.class);
			}
		}
		DictionaryTypeVo vo = null;
		DictionaryType model = null;
		//捕获异常
		try {
			model = typeRepo.findOne(id);
		} catch (Exception e) {
			String error = "select指定字典类别主键时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		//填充Vo对象
		if (model != null) {
			vo = new DictionaryTypeVo();
			BeanUtils.copyProperties(model, vo);
			if(model.getParentType() != null){
				vo.setPid(model.getParentType().getId());
				vo.setpName(model.getParentType().getName());
			}
		}
		return vo;
	}
	
	@Override
	public void modifyRecord(DictionaryTypeVo vo) throws Exception {
		{
			if(vo == null || vo.getId() == null){
				throw new ParameterNullException("vo或vo中的id属性", DictionaryTypeVo.class);
			}
		}
		//先加载一次
		DictionaryType model = null;
		try {
			model = typeRepo.getOne(vo.getId());
		} catch (Exception e) {
			String error = "查询指定ID的字典类别时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		if (model == null) {
			throw new ServiceException("查询不到指定ID的字典类别");
		}
		//若vo中的name不为空，并且vo中的name和model中的name不同时，需要检查vo中的name的唯一性
		if ((!StringUtils.isEmpty(vo.getName())) && (!vo.getName().equalsIgnoreCase(model.getName()))) {
			if(this.checkNameExists(vo.getName())){
				throw new RecordExistException("指点的字典类别名称[" + vo.getName() + "]已经存在");
			}
		}
		//使用Vo对象填充Entity对象
		model.setCode(vo.getCode());
		model.setSeq(vo.getSeq());
		model.setDescription(vo.getDescription());
		if(vo.getPid() != null){
			DictionaryType parent = new DictionaryType();
			parent.setId(vo.getPid());
			
			model.setParentType(parent);
		}
		//执行更新，捕获异常
		try {
			typeRepo.saveAndFlush(model);
		} catch (Exception e) {
			String error = "update字典类别表时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
	}
	
	//////////////////////////////////////////私有方法////////////////////////////////////////////////////
	
	/**
	 * 检查指定的 dicTypeName 是否存在，
	 * @param dicTypeName 不能为空，否则直接返回true
	 * @return true存在/false不存在
	 */
	private boolean checkNameExists(String dicTypeName) throws Exception{
		{
			boolean result = true;
			if (StringUtils.isEmpty(dicTypeName)) {
				return result;
			}
			DictionaryType model = null;
			//捕获异常
			try {
				model = typeRepo.queryByName(dicTypeName);
			} catch (Exception e) {
				String error = "select指定字典类别名称时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			
			if(model == null){
				result = false;
			}
			
			return result;
		}
	}
	
	/**
	 * 递归 字典类别 树状结构
	 * @param node 节点对象
	 * @throws Exception
	 */
	private void recursiveTree(NodeTree node) throws Exception{
		{
			List<DictionaryType> childs = new ArrayList<>();
			//捕获异常
			try {
				Specification<DictionaryType> typeSpec = new TreeSpecification<>("parentType", null, node.getId());
				childs = typeRepo.findAll(typeSpec);
			} catch (Exception e) {
				String error = "select指定字典类别父节点时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//循环子节点并递归
			if (!childs.isEmpty()) {
				for (DictionaryType dicType : childs) {
					NodeTree n = new NodeTree(dicType.getId(), dicType.getParentType().getId(), dicType.getName());
					node.getChildren().add(n);
					
					recursiveTree(n);//递归
				}
			}
		}
	}
}
