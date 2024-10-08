package kr.co.hivesys.chart.vo;

public class ChartVo {
	private String COMPANY_ID;
	private String BR_NUMBER;
	private String COMPANY_NAME;
	private String RP_NAME;
	private String BR_ADDRESS;
	private String BILLER_PHONE;
	private String BILLER_EMAIL;
	private String RP_PHONE;
	private String MANAGER_ID;
	private String MANAGER_NAME;
	private String MANAGER_EMAIL;
	private String MANAGER_DEPT;
	private String MANAGER_RANK;
	private String MANAGER_PHONE;
	private String COMPANY_ADDRESS;
	private String MSP_NHN;
	private String MSP_NAVER;
	private String MSP_KT;
	private String MSP_AWS;
	private String MSP_RESALE;
	private String IAAS_NHN;
	private String IAAS_NAVER;
	private String IAAS_KT;
	private String IAAS_AWS;
	private String SDR_NHN;
	private String SDR_NAVER;
	private String SDR_KT;
	private String MSPB_LITE;
	private String MSPB_STANDARD;
	private String MSPB_PREMIUM;
	private String CONTRACT_STDT;
	private String CONTRACT_EDT;
	private String BILL_DT;
	private String BILL_RFDT;
	private String TAX_DT;
	
	public String RES_SALES;
	public String RES_MANAGER;
	public String CONTRACT;
	public String EVIDENCE;
	public String COM_DIV;
	public String ENROLL_ID;
	public String ENROLL_NAME;
	
	//전체 수
	public String SVC_ALL;
	//서비스중인 수
	public String SVC_NOW;
	//서비스 종료 수
	public String SVC_END;
	
	//서비스 종료여부
	public String SVC_YN;
	
	//검색타입
	private String searchType;
	//검색값
	private String searchValue;
	//검색 시작 시간
	private String sDate;
	//검색 종료 시간
	private String eDate;
	//라디오버튼 1
	private String searchRadio1;
	//라디오버튼 2
	private String searchRadio2;
	
