public class UsuarioDonoDeAndar extends UsuarioComum {
    public int andar;
    public int senha;

    public UsuarioDonoDeAndar(String nome, String email, BiometriaFacial biometriaFacial, int andar, int senha) {
        super(nome, email, biometriaFacial);
        this.andar = andar;
        this.senha = senha;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void validarSenha(int senha) {
        if (this.senha == senha) {
            System.out.println("Senha válida!");
        } else {
            System.out.println("Senha inválida!");
        }
    }
}
