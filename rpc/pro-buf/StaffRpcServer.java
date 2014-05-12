package jp.co.Acroquest.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StaffRpcServer {
	public static void main(String[] args) throws Exception {
		int port = Integer.parseInt(args[0]);
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket           socket = serverSocket.accept();
			DataInputStream  in     = new DataInputStream(socket.getInputStream());
			DataOutputStream out    = new DataOutputStream(socket.getOutputStream());
			
			// リクエストを処理(*1)
			int len = in.readInt();
			byte[] buf = new byte[len];
			in.read(buf, 0, len);
			
			// リクエストからデータを復元(*2)
			Staff.StaffData staff = Staff.StaffData.parseFrom(buf);
			System.out.println(staff.toString());
			
			// スタッフ名から収入を決めてしまいます
			int salary = 20000 * staff.getName().length();
			int bonus = 0;
			if (staff.getPositionCount() != 0) {
				bonus = 1000 * staff.getPosition(0).length();
			}
			
			// 戻り値を作成します(*3)
			Staff.IncomeData resp = Staff.IncomeData.newBuilder()
			.setSalary(salary)
			.setBonus(bonus)
			.build();
			byte[] data = resp.toByteArray();
			
			// レスポンスを送信
			out.writeInt(data.length);
			resp.writeTo(out);
			
			in.close();
			out.close();
		}
	}
}
