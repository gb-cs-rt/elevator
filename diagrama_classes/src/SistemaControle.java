import java.util.ArrayList;
import java.util.Date;

public class SistemaControle {

    public ArrayList<UsuarioComum> usuariosComums = new ArrayList<UsuarioComum>();
    public ArrayList<UsuarioAdmin> usuariosAdmins = new ArrayList<UsuarioAdmin>();
    public ArrayList<UsuarioMaster> usuariosMasters = new ArrayList<UsuarioMaster>();
    public ArrayList<AndarVIP> andaresVIP = new ArrayList<AndarVIP>();
    public ArrayList<UsuarioDonoDeAndar> usuariosDonosDeAndar = new ArrayList<UsuarioDonoDeAndar>();
    public ArrayList<Elevador> elevadores = new ArrayList<Elevador>();
    public SistemaChamadas sistemaChamadas = new SistemaChamadas(elevadores, andaresVIP);
    public int quantidadeElevadores;
    public int quantidadeAndares;
    public Date dataEHoraDoUltimoLogin;

    public UsuarioComum usuarioLogado;

    public SistemaControle(int quantidadeAndares) {
        quantidadeElevadores = 0;
        this.quantidadeAndares = quantidadeAndares;
    }

    public void iniciarLogDaOperacao() {
        ;
    }

    public void salvarLogDaOperacao() {
        ;
    }


    public void cadastrarNovoUsuario(String nome, String email) {
        iniciarLogDaOperacao();
        
        String foto = tirarFoto();
        BiometriaFacial biometriaFacial = new BiometriaFacial(foto);
        UsuarioComum usuario = new UsuarioComum(nome, email, biometriaFacial);
        usuariosComums.add(usuario);

        salvarLogDaOperacao();
    }

    public void exibirUltimoLogin() {
        ;
    }

    public void salvarDataEHoraDoUltimoLogin() {
        ;
    }

    public void login(String email, int senha) {

        iniciarLogDaOperacao();

        exibirUltimoLogin();
        salvarDataEHoraDoUltimoLogin();

        for (UsuarioAdmin usuario : usuariosAdmins) {
            if (usuario.getEmail().equals(email)) {
                usuario.validarSenha(senha);
                return;
            }
        }
        for (UsuarioMaster usuario : usuariosMasters) {
            if (usuario.getEmail().equals(email)) {
                usuario.validarSenha(senha);
                return;
            }
        }
        for (UsuarioDonoDeAndar usuario : usuariosDonosDeAndar) {
            if (usuario.getEmail().equals(email)) {
                usuario.validarSenha(senha);
                return;
            }
        }

        salvarLogDaOperacao();
        throw new IllegalArgumentException("Usuário não encontrado");

    }

    public void promoverUsuario(String tipoPromocao, UsuarioComum usuario, int senha, Integer... andar) {

        iniciarLogDaOperacao();

        switch (tipoPromocao.toLowerCase()) {
            case "admin":
                UsuarioAdmin usuarioAdmin = new UsuarioAdmin(usuario.getNome(), usuario.getEmail(), usuario.getBiometriaFacial(), senha);
                usuariosAdmins.add(usuarioAdmin);
                usuariosComums.remove(usuario);
                break;
            case "master":
                UsuarioAdmin usuarioAdminTemp = new UsuarioAdmin(usuario.getNome(), usuario.getEmail(), usuario.getBiometriaFacial(), senha);
                UsuarioMaster usuarioMaster = new UsuarioMaster(usuarioAdminTemp.getNome(), usuarioAdminTemp.getEmail(), usuarioAdminTemp.getBiometriaFacial(), usuarioAdminTemp.getSenha());
                usuariosMasters.add(usuarioMaster);
                usuariosAdmins.remove(usuarioAdminTemp);
                break;
            case "dono de andar":
                if (andar.length == 0) {
                    throw new IllegalArgumentException("Andar é necessário para promoção a dono de andar");
                }
                cadastrarAndarVIP(andar[0]);
                UsuarioDonoDeAndar usuarioDonoDeAndar = new UsuarioDonoDeAndar(usuario.getNome(), usuario.getEmail(), usuario.getBiometriaFacial(), andar[0], senha);
                usuariosDonosDeAndar.add(usuarioDonoDeAndar);
                usuariosComums.remove(usuario);
                break;

            }
        
        salvarLogDaOperacao();
    }

