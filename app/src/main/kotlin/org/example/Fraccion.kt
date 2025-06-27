package org.example

class Fraccion(
    private var _numerador: Int,
    private var _denominador: Int
) {
    
    // Bloque init para validaciones y normalización
    init {
        if (_denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
        // Normalizar signos: el denominador siempre positivo
        if (_denominador < 0) {
            _numerador = -_numerador
            _denominador = -_denominador
        }
        val simplified = simplificar()
        _numerador = simplified._numerador
        _denominador = simplified._denominador
    }
    
    var numerador: Int
        get() = _numerador
        set(value) {
            _numerador = value
        }
    
    var denominador: Int
        get() = _denominador
        set(value) {
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            _denominador = value
        }
    
    override fun toString(): String {
        return "$_numerador/$_denominador"
    }
    
    fun mostrar() {
        println(this.toString())
    }
    
    // Método privado para calcular el MCD usando el algoritmo de Euclides
    private fun mcd(a: Int, b: Int): Int {
        val absA = kotlin.math.abs(a)
        val absB = kotlin.math.abs(b)
        return if (absB == 0) absA else mcd(absB, absA % absB)
    }
    
    // Método privado para simplificar la fracción
    private fun simplificar(): Fraccion {
        val divisorComun = mcd(_numerador, _denominador)
        return Fraccion(_numerador / divisorComun, _denominador / divisorComun)
    }
    
    operator fun plus(otra: Fraccion): Fraccion {
        // Validar que la otra fracción sea válida (denominador ≠ 0)
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        
        val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1).simplificar()
    }
    
    operator fun minus(otra: Fraccion): Fraccion {
        // Validar que la otra fracción sea válida (denominador ≠ 0)
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        
        val numerador1 = this._numerador * otra._denominador - this._denominador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1).simplificar()
    }
}