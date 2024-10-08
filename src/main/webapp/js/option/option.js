var pageFlag = "code";
var saveMent = "저장되었습니다.";
var modifyMent = "수정되었습니다.";
var delMent = "삭제되었습니다.";
var selArea="";

function init(){
	console.log("optionjs init");
	$('#tabMenu').load('/option/reportType/main.do?pageFlag=code&parent_code=100');
}

function chgTabMenu(menuNum){
	//권한은 main.jsp 에서 전역변수로 받아옴
	console.log("authCode / menuNum : "+authCode+menuNum);
	//$('#tabMenu').empty();
	 
	//마스터 권한에 통신원 유형 선택시 방송국 선택이 가능
	if(authCode=='999' && menuNum == '2'){
		$("#areaDivsel").show();
		$("#areaOptSel").prop("disabled", false);
		selArea="";
	}else{
		$("#areaDivsel").hide();
		$("#areaOptSel").prop("disabled", true);
		selArea="010";
	}
	
	if (menuNum == '1'){
		goMenuSite('/option/reportType/main.do?pageFlag="+pageFlag+"',"tabMenu")		
	} else if(menuNum == '2'){
		goMenuSite('/option/infrm/main.do?pageFlag="+pageFlag+"',"tabMenu")		
	} else if(menuNum == '3'){
		goMenuSite('/option/area/main.do?pageFlag="+pageFlag+"',"tabMenu")
	} else if(menuNum == '4'){
		goMenuSite('/option/road/main.do?pageFlag="+pageFlag+"',"tabMenu")
	} else if(menuNum == '5'){
		goMenuSite('/option/inTel/main.do?pageFlag="+pageFlag+"',"tabMenu")
	} else{
		goMenuSite('/option/color/main.do?pageFlag="+pageFlag+"',"tabMenu")
	}
}

/**************** 전송 관련 기능 *****************/
/* 파라미터 - sUrl : 요청보낼 URL, sData : 요청 시 실어보낼 데이터, 
 *           sTarget : 데이터를 리턴받을 영역, sSuccessMent : 정상 동작 시 표현할 멘트 
 */
