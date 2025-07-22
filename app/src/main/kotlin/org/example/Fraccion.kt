package org.example

class Fraccion(
    private var _numerador: Int,
    private var _denominador: Int
) {
    
    init {
    if (_denominador == 0) {
        throw IllegalArgumentException("El denominador no puede ser cero")
    }

    if (_denominador < 0) {
        _numerador = -_numerador
        _denominador = -_denominador
    }

    val divisorComun = mcd(_numerador, _denominador)
    _numerador /= divisorComun
    _denominador /= divisorComun
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

    private fun mcd(a: Int, b: Int): Int {
        val absA = kotlin.math.abs(a)
        val absB = kotlin.math.abs(b)
        return if (absB == 0) absA else mcd(absB, absA % absB)
    }

    
    private fun simplificar(): Fraccion {
        val divisorComun = mcd(_numerador, _denominador)
        return Fraccion(_numerador / divisorComun, _denominador / divisorComun)
    }

    operator fun plus(otra: Fraccion): Fraccion {

        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1)
    }

    operator fun minus(otra: Fraccion): Fraccion {
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        val numerador1 = this._numerador * otra._denominador - this._denominador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1)
    }

    operator fun times(otra: Fraccion): Fraccion {
        if (otra._denominador == 0) {
            throw IllegalArgumentException("No se puede operar con una fracción de denominador cero")
        }
        val numerador1 = this._numerador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1)
    }

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
}