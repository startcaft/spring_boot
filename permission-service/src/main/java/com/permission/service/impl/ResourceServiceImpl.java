package com.permission.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.permission.core.entity.Resource;
import com.permission.core.enums.ResourceEnum;
import com.permission.core.enums.StateEnum;
import com.permission.core.exception.ParameterNullException;
import com.permission.core.vo.NodeTree;
import com.permission.core.vo.ResourceVo;
import com.permission.repository.ResourceRepository;
import com.permission.service.ResourceService;

@Service
public class ResourceServiceImpl extends TreeService implements ResourceService {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceRepository resourceRepo;
	
	@Override
	public boolean insertResource(ResourceVo vo) throws Exception {
		{
			if (vo == null) {
				throw new ParameterNullException("vo", ResourceVo.class);
			}
		}
		boolean result = false;
		Resource model = new Resource();
		BeanUtils.copyProperties(vo, model);
		if(vo.getPid() != null){
			Resource parent = new Resource();
			parent.setId(vo.getPid());
			
			model.setParentResource(parent);
		}
		model.setCreatedatetime(new Date());
		model.setState(StateEnum.ENABLE);
		//执行insert
		try {
			resourceRepo.save(model);
		} catch (Exception e) {
			String error = "insert系统资源时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error,e);
			}
			throw new ServiceException(error);
		}
		//回传id给vo，缓存时使用
		if (model.getId() != null) {
			vo.setId(model.getId());
			result = false;
		}
		return result;
	}

	@Override
	public boolean updateResource(ResourceVo vo) throws Exception {
		{
			if (vo == null || vo.getId() == null) {
				throw new ParameterNullException("vo或者vo的id属性", ResourceVo.class);
			}
		}
		boolean result = false;
		//填充Entity
		Resource model = new Resource();
		BeanUtils.copyProperties(vo, model);
		if (vo.getPid() != null) {
			Resource parent = new Resource();
			parent.setId(vo.getPid());
			model.setParentResource(parent);
		}
		//执行update
		try {
			resourceRepo.saveAndFlush(model);
		} catch (Exception e) {
			String error = "update系统资源时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		return result;
	}

	@Override
	public void delete(Integer resourceId) throws Exception {
		{
			if (resourceId == null || resourceId.intValue() == 0) {
				throw new ParameterNullException("resourceId", Integer.class);
			}
		}
		Resource resource = resourceRepo.findOne(resourceId);
		this.del(resource);
	}

	@Override
	public ResourceVo getDetail(Integer resourceId) throws Exception {
		{
			if (resourceId == null || resourceId.intValue() == 0) {
				throw new ParameterNullException("resourceId", Integer.class);
			}
		}
		Resource model = null;
		ResourceVo vo = new ResourceVo();
		//执行select
		try {
			model = resourceRepo.queryById(resourceId);
		} catch (Exception e) {
			String error = "select系统资源主键时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		//填充Vo对象
		if (model != null) {
			BeanUtils.copyProperties(model, vo);
			if (model.getParentResource() != null) {
				vo.setPid(model.getParentResource().getId());
				vo.setpName(model.getParentResource().getName());
			}
		}
		return vo;
	}

	@Override
	public void recursiveTree(NodeTree node,Integer userId) throws Exception {
		{
			if (node == null || node.getId() == null) {
				throw new ParameterNullException("node或node中的id属性", NodeTree.class);
			}
			if (userId == null || userId.intValue() == 0) {
				throw new ParameterNullException("userId",Integer.class);
			}
			//迭代子节点并递归
			List<Resource> childs = this.getchildByUser(node.getId(), userId);
			if (!childs.isEmpty()) {
				for (Resource re : childs) {
					NodeTree n = new NodeTree(re.getId(), re.getParentResource().getId(), re.getName());
					node.getChildren().add(n);
					
					recursiveTree(n,userId);
				}
			}
		}
	}

	
	//////////////////////////////////////////私有方法////////////////////////////////////////////////////
	
	/**
	 * 获取指定用户，指定父节点的系统资源集合，
	 * User关联到Role，Role关联到Resource
	 */
	private List<Resource> getchildByUser(Integer pId,Integer userId) throws Exception{
		{
			List<Resource> childs = new ArrayList<>();
			try {
				childs = resourceRepo.queryByParentAndUser(userId, new Integer(ResourceEnum.FUNC.ordinal()), pId);
			} catch (Exception e) {
				String error = "select指定用户,指定父节点,指定类型的系统资源时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			return childs;
		}
	}
	
	/*
	 * 删除Resource及其包含的子Resource
	 */
	private void del(Resource resource) throws Exception{
		{
			if (!resource.getChildResources().isEmpty()) {
				for (Resource r : resource.getChildResources()) {
					del(r);
				}
			}
			//执行delete
			try {
				resourceRepo.delete(resource);
			} catch (Exception e) {
				String error = "delete系统资源时出错";
				if (logger.isErrorEnabled()) {
					logger.error(error,e);
				}
				throw new ServiceException(error);
			}
		}
	}
}
