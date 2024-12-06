import java.sql.Timestamp;

public class RegistoSensor {
    private int idSensor;
    private Timestamp timeStamp;
    private String valor;

    // Construtor
    public RegistoSensor(int idSensor, Timestamp timeStamp, String valor) {
        this.idSensor = idSensor;
        this.timeStamp = timeStamp;
        this.valor = valor;
    }

    // Getters e Setters
    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}