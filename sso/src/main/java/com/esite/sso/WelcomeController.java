/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.esite.sso;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class WelcomeController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	@Value("${spring.mvc.view.prefix: Hello nihaos}")
	private String testmes = "Hello nihao";

	@RequestMapping("/test")
	public String welcome(Map<String, Object> model) {
		System.out.println(testmes);
		System.out.println(123123123);
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(Map<String, Object> model) {

		return "welcome";
	}

	public static void main(String[] args){
		System.out.println("kaishi");
		String a = null;
		System.out.println(Integer.parseInt(a) );
	}

}
