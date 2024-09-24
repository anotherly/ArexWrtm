package kr.co.hivesys.user.vo;

public class UserVO {
	public String userId;
	public String userPw;
	public String userPw1;
	public String userName;
	public String departCode;
	public String cpyCode;
	public String hqCode;
	public String teamCode;
	public String cpyName;
	public String hqName;
	public String teamName;
	public String userRank;
	public String userPhone;
	public String userTel;
	public String userEmail;
	public String userAuth;
	public String authLevel;
	public String authDefine;
	public String usedYn;
	public String regYdt;
	public String updateYdt;
	
	//아래는 DB에 없는것들
	// 최종로그인
	private String stDt;
	// 최종로그아웃
	private String fnDt;
	//현재 접속여부
	private String cnYn;
	
	//검색타입
	private String searchType;
	//검색값
	private String searchValue;
	//사용 시간
	private String useTime;
	
	private String schUserId;
	private String schUserName;
	private String schUserCpy;
	private String schUserHq;
	private String schUserTeam;
	
	//검색 시작 시간
	private String sDate;
	//검색 종료 시간
	private String eDate;
	
	public String getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}
	public String getAuthDefine() {
		return authDefine;
	}
	public void setAuthDefine(String authDefine) {
		this.authDefine = authDefine;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserPw1() {
		return userPw1;
	}
	public void setUserPw1(String userPw1) {
		this.userPw1 = userPw1;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getCpyCode() {
		return cpyCode;
	}
	public void setCpyCode(String cpyCode) {
		this.cpyCode = cpyCode;
	}
	public String getHqCode() {
		return hqCode;
	}
	public void setHqCode(String hqCode) {
		this.hqCode = hqCode;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getCpyName() {
		return cpyName;
	}
	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}
	public String getHqName() {
		return hqName;
	}
	public void setHqName(String hqName) {
		this.hqName = hqName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getUserRank() {
		return userRank;
	}
	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getUsedYn() {
		return usedYn;
	}
	public void setUsedYn(String usedYn) {
		this.usedYn = usedYn;
	}
	public String getRegYdt() {
		return regYdt;
	}
	public void setRegYdt(String regYdt) {
		this.regYdt = regYdt;
	}
	public String getUpdateYdt() {
		return updateYdt;
	}
	public void setUpdateYdt(String updateYdt) {
		this.updateYdt = updateYdt;
	}
	public String getStDt() {
		return stDt;
	}
	public void setStDt(String stDt) {
		this.stDt = stDt;
	}
	public String getFnDt() {
		return fnDt;
	}
	public void setFnDt(String fnDt) {
		this.fnDt = fnDt;
	}
	public String getCnYn() {
		return cnYn;
	}
	public void setCnYn(String cnYn) {
		this.cnYn = cnYn;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getSchUserId() {
		return schUserId;
	}
	public void setSchUserId(String schUserId) {
		this.schUserId = schUserId;
	}
	public String getSchUserName() {
		return schUserName;
	}
	public void setSchUserName(String schUserName) {
		this.schUserName = schUserName;
	}
	public String getSchUserCpy() {
		return schUserCpy;
	}
	public void setSchUserCpy(String schUserCpy) {
		this.schUserCpy = schUserCpy;
	}
	public String getSchUserHq() {
		return schUserHq;
	}
	public void setSchUserHq(String schUserHq) {
		this.schUserHq = schUserHq;
	}
	public String getSchUserTeam() {
		return schUserTeam;
	}
	public void setSchUserTeam(String schUserTeam) {
		this.schUserTeam = schUserTeam;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw + ", userPw1=" + userPw1 + ", userName=" + userName
				+ ", departCode=" + departCode + ", cpyCode=" + cpyCode + ", hqCode=" + hqCode + ", teamCode="
				+ teamCode + ", cpyName=" + cpyName + ", hqName=" + hqName + ", teamName=" + teamName + ", userRank="
				+ userRank + ", userPhone=" + userPhone + ", userTel=" + userTel + ", userEmail=" + userEmail
				+ ", userAuth=" + userAuth + ", authLevel=" + authLevel + ", authDefine=" + authDefine + ", usedYn="
				+ usedYn + ", regYdt=" + regYdt + ", updateYdt=" + updateYdt + ", stDt=" + stDt + ", fnDt=" + fnDt
				+ ", cnYn=" + cnYn + ", searchType=" + searchType + ", searchValue=" + searchValue + ", useTime="
				+ useTime + ", schUserId=" + schUserId + ", schUserName=" + schUserName + ", schUserCpy=" + schUserCpy
				+ ", schUserHq=" + schUserHq + ", schUserTeam=" + schUserTeam + ", sDate=" + sDate + ", eDate=" + eDate
				+ "]";
	}
	
	
}