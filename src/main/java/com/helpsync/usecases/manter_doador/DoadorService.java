package com.helpsync.usecases.manter_doador;

import com.helpsync.entity.Doador;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DoadorService {

    private final DoadorRepository doadorRepository;
    private final PasswordEncoder passwordEncoder;

    public DoadorResponse criar(DoadorRequest request) {
        if (doadorRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Erro: Email já está em uso");
        }
        if (doadorRepository.existsByCpf(request.cpf())) {
            throw new RuntimeException("Erro: CPF já está em uso");
        }

        String senhaCriptografada = passwordEncoder.encode(request.senha());

        Doador doador = new Doador();
        doador.setNome(request.nome());
        doador.setCpf(request.cpf());
        doador.setEmail(request.email());
        doador.setSenha(senhaCriptografada);

        Doador salvo = doadorRepository.save(doador);
        return DoadorResponse.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public DoadorResponse buscarPorId(UUID id) {
        Doador doador = doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado com ID: " + id));
        return DoadorResponse.fromEntity(doador);
    }

    @Transactional(readOnly = true)
    public List<DoadorResponse> listarTodos() {
        return doadorRepository.findAll()
                .stream()
                .map(DoadorResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public DoadorResponse atualizar(UUID id, DoadorRequest request) {
        Doador doador = doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado com ID: " + id));

        if (!doador.getEmail().equals(request.email()) &&
            doadorRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Erro: Email já está em uso");
        }

        if (!doador.getCpf().equals(request.cpf()) &&
            doadorRepository.existsByCpf(request.cpf())) {
            throw new RuntimeException("Erro: CPF já está em uso");
        }

        doador.setNome(request.nome());
        doador.setEmail(request.email());
        doador.setCpf(request.cpf());

        if (request.senha() != null && !request.senha().isBlank()) {
            doador.setSenha(passwordEncoder.encode(request.senha()));
        }

        Doador atualizado = doadorRepository.save(doador);
        return DoadorResponse.fromEntity(atualizado);
    }

    public void deletar(UUID id) {
        if (!doadorRepository.existsById(id)) {
            throw new RuntimeException("Doador não encontrado com ID: " + id);
        }
        doadorRepository.deleteById(id);
    }
}
