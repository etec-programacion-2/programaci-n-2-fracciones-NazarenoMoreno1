package org.example
import java.util.Scanner
import java.util.InputMismatchException

class Calculadora {

    fun mostrarMenu() {
        println("=== CALCULADORA DE FRACCIONES ===")
        println("1. Sumar fracciones")
        println("2. Restar fracciones")
        println("3. Multiplicar fracciones")
        println("4. Dividir fracciones")
        println("5. Comparar fracciones")
        println("6. Convertir fracción a decimal")
        println("7. Crear fracción desde decimal")
        println("8. Ejemplos predefinidos")
        println("0. Salir")
        println("Ingrese su opción: ")
    }

    fun mostrarEjemplos() {
        println("\n=== EJEMPLOS PREDEFINIDOS ===")
        val f1 = Fraccion(1, 2) // 1/2
        val f2 = Fraccion(1, 3) // 1/3
        println("Fracción 1: $f1")
        println("Fracción 2: $f2")
        println("Suma: $f1 + $f2 = ${f1 + f2}")
        println("Resta: $f1 - $f2 = ${f1 - f2}")
        println("Multiplicación: $f1 * $f2 = ${f1 * f2}")
        println("División: $f1 / $f2 = ${f1 / f2}")
        println("¿$f1 > $f2? ${f1.esMayor(f2)}")
        println("$f1 en decimal: ${f1.aDecimal()}")
    }

    private fun leerFraccion(scanner: Scanner, mensaje: String): Fraccion? {
        var reintentar = true
        while (reintentar) {
            try {
                println(mensaje)
                print("Numerador: ")
                val numerador = scanner.nextInt()
                scanner.nextLine() // Limpiar buffer
                
                print("Denominador: ")
                val denominador = scanner.nextInt()
                scanner.nextLine() // Limpiar buffer
                
                return Fraccion(numerador, denominador)
                
            } catch (e: InputMismatchException) {
                println("Error: Por favor ingrese números enteros válidos.")
                scanner.nextLine() // Limpiar buffer del scanner
                print("¿Desea intentar de nuevo? (s/n): ")
                val respuesta = scanner.nextLine().lowercase()
                if (respuesta != "s" && respuesta != "si") {
                    reintentar = false
                }
            } catch (e: IllegalArgumentException) {
                println("Error: ${e.message}")
                print("¿Desea intentar de nuevo? (s/n): ")
                val respuesta = scanner.nextLine().lowercase()
                if (respuesta != "s" && respuesta != "si") {
                    reintentar = false
                }
            } catch (e: Exception) {
                println("Error inesperado: ${e.message}")
                print("¿Desea intentar de nuevo? (s/n): ")
                val respuesta = scanner.nextLine().lowercase()
                if (respuesta != "s" && respuesta != "si") {
                    reintentar = false
                }
            }
        }
        return null
    }

    fun realizarSuma(scanner: Scanner) {
        println("\n--- SUMAR FRACCIONES ---")
        val fraccion1 = leerFraccion(scanner, "Ingrese la primera fracción:")
        if (fraccion1 == null) {
            println("No se pudo leer la primera fracción.")
            return
        }
        
        val fraccion2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
        if (fraccion2 == null) {
            println("No se pudo leer la segunda fracción.")
            return
        }
        
        try {
            val resultado = fraccion1 + fraccion2
            println("\nResultado:")
            println("$fraccion1 + $fraccion2 = $resultado")
            println("En decimal: ${String.format("%.4f", resultado.aDecimal())}")
        } catch (e: Exception) {
            println("Error al realizar la suma: ${e.message}")
        }
    }

    fun realizarResta(scanner: Scanner) {
        println("\n--- RESTAR FRACCIONES ---")
        val fraccion1 = leerFraccion(scanner, "Ingrese la primera fracción (minuendo):")
        if (fraccion1 == null) {
            println("No se pudo leer la primera fracción.")
            return
        }
        
        val fraccion2 = leerFraccion(scanner, "Ingrese la segunda fracción (sustraendo):")
        if (fraccion2 == null) {
            println("No se pudo leer la segunda fracción.")
            return
        }
        
        try {
            val resultado = fraccion1 - fraccion2
            println("\nResultado:")
            println("$fraccion1 - $fraccion2 = $resultado")
            println("En decimal: ${String.format("%.4f", resultado.aDecimal())}")
        } catch (e: Exception) {
            println("Error al realizar la resta: ${e.message}")
        }
    }

