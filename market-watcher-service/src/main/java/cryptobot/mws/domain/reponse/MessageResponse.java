package cryptobot.mws.domain.reponse;

import static com.google.common.base.Preconditions.checkArgument;

public record MessageResponse(String message) {
    public MessageResponse {
        checkArgument(message != null && !message.isEmpty(), "Message cannot be null or empty");
    }
}
