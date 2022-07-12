package q.bit.qcommerce.shared;

import q.bit.qcommerce.dto.Response;

import java.util.Date;

public class Utils {

    public static Response buildResponse(String message, int status, Object payload) {
        long timeStamp = new Date().getTime();
        return new Response(message, status, payload, timeStamp);
    }
}
