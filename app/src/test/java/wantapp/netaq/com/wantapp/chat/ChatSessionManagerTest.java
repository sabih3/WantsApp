package wantapp.netaq.com.wantapp.chat;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by sabih on 24-Sep-17.
 */
public class ChatSessionManagerTest {

    @Test
    public void Testlogin() throws Exception {

        ChatSessionManager sessionManager = Mockito.mock(ChatSessionManager.class);
        sessionManager.initializeChatSession();
    }

}