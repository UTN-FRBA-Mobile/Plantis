package ar.utn.frba.mobile.plantis

class Global private constructor() {
    var data: String? = null
    companion object {
        val instance = Global()
    }
}