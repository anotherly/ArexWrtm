package kr.co.hivesys.statistic.vo;

public class ChkDateVO {
	public String yr;
	public String mn;
	public String minDt;
	public String maxDt;
	public String trg;
	public String getYr() {
		return yr;
	}
	public void setYr(String yr) {
		this.yr = yr;
	}
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}
	public String getMinDt() {
		return minDt;
	}
	public void setMinDt(String minDt) {
		this.minDt = minDt;
	}
	public String getMaxDt() {
		return maxDt;
	}
	public void setMaxDt(String maxDt) {
		this.maxDt = maxDt;
	}
	public String getTrg() {
		return trg;
	}
	public void setTrg(String trg) {
		this.trg = trg;
	}
	@Override
	public String toString() {
		return "ChkDateVO [yr=" + yr + ", mn=" + mn + ", minDt=" + minDt + ", maxDt=" + maxDt + ", trg=" + trg + "]";
	}
	
}
