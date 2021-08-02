package mcc53.client.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<E> {
    private Integer count;
    private String next;
    private String previous;
    private List<E> results;
}
