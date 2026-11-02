package budgeting;


import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = (".+"))
public class OpenAiChatModelIT {
    @Autowired
    OpenAiApi openAiApi;

    @Autowired
    OpenAiChatModel chatModel;

    @test
    void should_receive_when_chatModelIsCalled() {
        var chatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .build();
    }
}
