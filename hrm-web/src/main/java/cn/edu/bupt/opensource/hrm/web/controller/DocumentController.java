package cn.edu.bupt.opensource.hrm.web.controller;

import cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants;
import cn.edu.bupt.opensource.hrm.common.util.tag.PageModel;
import cn.edu.bupt.opensource.hrm.domain.pojo.Document;
import cn.edu.bupt.opensource.hrm.domain.pojo.PersonModel;
import cn.edu.bupt.opensource.hrm.domain.pojo.User;
import cn.edu.bupt.opensource.hrm.service.HrmService;
import cn.edu.bupt.opensource.hrm.web.listener.ExcelProcessingListener;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**   
 * @Description: 处理上传下载文件请求控制器
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */

@Controller
public class DocumentController {

	private static Logger log = LoggerFactory.getLogger(DocumentController.class);

	@Resource
	private HrmService hrmService;

	/**
	 * 处理/login请求
	 * */
	@RequestMapping(value="/document/selectDocument")
	 public String selectDocument(
			Model model, Integer pageIndex,
			@ModelAttribute Document document){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Document> documents = hrmService.findDocument(document, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		return "document/document";
		
	}
	
	/**
	 * 处理添加请求
	 * @param  flag 标记， 1表示跳转到上传页面，2表示执行上传操作
	 * @param  mv
	 * */
	@RequestMapping(value="/document/addDocument")
	 public ModelAndView addDocument(
			 String flag,
			 @ModelAttribute Document document,
			 ModelAndView mv,
			 HttpSession session)throws Exception{
		if(flag.equals("1")){
			mv.setViewName("document/showAddDocument");
		}else{
			// 获取上传文件
			MultipartFile file = document.getFile();
			String originName = file.getOriginalFilename();
			String fileSuffix = originName.substring(originName.lastIndexOf(".") + 1).toUpperCase();
			try(InputStream inputStream = file.getInputStream()) {
				ExcelProcessingListener listener = new ExcelProcessingListener();
				ExcelReader reader;
				if("XLSX".equalsIgnoreCase(fileSuffix)) {
					reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
				} else {
					reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
				}
				reader.read(new Sheet(1, 1, PersonModel.class));
			} catch (Exception e) {
				log.warn("读取文件失败，原因是：", e);
			}
			log.info("文件解析成功");
			String path = session.getServletContext().getRealPath("/upload/");
			log.info("上传文件的存储路径，path={}", path);
			String fileName = document.getFile().getOriginalFilename();
			document.getFile().transferTo(new File(path+File.separator+ fileName));
			document.setFileName(fileName);
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			document.setUser(user);
			hrmService.addDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理删除文档请求
	 * @param  ids 需要删除的id字符串
	 * @param  mv
	 * */
	@RequestMapping(value="/document/removeDocument")
	 public ModelAndView removeDocument(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除文档
			hrmService.removeDocumentById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/document/selectDocument");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理修改文档请求
	 * @param  flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param  document 要修改文档的对象
	 * @param  mv
	 * */
	@RequestMapping(value="/document/updateDocument")
	 public ModelAndView updateDocument(
			 String flag,
			 @ModelAttribute Document document,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询文档
			Document target = hrmService.findDocumentById(document.getId());
			// 设置Model数据
			mv.addObject("document", target);
			// 设置跳转到修改页面
			mv.setViewName("document/showUpdateDocument");
		}else{
			// 执行修改操作
			hrmService.modifyDocument(document);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/document/selectDocument");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理文档下载请求
	 * */
	@RequestMapping(value="/document/downLoad")
	 public ResponseEntity<byte[]> downLoad(
			 Integer id,
			 HttpSession session) throws Exception{
		// 根据id查询文档
		Document target = hrmService.findDocumentById(id);
		String fileName = target.getFileName();
		// 上传文件路径
		String path = session.getServletContext().getRealPath(
                "/upload/");
		// 获得要下载文件的File对象
		File file = new File(path+File.separator+ fileName);
		// 创建springframework的HttpHeaders对象
		HttpHeaders headers = new HttpHeaders();
        // 下载显示的文件名，解决中文名称乱码问题  
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        // 通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
     // 201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
	}
	
}
