package com.example.end.model;

import com.example.end.entity.Procedure;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class AppointmentCart {

    private List<Procedure> selectedProcedures = new ArrayList<>();
    private String date;
    private String time;
    private BigDecimal totalCost;

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
        // Используем цикл для суммирования стоимости выбранных услуг
        totalCost = BigDecimal.ZERO;

        for (Procedure procedure : selectedProcedures) {
            totalCost = totalCost.add(BigDecimal.valueOf(procedure.getPrice()));
        }
    }

//    private void recalculateTotalCost() {
//        // Рассчитываем общую стоимость на основе выбранных услуг
//        totalCost = selectedProcedures.stream()
//                .map(Procedure::getPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }

}

