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
 * @author chengtf
 * @date 2019/1/20
 */
@Controller
public class DocumentController {

	private static Logger log = LoggerFactory.getLogger(DocumentController.class);

	@Resource
	private HrmService hrmService;

	/**
	 * 处理login请求
	 * */
	@RequestMapping(value="/document/selectDocument")
	 public String selectDocument(
			Model model, Integer pageIndex,
			@ModelAttribute Document document){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Document> documents = hrmService.findDocument(document, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		return "document/document";
		
	}
	
	/**
	 * 文档上传
	 */
	@RequestMapping(value="/document/addDocument")
	 public ModelAndView addDocument(
			 String flag,
			 @ModelAttribute Document document,
			 ModelAndView mv,
			 HttpSession session)throws Exception{
		if(flag.equals("1")){
			mv.setViewName("document/showAddDocument");
		}else{
			MultipartFile file = document.getFile();
			String originName = file.getOriginalFilename();
			String fileSuffix = originName.substring(originName.lastIndexOf(".") + 1).toUpperCase();
			// 解析Excel文件
			try(InputStream inputStream = file.getInputStream()) {
				ExcelProcessingListener listener = new ExcelProcessingListener();
				ExcelReader reader;
				if(ExcelTypeEnum.XLSX.getValue().equalsIgnoreCase(fileSuffix)) {
					reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
				} else {
					reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
				}
				reader.read(new Sheet(1, 1, PersonModel.class));
				log.info("Excel文件解析成功");
			} catch (Exception e) {
				log.warn("Excel文件解析失败：{}", e);
			}
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
	 * 文档下载
	 */
	@RequestMapping(value="/document/downLoad")
	public ResponseEntity<byte[]> downLoad(
			Integer id,
			HttpSession session) throws Exception{
		Document target = hrmService.findDocumentById(id);
		String fileName = target.getFileName();
		String path = session.getServletContext().getRealPath("/upload/");
		File file = new File(path+File.separator+ fileName);
		HttpHeaders headers = new HttpHeaders();
		// 下载显示的文件名，解决中文名称乱码问题
		String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		// 通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFielName);
		// application/octet-stream ： 二进制流数据（最常见的文件下载）。
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 201 HttpStatus.CREATED
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/**
	 * 删除文档
	 * */
	@RequestMapping(value="/document/removeDocument")
	 public ModelAndView removeDocument(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			hrmService.removeDocumentById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/document/selectDocument");
		return mv;
	}
	
	/**
	 * 修改文档
	 * */
	@RequestMapping(value="/document/updateDocument")
	 public ModelAndView updateDocument(
			 String flag,
			 @ModelAttribute Document document,
			 ModelAndView mv){
		if(flag.equals("1")){
			Document target = hrmService.findDocumentById(document.getId());
			mv.addObject("document", target);
			mv.setViewName("document/showUpdateDocument");
		}else{
			hrmService.modifyDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		return mv;
	}
	
}
