
//удалить потом!

//import ru.otus.homework15.messagesystem.MessageSystem;
//import ru.otus.homework15.messagesystem.MessageSystemImpl;
//import ru.otus.homework15.messagesystem.MsClient;
//import ru.otus.homework15.messagesystem.MsClientImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.function.Consumer;
//
//public class MSMain {
//  private static final Logger logger = LoggerFactory.getLogger(MSMain.class);
//
//  private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
//  private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";
//
//  public static void main(String[] args) throws InterruptedException {
//      MessageSystem messageSystem = new MessageSystemImpl();
//      MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
////    DBService dbService = new DBServiceImpl();
////      UserService userService = new UserServiceImpl() {
//
//      databaseMsClient.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbService));
//      messageSystem.addClient(databaseMsClient);
//
//
//    MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
//    FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
//    frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
//    messageSystem.addClient(frontendMsClient);
//
//    frontendService.getUserData(1, new Consumer<String>() {
//      @Override
//      public void accept(String data) {
//        logger.info("got data:{}", data);
//      }
//    });
//
//    Thread.sleep(100);
//    messageSystem.dispose();
//    logger.info("done");
//  }
//}
