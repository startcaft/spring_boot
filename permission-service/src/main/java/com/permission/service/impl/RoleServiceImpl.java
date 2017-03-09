package com.permission.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.Resource;
import com.permission.core.entity.Role;
import com.permission.core.entity.Role_;
import com.permission.core.exception.ParameterNullException;
import com.permission.core.exception.RecordExistException;
import com.permission.core.queryable.PageInfo;
import com.permission.core.queryable.RoleQuery;
import com.permission.core.vo.RoleVo;
import com.permission.repository.ResourceRepository;
import com.permission.repository.RoleRepository;
import com.permission.service.RoleService;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private ResourceRepository resourceRepo;
	
	@Override
	public PageInfo<RoleVo> getPage(RoleQuery query) throws Exception {
		{
			Pageable pageable = this.buildPageableInstance(query);
			Page<Role> pagelist = null;
			//执行查询
			try {
				pagelist = roleRepo.findAll(this.getSpecification(query), pageable);
			} catch (Exception e) {
				String error = "动态select角色信息时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//填充Vo类型的集合
			List<RoleVo> voList = new ArrayList<>();
			if(pagelist != null && !pagelist.getContent().isEmpty()){
				for (Role model : pagelist.getContent()) {
					RoleVo vo = new RoleVo();
					BeanUtils.copyProperties(model, vo);
					
					voList.add(vo);
				}
			}
			return new PageInfo<RoleVo>(pagelist.getNumber() + 1,pagelist.getTotalPages(),pagelist.getTotalElements(),voList);
		}
	}

	@Override
	public void add(RoleVo role) throws Exception {
		{
			if (role == null) {
				throw new ParameterNullException("role", RoleVo.class);
			}
			//先检查name是否重复
			if(this.checkRoleNameExists(role.getName())){
				throw new RecordExistException("角色名称");
			}
			//填充实体对象
			Role model = new Role();
			BeanUtils.copyProperties(role, model);
			//执行insert
			try {
				roleRepo.save(model);
			} catch (Exception e) {
				String error = "insert角色表时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//insert成功，回传ID给Vo，以供缓存时使用
			if (model.getId() != null) {
				role.setId(model.getId());
			}
		}
	}

	@Override
	public void edit(RoleVo role) throws Exception {
		{
			if (role == null || role.getId() == null) {
				throw new ParameterNullException("role", RoleVo.class);
			}
			//填充实体对象
			Role model = new Role();
			BeanUtils.copyProperties(role, model);
			//执行update
			try {
				roleRepo.saveAndFlush(model);
			} catch (Exception e) {
				String error = "update指定角色时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
		}
	}

	@Override
	public RoleVo getDetail(Integer id) throws Exception {
		{
			if (id == null || id.intValue() == 0) {
				throw new ParameterNullException("id", Integer.class);
			}
		}
		Role model = null;
		RoleVo vo = new RoleVo();
		//执行select
		try {
			model = roleRepo.findOne(id);
		} catch (Exception e) {
			String error = "select指定主键的角色时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		//填充Vo对象
		if (model != null) {
			BeanUtils.copyProperties(model, vo);
			Set<Resource> resources = model.getResources();
			List<Integer> resourceIds = new LinkedList<>();
			List<String> resourceNames = new LinkedList<>();
			if (!resources.isEmpty()) {
				for (Resource re : resources){
					resourceIds.add(re.getId());
					resourceNames.add(re.getName());
				}
				vo.setResourceIds(resourceIds);
				vo.setResourceNames(resourceNames);
			}
		}
		return vo;
	}

	@Override
	public void grant(RoleVo vo) throws Exception {
		{
			if (vo == null || vo.getId() == 0) {
				throw new ParameterNullException("vo", RoleVo.class);
			}
			Role model = null;
			//执行select
			try {
				model = roleRepo.findOne(vo.getId());
			} catch (Exception e) {
				String error = "select指定ID的角色时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			if (model == null) {
				throw new ServiceException("找不到指定ID的角色信息");
			}
			//修改实体对象，会自动更新
			try {
				if (vo.getResourceIds() != null && vo.getResourceIds().size() > 0) {
					List<Resource> resources = resourceRepo.findByIdIn(vo.getResourceIds());
					model.setResources(new HashSet<Resource>(resources));
				}
				else{
					model.setResources(null);
				}
			} catch (Exception e) {
				String error = "update角色资源时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
		}
	}

	
	//////////////////////////////////////////私有方法////////////////////////////////////////////////////
	
	/**
	 * 动态生成where查询语句
	 * @param query	Role查询对象
	 */
	private Specification<Role> getSpecification(final RoleQuery query){
		{
			return new Specification<Role>() {

				@Override
				public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
					{
						List<Predicate> predicate = new ArrayList<>();
						//name like %value%
						if (!StringUtils.isEmpty(query.getRoleName())) {
							Path<String> namePath = root.get(Role_.name);
							Predicate namePre = cb.like(namePath, "%" + query.getRoleName() + "%");
							predicate.add(namePre);
						}
						
						Predicate[] pres = new Predicate[predicate.size()];
						return cq.where(predicate.toArray(pres)).getRestriction();
					}
				}
			};
		}
	}
	
	/**
	 * 检查指定的 roleName 是否存在，
	 * @param roleName 不能为空，否则直接返回true
	 * @return true存在/false不存在
	 */
	private boolean checkRoleNameExists(String roleName){
		{
			boolean result = true;
			if (StringUtils.isEmpty(roleName)) {
				return result;
			}
			Role model = null;
			//执行select
			try {
				model = roleRepo.queryByName(roleName);
			} catch (Exception e) {
				String error = "select指定名称的角色时错误";
				if (logger.isDebugEnabled()) {
					logger.error(error,e);
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
