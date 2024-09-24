package kr.co.hivesys.statistic.vo;

public class StkAreaVO {
	public int sumCnt;
	public int hh;
	public int cnt;
	public int yesC;
	public int todC;
	public int getSumCnt() {
		return sumCnt;
	}
	public void setSumCnt(int sumCnt) {
		this.sumCnt = sumCnt;
	}
	public int getHh() {
		return hh;
	}
	public void setHh(int hh) {
		this.hh = hh;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getYesC() {
		return yesC;
	}
	public void setYesC(int yesC) {
		this.yesC = yesC;
	}
	public int getTodC() {
		return todC;
	}
	public void setTodC(int todC) {
		this.todC = todC;
	}
	@Override
	public String toString() {
		return "StkAreaVO [sumCnt=" + sumCnt + ", hh=" + hh + ", cnt=" + cnt + ", yesC=" + yesC + ", todC=" + todC
				+ "]";
	}

}
