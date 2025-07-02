package com.hechoconamor.hcaapi.common;

public enum MovementType {

    INGRESO,               // Ingreso manual, producción, compra
    SALIDA,                // Salida genérica
    VENTA,                 // Salida por venta
    PRODUCCION,            // Ingreso por producción
    DEVOLUCION,            // Entrada o salida según contexto
    AJUSTE_POSITIVO,       // Ajuste contable a favor
    AJUSTE_NEGATIVO        // Ajuste contable en contra

}