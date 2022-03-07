function onLoad(){
    var app = new Vue({
      el: '#ofertas',
          data: {
            pedidos: [],
          },
          mounted() {
            axios
              .get('http://localhost:8080/api/pedidos/aguardando')
              .then(response => {
                        response.data.forEach(pedido => {
                                pedido.ofertaEnviada = false;
                                pedido.erros = {
                                    valor: "",
                                    dataEntrega: ""
                                }
                        });
                        this.pedidos = response.data;
              })
              .catch(error => {
                    console.log(error);
                    this.errored = true;
              });

          },
          methods:{
            enviarOferta: function(pedido){
                pedido.erros = {
                    valorNegociado: "",
                    dataEntrega: ""
                }
                this.erros = [];
                this.errored = false;
                axios
                         .post('http://localhost:8080/api/ofertas', {
                            pedidoId: pedido.id,
                            valor: pedido.valorNegociado,
                            comentario:pedido.comentario,
                            dataEntrega:pedido.dataEntrega,
                         })
                         .then(response => {
                                   //this.pedidos = response.data;
                                   console.log(response);
                                   pedido.ofertaEnviada = true;
                          })
                          .catch(error => {
                               console.log(error);
                               //this.errored = true;
                               error.response.data.errors.forEach(erro => {
                                    //this.erros.push({campo:erro.field, mensagem:erro.defaultMessage})
                                    pedido.erros[erro.field] = erro.defaultMessage;
                               });

                          });

            }
          }
    });
}