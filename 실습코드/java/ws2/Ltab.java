package ws2;

public class Ltab extends Mobile {
	
	public Ltab() 
    {
        super();
    }
 
    public Ltab(String mobileName, int batterSize, String osType) 
    {
        super(mobileName, batterSize, osType);
    }
	
	public int operate(int time) { // 1분 사용 시 배터리 10 감소 → 잔여 배터리 return
		// get : 변수의 값을 반환할 때 set : 변수의 새로운 값을 지정 할 목적
		int result = this.getBatterySize();
		result -= time*10;
		this.setBatterySize(result);
		return result;
	}

	public int charge(int time) { // 1분 충전 시 베터리 10 증가 → 잔여 배터리 return
		int result = this.getBatterySize();
		result += time*10;
		this.setBatterySize(result);
		return result;
	}

}
