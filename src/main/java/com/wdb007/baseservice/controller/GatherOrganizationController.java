package com.wdb007.baseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wdb007.baseservice.model.customer.Organization;
import com.wdb007.baseservice.service.OrganizationAnalyzerService;
import com.wdb007.baseservice.utility.response.BaseResponse;

import springfox.documentation.annotations.ApiIgnore;
@RequestMapping("/dianping")
@RestController
@ApiIgnore
public class GatherOrganizationController extends BaseController {
  
   @Autowired
   private OrganizationAnalyzerService organizationAnalyzerService;
	
	@RequestMapping(value="/resolveShopInfo",method=RequestMethod.GET)
	public BaseResponse<Object> resolveShopInfo(){
		BaseResponse<Object> baseResponse = success();
	    List<Organization> listOrganization=organizationAnalyzerService.getOrganization();
	    baseResponse.setDetail(listOrganization);
		return baseResponse;
	}
}
