package com.permission.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.Organization;
import com.permission.core.entity.User;
import com.permission.core.exception.ParameterNullException;
import com.permission.core.exception.RecordExistException;
import com.permission.core.exception.ServiceException;
import com.permission.core.vo.NodeTree;
import com.permission.core.vo.OrganizationVo;
import com.permission.repository.OrganizationRepository;
import com.permission.repository.UserRepository;
import com.permission.service.OrganizationService;

@Service
public class OrganizationServiceImpl extends BaseTreeService implements OrganizationService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	@Autowired
	private OrganizationRepository orgRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean insertOrg(OrganizationVo vo) throws Exception {
		{
			if (vo == null) {
				throw new ParameterNullException("vo", OrganizationVo.class);
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
			model.setCreateTime(new Date());
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
				throw new ParameterNullException("vo或vo中的id属性", OrganizationVo.class);
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
		{
			if (orgId == null || orgId.intValue() == 0) {
				throw new ParameterNullException("orgId", Integer.class);
			}
		}
		//执行select，捕获异常
		Organization model = null;
		OrganizationVo vo = new OrganizationVo();
		try {
			model = orgRepo.queryById(orgId);
		} catch (Exception e) {
			String error = "select组织(部门)主键时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		//填充Vo对象
		if (model != null) {
			BeanUtils.copyProperties(model, vo);
			if (model.getParentOrg() != null) {
				vo.setPid(model.getParentOrg().getId());
				vo.setpName(model.getParentOrg().getName());
			}
		}
		return vo;
	}

	@Override
	public void recursiveTree(NodeTree node) throws Exception {
		{
			if (node == null || node.getId() == null) {
				throw new ParameterNullException("node或node的id属性", NodeTree.class);
			}
		}
		{
			List<Organization> childs = this.getByPid(node.getId());
			if (!childs.isEmpty()) {
				for (Organization org : childs) {
					NodeTree n = new NodeTree(org.getId(), org.getParentOrg().getId(), org.getName());
					node.getChildren().add(n);
					
					recursiveTree(n);//递归
				}
			}
		}
	}
	
	@Override
	public void deleteOrg(Integer orgId) throws Exception {
		{
			if (orgId == null) {
				throw new ParameterNullException("orgId", Integer.class);
			}
		}
		try {
			Organization org = orgRepo.findOne(orgId);
			this.del(org);
		} catch (Exception e) {
			String error = "delete组织(部门)时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
	}
	
	/*******************************私有方法 *****************************************/
	
	/*
	 * 递归删除部门，先判断该部门下是否有用户
	 */
	private void del(Organization org) throws Exception{
		{
			List<User> userList = userRepo.queryByOrgId(org.getId());
			if(!userList.isEmpty()){
				throw new ServiceException("组织(部门):[" + org.getName() +"]下还关联有用户,暂时无法删除");
			}
			else{
				if (!org.getChildOrgs().isEmpty()) {
					for (Organization o : org.getChildOrgs()) {
						del(o);//递归
					}
				}
				//没有子部门就可以直接删除
				orgRepo.delete(org);
			}
		}
	}
	
	/*
	 * 获取指定父节点的组织(部门)
	 */
	private List<Organization> getByPid(Integer pid) throws Exception{
		{
			List<Organization> childs = new ArrayList<>();
			try {
				Specification<Organization> spec = new TreeSpecification<>("parentOrg", null, pid);
				childs = orgRepo.findAll(spec);
			} catch (Exception e) {
				String error = "select组织(部门)指定的父节点数据时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			return childs;
		}
	}
	
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
