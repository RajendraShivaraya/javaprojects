package kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class ResponseModel
{
    public JoybuyEnums.apiResponse responseCode;
    public String                  responseMSG;
    public Object                  responseBody;
}
