package pl.edu.wszib.order.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.edu.wszib.order.api.OrderDto;
import pl.edu.wszib.order.api.PositionDto;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
class DefaultOrderClient implements OrderClient {
    private final OrderClientConfig config;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @Override
    public Collection<OrderDto> getAll() {
        final Request request = new Request.Builder()
                .url(config.getUrl() + "/api/orders")
                .build();
        try (final Response response = okHttpClient.newCall(request).execute()) {
            final String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Optional<OrderDto> get(String orderId) {
        //FIXME impl
        return Optional.empty();
    }

    @Override
    public OrderDto removePosition(String orderId, Integer positionNumber) {
        //FIXME impl
        return null;
    }

    @Override
    public OrderDto addPosition(String orderId, PositionDto position) {
        //FIXME impl
        return null;
    }

    @Override
    public OrderDto submit(String orderId) {
        //FIXME impl
        return null;
    }

    @Override
    public OrderDto update(String orderId, OrderDto request) {
        //FIXME impl
        return null;
    }

    @Override
    public OrderDto create(OrderDto request) {
        //FIXME impl
        return null;
    }
}
