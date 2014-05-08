package jp.co.Acroquest.network;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

public class StaffRpcClient 
{
	public static void main(String[] args) throws Exception 
	{
		String host = args[0];
		int    port = Integer.parseInt(args[1]);
		
		Staff.StaffData.Builder staffBuilder = Staff.StaffData.newBuilder();
		Staff.StaffData staff = staffBuilder.setEmail("foo@example.com")
		.setId(5)
		.setName("foo")
		.build();
		
		// RPCチャネルを作成 (*1)
		StaffRpcChannel    rpcChannel = new StaffRpcChannel(host, port);
		RpcController      controller = rpcChannel.newController();
		Staff.StaffService service    = Staff.StaffService.newStub(rpcChannel);
		
		// RPCの実行 (*2)
		service.getIncome(controller, staff, new RpcCallback() {
			public void run(Staff.IncomeData response) {
				System.out.println("salary : " + response.getSalary());
				System.out.println("bonous : " + response.getBonus());
			}
		});
	}
}

