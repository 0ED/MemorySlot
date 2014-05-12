import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class ChatServer
{
    private ServerSocket server;
	private List<ChatClientHandler> clients;
	
	public ChatServer()
	{
		this.server = null;
		this.clients = new ArrayList<ChatClientHandler>();
	}
	
    public void listen()
	{
        try
		{
            this.server = new ServerSocket(18080);
            System.out.println("Echoサーバをポート18080で起動しました．");
			while(true)
			{
				Socket aSocket = this.server.accept();
				System.out.println("クライアントが接続してきました．");
				ChatClientHandler aClient = new ChatClientHandler(aSocket);
				this.setClient(aClient);
				aClient.setServer(this);
				aClient.start();
			}
        }
		catch(IOException anException)
		{
            anException.printStackTrace();
        }
		
		return;
    }
	
	public void sendAll(ChatClientHandler postClient, String aString) throws IOException
	{
		for (ChatClientHandler aClient : this.clients)
		{
			if (aClient != postClient)
			{
				aClient.send(postClient.getUserName() + ": " + aString);
			}
		}
		
		return;
	}
	
	public void setClient(ChatClientHandler aClient)
	{
		this.clients.add(aClient);
		
		return;
	}
	
	
	public List<String> getUserNames()
	{
		List<String> userNames = new ArrayList<String>();
		for (ChatClientHandler aClients : this.clients)
		{
			if (aClients.getUserName() != null)
			{
				userNames.add(aClients.getUserName());
			}
		}
		Collections.sort(userNames);
		
		return userNames;
	}
	
	
    public static void main(String[] argumets)
	{
        ChatServer aServer = new ChatServer();
        aServer.listen();
		
		return;
    }
}
