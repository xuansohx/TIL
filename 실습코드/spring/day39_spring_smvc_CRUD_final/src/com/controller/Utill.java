package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utill {
	public static void saveFile(MultipartFile mf) { // 파일을 넣어주면 특정 디렉토리에 저장
		String dir = "C:\\spring\\smvc4\\web\\img\\"; // Web에 image가 저장이 돼야 나중에 볼 수 있음
		byte[] data; // byte array 준비
		String imgname = mf.getOriginalFilename(); // mf만 넣어주면 저장할 수 있음
		try {
			data = mf.getBytes(); // 파일을 올리면 다 byte array로 옴..?
			FileOutputStream fo = new FileOutputStream(dir + imgname);
			fo.write(data);
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
