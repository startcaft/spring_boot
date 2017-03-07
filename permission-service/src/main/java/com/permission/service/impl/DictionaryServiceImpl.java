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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.Dictionary;
import com.permission.core.entity.DictionaryType;
import com.permission.core.exception.ParamterNullException;
import com.permission.core.exception.RecordExistException;
import com.permission.core.queryable.DictionaryQuery;
import com.permission.core.queryable.PageInfo;
import com.permission.core.vo.DictionaryTypeVo;
import com.permission.core.vo.DictionaryVo;
import com.permission.core.vo.NodeTree;
import com.permission.repository.DictionaryRepository;
import com.permission.repository.DictionaryTypeRepository;
import com.permission.service.DictionaryService;

@Service
public class DictionaryServiceImpl extends BaseService implements DictionaryService {
	
	@Autowired
	private DictionaryTypeRepository typeRepo;
	
	@Autowired
	private DictionaryRepository itemRepo;
	
	@Override
	public boolean checkNameExists(String dicTypeName) throws Exception {
		{
			boolean result = true;
			if (StringUtils.isEmpty(dicTypeName)) {
				return result;
			}
			
			//查询指定name的记录
			DictionaryType model = typeRepo.queryByName(dicTypeName);
			if(model == null){
				result = false;
			}
			return result;
		}
	}
	
	@CachePut(value=CACHE_NAME,key="'dic_type_' + #vo.id")
	@Override
	public boolean insertRecord(DictionaryTypeVo vo) throws Exception {
		{
			if(vo == null){
				throw new ParamterNullException("vo", DictionaryTypeVo.class);
			}
			if (StringUtils.isEmpty(vo.getName())) {
				throw new ParamterNullException("vo中的name", DictionaryTypeVo.class);
			}
		}
		{
			//先检查name是否重复
			boolean result = false;
			if(this.checkNameExists(vo.getName())){
				throw new RecordExistException("字典类型名称");
			}
			//不抛出异常则执行insert
			DictionaryType model = new DictionaryType();
			BeanUtils.copyProperties(vo, model);
			if (vo.getPid() != null) {
				DictionaryType parent = new DictionaryType();
				parent.setId(vo.getPid());
			}
			typeRepo.save(model);
			if(model.getId() != null){
				vo.setId(model.getId());//缓存时候使用
				result = true;
			}
			return result;
		}
	}
	
	@Override
	public void recursiveTree(NodeTree node) throws Exception {
		
		Specification<DictionaryType> typeSpec = new TreeSpecification<>("parentType", null, node.getId());
		
		List<DictionaryType> childs = typeRepo.findAll(typeSpec);
		if (!childs.isEmpty()) {
			for (DictionaryType dicType : childs) {
				
				NodeTree n = new NodeTree(dicType.getId(), dicType.getParentType().getId(), dicType.getName());
				node.getChildren().add(n);
				
				recursiveTree(n);//递归
			}
		}
	}
	
	@Cacheable(value=CACHE_NAME,key="'dic_type_' + #id")
	@Override
	public DictionaryTypeVo getById(Integer id) throws Exception {
		{
			if (id == null) {
				throw new ParamterNullException("id", DictionaryType.class);
			}
		}
		DictionaryTypeVo vo = new DictionaryTypeVo();
		DictionaryType model = typeRepo.findOne(id);
		if (model != null) {
			BeanUtils.copyProperties(model, vo);
			if(model.getParentType() != null){
				vo.setPid(model.getParentType().getId());
				vo.setpName(model.getParentType().getName());
			}
		}
		return vo;
	}
	
