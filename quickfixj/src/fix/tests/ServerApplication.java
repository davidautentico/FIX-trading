package fix.tests;

import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;

import com.sun.corba.se.pept.transport.Acceptor;

import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.FileStoreFactory;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.LogFactory;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RejectLogon;
import quickfix.ScreenLogFactory;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;
import quickfix.UnsupportedMessageType;

public class ServerApplication implements Application{

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogout(SessionID arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toAdmin(Message arg0, SessionID arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toApp(Message arg0, SessionID arg1) throws DoNotSend {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws ConfigError, FileNotFoundException, InterruptedException, SessionNotFound {
        SessionSettings settings = new SessionSettings("res/acceptor.config");

        Application application = new ServerApplication();
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory( true, true, true);
        MessageFactory messageFactory = new DefaultMessageFactory();

        SocketAcceptor initiator = new SocketAcceptor(application, messageStoreFactory, settings, logFactory, messageFactory);
        initiator.start();

        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }

}
