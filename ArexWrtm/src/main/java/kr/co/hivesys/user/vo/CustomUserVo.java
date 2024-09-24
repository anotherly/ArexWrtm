package kr.co.hivesys.user.vo;

public class CustomUserVo {
	private String AUTH_CODE;
	private String REG_DT;
	private String USER_ACCOUNT_ID;
	private String USER_BIZ_NO;
	private String USER_EMAIL;
	private String USER_NAME;
	private String USER_ORG;
	private String USER_PHONE;
	private String USER_PW;
	private String USER_RANK;
	private String USER_TEAM;
	private String USE_YN;
	public String getAUTH_CODE() {
		return AUTH_CODE;
	}
	public void setAUTH_CODE(String aUTH_CODE) {
		AUTH_CODE = aUTH_CODE;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getUSER_ACCOUNT_ID() {
		return USER_ACCOUNT_ID;
	}
	public void setUSER_ACCOUNT_ID(String uSER_ACCOUNT_ID) {
		USER_ACCOUNT_ID = uSER_ACCOUNT_ID;
	}
	public String getUSER_BIZ_NO() {
		return USER_BIZ_NO;
	}
	public void setUSER_BIZ_NO(String uSER_BIZ_NO) {
		USER_BIZ_NO = uSER_BIZ_NO;
	}
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}
	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getUSER_ORG() {
		return USER_ORG;
	}
	public void setUSER_ORG(String uSER_ORG) {
		USER_ORG = uSER_ORG;
	}
	public String getUSER_PHONE() {
		return USER_PHONE;
	}
	public void setUSER_PHONE(String uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}
	public String getUSER_PW() {
		return USER_PW;
	}
	public void setUSER_PW(String uSER_PW) {
		USER_PW = uSER_PW;
	}
	public String getUSER_RANK() {
		return USER_RANK;
	}
	public void setUSER_RANK(String uSER_RANK) {
		USER_RANK = uSER_RANK;
	}
	public String getUSER_TEAM() {
		return USER_TEAM;
	}
	public void setUSER_TEAM(String uSER_TEAM) {
		USER_TEAM = uSER_TEAM;
	}
	public String getUSE_YN() {
		return USE_YN;
	}
	public void setUSE_YN(String uSE_YN) {
		USE_YN = uSE_YN;
	}
	@Override
	public String toString() {
		return "customUserVo [AUTH_CODE=" + AUTH_CODE + ", REG_DT=" + REG_DT + ", USER_ACCOUNT_ID=" + USER_ACCOUNT_ID
				+ ", USER_BIZ_NO=" + USER_BIZ_NO + ", USER_EMAIL=" + USER_EMAIL + ", USER_NAME=" + USER_NAME
				+ ", USER_ORG=" + USER_ORG + ", USER_PHONE=" + USER_PHONE + ", USER_PW=" + USER_PW + ", USER_RANK="
				+ USER_RANK + ", USER_TEAM=" + USER_TEAM + ", USE_YN=" + USE_YN + "]";
	}
	
}
