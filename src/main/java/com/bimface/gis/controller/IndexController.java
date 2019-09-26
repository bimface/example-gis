package com.bimface.gis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bimface.gis.bean.GeneralResponse;
import com.bimface.gis.utils.HttpUtil;

@Controller
@RequestMapping("/")
public class IndexController{
	private static final String URL_GET_VIEW_TOKEN = "https://bimface.com/console/share/preview/viewtoken";

	@Value("${share.token.bim1}")
    private String shareTokenBim1;
	@Value("${share.token.bim2}")
    private String shareTokenBim2;
	@Value("${share.token.bim3}")
    private String shareTokenBim3;
	
	@Value("${example.id}")
    private Integer exampleIdGis;
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "exampleId", required = false) Integer exampleId,
    		ModelMap model) {
    	if(exampleId == null || exampleId == 0) {
    		exampleId = exampleIdGis;
    	}
    	
    	String viewToken1 = getViewTokenFromShareToken(shareTokenBim1);
    	String viewToken2 = getViewTokenFromShareToken(shareTokenBim2);
    	String viewToken3 = getViewTokenFromShareToken(shareTokenBim3);
    	
    	model.addAttribute("viewToken1", viewToken1);
    	model.addAttribute("viewToken2", viewToken2);
    	model.addAttribute("viewToken3", viewToken3);
    	model.addAttribute("exampleId", exampleId);
    	
        return new ModelAndView("gis/index");
    }

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    @ResponseBody
    public String go() {
        return "ok";
    }
    
    /**
     * 根据sharetoken获取viewtoken
     * @param shareToken
     * @return
     */
    @SuppressWarnings("unchecked")
	private static String getViewTokenFromShareToken(String shareToken) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("token", shareToken);
        String responseContent = HttpUtil.httpGet(URL_GET_VIEW_TOKEN, param);
        GeneralResponse<String> generalResponse = null;
        try {
        	generalResponse = JSON.parseObject(responseContent, GeneralResponse.class);
        } catch (Exception e) {
        	return e.getMessage();
        }
        return generalResponse.getData();
    }
}
