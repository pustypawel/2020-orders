package pl.edu.wszib.order.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.okhttp.LogbookInterceptor;
import pl.edu.wszib.order.api.OrderDto;
import pl.edu.wszib.order.api.PositionDto;

import java.util.Collection;
import java.util.Optional;

public interface OrderClient {

    static OrderClient create(final OrderClientConfig config) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Logbook logbook = Logbook.builder().build();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LogbookInterceptor(logbook))
                .build();
        return new DefaultOrderClient(config, okHttpClient, objectMapper);
    }

    Collection<OrderDto> getAll();

    Optional<OrderDto> get(String orderId);

    OrderDto removePosition(String orderId, Integer positionNumber);

    OrderDto addPosition(String orderId, PositionDto position);

    OrderDto submit(String orderId);

    OrderDto update(String orderId, OrderDto request);

    OrderDto create(OrderDto request);
}
