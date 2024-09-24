package kr.co.hivesys.auth.vo;

public class AuthVo {
	private String authLevel;
	private String authDefine;
	private String funcCtrl;
	private String funcMotr;
	private String funcDepart;
	private String funcAuth;
	private String funcUser;
	private String funcLter;
	private String usedYn;
	private String regYdt;
	private String updateYdt;
	
	private String schCtrl;
	private String schMotr;
	private String schDepart;
	private String schAuth;
	private String schUser;
	private String schLter;
	private String schAuthName;
	
	public String getSchAuthName() {
		return schAuthName;
	}
	public void setSchAuthName(String schAuthName) {
		this.schAuthName = schAuthName;
	}
	public String getSchCtrl() {
		return schCtrl;
	}
	public void setSchCtrl(String schCtrl) {
		this.schCtrl = schCtrl;
	}
	public String getSchMotr() {
		return schMotr;
	}
	public void setSchMotr(String schMotr) {
		this.schMotr = schMotr;
	}
	public String getSchDepart() {
		return schDepart;
	}
	public void setSchDepart(String schDepart) {
		this.schDepart = schDepart;
	}
	public String getSchAuth() {
		return schAuth;
	}
	public void setSchAuth(String schAuth) {
		this.schAuth = schAuth;
	}
	public String getSchUser() {
		return schUser;
	}
	public void setSchUser(String schUser) {
		this.schUser = schUser;
	}
	public String getSchLter() {
		return schLter;
	}
	public void setSchLter(String schLter) {
		this.schLter = schLter;
	}
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
	public String getFuncCtrl() {
		return funcCtrl;
	}
	public void setFuncCtrl(String funcCtrl) {
		this.funcCtrl = funcCtrl;
	}
	public String getFuncMotr() {
		return funcMotr;
	}
	public void setFuncMotr(String funcMotr) {
		this.funcMotr = funcMotr;
	}
	public String getFuncDepart() {
		return funcDepart;
	}
	public void setFuncDepart(String funcDepart) {
		this.funcDepart = funcDepart;
	}
	public String getFuncAuth() {
		return funcAuth;
	}
	public void setFuncAuth(String funcAuth) {
		this.funcAuth = funcAuth;
	}
	public String getFuncUser() {
		return funcUser;
	}
	public void setFuncUser(String funcUser) {
		this.funcUser = funcUser;
	}
	public String getFuncLter() {
		return funcLter;
	}
	public void setFuncLter(String funcLter) {
		this.funcLter = funcLter;
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
		return "AuthVo [authLevel=" + authLevel + ", authDefine=" + authDefine + ", funcCtrl=" + funcCtrl
				+ ", funcMotr=" + funcMotr + ", funcDepart=" + funcDepart + ", funcAuth=" + funcAuth + ", funcUser="
				+ funcUser + ", funcLter=" + funcLter + ", usedYn=" + usedYn + ", regYdt=" + regYdt + ", updateYdt="
				+ updateYdt + ", schCtrl=" + schCtrl + ", schMotr=" + schMotr + ", schDepart=" + schDepart
				+ ", schAuth=" + schAuth + ", schUser=" + schUser + ", schLter=" + schLter + "]";
	}
	
	
}