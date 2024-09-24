package kr.co.hivesys.statistic.vo;

public class LogDataVO {
	//연도
	public String ldYear;
	//데이터량
	public String ldCnt;
	//차지 비율
	public String ldPer;
	
	public String getLdYear() {
		return ldYear;
	}
	public void setLdYear(String ldYear) {
		this.ldYear = ldYear;
	}
	public String getLdCnt() {
		return ldCnt;
	}
	public void setLdCnt(String ldCnt) {
		this.ldCnt = ldCnt;
	}
	public String getLdPer() {
		return ldPer;
	}
	public void setLdPer(String ldPer) {
		this.ldPer = ldPer;
	}
	@Override
	public String toString() {
		return "LogDataVO [ldYear=" + ldYear + ", ldCnt=" + ldCnt + ", ldPer=" + ldPer + "]";
	}
	
}
