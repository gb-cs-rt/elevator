public class UsuarioAdmin extends UsuarioComum {
    public int senha;

    public UsuarioAdmin(String nome, String email, BiometriaFacial biometriaFacial, int senha) {
        super(nome, email, biometriaFacial);
        this.senha = senha;
        this.admin = true;
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
