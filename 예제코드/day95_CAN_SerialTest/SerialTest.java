package can;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialTest implements SerialPortEventListener {

	private BufferedInputStream bin;
	private InputStream in;
	// 들어올 때는 can과 serial로 들어오므로 buffered 이용 가능

	private OutputStream out;
	// 받은 쪽은 java가 아닐 수도 있어서 OutputStream 사용
	// buffered 인식 못 할 수도 있음

	private SerialPort serialPort;
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;

	public SerialTest() {
	}

	// SerialTest 시작할 때, portName 받아옴
	public SerialTest(String portName) throws NoSuchPortException {
		portIdentifier = CommPortIdentifier.getPortIdentifier(portName); // 살아있는지 검증
		System.out.println("Connect Com Port!");
		try {
			// 정상일 경우
			connectSerial();
			System.out.println("Connect OK !!");
		    (new Thread(new SerialWriter())).start(); // canvas(CAN-bus)에 참여하겠는 의미
		} catch (Exception e) {
			// 포트가 닫혀있거나, 사용하지 못 할 경우
			System.out.println("Connect Fail !!");
			e.printStackTrace();
		}
	}

	public void connectSerial() throws Exception {
		// 살아있는지 확인 후, 문제가 있는지 확인
		if (portIdentifier.isCurrentlyOwned()) { // 다른 쪽에서 사용하고 있는지 확인
			System.out.println("Error: Port is currently in use");
		} else {
			commPort = portIdentifier.open(this.getClass().getName(), 5000);
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.addEventListener(this);
				// addEventListener는 serial에서 event가 오는 순간 data를 가져옴
				// TCP/IP는 그냥 받는데, 이건 data가 있을 때만 받음??? data 오는 순간(?)동작
				// Android에서 버튼에 동작이 있을 때만 버튼의 실행되는 것과 같은 구조

				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(38400, // CAN 통신속도
						SerialPort.DATABITS_8, // 데이터 비트
						SerialPort.STOPBITS_1, // stop 비트
						SerialPort.PARITY_NONE); // 패리티 (데이터 검증)
				in = serialPort.getInputStream(); // in, bin, out → stream
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	private class SerialWriter implements Runnable {
		String data;

		public SerialWriter() {
			this.data = ":G11A9\r";
			// CAN-bus와 통신하겠다는 메시지
		}

		public SerialWriter(String serialData) {
			// W28 00000000 000000000000 (전송 할 데이터)
			// :W28 00000000 000000000000 53 \r (전송데이터 변환하기)
			String sdata = sendDataFormat(serialData);
			System.out.println(sdata);
			this.data = sdata;
		}

		// 전송데이터 변환하는 곳
		public String sendDataFormat(String serialData) {
			serialData = serialData.toUpperCase();
			serialData = serialData.substring(4,serialData.length()+1); 
			// 4번 째부터 끝까지 cut
			// checksum의 대상을 id와 data만! 그러면 checksum 동일하게 찍힘
			
			System.out.println("serialData : " + serialData);
			
			char c[] = serialData.toCharArray();
			int cdata = 0;
			for (char cc : c) {
				cdata += cc;
			}
			cdata = (cdata & 0xFF);

			// 데이터 변환
			String returnData = ":";
			returnData += serialData + Integer.toHexString(cdata).toUpperCase();
			returnData += "\r";
			return returnData;
		}

		public void run() {
			try {

				byte[] inputData = data.getBytes();
				// byte로 바꿔 데이터 전송
				// this.data가 run에 의하여 write
				out.write(inputData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// Thread
	public void sendData(String data) {
		SerialWriter sw = new SerialWriter(data);
		new Thread(sw).start();
	}
	
	public static void main(String[] args) {
		try {
			SerialTest st = new SerialTest("COM7"); // COM7 포트로 접속
			// 보내는 데이터
			st.sendData("W28000000010000000000000001"); 
			// 전송데이터
			// 앞에 '00000001'는 아이디 & '0000000000000001'는 데이터
		} catch (NoSuchPortException e) {
			e.printStackTrace();
		}
	}

	// 데이터 들어옴
	@Override
	public void serialEvent(SerialPortEvent event) {
		// 데이터 받는 역할
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[128]; // BufferedInputStream

			try {

				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer); // byte로 data를 읽음
				}

				// 받은 데이터
				String ss = new String(readBuffer);
				boolean result = checkSerialData(ss.trim());
				System.out.println("Result:" + result);
				// 데이터가 10 byte 씩 일정하게 들어오지 않으므로 check 필요
				System.out.println("Receive Low Data:" + ss + "||");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	// 데이터 체크
	public boolean checkSerialData(String data) {
		boolean check = false;
		// :U28 00000001 0000000000010000  46
		// 위의 형태로 데이터가 들어옴 (송신부 + 아이디 + 데이터) / Checksum은 '46'
		String checkData = data.substring(1, 28);
		String checkSum = data.substring(28, 30);
		System.out.println("checkData : " + checkData);
		System.out.println("checkSum : " + checkSum);

		char c[] = checkData.toCharArray();
		int cdata = 0;
		for (char cc : c) {
			cdata += cc;
		}
		cdata = (cdata & 0xFF);
		String serialCheckSum = Integer.toHexString(cdata).toUpperCase();
		if (serialCheckSum.trim().equals(checkSum)) {
			check = true;
		}
		return check;
	}
}
