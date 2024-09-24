package kr.co.hivesys.statistic.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.hivesys.comm.SessionListener;
import kr.co.hivesys.statistic.service.StatisticService;
import kr.co.hivesys.statistic.vo.StkAreaVO;
import kr.co.hivesys.statistic.vo.LogDataVO;
import kr.co.hivesys.statistic.vo.MainStVo;
import kr.co.hivesys.statistic.vo.MainYTVo;
import kr.co.hivesys.statistic.vo.ScatterVO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import kr.co.hivesys.terminal.vo.TerminalVo;
import kr.co.hivesys.user.vo.UserVO;
/**
 * statistic 컨트롤러 클래스
 * @author 미래전략사업팀 정다빈
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2020.07.23  정다빈           최초 생성
 */

@Controller
public class StatisticController {
	
	public static final Logger logger = LoggerFactory.getLogger(StatisticController.class);
	
	@Resource(name="statisticService")
	private StatisticService statisticService;
	public String url="";
	
	List<TerminalVo> svoList =null;
	
	//주소에 맞게 매핑
	@RequestMapping(value="/stat/**/*.do")
	public String statisticUrlMapping(HttpServletRequest request) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		try {
			HttpSession chkSession = request.getSession(false);
			logger.debug("▶▶▶▶▶▶▶.체크세션 : "+chkSession);
			logger.debug("▶▶▶▶▶▶▶.체크세션 id: "+chkSession.getId());
			// 이미 접속한 아이디인지 체크
            // 현재 접속자들 보여주기
            SessionListener.getInstance().printloginUsers();
		} catch (Exception e) {
			System.out.println("에러메시지"+e.getMessage());
		}
		return url;
	}
	
	@RequestMapping(value="/stat/list.ajax")
	public @ResponseBody ModelAndView statList( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("TerminalVo") TerminalVo inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			svoList= statisticService.selectDayLilst(inputVo);
			mav.addObject("data", svoList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//메인차트 - 관리자
		@RequestMapping(value="/stat/mainAdminChart.ajax")
		public @ResponseBody ModelAndView mainAdminChart( 
				HttpServletRequest request, HttpServletResponse response
				,@ModelAttribute("TerminalVo") TerminalVo inputVo
				) throws Exception{
			url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
			
			ModelAndView mav = new ModelAndView("jsonView");
			List<TerminalVo>  data1= new ArrayList<>();
			List<TerminalVo>  data2= new ArrayList<>();
			List<TerminalVo>  data3= new ArrayList<>();
			List<TerminalVo>  data4= new ArrayList<>();
			try {
				data1= statisticService.mainChart1(inputVo);
				data2= statisticService.mainChart2(inputVo);
				data3= statisticService.barChart(inputVo);
				//data4= statisticService.selectDayLilst(inputVo);
				mav.addObject("data1", data1);
				mav.addObject("data2", data2);
				mav.addObject("data3", data3);
				//mav.addObject("data4", data4);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(""+e);
				mav.addObject("msg","에러가 발생했습니다.");
			}
			return mav;
		}
		
		//메인차트 - 일반
		@RequestMapping(value="/stat/mainUserChart.ajax")
		public @ResponseBody ModelAndView mainUserChart( 
				HttpServletRequest request, HttpServletResponse response
				,@ModelAttribute("TerminalVo") TerminalVo inputVo
				) throws Exception{
			url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
			
			ModelAndView mav = new ModelAndView("jsonView");
			List<TerminalVo>  data1= new ArrayList<>();
			List<TerminalVo>  data2= new ArrayList<>();
			List<TerminalVo>  data3= new ArrayList<>();
			
			UserVO reqLoginVo = (UserVO) request.getSession().getAttribute("login");
			if(!(reqLoginVo.getUserAuth().equals("0"))) {
				inputVo.setDepartCode(reqLoginVo.getDepartCode());
			}
			
			try {
				data1= statisticService.userRsrp(inputVo);
				data2= statisticService.userRsrq(inputVo);
				data3= statisticService.barChart(inputVo);
				mav.addObject("data1", data1);
				mav.addObject("data2", data2);
				mav.addObject("data3", data3);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(""+e);
				mav.addObject("msg","에러가 발생했습니다.");
			}
			return mav;
		}
	

	//그래프 캡쳐(div) 및 통계 데이터 다운로드
	@RequestMapping(value = "/capture/*.do", method = RequestMethod.POST)
	public void slip(Map<String, Object> model, XSSFWorkbook workbook, 
			HttpServletRequest req, HttpServletResponse res
			//,@RequestParam(required=false, value="imgVal")String imgVal
			)
            throws Exception {
		
		url = req.getRequestURI().substring(req.getContextPath().length()).split(".do")[0].split("/")[2];
		
		int x=1;//열 인덱스 -> 0부터 시작 1칸 띄우고 시작
		int y=1;//행 인덱스 -> 0부터 시작 1칸 띄우고 시작
		
		//일월연계 분기
		String title=url.split("_")[0];
		//받아온 일시
		String sndDt=url.split("_")[1];
		//다운로드 파일명
		String chartTitle="";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String nowDt = sdf.format(date);
			
		res.setCharacterEncoding("UTF-8");
		
		XSSFSheet sheet1 = workbook.createSheet("image_sheet");
		
		logger.debug("▶▶▶▶▶▶▶.web으로부터 차트이미지 전송받음 : "+title);
		
        //엑셀 행렬 객체 생성
        XSSFRow objRow = null;
        XSSFCell objCell = null;
        
        /*셀 스타일 셋팅 시작*/
        
        // Cell style(제목 부분)
        XSSFCellStyle ctStyle = workbook.createCellStyle();
        //정렬
        ctStyle.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
        ctStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
        XSSFFont ctFont = workbook.createFont();
        ctFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        ctFont.setFontHeight((short)(18*20));//18포인트
        ctStyle.setFont(ctFont);
        
        // Cell style(제목 부분-일시)
        XSSFCellStyle dtStyle = workbook.createCellStyle();
        
        //정렬
        dtStyle.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
        dtStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
        XSSFFont dtFont = workbook.createFont();
        dtFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        dtFont.setFontHeight((short)(14*20));//14포인트
        dtStyle.setFont(dtFont);
        
        //일일통계 각 항목별 소제목
        XSSFCellStyle dayTt = workbook.createCellStyle();
        //정렬
        dayTt.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
        dayTt.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
        XSSFFont daytFont = workbook.createFont();
        daytFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        dayTt.setFont(daytFont);
        
        // Cell style(th 부분)
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        //정렬
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
        //배경색
        titleStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //테두리 선 (우,좌,위,아래)
        titleStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
         
        // Cell style(데이터 내용 tb)
        XSSFCellStyle contentStyle = workbook.createCellStyle();
        contentStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.index);
        contentStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        contentStyle.setBorderTop(CellStyle.BORDER_THIN);
        contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
        contentStyle.setBorderRight(CellStyle.BORDER_THIN);
        contentStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        /*셀 스타일 셋팅 종료*/
        
        //엑셀 제목
        if(title.equals("DAY")) {
			chartTitle="일일 통계";
		}else if(title.equals("MONTH")) {
			chartTitle="월별 통계";
			sndDt=sndDt.replace(")", "월)");
		}else if(title.equals("YEAR")) {
			chartTitle="연도별 통계";
			sndDt=sndDt.replace(")", "년)");
		}else if(title.equals("SEA")){
			chartTitle="계절별 통계";
			sndDt=sndDt.replace(")", "년)");
		}else {
			chartTitle="월간(일별) 통계";
			sndDt=sndDt.replace(")", "월)");
		}
        
        x=7;
        //셀 병합
        sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+3));//행시작, 행종료, 열시작, 열종료(4칸 셀병합)
        objRow = sheet1.createRow(y);
        objCell = objRow.createCell(x);
        objCell.setCellStyle(ctStyle);
        objCell.setCellValue(chartTitle);
        x=(x+3)+1;
        //연월일시
        sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+3));//행시작, 행종료, 열시작, 열종료
        objCell = objRow.createCell(x);
        objCell.setCellStyle(dtStyle);
        objCell.setCellValue(sndDt);
        //개행
		x=1;y++;
        
		//차트 이미지 세팅
	    try {
	    	//이미지 시작
	    	//데이터링크를 스트링으로
	    	//그림 3개 의 경우 (그림 갯수만금 반드시 for문 만들어야함)
	    	if (req.getParameter("img_val")==null) {
	    		//차트 제목 셋팅
		    	sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+3));
		    	objRow = sheet1.createRow(y);
	        	objCell = objRow.createCell(x);
	        	objCell.setCellStyle(dayTt);
	            objCell.setCellValue(req.getParameter("title_val0"));
	            x=(x+3)+2;
		    	
	        	sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+3));
	        	objCell = objRow.createCell(x);
	            objCell.setCellStyle(dayTt);
	            objCell.setCellValue(req.getParameter("title_val1"));
	        	x=(x+3)+2;
	        	
	        	sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+3));
	            objCell = objRow.createCell(x);
	            objCell.setCellStyle(dayTt);
	            objCell.setCellValue(req.getParameter("title_val2"));
	            //개행
	    		x=1;y++;
	            
				for (int i = 0; i < 3; i++) {
					String idx = Integer.toString(i);
					String paramName = "img_val"+idx;
					String data0 = req.getParameter(paramName);
					//String data = imgVal;
			        //데이터 헤더부분 자름
			        data0 = data0.replaceAll("data:image/png;base64,", "");
			        //바이트로 그림데이터 치환
			        byte[] bytes = Base64.decodeBase64(data0.getBytes());
			        /////////////////////
			        
			        int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
			        XSSFCreationHelper helper = workbook.getCreationHelper();
		            XSSFDrawing drawing = sheet1.createDrawingPatriarch();
		            XSSFClientAnchor anchor = helper.createClientAnchor();
		            XSSFPicture pict = drawing.createPicture(anchor, pictureIdx);
		            
		            // 이미지를 출력할 CELL 위치 선정 & 크기 지정
		            // 화면 차트 배치에 따라 각각 핸들링 필요!!
		            //가로 4~7칸 세로 11칸의 이미지 기준
		            // 같은 pie(원형) 그래프라도 원이 찌그러져나오니 
		            // 가로길이를 다르게 함...
		            if (i==0) {
		            	//x=x+1;
		            	anchor.setCol1(x);//오른쪽으로 3칸 시작점
		                anchor.setRow1(y);//아래로 3칸 시작점
			            pict.resize(5,11);
			            x=(x+5);
					} else if(i==1){
						anchor.setCol1(x);//오른쪽으로 10칸 시작점
		                anchor.setRow1(y);//아래로 3칸 시작점
		                pict.resize(4,11);//열 6칸,행 16칸
		                x=(x+4);
					} else{ //마지막
						anchor.setCol1(x);//오른쪽으로 15칸 시작점
		                anchor.setRow1(y);//아래로 3칸 시작점
		                pict.resize(7,11);//열 10칸,행 16칸
					}
				}
				y=y+11;
			} else { //단일 이미지의 경우
				
				String data = req.getParameter("img_val");
		    	//String data = imgVal;
		        //데이터 헤더부분 자름
		        data = data.replaceAll("data:image/png;base64,", "");
		        //바이트로 그림데이터 치환
		        byte[] bytes = Base64.decodeBase64(data.getBytes());
		        /////////////////////
		        
		        int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
		        XSSFCreationHelper helper = workbook.getCreationHelper();
	            XSSFDrawing drawing = sheet1.createDrawingPatriarch();
	            XSSFClientAnchor anchor = helper.createClientAnchor();
	            
	            // 이미지를 출력할 CELL 위치 선정
	            if(title.equals("MONTH") || title.equals("DATE")) {//월일 경우
	            	anchor.setCol1(2);//오른쪽으로 3칸 시작점
	                anchor.setRow1(3);//아래로 4칸 시작점
	            }else {
	            	anchor.setCol1(3);//오른쪽으로 4칸 시작점
	                anchor.setRow1(2);//아래로 3칸 시작점
	            }
	            
	            // 이미지 그리기
	            XSSFPicture pict = drawing.createPicture(anchor, pictureIdx);
	            
	            // 이미지 사이즈 비율 설정
	            if(title.equals("MONTH") || title.equals("DATE")) {//월일 경우
	            	pict.resize(16,13);//행 13칸,열 15칸
	            }else {
	            	pict.resize(13,15);//행 13칸,열 15칸
	            }
			}
            //이미지 끝
	    	//개행
    		x=1;y++;
            /*표 부분 생성*/
            /*th (컬럼제목)*/
    		sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+2));
            objRow = sheet1.createRow(y);
            //왜 이렇게 그지같이 되있는진 모르겠으나 ㅡㅡ
            //셀 1칸(1열1행) 기준으로 스타일이 먹음
            //따라서 노가다를 해야함
            //3칸 셀병합 기준
            
            //순서 : 셀병합 -> 스타일적용(각각의 병합하려는 셀 모두) ->  
            
            for (int i = 0; i < 3; i++) {
            	objCell = objRow.createCell(x);
                objCell.setCellStyle(titleStyle);
                objCell.setCellValue("사용 용도");
                x++;
			}
            sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+2));
            for (int i = 0; i < 3; i++) {
            	objCell = objRow.createCell(x);
            	objCell.setCellStyle(titleStyle);
            	objCell.setCellValue("WAN_IP");
            	x++;
            }
            objCell = objRow.createCell(x);
        	objCell.setCellStyle(titleStyle);
        	objCell.setCellValue("RSSI");
        	x++;
        	objCell = objRow.createCell(x);
        	objCell.setCellStyle(titleStyle);
        	objCell.setCellValue("RSRP");
        	x++;
        	objCell = objRow.createCell(x);
        	objCell.setCellStyle(titleStyle);
        	objCell.setCellValue("RSRQ");
        	x++;
        	objCell = objRow.createCell(x);
        	objCell.setCellStyle(titleStyle);
        	objCell.setCellValue("UPLOAD");
        	x++;
        	sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+1));
            for (int i = 0; i < 2; i++) {
            	objCell = objRow.createCell(x);
            	objCell.setCellStyle(titleStyle);
            	objCell.setCellValue("DOWNLOAD");
            	x++;
            }
            sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+1));
            for (int i = 0; i < 2; i++) {
            	objCell = objRow.createCell(x);
            	objCell.setCellStyle(titleStyle);
            	objCell.setCellValue("장애발생시각");
            	x++;
            }
            /*sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+1));
            for (int i = 0; i < 2; i++) {
            	objCell = objRow.createCell(x);
            	objCell.setCellStyle(titleStyle);
            	objCell.setCellValue("장애복구시각");
            	x++;
            }*/
        	//개행
    		x=1;y++;
    		
            //TD(통계 vo 리스트 데이터 삽입)
            for (int i = 0; i < svoList.size(); i++) {
            	sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+2));
                objRow = sheet1.createRow(y);
                for (int j = 0; j < 3; j++) {
                	objCell = objRow.createCell(x);
                    objCell.setCellStyle(contentStyle);
                    objCell.setCellValue(svoList.get(i).getLteRUsed());
                    x++;
    			}
                sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+2));
                for (int j = 0; j < 3; j++) {
                	objCell = objRow.createCell(x);
                	objCell.setCellStyle(contentStyle);
                	objCell.setCellValue(svoList.get(i).getLteRIp());
                	x++;
                }
                objCell = objRow.createCell(x);
            	objCell.setCellStyle(contentStyle);
            	objCell.setCellValue(svoList.get(i).getRSSI());
            	x++;
            	objCell = objRow.createCell(x);
            	objCell.setCellStyle(contentStyle);
            	objCell.setCellValue(svoList.get(i).getRSRP());
            	x++;
            	objCell = objRow.createCell(x);
            	objCell.setCellStyle(contentStyle);
            	objCell.setCellValue(svoList.get(i).getRSRQ());
            	x++;
            	objCell = objRow.createCell(x);
            	objCell.setCellStyle(contentStyle);
            	objCell.setCellValue(svoList.get(i).getLteRComUpVal());
            	x++;
            	sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+1));
                for (int j = 0; j < 2; j++) {
                	objCell = objRow.createCell(x);
                	objCell.setCellStyle(contentStyle);
                	objCell.setCellValue(svoList.get(i).getLteRComDnVal());
                	x++;
                }
                sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+1));
                for (int j = 0; j < 2; j++) {
                	objCell = objRow.createCell(x);
                	objCell.setCellStyle(contentStyle);
                	objCell.setCellValue(svoList.get(i).getFailureRegYdt());
                	x++;
                }
                /*sheet1.addMergedRegion(new CellRangeAddress(y,y,x,x+1));
                for (int j = 0; j < 2; j++) {
                	objCell = objRow.createCell(x);
                	objCell.setCellStyle(contentStyle);
                	objCell.setCellValue(svoList.get(i).getFailbackYdt());
                	x++;
                }*/
            	//개행
            	x=1;y++;
			}
            
	    }catch(Exception e) {
	    	logger.debug(e.toString());
	        e.printStackTrace();
	    }
	    
	 // 파일명 설정 ------------------------------------------///
        String fileName = "download";
        if (model.get("fileName") != null && !((String)model.get("fileName")).equals("")) {
            fileName = (String)model.get("fileName");
        }
        
        
        // 브라우저에 따른 파일명 인코딩
        String userAgent = req.getHeader("User-Agent");
        if (userAgent.indexOf("MSIE") > -1) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        
        //들어온 request에 따른(일 월 연 계) 파일명 분기처리
        res.setHeader("Content-Disposition", "attachment; filename="+title+"_"+nowDt+".xlsx"); 
        res.setHeader("Content-Description", "JSP Generated Data"); 
        res.setContentType("application/vnd.ms-excel"); 
        
        ServletOutputStream out= res.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close(); 
	}

}
