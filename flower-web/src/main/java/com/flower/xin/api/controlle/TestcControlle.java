package com.flower.xin.api.controlle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class TestcControlle {

	@RequestMapping("x")
	@ResponseBody
	public Object x() {
		return "success";
	}
}
