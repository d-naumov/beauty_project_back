package com.example.end.model;

import com.example.end.entity.Procedure;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AppointmentCart {

    private List<Procedure> selectedProcedures = new ArrayList<>();
    private String date;
    private String time;
    private BigDecimal totalCost;

    public List<Procedure> getSelectedProcedures() {
        return selectedProcedures;
    }

    public void setSelectedProcedures(List<Procedure> selectedProcedures) {
        this.selectedProcedures = selectedProcedures;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    // Методы для управления выбранными услугами
    public void addProcedure(Procedure procedure) {
        selectedProcedures.add(procedure);
        recalculateTotalCost();
    }

    public void removeProcedure(Procedure procedure) {
        selectedProcedures.remove(procedure);
        recalculateTotalCost();
    }

    private void recalculateTotalCost() {
        // Рассчитываем общую стоимость на основе выбранных услуг
        totalCost = selectedProcedures.stream()
                .map(Procedure::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

