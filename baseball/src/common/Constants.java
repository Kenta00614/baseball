package common;

import java.util.HashMap;

public class Constants {
	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	 @SuppressWarnings({ "unchecked", "rawtypes" })
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String,Integer> SEAT_PRICE = new HashMap(){
      {
          put("0B", 4200);
	      put("0F", 3700);
	      put("0T", 3700);
	      put("0R", 700);
	      put("0L", 700);
    }
  };
  @SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String,String> DUEL_STATUS = new HashMap(){
    {
        put("1", "試合前");
	    put("2", "試合中");
	    put("3", "試合終了");
	    put("4", "試合中止");
    }
  };
  @SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String,String> DUEL_ROUND = new HashMap(){
	{
		put("1", "第一回戦");
	    put("2", "第二回戦");
	    put("3", "第三回戦");
	    put("4", "準々決勝");
	    put("5", "準決勝");
	    put("6", "決勝");
	}
  };
}
