package br.com.caelum.eats.pagamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class PagamentoConfirmado {

  private Long pagamentoId;
  private Long pedidoId;

}