    fun realizarMultiplicacion(scanner: Scanner) {
        println("\n--- MULTIPLICAR FRACCIONES ---")
        val fraccion1 = leerFraccion(scanner, "Ingrese la primera fracción:")
        if (fraccion1 == null) {
            println("No se pudo leer la primera fracción.")
            return
        }
        
        val fraccion2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
        if (fraccion2 == null) {
            println("No se pudo leer la segunda fracción.")
            return
        }
        
        try {
            val resultado = fraccion1 * fraccion2
            println("\nResultado:")
            println("$fraccion1 * $fraccion2 = $resultado")
            println("En decimal: ${String.format("%.4f", resultado.aDecimal())}")
        } catch (e: Exception) {
            println("Error al realizar la multiplicación: ${e.message}")
        }
    }

    fun realizarDivision(scanner: Scanner) {
        println("\n--- DIVIDIR FRACCIONES ---")
        val fraccion1 = leerFraccion(scanner, "Ingrese la primera fracción (dividendo):")
        if (fraccion1 == null) {
            println("No se pudo leer la primera fracción.")
            return
        }
        
        val fraccion2 = leerFraccion(scanner, "Ingrese la segunda fracción (divisor):")
        if (fraccion2 == null) {
            println("No se pudo leer la segunda fracción.")
            return
        }
        
        try {
            val resultado = fraccion1 / fraccion2
            println("\nResultado:")
            println("$fraccion1 / $fraccion2 = $resultado")
            println("En decimal: ${String.format("%.4f", resultado.aDecimal())}")
        } catch (e: Exception) {
            println("Error al realizar la división: ${e.message}")
        }
    }

    fun realizarComparacion(scanner: Scanner) {
        println("\n--- COMPARAR FRACCIONES ---")
        val fraccion1 = leerFraccion(scanner, "Ingrese la primera fracción:")
        if (fraccion1 == null) {
            println("No se pudo leer la primera fracción.")
            return
        }
        
        val fraccion2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
        if (fraccion2 == null) {
            println("No se pudo leer la segunda fracción.")
            return
        }
        
        try {
            println("\nFracciones a comparar:")
            println("Fracción 1: $fraccion1 = ${String.format("%.4f", fraccion1.aDecimal())}")
            println("Fracción 2: $fraccion2 = ${String.format("%.4f", fraccion2.aDecimal())}")
            
            println("\nResultados de comparación:")
            println("¿$fraccion1 es mayor que $fraccion2? ${fraccion1.esMayor(fraccion2)}")
            println("¿$fraccion1 es menor que $fraccion2? ${fraccion1.esMenor(fraccion2)}")
            println("¿Son iguales? ${fraccion1.equals(fraccion2)}")
            
            val comparacion = fraccion1.compareTo(fraccion2)
            when (comparacion) {
                -1 -> println("Conclusión: $fraccion1 < $fraccion2")
                0 -> println("Conclusión: $fraccion1 = $fraccion2")
                1 -> println("Conclusión: $fraccion1 > $fraccion2")
            }
        } catch (e: Exception) {
            println("Error al realizar la comparación: ${e.message}")
        }
    }

    fun convertirADecimal(scanner: Scanner) {
        println("\n--- CONVERTIR FRACCIÓN A DECIMAL ---")
        val fraccion = leerFraccion(scanner, "Ingrese la fracción a convertir:")
        
        if (fraccion != null) {
            try {
                val decimal = fraccion.aDecimal()
                println("\nResultado:")
                println("$fraccion = ${String.format("%.6f", decimal)}")
            } catch (e: Exception) {
                println("Error en la conversión: ${e.message}")
            }
        } else {
            println("No se pudo leer la fracción.")
        }
    }

    fun crearDesdeDecimal(scanner: Scanner) {
        println("\n--- CREAR FRACCIÓN DESDE DECIMAL ---")
        var reintentar = true
        
        while (reintentar) {
            try {
                print("Ingrese un número decimal: ")
                val decimal = scanner.nextDouble()
                scanner.nextLine() // Limpiar buffer
                
                val fraccion = Fraccion.desdeDecimal(decimal)
                println("\nResultado:")
                println("El decimal $decimal convertido a fracción es: $fraccion")
                println("Verificación: $fraccion = ${String.format("%.6f", fraccion.aDecimal())}")
                return
                
            } catch (e: InputMismatchException) {
                println("Error: Por favor ingrese un número decimal válido.")
                scanner.nextLine() // Limpiar buffer
                print("¿Desea intentar de nuevo? (s/n): ")
                val respuesta = scanner.nextLine().lowercase()
                if (respuesta != "s" && respuesta != "si") {
                    reintentar = false
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
                print("¿Desea intentar de nuevo? (s/n): ")
                val respuesta = scanner.nextLine().lowercase()
                if (respuesta != "s" && respuesta != "si") {
                    reintentar = false
                }
            }
        }
        println("Operación cancelada.")
    }
}