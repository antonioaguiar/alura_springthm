package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Oferta;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NovaOfertaRequest {

    private static final DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @NotNull
    private Long pedidoId;

    @NotNull
    @Pattern(regexp="^\\d+(\\.\\d+{2})?$")  //exempoo "1231231.99"
    private String valor;

    private String comentario;

    @NotNull
    @Pattern(regexp="^\\d{2}/d{2}/d{4}")
    private String dataEntrega;

    public NovaOfertaRequest() {
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Oferta toOferta() {
        Oferta oferta = new Oferta();
        oferta.setComentario(this.getComentario());
        oferta.setDataEntrega(LocalDate.parse(this.dataEntrega, formater));
        oferta.setValor(new BigDecimal(this.valor));

        return oferta;
    }


}
