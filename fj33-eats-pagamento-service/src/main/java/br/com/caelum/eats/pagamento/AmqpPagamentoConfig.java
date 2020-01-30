package br.com.caelum.eats.pagamento;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import br.com.caelum.eats.pagamento.AmqpPagamentoConfig.PagamentoSource;

@EnableBinding(PagamentoSource.class)
@Configuration
class AmqpPagamentoConfig {

  static interface PagamentoSource {

	@Output
    MessageChannel pagamentosConfirmados();

  }

}