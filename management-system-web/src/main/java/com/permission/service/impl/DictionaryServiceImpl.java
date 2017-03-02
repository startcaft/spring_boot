package com.permission.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.permission.core.entity.DictionaryType;
import com.permission.core.vo.DictionaryTypeVo;
import com.permission.repository.DictionaryTypeRepository;
import com.permission.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
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
	
	@Override
	public boolean insertRecord(DictionaryTypeVo vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
