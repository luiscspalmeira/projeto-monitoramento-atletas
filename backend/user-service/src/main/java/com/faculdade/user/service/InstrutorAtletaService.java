package com.faculdade.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faculdade.user.dto.InstrutorAtletaRequest;
import com.faculdade.user.dto.UserResponse;
import com.faculdade.user.entity.InstrutorAtleta;
import com.faculdade.user.entity.User;
import com.faculdade.user.repository.InstrutorAtletaRepository;
import com.faculdade.user.repository.UserRepository;

@Service
public class InstrutorAtletaService {

        @Autowired
        private InstrutorAtletaRepository repository;

        public InstrutorAtleta vincular(
                        InstrutorAtletaRequest request) {

                InstrutorAtleta relacao = new InstrutorAtleta();

                relacao.setInstrutorId(
                                request.getInstrutorId());

                relacao.setAtletaId(
                                request.getAtletaId());

                return repository.save(relacao);

        }

        public List<InstrutorAtleta> listarPorInstrutor(
                        Long instrutorId) {

                return repository.findByInstrutorId(
                                instrutorId);

        }

        @Autowired
        private UserRepository userRepository;

        public List<UserResponse> listarAtletasDoInstrutor(Long instrutorId) {

                List<InstrutorAtleta> vinculos = repository.findByInstrutorId(instrutorId);

                List<Long> atletasIds = vinculos.stream()
                                .map(InstrutorAtleta::getAtletaId)
                                .toList();

                List<User> atletas = userRepository.findByIdIn(atletasIds);

                return atletas.stream()
                                .map(this::converterParaResponse)
                                .toList();

        }

        private UserResponse converterParaResponse(User user) {

                UserResponse response = new UserResponse();

                response.setId(user.getId());
                response.setNome(user.getNome());
                response.setEmail(user.getEmail());
                response.setPerfil(user.getTipoUsuario());
                response.setPcd(Boolean.TRUE.equals(user.getPcd()));

                return response;
        }
}