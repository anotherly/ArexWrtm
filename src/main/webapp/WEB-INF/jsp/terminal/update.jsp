<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>단말장치(LTE-R) 관리 WEB 시스템</title>
<meta charset="UTF-8">
<jsp:include page="../cmn/top.jsp" flush="false" />
 	 
	<script>
	
		$(document).ready(function() {
			console.log("고객 업데이트 화면");
			
			//최대일과 현재일이 같을 경우 발생할수 있는 문제에 대해 최대일에 +1초 
			//먼저 변수 선언
			var maxD = moment().add(1, 'seconds').format("YYYY-MM-DD");
			var defaultD  = moment().format("YYYY-MM-DD");
			$('#datetimepicker1,#datetimepicker2,#datetimepicker3,#datetimepicker4,#datetimepicker5').datetimepicker({
				 format:"YYYY-MM-DD",
				 //maxDate : maxD, 
				 defaultDate: defaultD
			 });
			
			$('.glyphicon-calendar').hover(function(){
				console.log('달력 체크2');
			});
			
			//팀 변경시
			$('#CPY_CODE').on("change",function(){
				var tagId = $(this).val();
				var ajaxData=ajaxMethod('/terminal/routerTeamCnt.ajax',{"departCode":tagId}).data;
				$("#teamCnt").val(ajaxData[0].teamCnt);
				$("#deviceCnt").val(ajaxData[0].deviceCnt);
			});
			
			$("#btnSave").on('click',function(){
				console.log("정보 저장");
				var validChk = true;
				$(".input_base_require").each(function(i,list){
					console.log("필수값체크");
					if($(this).val()==null||$(this).val()==''){
						alert("필수 항목을 기재해 주세요");
						$(this).focus();
						validChk=false;
						return false;
					}
				});
				
				$(".input_base_number").each(function(i,list){
					console.log("숫자체크");
					if(!(($(this).val()==null||$(this).val()=='')) &&  isNaN($(this).val())){
						alert("MSP 요금제는 숫자값만 입력 가능합니다");
						$(this).focus();
						validChk=false;
						return false;
					}
				});
				
				if(validChk){
					$("#lteRCode").val($("#CPY_CODE").val());
					let queryString = $("#acDetailFrm").serialize();
					ajaxMethod('/terminal/update.ajax',queryString,'/terminal/list.do','저장되었습니다');
				}
			}); 
			
			//y면 체크 아니면 비체크인데 비체크값을 n으로 변경
			$('input[type="checkbox"]').each(function(i,list){
				console.log("하단체크박스 : "+i+"	/	"+$(this).attr("id"));
				if($(this).is(':checked')){
					$(this).val('Y');
				}else{
					$(this).val('N');
				}
			});
			
			//input 하위 모든 체크박스 클릭 시
			$('input[type="checkbox"]').on('click',function(){
				console.log("하단체크박스클릭");
				if($(this).is(':checked')){
					$(this).val('Y');
				}else{
					$(this).val('N');
				}
			});
			
			$("#btnCancel").on('click',function(){
				location.href='/terminal/list.do';
			});
			
			$("#btnReload").on('click',function(){
				var rData=ajaxMethod('/terminal/deviceReload.ajax',{"lteRIp":$("#lteRIp").val()}).rData;
				console.log(rData);
				//$("#lteRUsed").val(rData.lteRUsed);
				//$("#insLocTxt").val(rData.insLocTxt);
				$("#lteRImei").val(rData.lteRImei);
				$("#lteRImsi").val(rData.lteRImsi);
				$("#lteRserial").val(rData.lteRserial);
				$("#lteRMacAdd").val(rData.lteRMacAdd);
				//$("#lteRIp").val(rData.lteRIp);
				//$("#conGw").val(rData.conGw);
				$("#conSystem01Ip").val(rData.conSystem01Ip);
				//$("#conSystem01").val(rData.conSystem01);
				$("#conSystem02Ip").val(rData.conSystem02Ip);
				//$("#conSystem02").val(rData.conSystem02);
				$("#lteRSimNo").val(rData.lteRSimNo);
				//$("#lteRUseRId").val(rData.lteRUseRId);
				$("#lteRDeviceType").val(rData.lteRDeviceType);
				$("#swVerCode").val(rData.swVerCode);
				$("#lterCellId").val(rData.lterCellId);
				$("#lteRBand").val(rData.lteRBand);
				$("#lteRChannel").val(rData.lteRChannel);
				$("#lteRMCC").val(rData.lteRMCC);
				$("#lteRMNC").val(rData.lteRMNC);
				$("#getDeviceInfoYdt").val(rData.getDeviceInfoYdt);
			});
			
		});
	</script>
	

