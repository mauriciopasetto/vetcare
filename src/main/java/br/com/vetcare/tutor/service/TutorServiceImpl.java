package br.com.vetcare.tutor.service;

import br.com.vetcare.tutor.dto.TutorCreateDTO;
import br.com.vetcare.tutor.dto.TutorPatchDTO;
import br.com.vetcare.tutor.dto.TutorResponseDTO;
import br.com.vetcare.tutor.dto.TutorUpdateDTO;
import br.com.vetcare.tutor.mapper.TutorMapper;
import br.com.vetcare.tutor.model.Tutor;
import br.com.vetcare.tutor.repository.TutorRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TutorServiceImpl implements TutorService {


    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    
    @Override
    public TutorResponseDTO salvar(TutorCreateDTO dto) {
        Tutor tutor = TutorMapper.toEntity(dto);

        validarRgDuplicadoNoCadastro(tutor.getRg());
        validarCpfDuplicadoNoCadastro(tutor.getCpf());
        validarEmailDuplicadoNoCadastro(tutor.getEmail());

        Tutor salvo = tutorRepository.save(tutor);
        return TutorMapper.toResponseDTO(salvo);
    }

    
    @Override
    public TutorResponseDTO buscarPorId(Long id) {
        return TutorMapper.toResponseDTO(buscarEntidadePorId(id));
    }

    
    @Override
    public Page<TutorResponseDTO> listar(Pageable pageable) {
        return tutorRepository.findAll(pageable).map(TutorMapper::toResponseDTO);
    }


    @Override
    public TutorResponseDTO atualizarParcial(Long id, TutorPatchDTO dto) {
        Tutor existente = buscarEntidadePorId(id);

        //verificar a validação duplicidade de rg, cpf e email
        validarRgDuplicadoNaAtualizacao(existente, dto.rg());
        validarCpfDuplicadoNaAtualizacao(existente, dto.cpf());
        validarEmailDuplicadoNaAtualizacao(existente, dto.email());
        
        TutorMapper.applyPatch(dto, existente);
        Tutor atualizado = tutorRepository.save(existente);
        return TutorMapper.toResponseDTO(atualizado);
    }


    @Override
    public TutorResponseDTO atualizar(Long id, TutorUpdateDTO dto) {
        //busca tutor no banco, caso não exista lança exceção
        Tutor existente = buscarEntidadePorId(id);

        //verificar a validação duplicidade de rg, cpf e email
        validarRgDuplicadoNaAtualizacao(existente, dto.rg());
        validarCpfDuplicadoNaAtualizacao(existente, dto.cpf());
        validarEmailDuplicadoNaAtualizacao(existente, dto.email());

        //atualizada o animal existente
        TutorMapper.applyUpdate(dto, existente);

        //salva a atualização no repositório
        Tutor atualizado = tutorRepository.save(existente);
        return TutorMapper.toResponseDTO(atualizado);
    }


    @Override
    public void remover(Long id) {
        if(!tutorRepository.existsById(id)){
            throw new IllegalArgumentException("Tutor não encontrado. id=" + id);
        }
        tutorRepository.deleteById(id);
    }


    // -------------------------
    // Auxiliares / validações
    // -------------------------
    private void validarEmailDuplicadoNoCadastro(String email) {
        if (email != null && tutorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Já existe um tutor cadastrado com o CPF informado.");
        }
    }

    private void validarCpfDuplicadoNoCadastro(String cpf) {
        if (cpf != null && tutorRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("Já existe um tutor cadastrado com o CPF informado.");
        }
    }

    private void validarRgDuplicadoNoCadastro(String rg) {
        if (rg != null && tutorRepository.existsByRg(rg)) {
            throw new IllegalArgumentException("Já existe um tutor cadastrado com o RG informado.");
        }
    }


    private Tutor buscarEntidadePorId(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tutor não encontrado. id=" + id));
    }


    private void validarEmailDuplicadoNaAtualizacao(Tutor existente, String emailNovo) {
        String emailAtual = existente.getEmail();
        // se não mudou, não valida
        if (emailNovo != null && emailNovo.equals(emailAtual)) {
            return;
        }
        // se mudou, verifica duplicidade
        if (emailNovo != null && tutorRepository.existsByEmail(emailNovo)) {
            throw new IllegalArgumentException("Já existe um tutor cadastrado com o EMAIL informado.");
        }
    }


    private void validarCpfDuplicadoNaAtualizacao(Tutor existente, String cpfNovo) {
        String cpfAtual = existente.getCpf();
        // se não mudou, não valida
        if (cpfNovo != null && cpfNovo.equals(cpfAtual)) {
            return;
        }
        // se mudou, verifica duplicidade
        if (cpfNovo != null && tutorRepository.existsByCpf(cpfNovo)) {
            throw new IllegalArgumentException("Já existe um tutor cadastrado com o CPF informado.");
        }
    }


    private void validarRgDuplicadoNaAtualizacao(Tutor existente, String rgNovo) {
        String rgAtual = existente.getRg();
        // se não mudou, não valida
        if (rgNovo != null && rgNovo.equals(rgAtual)) {
            return;
        }
        // se mudou, verifica duplicidade
        if (rgNovo != null && tutorRepository.existsByRg(rgNovo)) {
            throw new IllegalArgumentException("Já existe um tutor cadastrado com o RG informado.");
        }
    }


}
