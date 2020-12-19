package camping.dto;

public class Camp {
	private String ADDR;             
	private int CAMP_ID;        
	private String ADDR_DO;           
	private String CAMP_NAME;         
	private String URL;             
	private String INDUTY;          
	private String LONGITUDE;         
	private String LATITUDE;         
	private String FACIL;            
	private String ADDR_SIGUNGU;     
	private String TEL;               
	private String INTRO;
	@Override
	public String toString() {
		return "ReservationDAOimpl [ADDR=" + ADDR + ", CAMP_ID=" + CAMP_ID + ", ADDR_DO=" + ADDR_DO + ", CAMP_NAME="
				+ CAMP_NAME + ", URL=" + URL + ", INDUTY=" + INDUTY + ", LONGITUDE=" + LONGITUDE + ", LATITUDE="
				+ LATITUDE + ", FACIL=" + FACIL + ", ADDR_SIGUNGU=" + ADDR_SIGUNGU + ", TEL=" + TEL + ", INTRO=" + INTRO
				+ "]";
	}
	public String getADDR() {
		return ADDR;
	}
	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}
	public int getCAMP_ID() {
		return CAMP_ID;
	}
	public void setCAMP_ID(int cAMP_ID) {
		CAMP_ID = cAMP_ID;
	}
	public String getADDR_DO() {
		return ADDR_DO;
	}
	public void setADDR_DO(String aDDR_DO) {
		ADDR_DO = aDDR_DO;
	}
	public String getCAMP_NAME() {
		return CAMP_NAME;
	}
	public void setCAMP_NAME(String cAMP_NAME) {
		CAMP_NAME = cAMP_NAME;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getINDUTY() {
		return INDUTY;
	}
	public void setINDUTY(String iNDUTY) {
		INDUTY = iNDUTY;
	}
	public String getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public String getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public String getFACIL() {
		return FACIL;
	}
	public void setFACIL(String fACIL) {
		FACIL = fACIL;
	}
	public String getADDR_SIGUNGU() {
		return ADDR_SIGUNGU;
	}
	public void setADDR_SIGUNGU(String aDDR_SIGUNGU) {
		ADDR_SIGUNGU = aDDR_SIGUNGU;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getINTRO() {
		return INTRO;
	}
	public void setINTRO(String iNTRO) {
		INTRO = iNTRO;
	}
}
