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

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String home(Model model) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        //model.addAttribute("status", "todos");
        return "home";
    }

    @GetMapping("/{status}")
    public String status(@PathVariable("status") String status, Model model) {
        System.out.println(status);
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }
}
