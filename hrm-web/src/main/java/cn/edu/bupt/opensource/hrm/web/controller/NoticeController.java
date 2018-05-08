package cn.edu.bupt.opensource.hrm.web.controller;

import cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants;
import cn.edu.bupt.opensource.hrm.common.util.tag.PageModel;
import cn.edu.bupt.opensource.hrm.domain.pojo.Notice;
import cn.edu.bupt.opensource.hrm.domain.pojo.User;
import cn.edu.bupt.opensource.hrm.service.HrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**   
 * @Description: 处理公告请求控制器
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */

@Controller
public class NoticeController {

	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理/login请求
	 * */
	@RequestMapping(value="/notice/selectNotice")
	 public String selectNotice(Model model,Integer pageIndex,
			 @ModelAttribute Notice notice){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Notice> notices = hrmService.findNotice(notice, pageModel);
		model.addAttribute("notices", notices);
		model.addAttribute("pageModel", pageModel);
		return "notice/notice";
		
	}
	
	/**
	 * 处理添加请求
	 * @param  id  要显示的公告id
	 * @param  model
	 * */
	@RequestMapping(value="/notice/previewNotice")
	 public String previewNotice(
			 Integer id,Model model){
		
		Notice notice = hrmService.findNoticeById(id);
		
		model.addAttribute("notice", notice);
		// 返回
		return "notice/previewNotice";
	}
	
	/**
	 * 处理删除公告请求
	 * @param  ids 需要删除的id字符串
	 * @param  mv
	 * */
	@RequestMapping(value="/notice/removeNotice")
	 public ModelAndView removeNotice(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除公告
			hrmService.removeNoticeById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/notice/selectNotice");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param  flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param  notice  要添加的公告对象
	 * @param  mv
	 * */
	@RequestMapping(value="/notice/addNotice")
	 public ModelAndView addNotice(
			 String flag,
			 @ModelAttribute Notice notice,
			 ModelAndView mv,
			 HttpSession session){
		if(flag.equals("1")){
			mv.setViewName("notice/showAddNotice");
		}else{
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			notice.setUser(user);
			hrmService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param  flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param  notice  要添加的公告对象
	 * @param  mv
	 * */
	@RequestMapping(value="/notice/updateNotice")
	 public ModelAndView updateNotice(
			 String flag,
			 @ModelAttribute Notice notice,
			 ModelAndView mv,
			 HttpSession session){
		if(flag.equals("1")){
			Notice target = hrmService.findNoticeById(notice.getId());
			mv.addObject("notice",target);
			mv.setViewName("notice/showUpdateNotice");
		}else{
			hrmService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		// 返回
		return mv;
	}
	
	
}
