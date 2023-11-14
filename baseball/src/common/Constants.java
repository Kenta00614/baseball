package common;

import java.util.HashMap;

public class Constants {
	public static HashMap<Character,String> TIC_STA = new HashMap(){
	      {
	          put('1', "購入済み");
	          put('2', "販売中");
	          put('3', "販売停止");
	          put('4', "入場中");
	          put('5', "払い戻し済み");
	          put('6', "払い戻し可");
	          put('7', "退場済み");
	        }
	      };

	 public static HashMap<String,String> SEAT_TYPE = new HashMap(){
		      {
		          put("0B", "バックネット裏");
		          put("0F", "内野一塁側");
		          put("0T", "内野三塁側");
		          put("0R", "外野ライト");
		          put("0L", "外野レフト");
//		          put("FA", "一塁側アルプス");
//		          put("TA", "三塁側アルプス");
		        }
		      };

}
