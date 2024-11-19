public class AlarmeEmergencia {

    public SistemaControle sistemaControle;

    public AlarmeEmergencia(SistemaControle sistemaControle) {
        this.sistemaControle = sistemaControle;
    }

    public void dispararAlarme() {
        System.out.println("Alarme de emergência disparado!");
        sistemaControle.ativarProcedimentoEmergenciaGeral();
    }

}