	public String getSearchRadio1() {
		return searchRadio1;
	}
	public void setSearchRadio1(String searchRadio1) {
		this.searchRadio1 = searchRadio1;
	}
	public String getSearchRadio2() {
		return searchRadio2;
	}
	public void setSearchRadio2(String searchRadio2) {
		this.searchRadio2 = searchRadio2;
	}
	public String getSVC_ALL() {
		return SVC_ALL;
	}
	public void setSVC_ALL(String sVC_ALL) {
		SVC_ALL = sVC_ALL;
	}
	public String getSVC_NOW() {
		return SVC_NOW;
	}
	public void setSVC_NOW(String sVC_NOW) {
		SVC_NOW = sVC_NOW;
	}
	public String getSVC_END() {
		return SVC_END;
	}
	public void setSVC_END(String sVC_END) {
		SVC_END = sVC_END;
	}
	public String getSVC_YN() {
		return SVC_YN;
	}
	public void setSVC_YN(String sVC_YN) {
		SVC_YN = sVC_YN;
	}
	public String getRES_SALES() {
		return RES_SALES;
	}
	public void setRES_SALES(String rES_SALES) {
		RES_SALES = rES_SALES;
	}
	public String getRES_MANAGER() {
		return RES_MANAGER;
	}
	public void setRES_MANAGER(String rES_MANAGER) {
		RES_MANAGER = rES_MANAGER;
	}
	public String getCONTRACT() {
		return CONTRACT;
	}
	public void setCONTRACT(String cONTRACT) {
		CONTRACT = cONTRACT;
	}
	public String getEVIDENCE() {
		return EVIDENCE;
	}
	public void setEVIDENCE(String eVIDENCE) {
		EVIDENCE = eVIDENCE;
	}
	public String getCOM_DIV() {
		return COM_DIV;
	}
	public void setCOM_DIV(String cOM_DIV) {
		COM_DIV = cOM_DIV;
	}
	public String getENROLL_ID() {
		return ENROLL_ID;
	}
	public void setENROLL_ID(String eNROLL_ID) {
		ENROLL_ID = eNROLL_ID;
	}
	public String getENROLL_NAME() {
		return ENROLL_NAME;
	}
	public void setENROLL_NAME(String eNROLL_NAME) {
		ENROLL_NAME = eNROLL_NAME;
	}
	public String getCOMPANY_ID() {
		return COMPANY_ID;
	}
	public void setCOMPANY_ID(String cOMPANY_ID) {
		COMPANY_ID = cOMPANY_ID;
	}
	public String getBR_NUMBER() {
		return BR_NUMBER;
	}
	public void setBR_NUMBER(String bR_NUMBER) {
		BR_NUMBER = bR_NUMBER;
	}
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}
	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}
	public String getRP_NAME() {
		return RP_NAME;
	}
	public void setRP_NAME(String rP_NAME) {
		RP_NAME = rP_NAME;
	}
	public String getBR_ADDRESS() {
		return BR_ADDRESS;
	}
	public void setBR_ADDRESS(String bR_ADDRESS) {
		BR_ADDRESS = bR_ADDRESS;
	}
	public String getBILLER_PHONE() {
		return BILLER_PHONE;
	}
	public void setBILLER_PHONE(String bILLER_PHONE) {
		BILLER_PHONE = bILLER_PHONE;
	}
	public String getBILLER_EMAIL() {
		return BILLER_EMAIL;
	}
	public void setBILLER_EMAIL(String bILLER_EMAIL) {
		BILLER_EMAIL = bILLER_EMAIL;
	}
	public String getRP_PHONE() {
		return RP_PHONE;
	}
	public void setRP_PHONE(String rP_PHONE) {
		RP_PHONE = rP_PHONE;
	}
	public String getMANAGER_ID() {
		return MANAGER_ID;
	}
	public void setMANAGER_ID(String mANAGER_ID) {
		MANAGER_ID = mANAGER_ID;
	}
	public String getMANAGER_NAME() {
		return MANAGER_NAME;
	}
	public void setMANAGER_NAME(String mANAGER_NAME) {
		MANAGER_NAME = mANAGER_NAME;
	}
	public String getMANAGER_EMAIL() {
		return MANAGER_EMAIL;
	}
	public void setMANAGER_EMAIL(String mANAGER_EMAIL) {
		MANAGER_EMAIL = mANAGER_EMAIL;
	}
	public String getMANAGER_DEPT() {
		return MANAGER_DEPT;
	}
	public void setMANAGER_DEPT(String mANAGER_DEPT) {
		MANAGER_DEPT = mANAGER_DEPT;
	}
	public String getMANAGER_RANK() {
		return MANAGER_RANK;
	}
	public void setMANAGER_RANK(String mANAGER_RANK) {
		MANAGER_RANK = mANAGER_RANK;
	}
	public String getMANAGER_PHONE() {
		return MANAGER_PHONE;
	}
	public void setMANAGER_PHONE(String mANAGER_PHONE) {
		MANAGER_PHONE = mANAGER_PHONE;
	}
	public String getCOMPANY_ADDRESS() {
		return COMPANY_ADDRESS;
	}
	public void setCOMPANY_ADDRESS(String cOMPANY_ADDRESS) {
		COMPANY_ADDRESS = cOMPANY_ADDRESS;
	}
	public String getMSP_NHN() {
		return MSP_NHN;
	}
	public void setMSP_NHN(String mSP_NHN) {
		MSP_NHN = mSP_NHN;
	}
	public String getMSP_NAVER() {
		return MSP_NAVER;
	}
	public void setMSP_NAVER(String mSP_NAVER) {
		MSP_NAVER = mSP_NAVER;
	}
	public String getMSP_KT() {
		return MSP_KT;
	}
	public void setMSP_KT(String mSP_KT) {
		MSP_KT = mSP_KT;
	}
	public String getMSP_AWS() {
		return MSP_AWS;
	}
	public void setMSP_AWS(String mSP_AWS) {
		MSP_AWS = mSP_AWS;
	}
	public String getMSP_RESALE() {
		return MSP_RESALE;
	}
	public void setMSP_RESALE(String mSP_RESALE) {
		MSP_RESALE = mSP_RESALE;
	}
	public String getIAAS_NHN() {
		return IAAS_NHN;
	}
	public void setIAAS_NHN(String iAAS_NHN) {
		IAAS_NHN = iAAS_NHN;
	}
	public String getIAAS_NAVER() {
		return IAAS_NAVER;
	}
	public void setIAAS_NAVER(String iAAS_NAVER) {
		IAAS_NAVER = iAAS_NAVER;
	}
	public String getIAAS_KT() {
		return IAAS_KT;
	}
	public void setIAAS_KT(String iAAS_KT) {
		IAAS_KT = iAAS_KT;
	}
	public String getIAAS_AWS() {
		return IAAS_AWS;
	}
	public void setIAAS_AWS(String iAAS_AWS) {
		IAAS_AWS = iAAS_AWS;
	}
	public String getSDR_NHN() {
		return SDR_NHN;
	}
	public void setSDR_NHN(String sDR_NHN) {
		SDR_NHN = sDR_NHN;
	}
	public String getSDR_NAVER() {
		return SDR_NAVER;
	}
	public void setSDR_NAVER(String sDR_NAVER) {
		SDR_NAVER = sDR_NAVER;
	}
	public String getSDR_KT() {
		return SDR_KT;
	}
	public void setSDR_KT(String sDR_KT) {
		SDR_KT = sDR_KT;
	}
	public String getMSPB_LITE() {
		return MSPB_LITE;
	}
	public void setMSPB_LITE(String mSPB_LITE) {
		MSPB_LITE = mSPB_LITE;
	}
	public String getMSPB_STANDARD() {
		return MSPB_STANDARD;
	}
	public void setMSPB_STANDARD(String mSPB_STANDARD) {
		MSPB_STANDARD = mSPB_STANDARD;
	}
	public String getMSPB_PREMIUM() {
		return MSPB_PREMIUM;
	}
	public void setMSPB_PREMIUM(String mSPB_PREMIUM) {
		MSPB_PREMIUM = mSPB_PREMIUM;
	}
	public String getCONTRACT_STDT() {
		return CONTRACT_STDT;
	}
	public void setCONTRACT_STDT(String cONTRACT_STDT) {
		CONTRACT_STDT = cONTRACT_STDT;
	}
	public String getCONTRACT_EDT() {
		return CONTRACT_EDT;
	}
	public void setCONTRACT_EDT(String cONTRACT_EDT) {
		CONTRACT_EDT = cONTRACT_EDT;
	}
	public String getBILL_DT() {
		return BILL_DT;
	}
	public void setBILL_DT(String bILL_DT) {
		BILL_DT = bILL_DT;
	}
	public String getBILL_RFDT() {
		return BILL_RFDT;
	}
	public void setBILL_RFDT(String bILL_RFDT) {
		BILL_RFDT = bILL_RFDT;
	}
	public String getTAX_DT() {
		return TAX_DT;
	}
	public void setTAX_DT(String tAX_DT) {
		TAX_DT = tAX_DT;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	@Override
	public String toString() {
		return "CompanyVo [COMPANY_ID=" + COMPANY_ID + ", BR_NUMBER=" + BR_NUMBER + ", COMPANY_NAME=" + COMPANY_NAME
				+ ", RP_NAME=" + RP_NAME + ", BR_ADDRESS=" + BR_ADDRESS + ", BILLER_PHONE=" + BILLER_PHONE
				+ ", BILLER_EMAIL=" + BILLER_EMAIL + ", RP_PHONE=" + RP_PHONE + ", MANAGER_ID=" + MANAGER_ID
				+ ", MANAGER_NAME=" + MANAGER_NAME + ", MANAGER_EMAIL=" + MANAGER_EMAIL + ", MANAGER_DEPT="
				+ MANAGER_DEPT + ", MANAGER_RANK=" + MANAGER_RANK + ", MANAGER_PHONE=" + MANAGER_PHONE
				+ ", COMPANY_ADDRESS=" + COMPANY_ADDRESS + ", MSP_NHN=" + MSP_NHN + ", MSP_NAVER=" + MSP_NAVER
				+ ", MSP_KT=" + MSP_KT + ", MSP_AWS=" + MSP_AWS + ", MSP_RESALE=" + MSP_RESALE + ", IAAS_NHN="
				+ IAAS_NHN + ", IAAS_NAVER=" + IAAS_NAVER + ", IAAS_KT=" + IAAS_KT + ", IAAS_AWS=" + IAAS_AWS
				+ ", SDR_NHN=" + SDR_NHN + ", SDR_NAVER=" + SDR_NAVER + ", SDR_KT=" + SDR_KT + ", MSPB_LITE="
				+ MSPB_LITE + ", MSPB_STANDARD=" + MSPB_STANDARD + ", MSPB_PREMIUM=" + MSPB_PREMIUM + ", CONTRACT_STDT="
				+ CONTRACT_STDT + ", CONTRACT_EDT=" + CONTRACT_EDT + ", BILL_DT=" + BILL_DT + ", BILL_RFDT=" + BILL_RFDT
				+ ", TAX_DT=" + TAX_DT + ", RES_SALES=" + RES_SALES + ", RES_MANAGER=" + RES_MANAGER + ", CONTRACT="
				+ CONTRACT + ", EVIDENCE=" + EVIDENCE + ", COM_DIV=" + COM_DIV + ", ENROLL_ID=" + ENROLL_ID
				+ ", ENROLL_NAME=" + ENROLL_NAME + ", SVC_ALL=" + SVC_ALL + ", SVC_NOW=" + SVC_NOW + ", SVC_END="
				+ SVC_END + ", SVC_YN=" + SVC_YN + ", searchType=" + searchType + ", searchValue=" + searchValue
				+ ", sDate=" + sDate + ", eDate=" + eDate + ", searchRadio1=" + searchRadio1 + ", searchRadio2="
				+ searchRadio2 + "]";
	}
	
	
}
