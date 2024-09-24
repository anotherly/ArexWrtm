package kr.co.hivesys.comm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetUrlToImage {

	public void getImageUrl(String imageUrl) throws IOException {
        URL url = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            url = new URL(imageUrl);
            in = url.openStream();
            String dir = "C:/radarImage";
            File makeDir = new File(dir);
    		
    		if(!makeDir.exists()){ 			// 디렉터리가 없으면,
    			makeDir.mkdirs(); 			// 디렉터리 생성.
    		}
    		String fileName = imageUrl.split("/img/")[1];
    		String filePath = dir +"/"+fileName;
    		File makeFile = new File(filePath);
            
    		// 파일이 존재하지 않을 경우만 파일 생성
    		if(!makeFile.exists()) {
    			 // 컴퓨터 또는 서버의 저장할 경로(절대패스로 지정해 주세요.)
                out = new FileOutputStream(makeFile);

                while (true) {
                    // 루프를 돌면서 이미지데이터를 읽어들이게 됩니다.
                    int data = in.read();
                    // 데이터값이 -1이면 루프를 종료하고 나오게 됩니다.
                    if (data == -1) {
                        break;
                    }
                    // 읽어들인 이미지 데이터값을 컴퓨터 또는 서버공간에 저장하게 됩니다.
                    out.write(data);
                }
                // 저장이 끝난후 사용한 객체는 클로즈를 해줍니다.
                in.close();
                out.close();
			}
        } catch (Exception e) {
        	  // 예외처리
            e.printStackTrace();
        } finally {
            // 만일 에러가 발생해서 클로즈가 안됐을 가능성이 있기에
            // NULL값을 체크후 클로즈 처리를 합니다.
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
	
	public String getCurrentDateTime() {
		Date today = new Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyyMMddHHmm"; //hhmmss로 시간,분,초만 뽑기도 가능
		SimpleDateFormat formatter = new SimpleDateFormat(pattern,
				currentLocale);
		return formatter.format(today);
	}
    
}