package fix.tests;

import java.io.FileNotFoundException;
import java.util.Date;

import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.FileStoreFactory;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RejectLogon;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.UnsupportedMessageType;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;
import quickfix.fix42.NewOrderSingle;

public class ClientApplication implements Application {
	
	private static volatile SessionID sessionID;

	@Override
	public void fromAdmin(Message arg0, SessionID arg1) throws FieldNotFound,
			IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromApp(Message arg0, SessionID arg1) throws FieldNotFound,
			IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreate(SessionID arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogon(SessionID arg0) {
		 System.out.println("OnLogon");
	        ClientApplication.sessionID = sessionID;
		
	}

	@Override
	public void onLogout(SessionID arg0) {
		 System.out.println("OnLogout");
	        ClientApplication.sessionID = null;
		
	}

	@Override
	public void toAdmin(Message arg0, SessionID arg1) {
		System.out.println("ToApp: " + arg0);
		
	}

	@Override
	public void toApp(Message arg0, SessionID arg1) throws DoNotSend {
		// TODO Auto-generated method stub
		
	}
	
	 public static void main(String[] args) throws ConfigError, FileNotFoundException, InterruptedException, SessionNotFound {
	        SessionSettings settings = new SessionSettings("res/initiator.config");

	        Application application = new ClientApplication();
	        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
	        LogFactory logFactory = new ScreenLogFactory( true, true, true);
	        MessageFactory messageFactory = new DefaultMessageFactory();

	        Initiator initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory, messageFactory);
	        initiator.start();

	        while (sessionID == null) {
	            Thread.sleep(1000);
	        }

	        final String orderId = "342";
	        NewOrderSingle newOrder = new NewOrderSingle(new ClOrdID(orderId), new HandlInst('1'), new Symbol("6758.T"),
	                new Side(Side.BUY), new TransactTime(new Date()), new OrdType(OrdType.MARKET));
	        Session.sendToTarget(newOrder, sessionID);
	        Thread.sleep(5000);
	    }

   
}
