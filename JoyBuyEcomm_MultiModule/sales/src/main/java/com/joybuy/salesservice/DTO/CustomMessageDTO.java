package com.joybuy.salesservice.DTO;

import com.joybuy.salesservice.entities.Enums;
import lombok.Data;

@Data
public class CustomMessageDTO
{
    private Enums.RequestStatus requestStatus;
    private String              responseMessage;
    private Object              responseObject;
}
