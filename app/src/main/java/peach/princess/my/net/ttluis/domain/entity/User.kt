package peach.princess.my.net.ttluis.domain.entity

data class User (
    var am : String = "",
    var ap : String = "",
    var departamento : String = "",
    var email : String = "",
    var estado : Boolean = false,
    var idEmpleado : Int = -1,
    var nombre : String = "",
    var numero : Long = -1,
    var ordeneslist : ArrayList<Orden> = ArrayList(),
    var password : String = "",
    var subdepartamento : String = "",
    var tipo : String = "",
    var trabajos : String = ""
)