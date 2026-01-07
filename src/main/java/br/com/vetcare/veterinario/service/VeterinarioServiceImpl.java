package br.com.vetcare.veterinario.service;


import br.com.vetcare.veterinario.dto.VeterinarioCreateDTO;
import br.com.vetcare.veterinario.dto.VeterinarioPatchDTO;
import br.com.vetcare.veterinario.dto.VeterinarioResponseDTO;
import br.com.vetcare.veterinario.dto.VeterinarioUpdateDTO;
import br.com.vetcare.veterinario.mapper.VeterinarioMapper;
import br.com.vetcare.veterinario.model.Veterinario;
import br.com.vetcare.veterinario.repository.VeterinarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }


    @Override
    public VeterinarioResponseDTO salvar(VeterinarioCreateDTO dto) {
        Veterinario veterinario = VeterinarioMapper.toEntity(dto);

        validarCrmvDuplicadoNoCadastro(veterinario.getCrmv());
        validarCpfDuplicadoNoCadastro(veterinario.getCpf());
        validarEmailDuplicadoNoCadastro(veterinario.getEmail());

        Veterinario salvo = veterinarioRepository.save(veterinario);
        return VeterinarioMapper.toResponseDTO(salvo);
    }


    @Override
    public VeterinarioResponseDTO buscarPorId(Long id) {
        return VeterinarioMapper.toResponseDTO(buscarEntidadePorId(id));
    }


    @Override
    public Page<VeterinarioResponseDTO> listar(Pageable pageable) {
        return veterinarioRepository.findAll(pageable).map(VeterinarioMapper::toResponseDTO);
    }


    @Override
    public VeterinarioResponseDTO atualizarParcial(Long id, VeterinarioPatchDTO dto) {
        Veterinario existente = buscarEntidadePorId(id);

        //verificar a validação duplicidade de crmv, cpf e email
        validarCrmvDuplicadoNaAtualizacao(existente, dto.crmv());
        validarCpfDuplicadoNaAtualizacao(existente, dto.cpf());
        validarEmailDuplicadoNaAtualizacao(existente, dto.email());

        VeterinarioMapper.applyPatch(dto, existente);
        Veterinario atualizado = veterinarioRepository.save(existente);
        return VeterinarioMapper.toResponseDTO(atualizado);
    }


    @Override
    public VeterinarioResponseDTO atualizar(Long id, VeterinarioUpdateDTO dto) {
        //busca tutor no banco, caso não exista lança exceção
        Veterinario existente = buscarEntidadePorId(id);

        //verificar a validação duplicidade de rg, cpf e email
        validarCrmvDuplicadoNaAtualizacao(existente, dto.crmv());
        validarCpfDuplicadoNaAtualizacao(existente, dto.cpf());
        validarEmailDuplicadoNaAtualizacao(existente, dto.email());

        //atualizada o animal existente
        VeterinarioMapper.applyUpdate(dto, existente);

        //salva a atualização no repositório
        Veterinario atualizado = veterinarioRepository.save(existente);
        return VeterinarioMapper.toResponseDTO(atualizado);
    }


    @Override
    public void remover(Long id) {
        if(!veterinarioRepository.existsById(id)){
            throw new IllegalArgumentException("Veterinário não encontrado. id=" + id);
        }
        veterinarioRepository.deleteById(id);
    }




    // -------------------------
    // Auxiliares / validações
    // -------------------------
    private void validarEmailDuplicadoNoCadastro(String email) {
        if (email != null && veterinarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Já existe um veterinario cadastrado com o CPF informado.");
        }
    }

    private void validarCpfDuplicadoNoCadastro(String cpf) {
        if (cpf != null && veterinarioRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("Já existe um veterinario cadastrado com o CPF informado.");
        }
    }

    private void validarCrmvDuplicadoNoCadastro(String crmv) {
        if (crmv != null && veterinarioRepository.existsByCrmv(crmv)) {
            throw new IllegalArgumentException("Já existe um veterinario cadastrado com o crmv informado.");
        }
    }


    private Veterinario buscarEntidadePorId(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veterinario não encontrado. id=" + id));
    }


    private void validarEmailDuplicadoNaAtualizacao(Veterinario existente, String emailNovo) {
        String emailAtual = existente.getEmail();
        // se não mudou, não valida
        if (emailNovo != null && emailNovo.equals(emailAtual)) {
            return;
        }
        // se mudou, verifica duplicidade
        if (emailNovo != null && veterinarioRepository.existsByEmail(emailNovo)) {
            throw new IllegalArgumentException("Já existe um veterinario cadastrado com o EMAIL informado.");
        }
    }


    private void validarCpfDuplicadoNaAtualizacao(Veterinario existente, String cpfNovo) {
        String cpfAtual = existente.getCpf();
        // se não mudou, não valida
        if (cpfNovo != null && cpfNovo.equals(cpfAtual)) {
            return;
        }
        // se mudou, verifica duplicidade
        if (cpfNovo != null && veterinarioRepository.existsByCpf(cpfNovo)) {
            throw new IllegalArgumentException("Já existe um veterinario cadastrado com o CPF informado.");
        }
    }


    private void validarCrmvDuplicadoNaAtualizacao(Veterinario existente, String crmvNovo) {
        String rgAtual = existente.getCrmv();
        // se não mudou, não valida
        if (crmvNovo != null && crmvNovo.equals(rgAtual)) {
            return;
        }
        // se mudou, verifica duplicidade
        if (crmvNovo != null && veterinarioRepository.existsByCrmv(crmvNovo)) {
            throw new IllegalArgumentException("Já existe um tutor cadastrado com o RG informado.");
        }
    }

}
