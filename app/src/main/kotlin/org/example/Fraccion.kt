package org.example

class Fraccion(
    private var _numerador: Int,
    private var _denominador: Int
) {
    // Bloque init para validaciones y normalización
    init {
        // Validar que el denominador no sea cero
        if (_denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
        // Normalizar signos: el denominador siempre positivo
        if (_denominador < 0) {
            _numerador = -_numerador
            _denominador = -_denominador
        }
        // Simplificar la fracción automáticamente
        val divisorComun = mcd(_numerador, _denominador)
        _numerador /= divisorComun
        _denominador /= divisorComun
    }

    // Propiedad numerador con validación
    var numerador: Int
        get() = _numerador
        set(value) {
            _numerador = value
        }

    // Propiedad denominador con validación
    var denominador: Int
        get() = _denominador
        set(value) {
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            _denominador = value
        }

    // Representación en cadena de la fracción
    override fun toString(): String {
        return "$_numerador/$_denominador"
    }

    // Mostrar la fracción en consola
    fun mostrar() {
        println(this.toString())
    }

    // Método privado para calcular el MCD usando el algoritmo de Euclides
    // Parámetros: a - primer número, b - segundo número
    // Retorna: máximo común divisor de a y b
    private fun mcd(a: Int, b: Int): Int {
        val absA = kotlin.math.abs(a)
        val absB = kotlin.math.abs(b)
        return if (absB == 0) absA else mcd(absB, absA % absB)
    }

    // Operador suma: (a/b) + (c/d) = (a*d + b*c)/(b*d)
    // Parámetro: otra - fracción a sumar
    // Retorna: nueva fracción con el resultado de la suma
    operator fun plus(otra: Fraccion): Fraccion {
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1)
    }

    // Operador resta: (a/b) - (c/d) = (a*d - b*c)/(b*d)
    // Parámetro: otra - fracción a restar
    // Retorna: nueva fracción con el resultado de la resta
    operator fun minus(otra: Fraccion): Fraccion {
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        val numerador1 = this._numerador * otra._denominador - this._denominador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1)
    }

    // Operador multiplicación: (a/b) * (c/d) = (a*c)/(b*d)
    // Parámetro: otra - fracción a multiplicar
    // Retorna: nueva fracción con el resultado de la multiplicación
    operator fun times(otra: Fraccion): Fraccion {
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        val numerador1 = this._numerador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1)
    }

    // Operador división: (a/b) / (c/d) = (a*d)/(b*c)
    // Parámetro: otra - fracción divisor
    // Retorna: nueva fracción con el resultado de la división
    operator fun div(otra: Fraccion): Fraccion {
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        if (otra._numerador == 0) {
            throw IllegalArgumentException("No se puede dividir por una fracción con numerador cero")
        }
        val numerador1 = this._numerador * otra._denominador
        val denominador1 = this._denominador * otra._numerador
        return Fraccion(numerador1, denominador1)
    }

    // ETAPA 4: Operador de comparación
    // Parámetro: otra - fracción a comparar
    // Retorna: -1 si es menor, 0 si es igual, 1 si es mayor
    operator fun compareTo(otra: Fraccion): Int {
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede comparar con una fracción de denominador cero")
        }
        val valorEsta = this._numerador.toDouble() / this._denominador.toDouble()
        val valorOtra = otra._numerador.toDouble() / otra._denominador.toDouble()
        return when {
            valorEsta < valorOtra -> -1
            valorEsta > valorOtra -> 1
            else -> 0
        }
    }

    // ETAPA 4: Operador de igualdad
    // Parámetro: other - objeto a comparar
    // Retorna: true si las fracciones son iguales, false en caso contrario
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraccion) return false
        return this._numerador == other._numerador && this._denominador == other._denominador
    }

    // ETAPA 4: Verificar si esta fracción es mayor que otra
    // Parámetro: otra - fracción a comparar
    // Retorna: true si esta fracción es mayor, false en caso contrario
    fun esMayor(otra: Fraccion): Boolean {
        return this.compareTo(otra) > 0
    }

    // ETAPA 4: Verificar si esta fracción es menor que otra
    // Parámetro: otra - fracción a comparar
    // Retorna: true si esta fracción es menor, false en caso contrario
    fun esMenor(otra: Fraccion): Boolean {
        return this.compareTo(otra) < 0
    }

    // ETAPA 4: Convertir fracción a decimal
    // Retorna: representación decimal de la fracción
    fun aDecimal(): Double {
        return _numerador.toDouble() / _denominador.toDouble()
    }

    // ETAPA 4: Companion object para métodos estáticos
    companion object {
        // Crear fracción desde un valor decimal
        // Parámetro: decimal - valor decimal a convertir
        // Retorna: fracción equivalente al decimal
        fun desdeDecimal(decimal: Double): Fraccion {
            if (decimal.isNaN() || decimal.isInfinite()) {
                throw IllegalArgumentException("El decimal no puede ser NaN o infinito")
            }
            
            // Determinar la precisión basada en los decimales
            val decimalStr = decimal.toString()
            val decimalPlaces = if (decimalStr.contains('.')) {
                decimalStr.substringAfter('.').length
            } else {
                0
            }
            
            // Convertir a fracción
            val denominador = Math.pow(10.0, decimalPlaces.toDouble()).toInt()
            val numerador = (decimal * denominador).toInt()
            
            return Fraccion(numerador, denominador)
        }
    }
}