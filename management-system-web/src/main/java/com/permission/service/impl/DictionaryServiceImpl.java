package com.permission.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.DictionaryType;
import com.permission.core.exception.ParamterNullException;
import com.permission.core.exception.RecordExistException;
import com.permission.core.vo.DictionaryTypeVo;
import com.permission.repository.DictionaryTypeRepository;
import com.permission.service.DictionaryService;

@Service
public class DictionaryServiceImpl extends BaseService implements DictionaryService {
	
	@Autowired
	private DictionaryTypeRepository typeRepo;
	
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
}
