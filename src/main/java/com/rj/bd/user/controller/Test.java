package com.rj.bd.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class Test {

	
	/**
	 * @desc freemarke界面
	 * @param model
	 * @return
	 */
	  @RequestMapping("/getFk")
	    public ModelAndView getFk(){
		  System.out.println("sss");
	        ModelAndView mView=new ModelAndView();
	        mView.addObject("name","企业融资最应该关注的是团队，而投资者最关注的也是团队；投资人希望看到你要做的事是适合你做的；创业者做项目，需要有相关气质，积累过相关经验，在要做的事情上有过积累，不能只是因为你想做一件事你就去做；如果你要做旅游，那VC希望你是热爱旅游的；如果你要做社交，那么你应该热爱社交，是微博、微信上的社交达人；如果你要做游戏，那么你应该是一个骨灰级玩家或资深开发者；突出团队项目的经历和经验，与当前项目的匹配之处；你之前做过什么不重要，重要的是你之前的经历和经验跟现有项目的契合度；需要介绍团队主要成员的背景和特长。强调个人的能力适合该岗位，团队的组合适合创业项目。");
	        mView.setViewName("shi");
	        return mView;
	    }
}
