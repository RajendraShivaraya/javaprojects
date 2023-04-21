package kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@FieldNameConstants
public class APIResponseManager
{
    public ResponseModel buildAPIException(String errorMsg)
    {
        return new ResponseModel(JoybuyEnums.apiResponse.ERROR, errorMsg, null);
    }
    public ResponseModel buildAPIResponse(String responseMsg, Object responseObject)
    {
        return new ResponseModel(JoybuyEnums.apiResponse.SUCCESS, responseMsg, responseObject);
    }
}
