package ws2;

public class Otab extends Mobile {
	
	public Otab() {

	}

	public Otab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}

	public int operate(int time) { // 1분 사용 시 배터리 12 감소 → 잔여 배터리 return
		// get : 변수의 값을 반환할 때 set : 변수의 새로운 값을 지정 할 목적
		int result = this.getBatterySize();
		result -= time*12;
		this.setBatterySize(result);
		return result;
	}

	public int charge(int time) { // 1분 사용 시 배터리 8 증가 → 잔여 배터리 return
		int result = this.getBatterySize();
		result += time*8;
		this.setBatterySize(result);
		return result;
	}

}
