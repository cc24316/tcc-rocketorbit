package com.example.demo.Controller;

import com.example.demo.Models.Usuario;
import com.example.demo.UsuarioRepository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario) {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {

            return ResponseEntity
                    .status(409)
                    .body("Email já cadastrado");
        }

        if (usuarioRepository.findByNome(usuario.getNome()).isPresent()) {

            return ResponseEntity
                    .status(409)
                    .body("Nome já cadastrado");
        }

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {

        Optional<Usuario> encontrado =
                usuarioRepository.findByNome(usuario.getNome());

        if (encontrado.isEmpty()) {

            return ResponseEntity
                    .status(404)
                    .body("Usuário não encontrado");
        }

        boolean senhaCorreta =
                usuario.getSenha()
                        .equals(encontrado.get().getSenha());

        if (!senhaCorreta) {

            return ResponseEntity
                    .status(401)
                    .body("Senha incorreta");
        }

        return ResponseEntity.ok("Login realizado com sucesso!");
    }
}