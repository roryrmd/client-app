package mcc53.client.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    private String status;
    private List<String> message;
    private List<T> data;
}
