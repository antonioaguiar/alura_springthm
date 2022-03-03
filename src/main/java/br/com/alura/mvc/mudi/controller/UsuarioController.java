package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/pedido")
    public String home(Model model, Principal principal) {
        if (principal==null)
            return "redirect:/login";

        String username = principal.getName();
        List<Pedido> pedidos = pedidoRepository.findAllByUser(username);
        model.addAttribute("pedidos", pedidos);
        return "usuario/home";
    }

    @GetMapping("/pedido/{status}")
    public String status(@PathVariable("status") String status, Model model, Principal principal) {
        String username = principal.getName();
        List<Pedido> pedidos = pedidoRepository.findByStatusAndUsername(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)), username);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/usuario/home";
    }

}
