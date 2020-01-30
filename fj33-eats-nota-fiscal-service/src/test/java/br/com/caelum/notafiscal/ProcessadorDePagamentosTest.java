package br.com.caelum.notafiscal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.caelum.notafiscal.pedido.PedidoDto;
import br.com.caelum.notafiscal.pedido.PedidoRestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "br.com.caelum:eats-pagamento-service", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ProcessadorDePagamentosTest {

  @Autowired
  private StubTrigger stubTrigger;

  @MockBean
  private GeradorDeNotaFiscal notaFiscal;

  @MockBean
  private PedidoRestClient pedidos;

  @SpyBean
  private ProcessadorDePagamentos processadorPagamentos;

  @Test
  public void deveProcessarPagamentoConfirmado() {

    PedidoDto pedidoDto = new PedidoDto();
    Mockito.when(pedidos.detalhaPorId(3L)).thenReturn(pedidoDto);
    Mockito.when(notaFiscal.geraNotaPara(pedidoDto)).thenReturn("<xml>...</xml>");

    stubTrigger.trigger("pagamento_confirmado");

    ArgumentCaptor<PagamentoConfirmado> pagamentoArg = ArgumentCaptor.forClass(PagamentoConfirmado.class);

    Mockito.verify(processadorPagamentos).processaPagamento(pagamentoArg.capture());

    PagamentoConfirmado pagamentoConfirmado = pagamentoArg.getValue();
    Assert.assertEquals(2L, pagamentoConfirmado.getPagamentoId().longValue());
    Assert.assertEquals(3L, pagamentoConfirmado.getPedidoId().longValue());
  }

}