    public void cadastrarAndarVIP(int andar) {
        iniciarLogDaOperacao();

        AndarVIP andarVIP = new AndarVIP(andar);
        andaresVIP.add(andarVIP);

        salvarLogDaOperacao();
    }

    public void editarListaDeAcesso(int andar, UsuarioComum usuario, String tipoAcesso) {

        iniciarLogDaOperacao();

        for (AndarVIP andarVIP : andaresVIP) {
            if (andarVIP.getAndar() == andar) {

                andarVIP.validarDonoDeAndar((UsuarioDonoDeAndar) usuarioLogado);

                switch (tipoAcesso.toLowerCase()) {
                    case "adicionar":
                        andarVIP.adicionarUsuario(usuario);
                        break;
                    case "remover":
                        andarVIP.removerUsuario(usuario);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de acesso inválido: " + tipoAcesso);
                }
                return;
            }
        }
        
        salvarLogDaOperacao();
    }

    public void definirQuantidadeDeElevadores(int quantidade) {

        iniciarLogDaOperacao();

        quantidadeElevadores = quantidade;
        for (int i = 0; i < quantidade; i++) {
            Elevador elevador = new Elevador();
            elevadores.add(elevador);
        }

        salvarLogDaOperacao();
    }

    public void distribuirElevadoresParaAndares(int[][] distribuicao) {

        iniciarLogDaOperacao();

        for (int i = 0; i < distribuicao.length; i++) {
            int elevadorIndex = distribuicao[i][0];
            if (elevadorIndex < 0 || elevadorIndex >= elevadores.size()) {
                throw new IllegalArgumentException("Índice de elevador inválido: " + elevadorIndex);
            }
            Elevador elevador = elevadores.get(elevadorIndex);
            elevador.distribuicaoDeAndares = true; // Activate distribution
            elevador.andares.clear(); // Clear previous distribution
            for (int j = 1; j < distribuicao[i].length; j++) {
                int andar = distribuicao[i][j];
                if (andar < 0 || andar >= quantidadeAndares) {
                    throw new IllegalArgumentException("Índice de andar inválido: " + andar);
                }
                elevador.andares.add(andar);
            }
        }

        salvarLogDaOperacao();
    }

    public void limparDistribuicaoPorAndares() {

        iniciarLogDaOperacao();

        for (Elevador elevador : elevadores) {
            elevador.distribuicaoDeAndares = false;
            elevador.andares.clear();
        }

        salvarLogDaOperacao();
    }

    public void ativarProcedimentoEmergenciaGeral() {

        iniciarLogDaOperacao();

        for (Elevador elevador : elevadores) {
            int andarAtual = elevador.getAndarAtual();
            String sentidoAtual = elevador.getSentidoAtual();
            if (sentidoAtual == "subindo") {
                elevador.enviarParaAndar(andarAtual + 1);
            } else if (sentidoAtual == "descendo") {
                elevador.enviarParaAndar(andarAtual - 1);
            }

            elevador.abrirPorta();
            if (elevador.getBalanca().getPeso() == 0) {
                elevador.fecharPorta();
            }
        }

        sistemaChamadas.desativarChamadas();

        salvarLogDaOperacao();
    }

    public void ativarProcedimentoIncendio() {

        iniciarLogDaOperacao();

        for (Elevador elevador : elevadores) {
            elevador.enviarParaAndar(0);
        }

        for (Elevador elevador : elevadores) {
            elevador.abrirPorta();
        }

        sistemaChamadas.desativarChamadas();

        salvarLogDaOperacao();
    }

    public void ativarProcedimentoSobrecarga() {

        iniciarLogDaOperacao();

        for (Elevador elevador : elevadores) {
            if (elevador.verificarExcessoDePeso()) {
                elevador.exibirAvisoDeSobrecarga();
                elevador.pararElevador();
            }
        }

        salvarLogDaOperacao();
    }

    public String tirarFoto() {
        return "Foto tirada!";
    }

}

















