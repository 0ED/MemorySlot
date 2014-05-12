public class StaffRpcChannel implements RpcChannel {
	private String host_;
	private int port_;
	
	public StaffRpcChannel(String host, int port) {
		this.host_ = host;
		this.port_ = port;
	}
	
	public void callMethod(MethodDescriptor method, RpcController controller,
						   Message request, Message responsePrototype, RpcCallback done) {
		try {
			Socket           socket = new Socket(host_, port_);
			DataOutputStream out    = new DataOutputStream(socket.getOutputStream());
			DataInputStream  in     = new DataInputStream(socket.getInputStream());
			
			// 送信処理
			byte[] data = request.toByteArray();
			out.writeInt(data.length);
			out.write(data, 0, data.length);
			out.flush();
			
			// 受信処理
			int len = in.readInt();
			byte[] buf = new byte[len];
			in.read(buf, 0, len);
			
			// 戻り値の作成
			Staff.IncomeData response = Staff.IncomeData.parseFrom(buf);
			// 最後は必ずコールバックの呼び出しを行う。
			done.run(response);
			in.close();
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public RpcController newController() {
		return new RpcController() {
			public String errorText() {return null;}
			public boolean failed() {return false;}
			public boolean isCanceled() {return false;}
			public void    notifyOnCancel(RpcCallback <Object> callback) {}
			public void    reset() {}
			public void    setFailed(String reason) {}
			public void    startCancel() {}
		};
	}
}
