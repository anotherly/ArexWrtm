
var selType0 = '';
var selType1 = '';


function chgColor(code, flag, menu,pCode){
	console.log("선택한 텍스트 : "+$("select[name=sel_fc_"+code+"] option:checked").text());
	var url;
	var data;
	var pCode;
	url = "/option/color/modify.do"
	/*if (menu == 'code'){
		url = '<c:url value="/option/color/modify.do"/>'
	} else {
		url = '<c:url value="/option/reportMulti/modify.do"/>'
	}*/
	//플래그 1,2의 구분은 폰트냐 배경이냐 차이
	if (flag == "1"){
		data = {
				pCode : pCode, 
				code : code, 
				color : $("select[name=sel_fc_"+code+"]").val(), 
				colorName : $("select[name=sel_fc_"+code+"] option:checked").text(), 
				colFlag : flag, 
				pageFlag : pageFlag, 
				cdFlag : '1'
		};
	} else if (flag == "2"){
		data = {
				pCode : pCode, 
				code : code, 
				color : $("select[name=sel_bc_"+code+"]").val(), 
				colorName : $("select[name=sel_bc_"+code+"] option:checked").text(), 
				colFlag : flag, 
				pageFlag : pageFlag, 
				cdFlag : '2'
		};
	}
	
	sendAjaxRequest(url, data, '', modifyMent);
}

function toggleChkBox(obj, cdFlag, menu){
	console.log("toggleChkBox : "+$(obj).val());
	var parent_code;
	var flag_use;
	var url;
	var target;
	
	if (menu == 'type'){	// 제보 유형
		url = "/option/reportType/modify.do";
		target = "#divTypeCode"+cdFlag;
		if (cdFlag == '2'){
			parent_code = typeCode1;
			$("#divTypeCode3").empty();
		} else {
			parent_code = typeCode2;
		}
	} else {				// 코드 종합
		url = "/option/reportMulti/modify.do";
		if (cdFlag == '1'){
			target = "#tabMenu";
			parent_code = '800';
		} else {
			target = "#divMultiCode2";
			parent_code = multiCode1;
		}
	}
	
	if ($(obj).is(':checked')){
		flag_use = 'Y';
	} else {
		flag_use = 'N';
	}
	
	var data = {parent_code : parent_code, code : $(obj).val(), flag_use : flag_use, pageFlag : pageFlag, cdFlag : cdFlag};
	
	sendAjaxRequest(url, data, target, modifyMent);
}

