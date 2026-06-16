package com.helpsync;

import com.helpsync.entity.Administrador;
import com.helpsync.entity.FundoMunicipal;
import com.helpsync.usecases.manter_administrador.AdministradorRepository;
import com.helpsync.usecases.manter_fundoMunicipal.FundoMunicipalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HelpsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpsyncApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(AdministradorRepository adminRepository, 
                                   FundoMunicipalRepository fundoRepository, 
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Garante que o administrador existe
            Administrador admin = adminRepository.findByEmail("admin@helpsync.com").orElseGet(() -> {
                Administrador novoAdmin = new Administrador();
                novoAdmin.setNome("Administrador Teste");
                novoAdmin.setEmail("admin@helpsync.com");
                novoAdmin.setSenha(passwordEncoder.encode("123456"));
                return adminRepository.save(novoAdmin);
            });
            System.out.println("✅ Administrador pronto.");

            // 2. Cria um Fundo Municipal automático se não existir nenhum
            if (fundoRepository.count() == 0) {
                FundoMunicipal fundo = new FundoMunicipal();
                fundo.setNome("Fundo Municipal de Assistência Social - Naviraí");
                fundo.setDescricao("Fundo principal para gestão das doações do município");
                fundo.setAdministrador(admin); // Associa o fundo ao admin
                fundoRepository.save(fundo);
                System.out.println("✅ Fundo Municipal de teste criado com sucesso!");
            }
        };
    }
}