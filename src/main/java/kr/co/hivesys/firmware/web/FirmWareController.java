package kr.co.hivesys.firmware.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hivesys.comm.file.FileUploadSave;
import kr.co.hivesys.comm.file.service.FileService;
import kr.co.hivesys.comm.file.vo.FileVo;


@Controller
public class FirmWareController implements ApplicationContextAware{

	public static final Logger logger = LoggerFactory.getLogger(FirmWareController.class);
	
	private WebApplicationContext context = null;
	
	@Resource(name="fileService")
	private FileService fileService;
	
	public String url="";
	
	//주소에 맞게 매핑
	@RequestMapping(value= "/firmware/*.do")
	public String urlMapping(HttpSession httpSession, HttpServletRequest request,Model model
			) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.고객지원 관리 페이지 최초 진입 및 분기 컨트롤러");
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}
	
	//펌웨어 목록 조회
	@RequestMapping(value="/firmware/list.ajax")
	public @ResponseBody ModelAndView reqList( 
			HttpServletRequest request
			//@RequestParam(required=false, value="idArr[]")List<String> listArr
			,@ModelAttribute("FileVo") FileVo inputVo) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		List<FileVo> sList= null;
		try {
			sList = fileService.selectFileList(inputVo);
			mav.addObject("data", sList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//펌웨어 등록 저장
	@RequestMapping(value= "/firmware/insert.ajax")
	public ModelAndView insertReq(
			@RequestParam("multiFile") List<MultipartFile> multiFileList
			,HttpSession httpSession, 
			HttpServletRequest request,Model model
			,@ModelAttribute("FileVo") FileVo inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			/*파일 업로드 관련*/
			String fdir = context.getServletContext().getRealPath("/")+"firmwareFile/"+inputVo.getFileVersion()+"/";
			FileUploadSave fus = new FileUploadSave();
			List<FileVo> fileList = fus.fileUploadMultiple(multiFileList,fdir,inputVo);
			if(fileList.size()!=0) {
				fileService.insertFile(fileList);
			}
			//mav.addObject("cnt", cnt);
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
			mav.addObject("msg","저장에 실패하였습니다");
		}
		return mav;
	}
	
	//구버전 펌웨어 프로그램에서 다운로드 할때
	@RequestMapping(value= {"/700M/**","/700P/**"})
	public ModelAndView download(
			String fileId, ModelAndView mView
			,HttpSession httpSession, 
			HttpServletRequest request,Model model
			) {
		String[] downUrl = request.getRequestURI().substring(request.getContextPath().length()).split("/");
		FileVo fvo = new FileVo();
		String filePath= downUrl[2]+"/"+downUrl[3];
		/*if(fileId.equals("700P-I")) {
			filePath="/700P/"+fileId;
		}else if(fileId.equals("700P-V")) {
			filePath="/700P/"+fileId;
		}else if(fileId.equals("700M-I")) {
			filePath="/700M/"+fileId;
		}else if(fileId.equals("700M-V")) {
			filePath="/700M/"+fileId;
		} else {
			return mView;
		}*/
		fvo.setFileName(downUrl[3]);
		fvo.setFilePath(filePath);
		mView.addObject("fvo", fvo);
		// 응답을 할 bean의 이름 설정
		mView.setViewName("fileDownView");
		return mView;
	}
		
	//web페이지에서 다운로드할때(신버전)
	@RequestMapping("/fileDownload.ajax")
	public ModelAndView fileDownload(
			String fileId, ModelAndView mView
			) {
		FileVo fvo = new FileVo();
		fvo.setFileId(fileId);
		fvo=fileService.selectFileList(fvo).get(0);
		String filePath = fvo.getFileDir()+fvo.getFileName();
		fvo.setFilePath(filePath);
		mView.addObject("fvo", fvo);
		// 응답을 할 bean의 이름 설정
		mView.setViewName("fileDownView");
		return mView;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}
	
}
