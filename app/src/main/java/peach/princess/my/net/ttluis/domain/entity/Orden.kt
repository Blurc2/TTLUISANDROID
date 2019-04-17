package peach.princess.my.net.ttluis.domain.entity

data class Orden (
    var depto : Depto = Depto(),
    var descripcioneslist : ArrayList<String> = ArrayList(),
    var end : String = "",
    var equipo : String = "",
    var estado : Int = -1,
    var incidencia : String = "",
    var nofolio : Int = -1,
    var start : String = "",
    var subdepto : Depto? = null,
    var trabajo : String = ""

    )