/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.example.demo.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author lolo4
 */
@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepositorio repos;

    @GetMapping
    public String index(Model model, Usuario usuario){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarios", repos.findAll());

        return "index";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(Model modelo, Usuario usuario){
        repos.save(usuario);
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("usuarios", repos.findAll());

        return "index";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(Model modelo, @PathVariable(name="id") Long id){
        Usuario usuarioEdit = repos.findById(id).get();
        modelo.addAttribute("usuario", usuarioEdit);
        modelo.addAttribute("usuarios", repos.findAll());
        String type = "show";

        return "index";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(Model modelo, @PathVariable(name="id") Long id){
        Usuario usuarioElimina = repos.findById(id).get();
        repos.delete(usuarioElimina);
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("usuarios", repos.findAll());

        return "index";
    }
    
}
