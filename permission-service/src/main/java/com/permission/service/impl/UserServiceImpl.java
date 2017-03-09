package com.permission.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.Organization;
import com.permission.core.entity.Role;
import com.permission.core.entity.User;
import com.permission.core.enums.StateEnum;
import com.permission.core.exception.ParameterNullException;
import com.permission.core.exception.RecordExistException;
import com.permission.core.vo.UserVo;
import com.permission.repository.RoleRepository;
import com.permission.repository.UserRepository;
import com.permission.service.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public void add(UserVo vo) throws Exception {
		{
			if (vo == null) {
				throw new ParameterNullException("vo", UserVo.class);
			}
			//检查用户名是否存在
			if (this.checkUserNameExists(vo.getUsername())) {
				throw new RecordExistException("username");
			}
			//填充实体对象
			User model = new User();
			BeanUtils.copyProperties(model, vo);
			if (vo.getOrgId() != null) {
				Organization org = new Organization();
				org.setId(vo.getOrgId());
				
				model.setOrganization(org);
			}
			List<Integer> roleIds = new LinkedList<>();
			if (vo.getRoleIds() != null && vo.getRoleIds().size() > 0) {
				for (Integer roleId : vo.getRoleIds()) {
					roleIds.add(roleId);
				}
				
				Set<Role> resources = new HashSet<>(roleRepo.findByIdIn(roleIds));
				model.setRoles(resources);
			}
			model.setCreatedatetime(new Date());
			model.setState(StateEnum.ENABLE);
			//执行insert
			try {
				userRepo.save(model);
			} catch (Exception e) {
				String error = "insert用户时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//insert成功，回传ID给Vo，以供缓存时使用
			if (model.getId() != null) {
				vo.setId(model.getId());
			}
		}
	}

	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserVo getDetail(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVo login(UserVo user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editUserPwd(String oldPwd, String pwd, Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	////////////////////////////////////////////私有方法/////////////////////////////////////////////////////////
	
	/**
	 * 检查用户名是否存在
	 * @param userName 用户名，为空直接返回true
	 * @return true存在/false不存在
	 */
	private boolean checkUserNameExists(String userName){
		{
			boolean result = true;
			if (StringUtils.isEmpty(userName)) {
				return result;
			}
			User model = new User();
			//执行select
			try {
				model = userRepo.findByUsername(userName);
			} catch (Exception e) {
				String error = "select指明用户名的用户时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			if (model == null) {
				result = false;
			}
			return result;
		}
	}
}
