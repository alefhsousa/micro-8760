package br.com.caelum.apigateway;

import java.util.Map;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.caelum.apigateway.AmqpApiGatewayConfig.AtualizacaoPedidoSink;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class StatusDoPedidoService {

  private SimpMessagingTemplate websocket;

  @StreamListener(AtualizacaoPedidoSink.PEDIDO_COM_STATUS_ATUALIZADO)
  void pedidoAtualizado(Map<String, Object> pedido) {
	websocket.convertAndSend("/pedidos/"+pedido.get("id")+"/status", pedido);

    if ("PAGO".equals(pedido.get("status"))) {
      Map<String, Object> restaurante = (Map<String, Object>) pedido.get("restaurante");
      websocket.convertAndSend("/parceiros/restaurantes/"+restaurante.get("id")+"/pedidos/pendentes", pedido);
    }
  }

}