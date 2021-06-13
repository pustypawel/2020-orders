package pl.edu.wszib.order.client;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class OrderClientConfig {
    @NotBlank
    String url;
}