	@CachePut(value=CACHE_NAME,key="'dic_type_' + #vo.id")
	@CacheEvict(value=CACHE_NAME,key="'dic_type_' + #vo.id")
	@Override
	public boolean modifyRecord(DictionaryTypeVo vo) throws Exception {
		{
			if(vo == null || vo.getId() == null){
				throw new ParamterNullException("vo或vo中的id属性", DictionaryTypeVo.class);
			}
		}
		boolean result = false;
		//先检查需要更新的名称是否为空
		if (!StringUtils.isEmpty(vo.getName())) {
			if(this.checkNameExists(vo.getName())){
				throw new RecordExistException("name");
			}
		}
		//执行更新
		DictionaryType model = new DictionaryType();
		BeanUtils.copyProperties(vo, model);
		if(vo.getPid() != null){
			DictionaryType parent = new DictionaryType();
			parent.setId(vo.getPid());
			
			model.setParentType(parent);
		}
		typeRepo.saveAndFlush(model);
		result = true;
		
		return result;
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	
	@CachePut(value=CACHE_NAME,key="'dic_item_' + #vo.id")
	@Override
	public boolean insertDicItem(DictionaryVo vo) throws Exception {
		{
			if(vo == null){
				throw new ParamterNullException("vo", DictionaryVo.class);
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
		
		itemRepo.save(model);
		if(model.getId() != null){
			vo.setId(model.getId());//缓存时候使用
			result = true;
		}
		return result;
	}

	@CachePut(value=CACHE_NAME,key="'dic_item_' + #vo.id")
	@CacheEvict(value=CACHE_NAME,key="'dic_item_' + #vo.id")
	@Override
	public boolean modifyDicItem(DictionaryVo vo) throws Exception {
		{
			if(vo == null || vo.getId() == null){
				throw new ParamterNullException("vo或者vo中的id属性", DictionaryVo.class);
			}
		}
		//执行更新操作
		boolean result = false;
		Dictionary model = new Dictionary();
		BeanUtils.copyProperties(vo, model);
		if(vo.getDicTypeId() != null){
			DictionaryType dicType = new DictionaryType();
			dicType.setId(vo.getDicTypeId());
			
			model.setDictionaryType(dicType);
		}
		itemRepo.saveAndFlush(model);
		return result;
	}
	
	@Cacheable(value=CACHE_NAME,key="'dic_item_list_' + #typeId")
	@Override
	public List<DictionaryVo> getByTypeId(Integer typeId) throws Exception {
		{
			if(typeId == null || typeId.intValue() == 0){
				throw new ParamterNullException("typeId", Integer.class);
			}
		}
		List<DictionaryVo> voList = new ArrayList<>();
		//执行select
		List<Dictionary> list = itemRepo.queryByDicTypeId(typeId);
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
				throw new ParamterNullException("id", Integer.class);
			}
		}
		DictionaryVo vo = new DictionaryVo();
		//执行select
		Dictionary model = itemRepo.queryById(id);
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
			if(query == null){
				throw new ParamterNullException("query", DictionaryQuery.class);
			}
		}
		//构建Pageable对象，pageIndex是从0开始的
		int pageIndex,pageSize;
		if (query.getPage() <= 0) {
			pageIndex = 0;
		}
		else{
			pageIndex = query.getPage() - 1;
		}
		if(query.getRows() <= 0){
			pageSize = 20;
		}
		else{
			pageSize = query.getRows();
		}
		Direction direction;
		if(query.getSort().equalsIgnoreCase("desc")){
			direction = Direction.DESC;
		}
		else{
			direction = Direction.ASC;
		}
		Order order = new Order(direction, query.getOrder());
		Sort sort = new Sort(order);
		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		//执行select
		Page<Dictionary> page = itemRepo.findAll(this.getWhereClause(query), pageable);
		List<DictionaryVo> voList = new ArrayList<>();
		if(!page.getContent().isEmpty()){
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
	
	
	/*
	 * 动态生成where查询语句
	 */
	private Specification<Dictionary> getWhereClause(final DictionaryQuery queryVo){
		{
			return new Specification<Dictionary>() {

				@Override
				public Predicate toPredicate(Root<Dictionary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicate = new ArrayList<>();
					if (queryVo.getDicTypeId() != null || queryVo.getDicTypeId().intValue() != 0) {
						predicate.add(cb.equal(root.get("id").as(Integer.class), queryVo.getDicTypeId()));
					}
					if (!StringUtils.isEmpty(queryVo.getDicName())) {//关联查询
						Join<Dictionary, DictionaryType> joinDicType = root.join("dictionaryType", JoinType.INNER);
						Path<String> joinPath = joinDicType.get("name");
						predicate.add(cb.like(joinPath.as(String.class), "%" + queryVo.getDicName() + "%"));
					}
					Predicate[] pres = new Predicate[predicate.size()];
					return query.where(predicate.toArray(pres)).getRestriction();
				}
			};
		}
	}
}
