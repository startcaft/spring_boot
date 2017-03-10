package com.permission.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.permission.core.vo.DictionaryTypeVo;
import com.permission.core.vo.NodeTree;
import com.permission.service.DictionaryTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin/dic")
@Api(value="数据字典相关API")
public class DictionaryController extends BaseController {
	
	@Autowired
	private DictionaryTypeService dicTypeService;
	
	@ApiOperation(value="添加一个新的字典类别",consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="form",name="name",dataType="String",required=true,value="字典类别的名称"),
		@ApiImplicitParam(paramType="form",name="code",dataType="String",required=false,value="字典类别的编码"),
		@ApiImplicitParam(paramType="form",name="seq",dataType="int",required=false,value="字典类别的排序值",defaultValue="1"),
		@ApiImplicitParam(paramType="form",name="desc",dataType="String",required=false,value="字典类别的描述"),
		@ApiImplicitParam(paramType="form",name="pid",dataType="int",required=false,value="字典类别的父节点ID")
	})
	@RequestMapping(value="/type",method=RequestMethod.POST,consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE},produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public JSONResult addDicType(@RequestParam(value="name",required=true) String name,
								@RequestParam(value="code",required=false) String code,
								@RequestParam(value="seq",required=true,defaultValue="1") Integer seq,
								@RequestParam(value="desc",required=false) String desc,
								@RequestParam(value="pid",required=false) Integer pid){
		{
			JSONResult result = new JSONResult();
			DictionaryTypeVo vo = new DictionaryTypeVo();
			vo.setCode(code);
			vo.setName(name);
			vo.setSeq(seq);
			vo.setDescription(desc);
			vo.setPid(pid);
			try {
				dicTypeService.insertRecord(vo);
				result.setSuccess(true);
				result.setResponse(vo);
			} catch (Exception e) {
				result.setTipInfo(e.getMessage());
			}
			return result;
		}
	}
	
	@ApiOperation(value="获取字典类别详细信息",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,notes="根据url路径中的ID参数获取字典类别的详细信息(包含一级父节点的信息)")
	@ApiImplicitParam(paramType="path",name="typeid",dataType="int",required=true,value="字典类别ID")
	@RequestMapping(value="/type/{typeid}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public JSONResult getTypeDetail(@PathVariable("typeid") Integer typeId){
		{
			JSONResult result = new JSONResult();
			try {
				DictionaryTypeVo typeVo = dicTypeService.getById(typeId);
				if (typeVo == null) {
					result.setTipInfo("查询不到指定ID的字典类别数据");
				}
				else{
					result.setSuccess(true);
					result.setResponse(typeVo);
				}
			} catch (Exception e) {
				result.setTipInfo(e.getMessage());
			}
			return result;
		}
	}
	
	@ApiOperation(value="获取字典类别树状结构",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,notes="根据url路径中的ID参数获取指定字典类别的树状结构")
	@ApiImplicitParam(paramType="path",name="typeid",dataType="int",required=true,value="字典类别ID")
	@RequestMapping(value="/typetree/{typeid}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public JSONResult getNodeTree(@PathVariable("typeid") Integer typeId){
		{
			JSONResult result = new JSONResult();
			NodeTree node = new NodeTree();
			node.setId(typeId);
			try {
				dicTypeService.getDicTypeTree(node);
				result.setSuccess(true);
				result.setResponse(node);
			} catch (Exception e) {
				result.setTipInfo(e.getMessage());
			}
			return result;
		}
	}
	
	@ApiOperation(value="更新指定的字典类别信息",consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="path",name="typeid",dataType="int",required=true,value="字典类别ID"),
		@ApiImplicitParam(paramType="form",name="name",dataType="String",required=false,value="新的字典类别的名称"),
		@ApiImplicitParam(paramType="form",name="code",dataType="String",required=false,value="新的字典类别的编码"),
		@ApiImplicitParam(paramType="form",name="seq",dataType="int",required=false,value="新的字典类别的排序值"),
		@ApiImplicitParam(paramType="form",name="desc",dataType="String",required=false,value="新的字典类别的描述"),
		@ApiImplicitParam(paramType="form",name="pid",dataType="int",required=false,value="新的字典类别的父节点ID")
	})
	@RequestMapping(value="/type/{typeid}",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public JSONResult updateType(@PathVariable("typeid") Integer typeId,
								@RequestParam(name="name",required=false) String name,
								@RequestParam(value="code",required=false) String code,
								@RequestParam(value="seq",required=false) Integer seq,
								@RequestParam(value="desc",required=false) String desc,
								@RequestParam(value="pid",required=false) Integer pid){
		{
			JSONResult result = new JSONResult();
			DictionaryTypeVo vo = new DictionaryTypeVo();
			vo.setId(typeId);
			vo.setName(name);
			vo.setSeq(seq);
			vo.setCode(code);
			vo.setDescription(desc);
			vo.setPid(pid);
			try {
				dicTypeService.modifyRecord(vo);
				result.setSuccess(true);
			} catch (Exception e) {
				result.setTipInfo(e.getMessage());
			}
			return result;
		}
	}
}
