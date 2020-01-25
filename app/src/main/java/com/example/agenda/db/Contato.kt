import java.io.Serializable

data class Contato(
    var id: Long = 0,
    var foto: String? = null,
    var nome: String? = null,
    var endereco: String? = null,
    var telefone: String? = null,
    var dataNascimento: String? = null,
    var email: String? = null,
    var site: String? = null ) : Serializable {

    override fun toString(): String {
        return nome.toString()
    }
}
