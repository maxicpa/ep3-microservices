package cl.innovatech.msresources.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsService {

    private final SqsClient sqsClient;

    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    // REEMPLAZAREMOS ESTA URL EN EL SIGUIENTE PASO
    private final String queueUrl =
            "https://sqs.us-east-1.amazonaws.com/596287682820/innovatech-sqs";

    public void enviarMensaje(String mensaje) {

        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(mensaje)
                .build();

        sqsClient.sendMessage(request);

    }

}