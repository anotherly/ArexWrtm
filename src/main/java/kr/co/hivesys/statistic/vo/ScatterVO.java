package kr.co.hivesys.statistic.vo;

public class ScatterVO {
	public String xDate;
	public String carNum;
	public String ryangNum;
	public int wgt;
	public String getxDate() {
		return xDate;
	}
	public void setxDate(String xDate) {
		this.xDate = xDate;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getRyangNum() {
		return ryangNum;
	}
	public void setRyangNum(String ryangNum) {
		this.ryangNum = ryangNum;
	}
	public int getWgt() {
		return wgt;
	}
	public void setWgt(int wgt) {
		this.wgt = wgt;
	}
	@Override
	public String toString() {
		return "ScatterVO [xDate=" + xDate + ", carNum=" + carNum + ", ryangNum=" + ryangNum + ", wgt=" + wgt + "]";
	}
}