function sendAjaxRequest(sUrl, sData, sTarget, sSuccessMent){
	var options = {
            url:sUrl,
            type:"post",
            data:sData,
            target: sTarget,
            success: function(res){
            	//alert(res);
            	if (sSuccessMent != null){
            		alert(sSuccessMent);
            	}
            },
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
	
    $('#tabMenu').ajaxSubmit(options);
}

/**************** 제보코드 종합 관리 기능 *****************/

var multiCode1;
var multiCode2;

function getMultiCode(code, type, obj){
	if(!changeStatus(code, type, obj)){
        return false;
    }
	
	if (code != ""){
		var url;
		if (type == '2'){
			multiCode1 = code;
			url = '/option/reportMulti/code2.do';	// 대분류 코드의 하위 중분류 코드를 가져온다.
			$('#divMultiCode2').empty();
			$('#divMultiCode3').empty();
		} else {
			multiCode2 = code;
			url = '/option/reportMulti/code3.do';	// 중분류 코드의 하위 소분류 코드를 가져온다.
			$('#divMultiCode3').empty();
		}
		var data = {parent_code : code, pageFlag : pageFlag};
		
	    sendAjaxRequest(url, data, '#divMultiCode'+type);
	}
}

function addMultiCode(cdFlag){
	removeInputBox();
    
	if ($('#newMultiCode'+cdFlag).length > 0){
		alert("추가는 1개씩 가능합니다.");
	} else {
		var ul = $("#ulMultiCode"+cdFlag);
		var lis = ul.children();
		
		addObjId = 'newLi'+cdFlag;
		
		ul.children().remove();
		
		var li = $(document.createElement("li")).attr({id:'newLi'+cdFlag}).addClass("");
		li.append($(document.createElement("input"))
					.attr({type:'text', id:'newMultiCode'+cdFlag, name:'newMultiCode'+cdFlag })
					.css('width','80px')
					.focus());
		li.append($(document.createElement("img"))
					.attr({src:'../images/code_add.gif', title:'추가'})
					.css('vertical-align','middle')
					.click(function(){
						//alert(cdFlag+", "+typeCode0+", "+typeCode1+", "+typeCode2+", "+$('#newTypeCode'+cdFlag).val());
						var parent_code;
						var target;
						if (cdFlag == '1'){
							parent_code = '000';
							target = '#tabMenu';
						} else if (cdFlag == '2'){
							parent_code = multiCode1;
							target = '#divMultiCode2';
						} else {
							parent_code = multiCode2;
							target = '#divMultiCode3';
						}
						var url = '/option/reportMulti/save.do';
						var data = {"cdFlag" : cdFlag, "pCode" : parent_code, "pageFlag" : pageFlag, "value" : $('#newMultiCode'+cdFlag).val()};
						sendAjaxRequest(url, data, target, saveMent);
					}));
		ul.append(li);
		ul.append(lis);
	}
}

function modMultiCode(cd, cdName, cdFlag){
	//alert(cdFlag+", "+cd+", "+cdName);
	var li = $("#liMultiCode_"+cd);
	
	dbObj = li;
    dbObjVal = li.html();
    dbObjFlag = cdFlag;
	
	li.children().remove();
	li.append($(document.createElement("input")).attr({type:'text', id:'chgMultiCode'+cd, value:cdName}).css('width','70px').focus());
	li.append($(document.createElement("img")).attr({src:'../images/code_add.gif', title:'추가'})
			.css('vertical-align','middle').css('margin-top', '-4px')
			.click(function(){
				//alert($("#chgTypeCode"+cd).val());
				var target;
				var parent_code;
				if (cdFlag == '1'){
					target = '#tabMenu';
					parent_code = multiCode0;
					$('#divMultiCode2').empty();
					$('#divMultiCode3').empty();
				} else if (cdFlag == '2'){
					target = '#divMultiCode'+cdFlag;
					parent_code = multiCode1;
					$('#divMultiCode3').empty();
				} else {
					target = '#divMultiCode'+cdFlag;
					parent_code = multiCode2;
				}
				var url = '/option/reportMulti/modify.do';
				var data = {
						pageFlag : pageFlag, 
						cdFlag : cdFlag, 
						parent_code : parent_code, 
						code : cd, 
						code_name : $('#chgMultiCode'+cd).val()
				};
				
				sendAjaxRequest(url, data, target, modifyMent);
			}));
}

function delMultiCode(cdFlag){
	var errorMent = '항목이 없습니다.';
	if ($("input:checkbox[id='chkMultiCode"+cdFlag+"']").length != 0){
		var strArr = new Array();
		var idx = 0;
		
		$("input:checkbox[id='chkMultiCode"+cdFlag+"']").each(function(){
			if ($(this).is(":checked")){
				strArr[idx++] = $(this).val();	// 체크된 코드를 리스트에 저장
			}
		});
		
		if (strArr.length == 0){
			alert("체크된 항목이 없습니다.");
			return false;
		}
		
		if (confirm("하위 항목도 같이 삭제됩니다.\n    삭제하시겠습니까?")){
			var url = '/option/reportMulti/delete.do';
			var data;
			var target;
			
			//alert(strArr.length);
			var pcode='';
			
			if (cdFlag == "1"){
				pcode=multiCode0;
				target = '#tabMenu';
			} else if (cdFlag == "2"){
				pcode=multiCode1;
				target = '#divMultiCode2';
				$('#divMultiCode2').empty();
				$('#divMultiCode3').empty();
			} else {
				pcode=multiCode2;
				target = '#divMultiCode3';
				$('#divMultiCode3').empty();
			}
			
			data = {
					"cdFlag" : cdFlag, 
					"pCode" : pcode, 
					"chkValList" : strArr, 
					"pageFlag" : pageFlag
					};
			
			sendAjaxRequest(url, data, target, delMent);
			
		} else {
			return false;
		}
	} else {
		alert(errorMent);
		return false;
	}
}

function changeStatus(code, type, obj){
	if(addObjId != ''){
        $('#'+addObjId).remove();
        addObjId == '';
    }
    
    if(dbObj != '' && dbObjVal != ''){
        dbObj.html(dbObjVal);
        dbObj.children('span').css('font-weight','');
    }
    
    if(type == '2'){
        if(selType0 != null && selType0 != ''){
            selType0.css('font-weight','');
        }
        selType0 = obj;
        obj.css('font-weight','bold');
        
    }
    
    if(type == '3'){
        if(selType1 != null && selType1 != ''){
            selType1.css('font-weight','');
        }
        selType1 = obj;
        obj.css('font-weight','bold');
        
        if(dbObj != '' && dbObjFlag == '1'){
            dbObj.children('span').css('font-weight','bold');
            selType0 = dbObj.children('span');
        }
    }
    
    if(type == '4'){
        if(dbObj != '' && dbObjFlag == '2'){
            dbObj.children('span').css('font-weight','bold');
            selType1 = dbObj.children('span');
        }
        dbObj = '';
        dbObjVal = '';
        
        return false;
    }
    
    dbObj = '';
    dbObjVal = '';
    
    return true;
}

function removeInputBox(){
	if(addObjId != ''){
        $('#'+addObjId).remove();
        addObjId == '';
    }
    
    if(dbObj != '' && dbObjVal != ''){
        dbObj.html(dbObjVal);
    }
}
