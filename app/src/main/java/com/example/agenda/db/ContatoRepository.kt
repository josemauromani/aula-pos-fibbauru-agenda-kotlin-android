package com.example.agenda.db
import Contato
import android.content.Context
import com.example.agenda.db.constantesDb.ConstantsDb.CONTATOS_DB_TABLE
import database
import org.jetbrains.anko.db.*


class ContatoRepository(val context: Context) {

    fun findAll() : ArrayList<Contato> = context.database.use {
        val contatos = ArrayList<Contato>()

        select(CONTATOS_DB_TABLE, "id", "email", "endereco", "nome", "telefone", "datanascimento", "site", "foto")
            .parseList(object: MapRowParser<List<Contato>> {
                override fun parseRow(columns: Map<String, Any?>): List<Contato> {
                    val id = columns.getValue("id")
                    val email = columns.getValue("email")
                    val endereco = columns.getValue("endereco")
                    val nome = columns.getValue("nome")
                    val telefone = columns.getValue("telefone")
                    val datanascimento = columns.getValue("datanascimento")
                    val site = columns.getValue("site")
                    val foto = columns.getValue("foto")

                    val contato = Contato(
                        id.toString()?.toLong(),
                        foto?.toString(),
                        nome?.toString(),
                        endereco?.toString(),
                        telefone?.toString(),
                        datanascimento?.toString(),
                        email?.toString(),
                        site?.toString())
                    contatos.add(contato)
                    return contatos
                }
            })

        contatos
    }

    fun create(contato: Contato) = context.database.use {
        insert(CONTATOS_DB_TABLE,
            "foto" to contato.foto,
            "nome" to contato.nome,
            "endereco" to contato.endereco,
            "telefone" to contato.telefone,
            "email" to contato.email,
            "site" to contato.site,
            "dataNascimento" to contato.dataNascimento)
    }

    fun update(contato: Contato) = context.database.use {
        val updateResult = update(CONTATOS_DB_TABLE,
            "foto" to contato.foto,
            "nome" to contato.nome,
            "endereco" to contato.endereco,
            "telefone" to contato.telefone,
            "email" to contato.email,
            "site" to contato.site)
            .whereArgs("id = {id}","id" to contato.id).exec()


    }

    fun delete(id: Long) = context.database.use {
        delete(CONTATOS_DB_TABLE, "id = {contatoId}", args = *arrayOf("contatoId" to id))
    }

}