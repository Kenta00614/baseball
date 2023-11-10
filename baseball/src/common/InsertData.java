package common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bean.Seat;
import bean.Tickets;
import dao.SeatDAO;
import dao.TicketsDAO;

public class InsertData {

	public void insertSeatData() {
		//変数宣言
		//SQLに投げる席情報のリスト
		List<Seat> list = new ArrayList<>();
		//席種　0F⇒1塁　0T⇒3塁　0B⇒バックネット裏　0R⇒外野ライト　0L⇒外野レフト
		String seatType = "0B";//実行するときに書き換える

		// CSVファイルの読み込み　Fileのパスを実行するときに書き換える
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:\\work\\center.csv"), Charset.forName("Shift-JIS")))) {
            String line;
            int index = 0;
            String blockStr = "";
            while ((line = reader.readLine()) != null) {
                if (index > 0) {
                    String[] data = line.split(",");
                    if (data.length > 2) {
                        // 読み込んだCSVファイルの内容で座席インスタンスを生成
                    	for(int i=Integer.parseInt(data[1]);i <= Integer.parseInt(data[2]);i++){
                           	Seat seat = new Seat();
                            seat.setType(seatType);
                            seat.setStep(data[0]);
                            seat.setNumber(i);
                            seat.setGate(Integer.parseInt(data[3]));
                            seat.setPassage(data[4]);
                            blockStr = seatType+data[5]+"-"+data[6];
                            seat.setBlock(blockStr);
                            seat.createSeatId();
                            //System.out.println(seat);
                            list.add(seat);
                    	}
                    }
                }
                index++;
            }
            //System.out.println(list);
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }
		//SQLに流す
        SeatDAO dao = new SeatDAO();
        dao.insert(list);
	}

	//20240318の日付で、各種のチケットのレコードを生成
	//あくまでテスト用のデータ作成メソッド
	public void insertTicketsData() {
		ArrayList<Tickets> ticketsList = new ArrayList<>();

		//daoから席のIDを取ってくる(35000件ぐらいｗ)
        SeatDAO seatDao = new SeatDAO();
		ArrayList<Seat> seatList = seatDao.getAllId();
		//System.out.println(seatList.size());

		//seatListの分だけ回す
		for(Seat seat:seatList){
			Tickets ticket = new Tickets();
			ticket.setSeatId(seat.getSeatId());
			ticket.createTicketsId();
			ticket.setMatchId(0);
			ticket.setPurchaseId(0);
			ticket.setShared(false);
			ticket.setStatus("3");
			//System.out.println(ticket);
			ticketsList.add(ticket);
		}

		//SQLに流す
		TicketsDAO dao = new TicketsDAO();
		dao.insert(ticketsList);

	}

}
