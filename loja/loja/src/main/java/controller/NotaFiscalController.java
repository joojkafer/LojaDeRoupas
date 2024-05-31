package controller;

import model.entities.NotaFiscal;
import model.entities.Pedido;
import model.repositories.NotaFiscalRepository;
import model.repositories.PedidoRepository;

import java.util.Date;
import java.util.List;

public class NotaFiscalController {

    private NotaFiscalRepository notaFiscalRepository;
    private PedidoRepository pedidoRepository;

    public NotaFiscalController() {
        this.notaFiscalRepository = new NotaFiscalRepository();
        this.pedidoRepository = new PedidoRepository();
    }

    public NotaFiscal createNotaFiscal(int idPedido, double valorTotal, Date dataEmissao) {
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null) {
            throw new RuntimeException("Pedido nao encontrado");
        }

        NotaFiscal notaFiscal = new NotaFiscal(null, valorTotal, dataEmissao, pedido);
        return notaFiscalRepository.create(notaFiscal);
    }

    public NotaFiscal findNotaFiscalById(int id) {
        return notaFiscalRepository.findById(id);
    }

    public List<NotaFiscal> findAllNotaFiscal() {
        return notaFiscalRepository.findAll();
    }

    public void deleteNotaFiscal(int id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id);
        if (notaFiscal != null) {
            notaFiscalRepository.delete(id);
        } else {
            throw new RuntimeException("Nota Fiscal nao encontrada.");
        }
    }
}
