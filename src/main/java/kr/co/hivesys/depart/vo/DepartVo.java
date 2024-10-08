package kr.co.hivesys.depart.vo;

public class DepartVo {
	public String departCode;
	public String departName;
	
	public String oldDeptCode;
	public String oldDeptName;
	
	public String cpyName;
	public String cpyCode;
	public String hqName;
	public String hqCode;
	public String teamName;
	public String teamCode;
	public String usedYn;
	public String regYdt;
	public String updateYdt;
	
	//수정시 기존 키와 신규키 변경여부를 파악하여 
	//비교할지 안할지를 판별
	public String updateChk;
	
	//검색변수
	public String schAllCode;
	public String schCpy;
	public String schHq;
	public String schTeam;

	public String getSchAllCode() {
		return schAllCode;
	}
	public void setSchAllCode(String schAllCode) {
		this.schAllCode = schAllCode;
	}
	
	public String getSchCpy() {
		return schCpy;
	}
	public void setSchCpy(String schCpy) {
		this.schCpy = schCpy;
	}
	public String getSchHq() {
		return schHq;
	}
	public void setSchHq(String schHq) {
		this.schHq = schHq;
	}
	public String getSchTeam() {
		return schTeam;
	}
	public void setSchTeam(String schTeam) {
		this.schTeam = schTeam;
	}
	public String getOldDeptCode() {
		return oldDeptCode;
	}
	public void setOldDeptCode(String oldDeptCode) {
		this.oldDeptCode = oldDeptCode;
	}
	public String getOldDeptName() {
		return oldDeptName;
	}
	public void setOldDeptName(String oldDeptName) {
		this.oldDeptName = oldDeptName;
	}
	public String getUpdateChk() {
		return updateChk;
	}
	public void setUpdateChk(String updateChk) {
		this.updateChk = updateChk;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getCpyName() {
		return cpyName;
	}
	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}
	public String getCpyCode() {
		return cpyCode;
	}
	public void setCpyCode(String cpyCode) {
		this.cpyCode = cpyCode;
	}
	public String getHqName() {
		return hqName;
	}
	public void setHqName(String hqName) {
		this.hqName = hqName;
	}
	public String getHqCode() {
		return hqCode;
	}
	public void setHqCode(String hqCode) {
		this.hqCode = hqCode;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
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
	
	@Override
	public String toString() {
		return "DepartVo [departCode=" + departCode + ", departName=" + departName + ", oldDeptCode=" + oldDeptCode
				+ ", oldDeptName=" + oldDeptName + ", cpyName=" + cpyName + ", cpyCode=" + cpyCode + ", hqName="
				+ hqName + ", hqCode=" + hqCode + ", teamName=" + teamName + ", teamCode=" + teamCode + ", usedYn="
				+ usedYn + ", regYdt=" + regYdt + ", updateYdt=" + updateYdt + ", updateChk=" + updateChk
				+ ", schAllCode=" + schAllCode + ", schCpy=" + schCpy + ", schHq=" + schHq + ", schTeam=" + schTeam
				+ "]";
	}
	
}