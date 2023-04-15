/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Oscar
 */
public class ManejadorFechas {
    public static GregorianCalendar obtenerFechaActual() {
        LocalDateTime fechaHora = LocalDateTime.now();
        ZonedDateTime zonedDateTime = fechaHora.atZone(ZoneId.systemDefault());
        
        return GregorianCalendar.from(zonedDateTime);
    }
    
    public static GregorianCalendar sumarAnios(GregorianCalendar fecha, int anios) {
        fecha.add(Calendar.YEAR, (int) anios);
        
        return fecha;
    }
    
    public static boolean fechaActualEntre(Calendar fechaInicio, Calendar fechaFin) {
        Calendar fechaActual = Calendar.getInstance();
        return fechaActual.compareTo(fechaInicio) >= 0 && fechaActual.compareTo(fechaFin) <= 0;
    }
}
