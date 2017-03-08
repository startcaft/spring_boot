package com.permission.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.Organization;
import com.permission.core.exception.ParamterNullException;
import com.permission.core.exception.RecordExistException;
import com.permission.core.exception.ServiceException;
import com.permission.core.vo.NodeTree;
import com.permission.core.vo.OrganizationVo;
import com.permission.repository.OrganizationRepository;
import com.permission.service.OrganizationService;

@Service
public class OrganizationServiceImpl extends BaseService implements OrganizationService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	@Autowired
	private OrganizationRepository orgRepo;

	@Override
	public boolean insertOrg(OrganizationVo vo) throws Exception {
		{
			if (vo == null) {
				throw new ParamterNullException("vo", OrganizationVo.class);
			}
		}
		{
			//先检查name是否重复
			boolean result = false;
			if(this.checkNameExists(vo.getName())){
				throw new RecordExistException("组织(部门)名称");
			}
			//填充Entity对象
			Organization model = new Organization();
			BeanUtils.copyProperties(vo, model);
			if (vo.getPid() != null) {
				Organization parent = new Organization();
				parent.setId(vo.getPid());
				
				model.setParentOrg(parent);
			}
			//捕获异常
			try {
				orgRepo.save(model);
			} catch (Exception e) {
				String error = "insert组织(部门)时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//insert成功，回传ID给Vo，以供缓存时使用
			if (model.getId() != null) {
				vo.setId(model.getId());
				result = true;
			}
			return result;
		}
	}

	@Override
	public boolean updateOrg(OrganizationVo vo) throws Exception {
		{
			if(vo == null || vo.getId() == null){
				throw new ParamterNullException("vo或vo中的id属性", OrganizationVo.class);
			}
		}
		boolean result = false;
		//先检查需要更新的名称是否为空
		if (!StringUtils.isEmpty(vo.getName())) {
			if(this.checkNameExists(vo.getName())){
				throw new RecordExistException("组织(部门)名称");
			}
		}
		//填充Entity对象
		Organization model = new Organization();
		BeanUtils.copyProperties(vo, model);
		if(vo.getPid() != null){
			Organization parent = new Organization();
			parent.setId(vo.getPid());
			
			model.setParentOrg(parent);
		}
		//捕获异常
		try {
			orgRepo.saveAndFlush(model);
			result = true;
		} catch (Exception e) {
			String error = "update组织(部门)时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		return result;
	}

	@Override
	public OrganizationVo getDetail(Integer orgId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recursiveTree(NodeTree node) throws Exception {
		// TODO Auto-generated method stub

	}
	
	/*******************************私有方法 *****************************************/
	
	/*
	 * 检查指定的 orgName 是否存在，
	 * @param orgName 不能为空，否则直接返回true
	 * @return true存在/false不存在
	 */
	private boolean checkNameExists(String orgName) throws Exception {
		{
			boolean result = true;
			if (StringUtils.isEmpty(orgName)) {
				return result;
			}
			//执行select，捕获异常
			Organization model = null;
			try {
				model = orgRepo.queryByName(orgName);
			} catch (Exception e) {
				String error = "select组织(部门)名称时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//查询有可能为空
			if(model == null){
				result = false;
			}
			return result;
		}
	}
}