</head>
	<!-- work Start -->
	<div id="work" class="work-wrap" style="padding-top: 0px;">
		<!-- contents_box Start -->
		<div id="contents_box" class="contents_box" style="margin-top: 0px;">
			<!-- 컨텐츠 테이블 헤더 Start -->
			<div class="ctn_tbl_header">
				<div class="ttl_ctn">단말기 정보 수정</div>
				<!-- 컨텐츠 타이틀 -->
				<div class="txt_info">
					<em class="txt_info_rep">*</em> 표시는 필수 입력 항목입니다.
				</div>
				<!-- 설명글 -->
			</div>
			<!-- 컨텐츠 테이블 헤더 End -->
			<!-- 컨텐츠 테이블 영역 Start -->
			<form id="acDetailFrm" name="acDetailFrm" method="post" enctype="multipart/form-data">
				<div class="ctn_tbl_area">
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th fm_rep">팀명</div>
						<div class="ctn_tbl_td">
							<input type="hidden" id="lteRCode" name ="lteRCode" class="form-control">
							<input type="hidden" id="teamCnt" name ="teamCnt" placeholder="" class="form-control" readonly>
							<input type="hidden" id="deviceCnt" name ="deviceCnt" placeholder="" class="form-control" readonly>
							<input type="hidden" id="getDeviceInfoYdt" name ="getDeviceInfoYdt" placeholder="" class="form-control" readonly>
							
							<div class="form-group">
	                            <select name="select" id="CPY_CODE" class="form-control">
	                            	<c:set var="SHT" value="ARX-SCH-SHT" />
	                            	<c:set var="SST" value="ARX-SSC-SST" />
	                            	<c:set var="JGT" value="ARX-ELC-JGT" />
	                                <option value="ARX-SCH-SHT" <c:if test="${data.lteRCode eq SHT}">selected</c:if> >신호팀</option>
	                                <option value="ARX-SSC-SST" <c:if test="${data.lteRCode eq SST}">selected</c:if> >시설팀</option>
	                                <option value="ARX-ELC-JGT" <c:if test="${data.lteRCode eq JGT}">selected</c:if> >전기팀</option>
	                            </select>
	                        </div>
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">장비명(사용 용도)</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRUsed" name ="lteRUsed" value="${data.lteRUsed}" class="form-control">
						</div>
						<div class="ctn_tbl_th">설치 위치</div>
						<div class="ctn_tbl_td">
							<input type="text" id="insLocTxt" name ="insLocTxt" value="${data.insLocTxt}" class="form-control">
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">IMEI 정보</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRImei" name ="lteRImei" value="${data.lteRImei}" class="form-control">
						</div>
						<div class="ctn_tbl_th">IMSI 정보</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRImsi" name ="lteRImsi" value="${data.lteRImsi}" class="form-control">
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">Serial No</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRserial" name ="lteRserial" value="${data.lteRserial}" class="form-control">
						</div>
						<div class="ctn_tbl_th">MAC Add</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRMacAdd" name ="lteRMacAdd" value="${data.lteRMacAdd}" class="form-control" readonly>
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">WAN IP</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRIp" name ="lteRIp" value="${data.lteRIp}" class="form-control" readonly>
						</div>
						<div class="ctn_tbl_th">게이트웨이 IP</div>
						<div class="ctn_tbl_td">
							<input type="text" id="conGw" name ="conGw" value="${data.conGw}" class="form-control">
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">Client#1 IP</div>
						<div class="ctn_tbl_td">
							<input type="text" id="conSystem01Ip" name ="conSystem01Ip" value="${data.conSystem01Ip}" class="form-control">
						</div>
						<div class="ctn_tbl_th">Client#1 이름</div>
						<div class="ctn_tbl_td">
							<input type="text" id="conSystem01" name ="conSystem01" value="${data.conSystem01}" class="form-control">
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">Client#2 IP</div>
						<div class="ctn_tbl_td">
							<input type="text" id="conSystem02Ip" name ="conSystem02Ip" value="${data.conSystem02Ip}" class="form-control">
						</div>
						<div class="ctn_tbl_th">Client#2 이름</div>
						<div class="ctn_tbl_td">
							<input type="text" id="conSystem02" name ="conSystem02" value="${data.conSystem02}" class="form-control">
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">유심번호(Volte번호)</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRSimNo" name ="lteRSimNo" value="${data.lteRSimNo}" class="form-control">
						</div>
						<div class="ctn_tbl_th">사용자 id</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRUseRId" name ="lteRUseRId" value="${data.lteRUseRId}" placeholder="" class="form-control">
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">무선장치 모델</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRDeviceType" name ="lteRDeviceType" value="${data.lteRDeviceType}" class="form-control">
						</div>
						<div class="ctn_tbl_th">펌웨어 버전</div>
						<div class="ctn_tbl_td">
							<input type="text" id="swVerCode" name ="swVerCode" value="${data.swVerCode}" class="form-control">
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">Cell ID</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lterCellId" name ="lterCellId" value="${data.lterCellId}" class="form-control" >
						</div>
						<div class="ctn_tbl_th ">사용 유무</div>
						<div class="ctn_tbl_td">
							<select name ="usedYn">
								<option value='1' <c:if test="${data.useYn eq 1}">selected</c:if> >사용</option>
								<option value='0' <c:if test="${data.useYn eq 0}">selected</c:if> >미사용</option>
							</select>
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">Band</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRBand" name ="lteRBand" value="${data.lteRBand}" class="form-control" >
						</div>
						<div class="ctn_tbl_th">Channel </div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRChannel" name ="lteRChannel" value="${data.lteRChannel}" class="form-control" >
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">MCC</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRMCC" name ="lteRMCC" value="${data.lteRMCC}" class="form-control" >
						</div>
						<div class="ctn_tbl_th">MNC</div>
						<div class="ctn_tbl_td">
							<input type="text" id="lteRMNC" name ="lteRMNC" value="${data.lteRMNC}" class="form-control" >
						</div>
					</div>
				</div>
				
				<!-- btn_box Start -->
				<div class="btn_box">
					<div class="right">
						<input type="button" class="btn" id="btnReload" alt="갱신" value="단말기정보 불러오기" style="background:darkgrey;color:#fff;"/>
						<input type="button" class="btn btn_primary" id="btnSave" alt="저장" value="저장" />
						<input type="button" class="btn" id="btnCancel" alt="취소" value="취소" />
					</div>
				</div>
				<!-- btn_box End -->
			</form>
		</div>
		<!-- contents_box End -->
	
		<!-- footer End ------------------>
	
	</div>
	<!-- work End -->
</html>