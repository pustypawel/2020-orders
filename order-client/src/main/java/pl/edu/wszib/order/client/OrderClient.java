package pl.edu.wszib.order.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import pl.edu.wszib.order.api.OrderDto;

import java.util.Collection;

public interface OrderClient {

    static OrderClient create(final OrderClientConfig config) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final OkHttpClient okHttpClient = new OkHttpClient();
        return new DefaultOrderClient(config, okHttpClient, objectMapper);
    }

    Collection<OrderDto> getAll();
}
