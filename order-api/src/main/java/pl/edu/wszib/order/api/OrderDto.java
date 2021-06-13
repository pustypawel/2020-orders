package pl.edu.wszib.order.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.util.List;

@Value
public class OrderDto {
    @NotBlank
    String id;
    @NotEmpty
    List<@Valid PositionDto> positions;
    @NotNull
    OrderStatus status;

    @JsonCreator
    public OrderDto(@JsonProperty("id") String id,
                    @JsonProperty("positions") List<@Valid PositionDto> positions,
                    @JsonProperty("status") OrderStatus status) {
        this.id = id;
        this.positions = positions;
        this.status = status;
    }
}
