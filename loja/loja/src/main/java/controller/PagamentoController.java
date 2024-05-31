package controller;

import model.entities.Pagamento;
import model.repositories.PagamentoRepository;

import java.util.Date;

public class PagamentoController {

    private PagamentoRepository pagamentoRepository = new PagamentoRepository();

    public Pagamento createPagamento(int fk_idPedido, int metodoPagamento, Date dataPagamento, boolean statusPagamento, double valorFinal) {
        Pagamento pagamento = new Pagamento();
        pagamento.setMetodoPagamento(metodoPagamento);
        pagamento.setDataPagamento(dataPagamento);
        pagamento.setStatusPagamento(statusPagamento);
        pagamento.setValorFinal(valorFinal);

        return pagamentoRepository.create(pagamento);
    }

    public Pagamento findPagamentoById(int id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento updatePagamento(int id, int metodoPagamento, boolean statusPagamento, double valorFinal, Date dataPagamento) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento != null) {
            pagamento.setMetodoPagamento(metodoPagamento);
            pagamento.setStatusPagamento(statusPagamento);
            pagamento.setValorFinal(valorFinal);
            pagamento.setDataPagamento(dataPagamento);

            return pagamentoRepository.updateById(pagamento);
        } else {
            System.out.println("Pagamento nao encontrado.");
            return null;
        }
    }

    public void deletePagamento(int id) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento != null) {
            pagamentoRepository.delete(id);
            System.out.println("\nPagamento deletado com sucesso!");
        } else {
            System.out.println("\nPagamento nao encontrado.");
        }
    }
}
