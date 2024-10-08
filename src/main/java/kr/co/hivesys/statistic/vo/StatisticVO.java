package kr.co.hivesys.statistic.vo;

public class StatisticVO {
	//화면에서 받아오는 키값
	private String keyDate;
	//메시지 송신시간
	private String sndDt;
	//운행 번호(또는 메인화면 가로축)
	private String trainNum;
	//열차 번호
	private String cnum;
	//메인화면 전체 합계
	private String allCnt;
	//메인화면 주혼 합계
	private String ccCnt;
	//항목명
	private String title;
	//여유 건수
	private int rlxCnt;
	//보통 건수
	private int usCnt;
	//주의 건수
	private int careCnt;
	//혼잡 건수
	private int cwdCnt;
	//혼잡도(평균)
	private String avgCwd;
	//최소중량
	private String minWgt;
	//최소수집시간
	private String minDt;
	//최소열번
	private String minTnum;
	//최대중량
	private String maxWgt;
	//최대수집시간
	private String maxDt;
	//최대열번
	private String maxTnum;
	//최종 수정일
	private String fnDate;
	//1량 무게값
	private int cw1;
	//2량 무게값
	private int cw2;
	//3량 무게값
	private int cw3;
	//4량 무게값
	private int cw4;
	//5량 무게값
	private int cw5;
	//6량 무게값
	private int cw6;
	//1량 혼잡도
	private String c1;
	//2량 혼잡도
	private String c2;
	//3량 혼잡도
	private String c3;
	//4량 혼잡도
	private String c4;
	//5량 혼잡도
	private String c5;
	//6량 혼잡도
	private String c6;
	
	public String getKeyDate() {
		return keyDate;
	}
	public void setKeyDate(String keyDate) {
		this.keyDate = keyDate;
	}
	public String getSndDt() {
		return sndDt;
	}
	public void setSndDt(String sndDt) {
		this.sndDt = sndDt;
	}
	public String getTrainNum() {
		return trainNum;
	}
	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getAllCnt() {
		return allCnt;
	}
	public void setAllCnt(String allCnt) {
		this.allCnt = allCnt;
	}
	public String getCcCnt() {
		return ccCnt;
	}
	public void setCcCnt(String ccCnt) {
		this.ccCnt = ccCnt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRlxCnt() {
		return rlxCnt;
	}
	public void setRlxCnt(int rlxCnt) {
		this.rlxCnt = rlxCnt;
	}
	public int getUsCnt() {
		return usCnt;
	}
	public void setUsCnt(int usCnt) {
		this.usCnt = usCnt;
	}
	public int getCareCnt() {
		return careCnt;
	}
	public void setCareCnt(int careCnt) {
		this.careCnt = careCnt;
	}
	public int getCwdCnt() {
		return cwdCnt;
	}
	public void setCwdCnt(int cwdCnt) {
		this.cwdCnt = cwdCnt;
	}
	public String getAvgCwd() {
		return avgCwd;
	}
	public void setAvgCwd(String avgCwd) {
		this.avgCwd = avgCwd;
	}
	public String getMinWgt() {
		return minWgt;
	}
	public void setMinWgt(String minWgt) {
		this.minWgt = minWgt;
	}
	public String getMinDt() {
		return minDt;
	}
	public void setMinDt(String minDt) {
		this.minDt = minDt;
	}
	public String getMinTnum() {
		return minTnum;
	}
	public void setMinTnum(String minTnum) {
		this.minTnum = minTnum;
	}
	public String getMaxWgt() {
		return maxWgt;
	}
	public void setMaxWgt(String maxWgt) {
		this.maxWgt = maxWgt;
	}
	public String getMaxDt() {
		return maxDt;
	}
	public void setMaxDt(String maxDt) {
		this.maxDt = maxDt;
	}
	public String getMaxTnum() {
		return maxTnum;
	}
	public void setMaxTnum(String maxTnum) {
		this.maxTnum = maxTnum;
	}
	public String getFnDate() {
		return fnDate;
	}
	public void setFnDate(String fnDate) {
		this.fnDate = fnDate;
	}
	public int getCw1() {
		return cw1;
	}
	public void setCw1(int cw1) {
		this.cw1 = cw1;
	}
	public int getCw2() {
		return cw2;
	}
	public void setCw2(int cw2) {
		this.cw2 = cw2;
	}
	public int getCw3() {
		return cw3;
	}
	public void setCw3(int cw3) {
		this.cw3 = cw3;
	}
	public int getCw4() {
		return cw4;
	}
	public void setCw4(int cw4) {
		this.cw4 = cw4;
	}
	public int getCw5() {
		return cw5;
	}
	public void setCw5(int cw5) {
		this.cw5 = cw5;
	}
	public int getCw6() {
		return cw6;
	}
	public void setCw6(int cw6) {
		this.cw6 = cw6;
	}
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public String getC4() {
		return c4;
	}
	public void setC4(String c4) {
		this.c4 = c4;
	}
	public String getC5() {
		return c5;
	}
	public void setC5(String c5) {
		this.c5 = c5;
	}
	public String getC6() {
		return c6;
	}
	public void setC6(String c6) {
		this.c6 = c6;
	}
	@Override
	public String toString() {
		return "StatisticVO [keyDate=" + keyDate + ", sndDt=" + sndDt + ", trainNum=" + trainNum + ", cnum=" + cnum
				+ ", allCnt=" + allCnt + ", ccCnt=" + ccCnt + ", title=" + title + ", rlxCnt=" + rlxCnt + ", usCnt="
				+ usCnt + ", careCnt=" + careCnt + ", cwdCnt=" + cwdCnt + ", avgCwd=" + avgCwd + ", minWgt=" + minWgt
				+ ", minDt=" + minDt + ", minTnum=" + minTnum + ", maxWgt=" + maxWgt + ", maxDt=" + maxDt + ", maxTnum="
				+ maxTnum + ", fnDate=" + fnDate + ", cw1=" + cw1 + ", cw2=" + cw2 + ", cw3=" + cw3 + ", cw4=" + cw4
				+ ", cw5=" + cw5 + ", cw6=" + cw6 + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5="
				+ c5 + ", c6=" + c6 + "]";
	}
	
}
