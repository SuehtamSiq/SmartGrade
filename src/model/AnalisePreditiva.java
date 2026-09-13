package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AnalisePreditiva {
    private int idAnalise;
    private int idRegistro;
    private BigDecimal scoreRisco;
    private String nivelRisco;
    private Timestamp dataAnalise;

    public AnalisePreditiva() {}

    public AnalisePreditiva(int idAnalise, int idRegistro, BigDecimal scoreRisco, String nivelRisco, Timestamp dataAnalise) {
        this.idAnalise = idAnalise;
        this.idRegistro = idRegistro;
        this.scoreRisco = scoreRisco;
        this.nivelRisco = nivelRisco;
        this.dataAnalise = dataAnalise;
    }

    public int getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(int idAnalise) {
        this.idAnalise = idAnalise;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public BigDecimal getScoreRisco() {
        return scoreRisco;
    }

    public void setScoreRisco(BigDecimal scoreRisco) {
        this.scoreRisco = scoreRisco;
    }

    public String getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(String nivelRisco) {
        this.nivelRisco = nivelRisco;
    }

    public Timestamp getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(Timestamp dataAnalise) {
        this.dataAnalise = dataAnalise;
    }
}