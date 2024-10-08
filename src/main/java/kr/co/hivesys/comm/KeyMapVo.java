package kr.co.hivesys.comm;

public class KeyMapVo {
	public String setNameKey;
	
	public String setValValue;

	public String getSetNameKey() {
		return setNameKey;
	}

	public void setSetNameKey(String setNameKey) {
		this.setNameKey = setNameKey;
	}

	public String getSetValValue() {
		return setValValue;
	}

	public void setSetValValue(String setValValue) {
		this.setValValue = setValValue;
	}

	@Override
	public String toString() {
		return "KeyMapVo [setNameKey=" + setNameKey + ", setValValue=" + setValValue + "]";
	}
	
	
}
