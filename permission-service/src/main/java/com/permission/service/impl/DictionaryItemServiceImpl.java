package com.permission.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.Dictionary;
import com.permission.core.entity.DictionaryType;
import com.permission.core.entity.DictionaryType_;
import com.permission.core.entity.Dictionary_;
import com.permission.core.enums.StateEnum;
import com.permission.core.exception.ParameterNullException;
import com.permission.core.exception.ServiceException;
import com.permission.core.queryable.DictionaryQuery;
import com.permission.core.queryable.PageInfo;
import com.permission.core.vo.DictionaryVo;
import com.permission.repository.DictionaryRepository;
import com.permission.service.DictionaryItemService;

@Service
public class DictionaryItemServiceImpl extends PageService implements DictionaryItemService {
	
	private static final Logger logger = LoggerFactory.getLogger(DictionaryItemServiceImpl.class);
	
	@Autowired
	private DictionaryRepository itemRepo;
	
	@Override
	public boolean insertDicItem(DictionaryVo vo) throws Exception {
		{
			if(vo == null){
				throw new ParameterNullException("vo", DictionaryVo.class);
			}
		}
		//执行insert操作
		boolean result = false;
		Dictionary model = new Dictionary();
		BeanUtils.copyProperties(vo, model);
		if (vo.getDicTypeId() != null) {
			DictionaryType type = new DictionaryType();
			type.setId(vo.getDicTypeId());
			
			model.setDictionaryType(type);
		}
		//捕获异常
		try {
			itemRepo.save(model);
		} catch (Exception e) {
			String error = "insert字典项时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		vo.setState(StateEnum.ENABLE);
		//insert成功，回传id给vo对象，以供缓存时使用
		if(model.getId() != null){
			vo.setId(model.getId());
			result = true;
		}
		return result;
	}

	@Override
	public boolean modifyDicItem(DictionaryVo vo) throws Exception {
		{
			if(vo == null || vo.getId() == null){
				throw new ParameterNullException("vo或者vo中的id属性", DictionaryVo.class);
			}
		}
		boolean result = false;
		//填充Entity对象
		Dictionary model = new Dictionary();
		BeanUtils.copyProperties(vo, model);
		if(vo.getDicTypeId() != null){
			DictionaryType dicType = new DictionaryType();
			dicType.setId(vo.getDicTypeId());
			
			model.setDictionaryType(dicType);
		}
		//捕获异常
		try {
			itemRepo.saveAndFlush(model);
			result = true;
		} catch (Exception e) {
			String error = "update字典项时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		return result;
	}
	
	@Override
	public List<DictionaryVo> getByTypeId(Integer typeId) throws Exception {
		{
			if(typeId == null || typeId.intValue() == 0){
				throw new ParameterNullException("typeId", Integer.class);
			}
		}
		List<DictionaryVo> voList = new ArrayList<>();
		List<Dictionary> list = new ArrayList<>();
		//执行select，捕获异常
		try {
			list = itemRepo.queryByDicTypeId(typeId);
		} catch (Exception e) {
			String error = "select字典项的dic_type_id时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		//填充Vo对象集合
		if(!list.isEmpty()){
			for (Dictionary dic : list) {
				DictionaryVo vo = new DictionaryVo();
				BeanUtils.copyProperties(dic, vo);
				if(dic.getDictionaryType() != null){
					vo.setDicTypeId(dic.getDictionaryType().getId());
					vo.setDicTypeName(dic.getDictionaryType().getName());
				}
				
				voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public DictionaryVo getDetail(Integer id) throws Exception {
		{
			if(id == null || id.intValue() == 0){
				throw new ParameterNullException("id", Integer.class);
			}
		}
		DictionaryVo vo = new DictionaryVo();
		Dictionary model = null;
		//执行select，捕获异常
		try {
			model = itemRepo.queryById(id);
		} catch (Exception e) {
			String error = "select字典项主键时错误";
			if (logger.isErrorEnabled()) {
				logger.error(error, e);
			}
			throw new ServiceException(error);
		}
		//填充Vo对象
		if(model != null){
			BeanUtils.copyProperties(model, vo);
			if(model.getDictionaryType() != null){
				vo.setDicTypeId(model.getDictionaryType().getId());
				vo.setDicTypeName(model.getDictionaryType().getName());
			}
		}
		return vo;
	}

	@Override
	public PageInfo<DictionaryVo> pageQuery(DictionaryQuery query) throws Exception {
		{
			Pageable pageable = this.buildPageableInstance(query);
			Page<Dictionary> page = null;
			//执行select,捕获异常
			try {
				page = itemRepo.findAll(this.getWhereClause(query), pageable);
			} catch (Exception e) {
				String error = "分页select字典项时错误";
				if (logger.isErrorEnabled()) {
					logger.error(error, e);
				}
				throw new ServiceException(error);
			}
			//填充Vo类型的集合
			List<DictionaryVo> voList = new ArrayList<>();
			if(page != null && !page.getContent().isEmpty()){
				for (Dictionary model : page.getContent()) {
					DictionaryVo vo = new DictionaryVo();
					BeanUtils.copyProperties(model, vo);
					if(model.getDictionaryType() != null){
						vo.setDicTypeId(model.getDictionaryType().getId());
						vo.setDicTypeName(model.getDictionaryType().getName());
					}
					
					voList.add(vo);
				}
			}
			
			return new PageInfo<DictionaryVo>(page.getNumber() + 1,page.getTotalPages(),page.getTotalElements(),voList);
		}
	}
	
	//////////////////////////////////////////私有方法////////////////////////////////////////////////////
	
	/**
	 * 动态生成where查询语句
	 * @param queryVo	Dictionary查询对象
	 */
	private Specification<Dictionary> getWhereClause(final DictionaryQuery queryVo){
		{
			return new Specification<Dictionary>() {

				@Override
				public Predicate toPredicate(Root<Dictionary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					{
						List<Predicate> predicate = new ArrayList<>();
						Join<Dictionary, DictionaryType> joinDicType = root.join(Dictionary_.dictionaryType,JoinType.INNER);
						//Join<Dictionary, DictionaryType> joinDicType = root.join("dictionaryType", JoinType.INNER);//关联查询
						
						//关联查询id
						if (queryVo.getDicTypeId() != null && queryVo.getDicTypeId().intValue() != 0) {
							Path<Integer> dicTypeIdPath = joinDicType.get(DictionaryType_.id);
							Predicate dicTypeIdPre = cb.equal(dicTypeIdPath, queryVo.getDicTypeId());
							predicate.add(dicTypeIdPre);
						}
						//关联查询name
						if (!StringUtils.isEmpty(queryVo.getDicName())) {
							Path<String> dicTypeNamePath = joinDicType.get(DictionaryType_.name);
							Predicate dicTypeNamePre = cb.like(dicTypeNamePath, "%" + queryVo.getDicName() + "%");
							predicate.add(dicTypeNamePre);
						}
						
						Predicate[] pres = new Predicate[predicate.size()];
						return query.where(predicate.toArray(pres)).getRestriction();
					}
				}
			};
		}
	}
}
