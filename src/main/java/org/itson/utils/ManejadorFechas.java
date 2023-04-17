/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.utils;

import java.time.LocalDate;
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
    
    public static boolean estaEnPeriodo(Calendar fecha, Calendar fechaInicio, Calendar fechaFin) {
        return fecha.after(fechaInicio) && fecha.before(fechaFin);
    }
    
    public static boolean validarRangoFechas(Calendar desde, Calendar hasta) {
        if (desde == null || hasta == null) {
            return false;
        }
        
        return desde.compareTo(hasta) < 0;
    }
    
    public static Calendar convertirLocalDateACalendar(LocalDate fecha) {        
        if (fecha == null) {
            return null;
        }
        
        LocalDate localDate = fecha;
        LocalDateTime localDateTime = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(zonedDateTime);
        
        return gregorianCalendar;
    }
}
