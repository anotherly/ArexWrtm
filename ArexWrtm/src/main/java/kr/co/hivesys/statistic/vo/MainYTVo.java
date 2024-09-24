package kr.co.hivesys.statistic.vo;

import java.util.ArrayList;

public class MainYTVo {
	public ArrayList<String> tNum;
	public ArrayList<String> yesCare;
	public ArrayList<String> yesCwd;
	public ArrayList<String> toCare;
	public ArrayList<String> toCwd;
	
	public ArrayList<String> gettNum() {
		return tNum;
	}
	public void settNum(ArrayList<String> tNum) {
		this.tNum = tNum;
	}
	public ArrayList<String> getYesCare() {
		return yesCare;
	}
	public void setYesCare(ArrayList<String> yesCare) {
		this.yesCare = yesCare;
	}
	public ArrayList<String> getYesCwd() {
		return yesCwd;
	}
	public void setYesCwd(ArrayList<String> yesCwd) {
		this.yesCwd = yesCwd;
	}
	public ArrayList<String> getToCare() {
		return toCare;
	}
	public void setToCare(ArrayList<String> toCare) {
		this.toCare = toCare;
	}
	public ArrayList<String> getToCwd() {
		return toCwd;
	}
	public void setToCwd(ArrayList<String> toCwd) {
		this.toCwd = toCwd;
	}
	@Override
	public String toString() {
		return "MainYTVo [tNum=" + tNum + ", yesCare=" + yesCare + ", yesCwd=" + yesCwd + ", toCare=" + toCare
				+ ", toCwd=" + toCwd + "]";
	}
	
}
