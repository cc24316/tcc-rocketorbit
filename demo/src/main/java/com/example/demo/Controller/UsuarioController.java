package com.example.RocketOrbitTCC.Controller;

import com.example.RocketOrbitTCC.Models.Usuario;
import com.example.RocketOrbitTCC.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/cadastrar")
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


        Usuario usuarioBanco = encontrado.get();


        if (!usuario.getSenha().equals(usuarioBanco.getSenha())) {

            return ResponseEntity
                    .status(401)
                    .body("Senha incorreta");
        }


        return ResponseEntity.ok("Login realizado com sucesso!");
    }



    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {

        return ResponseEntity.ok(
                usuarioRepository.findAll()
        );
    }
}