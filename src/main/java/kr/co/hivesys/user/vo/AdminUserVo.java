package kr.co.hivesys.user.vo;

public class AdminUserVo {
	
	private String SP_AUTH_LVL;
	private String SP_AUTH_NM;
	private String SP_AUTH_CNT;
	private String SP_ID;
	private String SP_PW;
	private String SP_RESP_NM;
	private String SP_RESP_PNO;
	private String SP_RESP_EMAIL;
	private String SP_RESP_TEAM;
	private String SP_RESP_RANK;
	private String SP_RESP_MEMO;
	private String REG_YDT;
	private String USED_YN;
	
	public String getSP_AUTH_LVL() {
		return SP_AUTH_LVL;
	}
	public void setSP_AUTH_LVL(String sP_AUTH_LVL) {
		SP_AUTH_LVL = sP_AUTH_LVL;
	}
	public String getSP_AUTH_NM() {
		return SP_AUTH_NM;
	}
	public void setSP_AUTH_NM(String sP_AUTH_NM) {
		SP_AUTH_NM = sP_AUTH_NM;
	}
	public String getSP_AUTH_CNT() {
		return SP_AUTH_CNT;
	}
	public void setSP_AUTH_CNT(String sP_AUTH_CNT) {
		SP_AUTH_CNT = sP_AUTH_CNT;
	}
	public String getSP_ID() {
		return SP_ID;
	}
	public void setSP_ID(String sP_ID) {
		SP_ID = sP_ID;
	}
	public String getSP_PW() {
		return SP_PW;
	}
	public void setSP_PW(String sP_PW) {
		SP_PW = sP_PW;
	}
	public String getSP_RESP_NM() {
		return SP_RESP_NM;
	}
	public void setSP_RESP_NM(String sP_RESP_NM) {
		SP_RESP_NM = sP_RESP_NM;
	}
	public String getSP_RESP_PNO() {
		return SP_RESP_PNO;
	}
	public void setSP_RESP_PNO(String sP_RESP_PNO) {
		SP_RESP_PNO = sP_RESP_PNO;
	}
	public String getSP_RESP_EMAIL() {
		return SP_RESP_EMAIL;
	}
	public void setSP_RESP_EMAIL(String sP_RESP_EMAIL) {
		SP_RESP_EMAIL = sP_RESP_EMAIL;
	}
	public String getSP_RESP_TEAM() {
		return SP_RESP_TEAM;
	}
	public void setSP_RESP_TEAM(String sP_RESP_TEAM) {
		SP_RESP_TEAM = sP_RESP_TEAM;
	}
	public String getSP_RESP_RANK() {
		return SP_RESP_RANK;
	}
	public void setSP_RESP_RANK(String sP_RESP_RANK) {
		SP_RESP_RANK = sP_RESP_RANK;
	}
	public String getSP_RESP_MEMO() {
		return SP_RESP_MEMO;
	}
	public void setSP_RESP_MEMO(String sP_RESP_MEMO) {
		SP_RESP_MEMO = sP_RESP_MEMO;
	}
	public String getREG_YDT() {
		return REG_YDT;
	}
	public void setREG_YDT(String rEG_YDT) {
		REG_YDT = rEG_YDT;
	}
	public String getUSED_YN() {
		return USED_YN;
	}
	public void setUSED_YN(String uSED_YN) {
		USED_YN = uSED_YN;
	}
	@Override
	public String toString() {
		return "AdminUserVo [SP_AUTH_LVL=" + SP_AUTH_LVL + ", SP_AUTH_NM=" + SP_AUTH_NM + ", SP_AUTH_CNT=" + SP_AUTH_CNT
				+ ", SP_ID=" + SP_ID + ", SP_PW=" + SP_PW + ", SP_RESP_NM=" + SP_RESP_NM + ", SP_RESP_PNO="
				+ SP_RESP_PNO + ", SP_RESP_EMAIL=" + SP_RESP_EMAIL + ", SP_RESP_TEAM=" + SP_RESP_TEAM
				+ ", SP_RESP_RANK=" + SP_RESP_RANK + ", SP_RESP_MEMO=" + SP_RESP_MEMO + ", REG_YDT=" + REG_YDT
				+ ", USED_YN=" + USED_YN + "]";
	}
	
}
